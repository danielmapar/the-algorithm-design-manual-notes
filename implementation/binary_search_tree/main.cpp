#include <iostream>
#include <memory>
#include <vector>
#include <stack>
#include <queue>
#include <string>

template <typename T>
class Node {
    public:
        Node(T value);
        Node();
        T getValue();
        void setLeft(T value);
        void setRight(T value);
        void setLeft(std::shared_ptr<Node<T>> node);
        void setRight(std::shared_ptr<Node<T>> node);
        std::shared_ptr<Node<T>> getLeft();
        std::shared_ptr<Node<T>> getRight();
    private:
        bool initialized;
        T value;
        std::shared_ptr<Node<T>> left;
        std::shared_ptr<Node<T>> right;
};

template <typename T>
Node<T>::Node() {
    this->left = nullptr;
    this->right = nullptr;
}

template <typename T>
Node<T>::Node(T value) {
    this->value = value;
    this->left = nullptr;
    this->right = nullptr;
}

template <typename T>
T Node<T>::getValue() {
    return this->value;
}

template <typename T>
void Node<T>::setLeft(T value) {
    this->left = std::make_shared<Node<T>>(value);
}

template <typename T>
void Node<T>::setLeft(std::shared_ptr<Node<T>> node) {
    this->left = node;
}

template <typename T>
void Node<T>::setRight(T value) {
    this->right = std::make_shared<Node<T>>(value);
}

template <typename T>
void Node<T>::setRight(std::shared_ptr<Node<T>> node) {
    this->right = node;
}

template <typename T>
std::shared_ptr<Node<T>> Node<T>::getLeft() {
    return this->left;
}

template <typename T>
std::shared_ptr<Node<T>> Node<T>::getRight() {
    return this->right;
}

template <typename T>
class BST {
    public:
        BST();
        void insert(T value);
        std::pair<std::shared_ptr<Node<T>>,std::shared_ptr<Node<T>>> get(T value);
        std::shared_ptr<Node<T>> remove(T value);
        std::vector<Node<T>> inOrder();
        void draw();
    private:
        std::shared_ptr<Node<T>> head;
};

template <typename T>
BST<T>::BST() {
    this->head = nullptr;
}

template <typename T>
void BST<T>::insert(T value) {
    if (this->head == nullptr) {
        this->head = std::make_shared<Node<T>>(value);
        return;
    }
    std::shared_ptr<Node<T>> node = this->head;
    while(node != nullptr) {
        if (value >= node->getValue()) {
            if (node->getRight() == nullptr) {
                node->setRight(value);
                break;
            }
            node = node->getRight();
        } else {
            if (node->getLeft() == nullptr) {
                node->setLeft(value);
                break;
            }
            node = node->getLeft();
        }
    }
}

template <typename T>
std::pair<std::shared_ptr<Node<T>>,std::shared_ptr<Node<T>>> BST<T>::get(T value) {
    if (this->head == nullptr) return std::pair<std::shared_ptr<Node<T>>,std::shared_ptr<Node<T>>>(nullptr, nullptr);

    std::shared_ptr<Node<T>> node = this->head;
    std::shared_ptr<Node<T>> parent = nullptr;
    while(node != nullptr) {
        if (node->getValue() == value) return std::pair<std::shared_ptr<Node<T>>,std::shared_ptr<Node<T>>>(parent, node);
        parent = node;
        if (value > node->getValue()) node = node->getRight();
        else node = node->getLeft();
    }
    return std::pair<std::shared_ptr<Node<T>>,std::shared_ptr<Node<T>>>(nullptr, nullptr);
}

