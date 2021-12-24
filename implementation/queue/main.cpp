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
        std::shared_ptr<Node<T> > getNextNode();
        void setNextNode(std::shared_ptr<Node<T> > node);
        T getValue();
};

template <class T>
Node<T>::Node(T value) {
    this->value = value;
    this->nextNode = NULL;
}

template <class T>
Node<T>::~Node() {
    std::cout << "Destroying node: " << this->value << std::endl;
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
T Node<T>::getValue() {
    return this->value;
}

template <class T>
class Queue {
    private:
        std::shared_ptr<Node<T> > head;
        std::shared_ptr<Node<T> > tail;
    public:
        Queue();
        void enqueue(T value);
        std::shared_ptr<Node<T> > dequeue();
        bool isEmpty();
        std::shared_ptr<Node<T> > peek();
        void print();
};

template <class T>
Queue<T>::Queue() {
    this->head = NULL;
    this->tail = NULL;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
void Queue<T>::enqueue(T value) {
    std::shared_ptr<Node<T> > node = std::make_shared<Node<T> >(value);
    if (this->head == NULL) {
        this->head = node;
        this->tail = node;
        return;
    }
    this->tail->setNextNode(node);
    this->tail = node;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
std::shared_ptr<Node<T> > Queue<T>::dequeue() {
    std::shared_ptr<Node<T> > node = this->head;
    this->head = this->head->getNextNode();
    node->setNextNode(NULL);
    return node;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
bool Queue<T>::isEmpty() {
    return this->head == NULL;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
std::shared_ptr<Node<T> > Queue<T>::peek() {
    return this->head;
}

// O(n) runtime complexity
// O(1) space complexity
template <class T>
void Queue<T>::print() {
    std::shared_ptr<Node<T> > node = this->head;
    std::cout << "Printing queue: " << std::endl;
    while (node != NULL) {
        std::cout << node->getValue() << std::endl;
        node = node->getNextNode();
    }
}

int main() {
    std::shared_ptr<Queue<int> > queue = std::make_shared<Queue<int> >(); 
    for (int num : std::vector<int>{1,2,3}) queue->enqueue(num);
    {
        std::shared_ptr<Node<int> > node = queue->dequeue();
        std::cout << "Dequeue: " << node->getValue() << std::endl;

        std::cout << "Is queue empty: " << (queue->isEmpty() ? "true" : "false") << std::endl;
        std::cout << "Peak: " << (queue->peek() != NULL ? (queue->peek())->getValue() : -1) << std::endl;
    }
    queue->print();

    return 0;
}