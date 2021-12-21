#include <iostream>
#include <memory>

template <class T> class Node {
public:
  Node(T value);
  ~Node();
  void setNextNode(std::shared_ptr<Node<T>> node);
  std::shared_ptr<Node<T>> getNextNode();
  T getValue();

private:
  T value;
  std::shared_ptr<Node<T>> nextNode;
};

template <class T> Node<T>::Node(T value) {
  this->value = value;
  this->nextNode = NULL;
}

template <class T> Node<T>::~Node() { 
    // std::cout << "Destructor for node value: " << this->getValue() << std::endl;
}

template <class T> void Node<T>::setNextNode(std::shared_ptr<Node<T>> node) {
  this->nextNode = node;
}

template <class T> T Node<T>::getValue() { return this->value; }

template <class T> std::shared_ptr<Node<T>> Node<T>::getNextNode() {
  return this->nextNode;
}

template <class T> class SingleLinkedList {
private:
  std::shared_ptr<Node<T>> head;
  std::shared_ptr<Node<T>> tail;

public:
  SingleLinkedList();
  std::shared_ptr<Node<T>> getHead();
  std::shared_ptr<Node<T>> getTail();
  std::shared_ptr<Node<T>> add(T value);
  std::shared_ptr<Node<T>> add(std::shared_ptr<Node<T> > node);
  void remove(T value);
  void remove(std::shared_ptr<Node<T> > node);
  std::shared_ptr<Node<T>> find(T value);
  std::shared_ptr<Node<T>> find(std::shared_ptr<Node<T> > node);
  void print();
};

template <class T> SingleLinkedList<T>::SingleLinkedList() {
  this->head = NULL;
  this->tail = NULL;
}

template <class T> std::shared_ptr<Node<T>> SingleLinkedList<T>::getHead() {
  return this->head;
}

template <class T> std::shared_ptr<Node<T>> SingleLinkedList<T>::getTail() {
  return this->tail;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T> std::shared_ptr<Node<T>> SingleLinkedList<T>::add(T value) {
  std::shared_ptr<Node<T>> node = std::make_shared<Node<T>>(value);
  return this->add(node);
}

// O(1) runtime complexity
// O(1) space complexity
template <class T> std::shared_ptr<Node<T>> SingleLinkedList<T>::add(std::shared_ptr<Node<T> > node) {
  if (this->head == NULL) {
    this->head = node;
    this->tail = this->head;
    return this->head;
  }
  this->tail->setNextNode(node);
  this->tail = node;
  this->tail->setNextNode(NULL);

  return node;
}

// O(n) runtime complexity
// O(1) space complexity
template <class T> 
void SingleLinkedList<T>::remove(T value) {
  std::shared_ptr<Node<T> > node = this->head;
  std::shared_ptr<Node<T> > prevNode = NULL;
  while (node != NULL) {
    if(node->getValue() == value) {
        if (node.get() == this->head.get()) {
            this->head = node->getNextNode();
            node = this->head;
            if (node == NULL) this->tail = NULL;
            continue;
        } else if (node.get() == this->tail.get()) {
            this->tail = prevNode;
            node = NULL;
            continue;
        } else {
            if (prevNode == NULL) prevNode = this->head;
            prevNode->setNextNode(node->getNextNode());
        }
    }
    prevNode = node;
    node = node->getNextNode();
  }
}

// O(n) runtime complexity
// O(1) space complexity
template <class T> 
void SingleLinkedList<T>::remove(std::shared_ptr<Node<T> > nodeToRemove) {
  std::shared_ptr<Node<T> > node = this->head;
  std::shared_ptr<Node<T> > prevNode = NULL;
  while (node != NULL) {
    if(node.get() == nodeToRemove.get()) {
        if (node.get() == this->head.get()) {
            this->head = node->getNextNode();
            node = this->head;
            if (node == NULL) this->tail = NULL;
        } else if (node.get() == this->tail.get()) {
            this->tail = prevNode;
            node = NULL;
        } else {
            if (prevNode == NULL) prevNode = this->head;
            prevNode->setNextNode(node->getNextNode());
        }
        break;
    }
    prevNode = node;
    node = node->getNextNode();
  }
}

// O(n) time complexity
// O(1) space complexity
template <class T>
std::shared_ptr<Node<T>> SingleLinkedList<T>::find(T value) {
  std::shared_ptr<Node<T> > node = this->head;
  while(node != NULL) {
    if (node->getValue() == value) return node;
    node = node->getNextNode();
  }
  return NULL;
}

// O(n) time complexity
// O(1) space complexity
template <class T>
std::shared_ptr<Node<T>> SingleLinkedList<T>::find(std::shared_ptr<Node<T>> searchNode) {
  std::shared_ptr<Node<T> > node = this->head;
  while(node != NULL) {
    if (node->getValue() == searchNode->getValue()) return node;
    node = node->getNextNode();
  }
  return NULL;
}

// O(n) time complexity
// O(1) space complexity
template <class T> void SingleLinkedList<T>::print() {
  std::cout << "Printing list:" << std::endl;  
  std::cout << "-------" << std::endl;
  std::shared_ptr<Node <T> > node = this->head;
  while (node != NULL) {
    std::cout << node->getValue() << std::endl;
    node = node->getNextNode();
  }
  std::cout << "-------" << std::endl;
}

int main() {

  {
    std::unique_ptr<SingleLinkedList<int>> list =
        std::make_unique<SingleLinkedList<int>>();
    list->add(1);
    list->add(1);
    std::shared_ptr<Node<int> > nodeToRemove = list->add(2);
    list->add(3);
    list->add(4);
    list->add(std::make_shared<Node<int> >(5));
    list->remove(1);
    list->remove(nodeToRemove);
    nodeToRemove = NULL;
    list->print();

    std::shared_ptr<Node<int> > foundNode = list->find(1);
    if (foundNode != NULL) std::cout << foundNode->getValue() << std::endl;
  }
  return 0;
}