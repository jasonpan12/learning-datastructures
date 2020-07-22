package ds.queue;

/**
 *
 */
public class Queue {
    private int maxSize; // initialize the set of slots
    private long[] queueArray; // slots to maintain the data
    private int front; // this will be the index position for the element in the front
    private int rear; // this is also going to be the index position for the element at the back of the line
    private int nItems; // counter to maintain the number of items in our queue

    public Queue(int size) {
        this.maxSize = size;
        this.queueArray = new long[maxSize];
        front = 0; // index position of the first slot of the array
        rear = -1; // there is no item in the array yet to be considered as the last item
        nItems = 0; // we don't have elements int he array yet
    }

    public void insert(long j) {
        if (rear == maxSize - 1) {
            rear = -1; // If we're at the end, set "rear" back to the beginning and overwrite
        }
        rear++; // Add to the end
        queueArray[rear] = j;
        nItems++;
    }

    public long remove() { // remove item from front
        long temp = queueArray[front];
        front++;
        if (front == maxSize) { // We have removed all items from the queue
            front = 0; // we can set front back to zeroth index
        }
        nItems--;
        return temp;
    }

    public long peekFront() {
        return queueArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == 0);
    }

    public void view() {
        System.out.println("[ ");
        for (int i = 0; i < queueArray.length; i++) {
            System.out.println(queueArray[i] + " ");
        }
        System.out.println(" ]");
    }
}
