#include <iostream>
#include <memory>
#include <vector>
#include <stack>

template <typename T>
class Node {
    public:
        Node(T value);
        T getValue();
        void setParent(std::shared_ptr<Node<T>> node);
        void setLeft(T value, std::shared_ptr<Node<T>> parent);
        void setRight(T value, std::shared_ptr<Node<T>> parent);
        void setLeft(std::shared_ptr<Node<T>> node, std::shared_ptr<Node<T>> parent);
        void setRight(std::shared_ptr<Node<T>> node, std::shared_ptr<Node<T>> parent);
        std::shared_ptr<Node<T>> getParent();
        std::shared_ptr<Node<T>> getLeft();
        std::shared_ptr<Node<T>> getRight();
    private:
        T value;
        std::shared_ptr<Node<T>> parent;
        std::shared_ptr<Node<T>> left;
        std::shared_ptr<Node<T>> right;
};

template <typename T>
Node<T>::Node(T value) {
    this->value = value;
    this->left = nullptr;
    this->right = nullptr;
    this->parent = nullptr;
}

template <typename T>
T Node<T>::getValue() {
    return this->value;
}

template <typename T>
void Node<T>::setParent(std::shared_ptr<Node<T>> node) {
    this->parent = node;
}

template <typename T>
void Node<T>::setLeft(T value, std::shared_ptr<Node<T>> parent) {
    this->left = std::make_shared<Node<T>>(value);
    this->left->setParent(parent);
}

template <typename T>
void Node<T>::setLeft(std::shared_ptr<Node<T>> node, std::shared_ptr<Node<T>> parent) {
    this->left = node;
    this->left->setParent(parent);
}

template <typename T>
void Node<T>::setRight(T value, std::shared_ptr<Node<T>> parent) {
    this->right = std::make_shared<Node<T>>(value);
    this->right->setParent(parent);
}

template <typename T>
void Node<T>::setRight(std::shared_ptr<Node<T>> node, std::shared_ptr<Node<T>> parent) {
    this->right = node;
    this->right->setParent(parent);
}

template <typename T>
std::shared_ptr<Node<T>> Node<T>::getParent() {
    return this->parent;
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
        std::shared_ptr<Node<T>> get(T value);
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
                node->setRight(value, node);
                break;
            }
            node = node->getRight();
        } else {
            if (node->getLeft() == nullptr) {
                node->setLeft(value, node);
                break;
            }
            node = node->getLeft();
        }
    }
}

template <typename T>
std::shared_ptr<Node<T>> BST<T>::get(T value) {
    if (this->head == nullptr) return nullptr;

    std::shared_ptr<Node<T>> node = this->head;
    while(node != nullptr) {
        if (node->getValue() == value) return node;
        if (value > node->getValue()) node = node->getRight();
        else node = node->getLeft();
    }
    return nullptr;
}

template <typename T>
std::shared_ptr<Node<T>> BST<T>::remove(T value) {
    std::shared_ptr<Node<T>> nodeToRemove = this->get(value);
    if (nodeToRemove == nullptr) return nullptr;

    // TODO

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
        std::cout << "Node val: " << node.getValue() << ", parent: " << (node.getParent() != nullptr ? node.getParent()->getValue() : -1) << std::endl;
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
    
    if (bst->get(5) != nullptr) 
        std::cout << "Get number: " << bst->get(5)->getValue() << std::endl;
    else 
        std::cout << "Node not found!" << std::endl;

    bst->remove(5);
    /* Example tree:
           3
          / \
         2   6
        /   / 
       1   4   
    */

    bst->inOrder();

    bst->remove(3);

    /* Example tree:
           6
          / \
         2   4
        /    
       1    
    */

    bst->inOrder();
    return 0;
}