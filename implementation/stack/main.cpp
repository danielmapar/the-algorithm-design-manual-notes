#include <iostream>
#include <memory>
#include <vector>

template <class T>
class Node {
    private:
        T value;
        std::shared_ptr<Node<T> > nextNode;
    public:
        Node(T value);
        ~Node();
        T getValue();
        std::shared_ptr<Node<T> > getNextNode();
        void setNextNode(std::shared_ptr<Node<T> > node);
};

template <class T>
Node<T>::Node(T value) {
    this->value = value;
    this->nextNode = NULL;
}

template <class T>
Node<T>::~Node() {
    std::cout << "Destroying: " << this->value << std::endl;
}

template <class T>
T Node<T>::getValue() {
    return this->value;
}

template <class T>
std::shared_ptr<Node<T> > Node<T>::getNextNode() {
    return this->nextNode;
}

template <class T>
void Node<T>::setNextNode(std::shared_ptr<Node<T> > node) {
    this->nextNode = node;
}

template <class T>
class Stack {
    public:
        Stack();
        std::shared_ptr<Node<T> > push(T value);
        std::shared_ptr<Node<T> > pop();
        std::shared_ptr<Node<T> > peek();
        void clear(); 
        void print();
    private:
        std::shared_ptr<Node<T> > top;
        std::shared_ptr<Node<T> > bottom;

};

template <class T>
Stack<T>::Stack() {
    this->top = NULL;
    this->bottom = NULL;
}

template <class T>
std::shared_ptr<Node<T> > Stack<T>::push(T value) {
    std::shared_ptr<Node<T> > node = std::make_shared<Node<T> >(value);
    if (this->top == NULL) {
        this->top = node;
        this->bottom  = node;
        return node;
    }
    node->setNextNode(this->top);
    this->top = node;
    return this->top;
}

template <class T>
std::shared_ptr<Node<T> > Stack<T>::pop() {
    std::shared_ptr<Node<T> > node = this->top->getNextNode();
    if (node != NULL) {
        std::shared_ptr<Node<T> > oldTop = this->top;
        this->top = node;
        return oldTop;
    }
    return NULL;
}

template <class T>
std::shared_ptr<Node<T> > Stack<T>::peek() {
    return this->top;
}

template <class T>
void Stack<T>::print() {
    std::shared_ptr<Node<T> > node = this->top;
    std::cout << "Printing stack: " << std::endl;
    while (node != NULL) {
        std::cout << node->getValue() << std::endl;
        node = node->getNextNode();
    }
}

int main() {

    std::shared_ptr<Stack<int> > stack = std::make_shared<Stack<int> >();
    
    for (int item : std::vector<int>{1,2,3,4,5,6}) {
        stack->push(item);
    }
    stack->pop();
    std::cout << "Peek: " << (stack->peek() != NULL ? (stack->peek())->getValue() : -1) << std::endl;
    stack->print();
    return 0;
}