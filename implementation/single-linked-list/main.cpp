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
  this->nextNode = nullptr;
}

template <class T> Node<T>::~Node() { 
    std::cout << "Destructor for node value: " << this->getValue() << std::endl;
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
  void remove(T value);
  void remove(std::shared_ptr<Node<T> > node);
  std::shared_ptr<Node<T>> get(T value);
  void print();
};

template <class T> SingleLinkedList<T>::SingleLinkedList() {
  this->head = nullptr;
  this->tail = nullptr;
}

template <class T> std::shared_ptr<Node<T>> SingleLinkedList<T>::getHead() {
  return this->head;
}

template <class T> std::shared_ptr<Node<T>> SingleLinkedList<T>::getTail() {
  return this->tail;
}

template <class T> std::shared_ptr<Node<T>> SingleLinkedList<T>::add(T value) {
  std::shared_ptr<Node<T>> node = std::make_shared<Node<T>>(value);
  if (this->head == nullptr) {
    this->head = node;
    this->tail = this->head;
    return this->head;
  }
  this->tail->setNextNode(node);
  this->tail = node;
  this->tail->setNextNode(nullptr);

  return node;
}

template <class T> 
void SingleLinkedList<T>::remove(T value) {
  std::shared_ptr<Node<T> > node = this->head;
  std::shared_ptr<Node<T> > prevNode = NULL;
  while (node != NULL) {
    if(node->getValue() == value) {
        if (prevNode == NULL) prevNode = this->getHead();
        prevNode->setNextNode(node->getNextNode());
        if (node == this->tail) this->tail = prevNode;
        node = node->getNextNode();
        continue;
    }
    prevNode = node;
    node = node->getNextNode();
  }
}

template <class T> 
void SingleLinkedList<T>::remove(std::shared_ptr<Node<T> > nodeToRemove) {
  std::shared_ptr<Node<T> > node = this->head;
  std::shared_ptr<Node<T> > prevNode = NULL;
  while (node != NULL) {
    if(node.get() == nodeToRemove.get()) {
        if (prevNode == NULL) prevNode = this->getHead();
        prevNode->setNextNode(node->getNextNode());
        if (node == this->tail) this->tail = prevNode;
        break;
    }
    prevNode = node;
    node = node->getNextNode();
  }
}

template <class T> void SingleLinkedList<T>::print() {
  Node<T> *node = this->head.get();
  while (node != nullptr) {
    std::cout << node->getValue() << std::endl;
    node = node->getNextNode().get();
  }
}

int main() {

  {
    std::unique_ptr<SingleLinkedList<int>> list =
        std::make_unique<SingleLinkedList<int>>();
    list->add(1);
    std::shared_ptr<Node<int> > nodeToRemove = list->add(2);
    list->add(3);
    list->add(3);
    list->add(3);
    list->add(3);
    list->add(3);
    list->add(6);
    list->remove(nodeToRemove);
    nodeToRemove = NULL;
    list->remove(3);
    list->print();
    //std::cout << list->getTail()->getValue() << std::endl;
  }
  return 0;
}