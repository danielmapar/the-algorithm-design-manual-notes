#include <iostream>
#include <memory>
#include <vector>
#include <list>

template <class K, class V>
class HashTable {
    public:
        HashTable(int numOfBuckets);
        void insert(K key, V value);
        void remove(K key);
        std::pair<K, V> get(K key);
        void print();
    private:
        std::unique_ptr<std::vector<std::list<std::pair<K, V>>>> buckets;
        int numOfBuckets;
        int hashFunction(K key);
        int convertStringToInt(std::string str);
};  

template <class K, class V>
HashTable<K,V>::HashTable(int numOfBuckets) {
    this->numOfBuckets = numOfBuckets;
    this->buckets = std::make_unique<std::vector<std::list<std::pair<K,V>>>>(numOfBuckets);
}

// O(n) runtime complexity (n = string length)
// O(1) space complexity
template <class K, class V>
int HashTable<K,V>::convertStringToInt(std::string str) {
    int value = 0;
    for (char c : str) value += c;
    return value;
}

// O(1) runtime complexity
// O(1) space complexity
template <class K, class V>
int HashTable<K,V>::hashFunction(K key) {
    // TODO: support many key types
    return this->convertStringToInt(key) % this->numOfBuckets;
}

// O(1) runtime complexity
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::insert(K key, V value) {
    this->buckets->at(this->hashFunction(key)).push_back(std::pair<K,V>(key, value));
}

// O(1) runtime complexity
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::remove(K key) {
    std::pair<K,V> pairToRemove;
    std::list<std::pair<K,V>> &list = this->buckets->at(this->hashFunction(key));
    for (std::pair<K,V> pair : list) {
        if (pair.first == key) {
            pairToRemove = pair;
            break;
        }
    }
    list.remove(pairToRemove);
}

// O(1) runtime complexity
// O(1) space complexity
template <class K, class V>
std::pair<K, V> HashTable<K,V>::get(K key) {
    for (std::pair<K,V> pair : this->buckets->at(this->hashFunction(key))) {
        if (pair.first == key) return pair;
    }
    return std::pair<K, V>();
}

// O(n) runtime complexity
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::print() {
    std::cout << "Printing HashTable: " << std::endl;
    for (std::list<std::pair<K,V>> list : *(this->buckets.get())) {
        for (std::pair<K,V> pair : list) {
            std::cout << "Key: " << pair.first << ", Value: " << pair.second << std::endl;
        }
    }
}

int main() {
    std::unique_ptr<HashTable<std::string, std::string>> hashTable = std::make_unique<HashTable<std::string, std::string>>(100);
    hashTable->insert("firstName", "Daniel");
    hashTable->insert("lastName", "Parreira");
    hashTable->insert("age", "10");
    hashTable->print();

    std::cout << "Searching key age: " << hashTable->get("age").second << std::endl;
    hashTable->remove("age");
    std::cout << "Searching key age: " << hashTable->get("age").second << std::endl;
    
    return 0;
}