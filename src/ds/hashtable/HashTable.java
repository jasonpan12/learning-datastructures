package ds.hashtable;

/**
 *
 */
public class HashTable {
    String[] hashArray;
    int arraySize;
    int size = 0; // counter for number of elements in the hash table

    public HashTable(int numSlots) {

        if (isPrime(numSlots)) {
            hashArray = new String[numSlots];
            arraySize = numSlots;
        } else {
            int primeCount = getNextPrime(numSlots);
            hashArray = new String[primeCount];
            arraySize = primeCount;

            System.out.println("hash table size given " + numSlots + " is not a prime");
            System.out.println("hash table size changed to " + primeCount);

        }

    }

    private boolean isPrime(int num) {
        for (int i = 2; i * i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int getNextPrime(int minNumber) {
        for (int i = minNumber; true; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
    }

    // preferred index pos
    private int hashFunc1(String word) {
        int hashVal = word.hashCode();
        hashVal = hashVal % arraySize;

        if (hashVal < 0) { //hashval can be negative
            hashVal += arraySize;
        }

        return hashVal;
    }

    // step size
    private int hashFunc2(String word) {
        int hashVal = word.hashCode();
        hashVal %= arraySize;

        if (hashVal < 0) {
            hashVal += arraySize;
        }

        // use a prime number less than array size
        return 3 - hashVal % 3;
    }

    public void insert(String word) {
        int hashVal = hashFunc1(word);
        int stepSize = hashFunc2(word);

        // do the stepping if needed
        while (hashArray[hashVal] != null) {
            hashVal = hashVal + stepSize;
            hashVal = hashVal % arraySize;

        }

        // otherwise insert
        hashArray[hashVal] = word;
        System.out.println("inserted word " + word);
        size++;
    }

    public String find(String word) {
        int hashVal = hashFunc1(word);
        int stepSize = hashFunc2(word);

        // do the stepping if needed
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].equals(word)) {
                return hashArray[hashVal];
            }
            hashVal = hashVal + stepSize;
            hashVal = hashVal % arraySize;
        }

        // otherwise return null
        return hashArray[hashVal];
    }

    public void displayTable() {
        System.out.println("Table: ");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                System.out.print(hashArray[i] + "");

            } else {
                System.out.print("** ");
            }
            System.out.println("");
        }
    }
}
