#include <iostream>
#include <memory>

template <class T> class Node {
public:
  Node(T value);
  ~Node();
  void setNextNode(std::shared_ptr<Node<T>> node);
  void setPreviousNode(std::shared_ptr<Node<T>> node);
  std::shared_ptr<Node<T>> getNextNode();
  std::shared_ptr<Node<T>> getPreviousNode();
  T getValue();

private:
  T value;
  std::shared_ptr<Node<T>> nextNode;
  std::shared_ptr<Node<T>> previousNode;
};

template <class T> Node<T>::Node(T value) {
  this->value = value;
  this->nextNode = NULL;
  this->previousNode = NULL;
}

template <class T> Node<T>::~Node() { 
  std::cout << "Destructor for node value: " << this->getValue() << std::endl;
}

template <class T> void Node<T>::setNextNode(std::shared_ptr<Node<T>> node) {
  this->nextNode = node;
}

template <class T> void Node<T>::setPreviousNode(std::shared_ptr<Node<T>> node) {
  this->previousNode = node;
}

template <class T> T Node<T>::getValue() { return this->value; }

template <class T> std::shared_ptr<Node<T>> Node<T>::getNextNode() {
  return this->nextNode;
}

template <class T> std::shared_ptr<Node<T>> Node<T>::getPreviousNode() {
  return this->previousNode;
}

template <class T> class DoubleLinkedList {
private:
  std::shared_ptr<Node<T>> head;
  std::shared_ptr<Node<T>> tail;

public:
  DoubleLinkedList();
  std::shared_ptr<Node<T>> getHead();
  std::shared_ptr<Node<T>> getTail();
  std::shared_ptr<Node<T>> add(T value);
  std::shared_ptr<Node<T>> add(std::shared_ptr<Node<T> > node);
  void remove(T value);
  void remove(std::shared_ptr<Node<T> > node);
  std::shared_ptr<Node<T>> find(T value);
  std::shared_ptr<Node<T>> find(std::shared_ptr<Node<T> > node);
  void forwardPrint();
  void backwardPrint();
};

template <class T> DoubleLinkedList<T>::DoubleLinkedList() {
  this->head = NULL;
  this->tail = NULL;
}

template <class T> std::shared_ptr<Node<T>> DoubleLinkedList<T>::getHead() {
  return this->head;
}

template <class T> std::shared_ptr<Node<T>> DoubleLinkedList<T>::getTail() {
  return this->tail;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T> std::shared_ptr<Node<T>> DoubleLinkedList<T>::add(T value) {
  std::shared_ptr<Node<T>> node = std::make_shared<Node<T>>(value);
  return this->add(node);
}

// O(1) runtime complexity
// O(1) space complexity
template <class T> std::shared_ptr<Node<T>> DoubleLinkedList<T>::add(std::shared_ptr<Node<T> > node) {
  if (this->head == NULL) {
    this->head = node;
    this->tail = this->head;
    return this->head;
  }
  this->tail->setNextNode(node);
  node->setPreviousNode(this->tail);
  this->tail = node;
  this->tail->setNextNode(NULL);

  return node;
}

// O(n) runtime complexity
// O(1) space complexity
template <class T> 
void DoubleLinkedList<T>::remove(T value) {
  std::shared_ptr<Node<T> > node = this->head;
  std::shared_ptr<Node<T> > prevNode = NULL;
  while (node != NULL) {
    if(node->getValue() == value) this->remove(node);
    node = node->getNextNode();
  }
}

// O(1) runtime complexity
// O(1) space complexity
template <class T> 
void DoubleLinkedList<T>::remove(std::shared_ptr<Node<T> > node) {
  std::shared_ptr<Node<T> > prevNode = node->getPreviousNode();
  std::shared_ptr<Node<T> > nextNode = node->getNextNode();
  if (prevNode != NULL) prevNode->setNextNode(nextNode);
  if (nextNode != NULL) nextNode->setPreviousNode(prevNode);
  if (node == this->head) this->head = nextNode;
  if (node == this->tail) {
    if(prevNode != NULL) this->tail = prevNode;
    else this->tail = this->head;
  }
}

// O(n) time complexity
// O(1) space complexity
template <class T>
std::shared_ptr<Node<T>> DoubleLinkedList<T>::find(T value) {
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
std::shared_ptr<Node<T>> DoubleLinkedList<T>::find(std::shared_ptr<Node<T>> searchNode) {
  std::shared_ptr<Node<T> > node = this->head;
  while(node != NULL) {
    if (node->getValue() == searchNode->getValue()) return node;
    node = node->getNextNode();
  }
  return NULL;
}

// O(n) time complexity
// O(1) space complexity
template <class T> void DoubleLinkedList<T>::forwardPrint() {
  std::cout << "Printing list forward:" << std::endl;  
  std::cout << "-------" << std::endl;
  std::shared_ptr<Node <T> > node = this->head;
  while (node != NULL) {
    std::cout << node->getValue() << std::endl;
    node = node->getNextNode();
  }
  std::cout << "-------" << std::endl;
}

// O(n) time complexity
// O(1) space complexity
template <class T> void DoubleLinkedList<T>::backwardPrint() {
  std::cout << "Printing list backwards:" << std::endl;  
  std::cout << "-------" << std::endl;
  std::shared_ptr<Node <T> > node = this->tail;
  while (node != NULL) {
    std::cout << node->getValue() << std::endl;
    node = node->getPreviousNode();
  }
  std::cout << "-------" << std::endl;
}

int main() {

  {
    std::unique_ptr<DoubleLinkedList<int>> list =
        std::make_unique<DoubleLinkedList<int>>();
    list->add(1);
    list->add(1);
    std::shared_ptr<Node<int> > nodeToRemove = list->add(2);
    list->add(3);
    list->add(4);
    list->add(std::make_shared<Node<int> >(5));
    list->add(6);
    list->remove(1);
    list->remove(nodeToRemove);
    nodeToRemove = NULL;
    list->forwardPrint();
    list->backwardPrint();

    std::shared_ptr<Node<int> > foundNode = list->find(1);
    if (foundNode != NULL) std::cout << foundNode->getValue() << std::endl;
  }
  return 0;
}