template <typename T>
std::shared_ptr<Node<T>> BST<T>::remove(T value) {
    std::pair<std::shared_ptr<Node<T>>,std::shared_ptr<Node<T>>> nodePair = this->get(value);
    std::shared_ptr<Node<T>> nodeToRemove = nullptr;
    std::shared_ptr<Node<T>> parentOfNodeToRemove = nullptr;
    if (nodePair.second == nullptr) return nullptr;
    nodeToRemove = nodePair.second;
    if (nodePair.first != nullptr) parentOfNodeToRemove= nodePair.first;

    if (nodeToRemove->getLeft() != nullptr) {
        // Check right till it does not exist 
        std::shared_ptr<Node<T>> nodeToReplace = nodeToRemove->getLeft();
        while (nodeToReplace != nullptr) {
            if (nodeToReplace->getRight() != nullptr) nodeToReplace = nodeToReplace->getRight();
        } 

    } else if (nodeToRemove->getRight() != nullptr) {
        // Check left till it does not exist 
    } else {
        if (parentOfNodeToRemove->getLeft() == nodeToRemove) parentOfNodeToRemove->setLeft(nullptr);
        else parentOfNodeToRemove->setRight(nullptr);
    }

    return nodeToRemove;
}

template <typename T>
std::vector<Node<T>> BST<T>::inOrder() {
    std::cout << "InOrder traversal without recursion: " << std::endl;
    std::vector<Node<T>> output;
    if (this->head == nullptr) return output;

    std::stack<std::shared_ptr<Node<T>>> stack;
    std::shared_ptr<Node<T>> currentNode = this->head;

    while (currentNode != nullptr || !stack.empty()) {
        while (currentNode != nullptr) {
            stack.push(currentNode);
            currentNode = currentNode->getLeft();
        }
        currentNode = stack.top();
        stack.pop();

        output.push_back(*currentNode.get());

        currentNode = currentNode->getRight();
    }

    for (Node<T> node : output) 
        std::cout << "Node val: " << node.getValue() << std::endl;
    std::cout << "---------" << std::endl;
    
    return output;
}

template <typename T>
void BST<T>::draw() {
    int lineSize = 70;
    int emptySpaceSize = 1;
    bool lineIsEmpty = true;
    std::queue<std::shared_ptr<Node<T>>> queue;
    std::queue<std::shared_ptr<Node<T>>> nextLevelQueue;
    queue.emplace(this->head);

    lineSize /= 2;
    std::cout << std::string(lineSize, ' ') << this->head->getValue() << std::endl;

    lineSize /= 2;
    while(!queue.empty()) {
        std::shared_ptr<Node<T>> element = queue.front();
        queue.pop();
        
        std::cout << std::string(lineSize, ' ');
        if (element->getLeft() != nullptr) {
            lineIsEmpty = false;
            nextLevelQueue.emplace(element->getLeft());
            std::cout << element->getLeft()->getValue();
        } else {
            nextLevelQueue.emplace(std::make_shared<Node<T>>());
            std::cout << std::string(emptySpaceSize, ' ');
        }
        std::cout << std::string(lineSize, ' ');

        std::cout << std::string(lineSize, ' ');
        if (element->getRight() != nullptr) {
            lineIsEmpty = false;
            nextLevelQueue.emplace(element->getRight());
            std::cout << element->getRight()->getValue();
        } else {
            nextLevelQueue.emplace(std::make_shared<Node<T>>());
            std::cout << std::string(emptySpaceSize, ' ');
        }
        std::cout << std::string(lineSize, ' ');

        if (queue.empty()) {
            if (lineIsEmpty) return;
            lineIsEmpty = true;
            std::cout << std::endl;
            lineSize /= 2;
            queue = nextLevelQueue;
            nextLevelQueue = std::queue<std::shared_ptr<Node<T>>>();
        }
    }
}


int main (){
    std::unique_ptr<BST<int>> bst = std::make_unique<BST<int>>();
    bst->insert(3);
    bst->insert(2);
    bst->insert(1);
    bst->insert(5);
    bst->insert(4);
    bst->insert(6);
    bst->insert(2);
    bst->insert(7);
    bst->insert(0);
    bst->insert(8);
    bst->insert(9);
    bst->insert(5);

    /* Example tree:
            3
          /    \
         2      5
        / \    / \
       1   2  4   6
      /          /  \
     0          5    7
                      \
                       8
                        \
                         9
        
    */

    bst->inOrder();
    
    if (bst->get(5).second != nullptr) 
        std::cout << "Get node: " << bst->get(5).second->getValue() << ", with parent: " << bst->get(5).first->getValue() << std::endl;
    else 
        std::cout << "Node not found!" << std::endl;

    //bst->remove(6);
    bst->inOrder();

    bst->draw();
    return 0;
}