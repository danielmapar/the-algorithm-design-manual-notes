#include <iostream>
#include <memory>
#include <vector>

// Read more here:
// http://web.stanford.edu/class/archive/cs/cs166/cs166.1166/lectures/12/Small12.pdf

template <class K, class V>
class HashTable {
    public:
        HashTable(int capacity, const std::function<int(K, int&)> &hashFunction = nullptr);
        void insert(K key, V value);
        void remove(K key);
        std::pair<K,V> get(K key);
        static int convertStringToInt(std::string str);
        void print();
    private:
        std::unique_ptr<std::vector<std::pair<K,V>>> buffer;
        int capacity;
        std::function<int(K, int&)> hashFunction;
};

template <class K, class V>
HashTable<K,V>::HashTable(int capacity, const std::function<int(K, int&)> &hashFunction) {
    this->capacity = capacity;
    this->buffer = std::make_unique<std::vector<std::pair<K,V>>>(capacity);
    this->hashFunction = hashFunction != nullptr ? 
            hashFunction 
            : 
            [&](K key, int capacity) {
                return this->convertStringToInt(key) % capacity;
            };
}

// O(n) runtime complexity (n = string length)
// O(1) space complexity
template <class K, class V>
int HashTable<K,V>::convertStringToInt(std::string str) {
    int value = 0;
    for (char c : str) value += c;
    return value;
}

// O(m) runtime complexity -> m depends on the number of collissions
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::insert(K key, V value) {
    int hashKey = this->hashFunction(key, this->capacity);
    for (auto it = this->buffer->begin() + hashKey; it != this->buffer->end(); it++) {
        std::pair<K,V> &pair = *it;
        if (pair.first.empty() || pair.first == key) {
            pair.first = key;
            pair.second = value;
            break;
        }
    }
}

// O(m) runtime complexity -> m depends on the number of collissions
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::remove(K key) {
    int hashKey = this->hashFunction(key, this->capacity);
    if (this->buffer->at(hashKey).first.empty()) return;
    for (auto it = this->buffer->begin() + hashKey; it != this->buffer->end(); ++it) {
        std::pair<K,V> &pair = *it;
        if (pair.first == key) {
            // Create a thumbstone
            pair.first = "";
            pair.second = "";
            break;
        }
    }
}

// O(m) runtime complexity -> m depends on the number of collissions
// O(1) space complexity
template <class K, class V>
std::pair<K,V> HashTable<K,V>::get(K key) {
    int hashKey = this->hashFunction(key, this->capacity);
    if (this->buffer->at(hashKey).first.empty()) return std::pair<K, V>();
    for (auto it = this->buffer->begin() + hashKey ; it != this->buffer->end(); ++it) {
        std::pair<K,V> &pair = *it;
        if (pair.first == key) return pair;
    }
    return std::pair<K, V>();
}

// O(n) runtime complexity -> n represents all elements in the buffer
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::print() {
    std::cout << "Printing HashTable: " << std::endl;
    for (std::pair<K,V> &pair : *(this->buffer.get())) {
        if (!pair.first.empty()) 
            std::cout << "Key: " << pair.first << ", Value: " << pair.second << std::endl;
    }
}

int main() {
    std::unique_ptr<HashTable<std::string, std::string>> hashTable = std::make_unique<HashTable<std::string, std::string>>(10);
    hashTable->insert("firstName", "Daniel");
    hashTable->insert("lastName", "Parreira");
    hashTable->insert("lastName", "Parreira");
    hashTable->insert("age", "20");
    std::cout << "Key: age, Value: " << hashTable->get("age").second << std::endl;
    hashTable->remove("age");
    std::cout << "Key: age, Value: " << hashTable->get("age").second << std::endl;
    hashTable->insert("age", "28");
    hashTable->print();

    return 0;
}