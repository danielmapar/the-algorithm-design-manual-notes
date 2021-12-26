#include <iostream>
#include <memory>
#include <vector>
#include <list>

// Read more here:
// http://web.stanford.edu/class/archive/cs/cs166/cs166.1166/lectures/12/Small12.pdf

template <class K, class V>
class HashTable {
    public:
        HashTable(int numOfBuckets, const std::function<int(K, int&)> &hashFunction = nullptr);
        void insert(K key, V value);
        void remove(K key);
        std::pair<K, V> get(K key);
        static int convertStringToInt(std::string str);
        void print();
    private:
        // Instead of a Vector of List, we could also use a Vector of BST
        std::unique_ptr<std::vector<std::list<std::pair<K, V>>>> buckets;
        std::function<int(K, int&)> hashFunction;
        int numOfBuckets;
};  

template <class K, class V>
HashTable<K,V>::HashTable(int numOfBuckets, const std::function<int(K, int&)> &hashFunction) {
    this->numOfBuckets = numOfBuckets;
    this->buckets = std::make_unique<std::vector<std::list<std::pair<K,V>>>>(numOfBuckets);
    this->hashFunction = hashFunction != nullptr ? 
            hashFunction 
            : 
            this->hashFunction = [&](K key, int &numOfBuckets) -> int {
                return this->convertStringToInt(key) % numOfBuckets;
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

// O(m) runtime complexity -> m = size of collision list inside a bucket
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::insert(K key, V value) {
    int hashKey = this->hashFunction(key, this->numOfBuckets);
    if (!this->buckets->at(hashKey).empty()){
        for (std::pair<K,V> &pair: this->buckets->at(hashKey)) {
            if (pair.first == key) {
                pair.second = value;
                break;
            }
        }
        return;
    }
    this->buckets->at(hashKey).push_back(std::pair<K,V>(key, value));
}

// O(m) runtime complexity -> m = size of collision list inside a bucket
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::remove(K key) {
    std::pair<K,V> pairToRemove;
    std::list<std::pair<K,V>> &list = this->buckets->at(this->hashFunction(key, this->numOfBuckets));
    for (std::pair<K,V> pair : list) {
        if (pair.first == key) {
            pairToRemove = pair;
            break;
        }
    }
    list.remove(pairToRemove);
}

// O(m) runtime complexity -> m = size of collision list inside a bucket
// O(1) space complexity
template <class K, class V>
std::pair<K, V> HashTable<K,V>::get(K key) {
    for (std::pair<K,V> pair : this->buckets->at(this->hashFunction(key, this->numOfBuckets))) {
        if (pair.first == key) return pair;
    }
    return std::pair<K, V>();
}

// O(n) runtime complexity -> n = all elements inside hashtable (including collision lists)
// O(1) space complexity
template <class K, class V>
void HashTable<K,V>::print() {
    std::cout << "Printing HashTable: " << std::endl;
    for (std::list<std::pair<K,V>> list : *(this->buckets.get()))
        for (std::pair<K,V> pair : list)
            std::cout << "Key: " << pair.first << ", Value: " << pair.second << std::endl;
}

int main() {
    const std::function<int(std::string, int&)> &hashFunction = [](std::string key, int &numOfBuckets) -> int {
        return HashTable<std::string, std::string>::convertStringToInt(key) % numOfBuckets;
    };
    std::unique_ptr<HashTable<std::string, std::string>> hashTable = std::make_unique<HashTable<std::string, std::string>>(100);
    hashTable->insert("firstName", "Daniel");
    hashTable->insert("lastName", "Parreira");
    hashTable->insert("lastName", "Parreira");
    hashTable->insert("age", "10");
    hashTable->print();

    std::cout << "Searching key age: " << hashTable->get("age").second << std::endl;
    hashTable->remove("age");
    std::cout << "Searching key age: " << hashTable->get("age").second << std::endl;
    
    return 0;
}