#include <iostream>
#include <memory>
#include <vector>
#include <stack>

template <typename T>
class Node {
    public:
        Node(T value);
        T getValue();
        void setLeft(T value);
        void setRight(T value);
        void setLeft(std::shared_ptr<Node<T>> node);
        void setRight(std::shared_ptr<Node<T>> node);
        std::shared_ptr<Node<T>> getLeft();
        std::shared_ptr<Node<T>> getRight();
    private:
        T value;
        std::shared_ptr<Node<T>> left;
        std::shared_ptr<Node<T>> right;
};

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


int main (){
    std::unique_ptr<BST<int>> bst = std::make_unique<BST<int>>();
    bst->insert(3);
    bst->insert(2);
    bst->insert(1);
    bst->insert(5);
    bst->insert(4);
    bst->insert(6);

    /* Example tree:
           3
          / \
         2   5
        /   / \
       1   4   6
    */

    bst->inOrder();
    
    if (bst->get(5).second != nullptr) 
        std::cout << "Get node: " << bst->get(5).second->getValue() << ", with parent: " << bst->get(5).first->getValue() << std::endl;
    else 
        std::cout << "Node not found!" << std::endl;

    bst->remove(6);
    /* 
    Example tree:
           3
          / \
         2   6
        /   / 
       1   4   

    Example tree:
           2
          / 
         1   

    Example tree:
           3
            \
             6

    Example tree:
           3

    Example tree:
           6
          /  \
         2    7
        / \    
       1   3   
            \
             5
            /
           4

    Example tree:
           1
          / \
         0   8
            / 
           4 
          / \
         3   7
            / 
           5   

    Example tree:
           7
          / 
         4
        / \  
       1   6
          / 
         5
          
          
    */

    // bst->inOrder();

    // bst->remove(3);

    /* Example tree:
           2
          / \
         1   6
            / 
           4   
    */

    bst->inOrder();
    return 0;
}