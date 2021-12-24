#include <iostream>
#include <memory>

template <class T>
class Vector {
    public:
        Vector(const int &capacity);
        int getCapacity();
        int getSize();
        void pushBack(T item);
        T popBack();
        void print();
        T at(const int &index);
        T operator[](const int &index);
    private:
        std::unique_ptr<T[]> buffer;
        int size;
        int capacity;
        void resize();
};

template <class T>
Vector<T>::Vector(const int &capacity) {
    this->size = 0;
    this->capacity = capacity;
    this->buffer = std::make_unique<T[]>(capacity);
}

template <class T>
int Vector<T>::getCapacity() {
    return this->capacity;
}

template <class T>
int Vector<T>::getSize() {
    return this->size;
}

// O(n) runtime complexity
// O(n*2) space complexity
template <class T>
void Vector<T>::resize() {
    T *prevBuffer = this->buffer.get();
    this->capacity *= 2; // doubling the vector capacity
    T *nextBuffer = new T[this->capacity];
    for (int i = 0; i < this->size; i++) nextBuffer[i] = prevBuffer[i];
    this->buffer = std::unique_ptr<T[]>(nextBuffer);
    std::cout << "Resized vector capacity to: " << this->capacity << std::endl;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
T Vector<T>::at(const int &index) {
    if (index < this->size) return this->buffer[index];
    else throw std::invalid_argument("Index " + std::to_string(index) + " is out of bound!");
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
T Vector<T>::operator[](const int &index) {
    return this->at(index);
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
void Vector<T>::pushBack(T item) {
    if (this->size == this->capacity) this->resize();
    this->buffer[this->size++] = item;
}

// O(1) runtime complexity
// O(1) space complexity
template <class T>
T Vector<T>::popBack() {
    T item;
    if (this->size > 0) {
        --this->size;
        item = this->buffer[size];
        this->buffer[this->size] = 0;
        return item;
    }
    return item;
}

// O(n) runtime complexity
// O(1) space complexity
template <class T>
void Vector<T>::print() {
    std::cout << "Printing vector: " << std::endl;
    for (int i = 0; i < size; i++) {
        std::cout << this->buffer[i] << std::endl;
    }
}

int main() {
    int capacity = 10;
    std::unique_ptr<Vector<int>> vec = std::make_unique<Vector<int>>(capacity);
    // Insert 30 items
    int item = 0;
    while (item < 20) vec->pushBack(item++);
    // Remove the 10 last items
    item = 0;
    while (item < 10) {
        vec->popBack();
        item++;
    }
    std::cout << "Position 5 of the vector contains: " << vec->at(5) << std::endl;
    std::cout << "Position 6 of the vector contains: " << (*vec.get())[6] << std::endl;
    
    try {
        vec->at(100);
    } catch(const std::invalid_argument& e) {
        std::cerr << e.what() << std::endl;
    }
    vec->print();

    std::cout << "Vector capacity " << vec->getCapacity() << " and size " << vec->getSize() << std::endl;
    
    return 0;
}