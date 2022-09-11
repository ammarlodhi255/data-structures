/**
* Implementation of Hash table with Separate Chaining and Open addressing (Linear probing)
* for hash collision resolutions.
* Hashtable is a Data structure which provides a mapping from a 'Key' to a 'Value', this is achieved
* using a technique called hashing.
*
* @author Ammar Lodhi
*/

import java.util.LinkedList;

class Entry <K, V> {
    K key;
    V value;
    int hash;

    Entry (final K key, final V value) {
        this.key = key;
        this.value = value;
        hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> that) {
        if(this.hash != other.hash) return false;
        return key.equals(that.key);
    }

    public String toString() {
        return key + " ==> " + value;
    }
}

public class HashTableSeparateChaining <K, V> {
    private LinkedList<Entry<K, V>> [] table;
    private int capacity;
    private final static int DEFAULT_CAPACITY = 3;
    private int size;

    HashTableSeparateChaining() {
        this(DEFAULT_CAPACITY);
    }

    HashTableSeparateChaining(final int capacity) {
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        table = new LinkedList[this.capacity];
        size = 0;
    }

    public int normalizeIndex(int keyHash) {
        return Math.abs(keyHash % capacity);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void insert(K key, V value) {
        Entry<K, V> new_entry = new Entry<>(key, value);
        int index = normalizeIndex(new_entry.hash);
        if(!seekEntry(index, key)) {
            if(isBucketNull(index)) {
                LinkedList<Entry<K, V>> bucket = new LinkedList<>();
                table[index] = bucket;
            }
            table[index].addLast(new_entry);
        } else
            throw new IllegalArgumentException("Key-value pair already exists");
    }

    public boolean seekEntry(int index, K key) {
        LinkedList<Entry<K, V>> bucket = table[index];
        if(bucket == null) return false;
        for(Entry entry : bucket) {
            if(entry.key.equals(key)) return true;
        }
        return false;
    }

    private boolean isBucketNull(int index) {
        return table[index] == null;
    }
}
