package src;

public class ArrayDictionary implements Dictionary {
    private int capacity;
    private int count;
    private KVEntry[] entries;

    public ArrayDictionary(int capacity) {
        this.capacity = capacity;
        entries = new KVEntry[capacity];
    }

    private int hashFunction(int key) {
        return key % capacity;
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean add(int key, int value) {

        int hashedKey = hashFunction(key);

        // when there's no entry yet
        if (entries[hashedKey] == null) {
            if (count == capacity) {
                return false;
            }
            entries[hashedKey] = new KVEntry(key, value);
            count++;
            return true;
        }

        KVEntry ptr = entries[hashedKey];
        KVEntry pNewNode = null;
        while (ptr != null) {
            // update value if key already exists
            if (ptr.key == key) {
                ptr.value = value;
                return true;
            }
            pNewNode = ptr;
            ptr = ptr.next;
        }

        // add an entry to the end of the chain
        pNewNode.next = new KVEntry(key, value);
        return true;
    }

    /*Delete the entry with the given key from the dictionary
     Return true if an entry is deleted, false otherwise
     - Calling the contains function below, if it returns false, we return false for this as there is nothing to remove. Otherwise,
     I createa integer calling the hashFunction and create a KVEntry which will be the head of the linked list with entries[at
     the returned integer]. If the head value is equal to the key, I set the head to the next node. Otherwise I loop until something
     matches that value and do the same to it and break out of the loop. At the end, I decrement count as there is one less value
     and return true as something has been removed.
    */
    
    @Override
    public boolean remove(int key) {
        if(!this.contains(key))
        {
            return false;
        }
        
        int hashedKey = hashFunction(key);
        KVEntry curr = entries[hashedKey];
        if(curr.key == key)
        {
            entries[hashedKey] = curr.next;
        }
        while(curr.next != null)
        {
            if(curr.next.key == key)
            {
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        }
        count--;
        return true;
    }

    /*Return true when the dictionary contains an entry
    with the key
    - If the key is negative, false is immediately returned. Otherwise, I create a head node same as the method above (explination
    is in that comment block), and run through the list until we have reached the end (or until the key value of the node is 
    equal to the given key, then true is returned). If true is never returned then i return false.
    */
    @Override
    public boolean contains(int key) {
        int hashedKey = hashFunction(key);
        if(hashedKey < 0 || hashedKey >= this.capacity)
        {
            return false;
        }
        KVEntry head = entries[hashedKey];
        while(head != null)
        {
            if(head.key == key)
            {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    // Return the entry value with the given key
    // Return null if no entry exists with the given key
    @Override
    public Integer get(int key) {
        // NOT IMPLEMENTED
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (entries[i] == null) {
                builder.append("dictionary["+ i + "] = null\n");
                continue;
            }
            KVEntry ptr = entries[i];
            builder.append("dictionary["+i+"] = {");
            while (ptr != null) {
                builder.append("(" + ptr.key + ", " + ptr.value + ")");
                ptr = ptr.next;
            }
            builder.append("}\n");
        }
        return builder.toString();
    }
}
