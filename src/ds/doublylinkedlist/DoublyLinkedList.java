package ds.doublylinkedlist;

/**
 *
 */
public class DoublyLinkedList {
    private Node first;
    private Node last;

    public DoublyLinkedList() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int data) {
        Node newNode = new Node();
        newNode.data = data;

        if (isEmpty()) {
            last = newNode;
        } else {
            first.previous = newNode;
        }

        newNode.next = first; // the "old" first
        this.first = newNode; // set the new node to be the new first
    }

    public void insertLast(int data) {
        Node newNode = new Node();
        newNode.data = data;

        if (isEmpty()) {
            last = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
        }

        last = newNode;
    }

    // Assume non-empty list
    public Node deleteFirst() {
        Node temp = first;
        if (first.next == null) {
            // there is only one item in the list
            last = null;
        } else {
            first.next.previous = null; // remove reference to first
        }
        first = first.next; // move "next" back up to first
        return temp; // return deleted node
    }

    // Assume non-empty list
    public Node deleteLast() {
        Node temp = last;
        if (first.next == null) {
            // there is only one item in the list
            first = null;
        } else {
            last.previous.next = null;
        }

        last = last.previous;
        return temp;
    }

    // Assume non-empty list
    public boolean insertAfter(int key, int data) {
        Node current = first;

        while (current.data != key) {
            current = current.next;
            if (current == null) {
                return false; // we have reached the end
            }
        }

        // we have found a match
        Node newNode = new Node();
        newNode.data = data;

        // If we're on the last node
        if (current == last) {
            current.next = null;
            last = newNode;
        } else {
            newNode.next = current.next; // bump over current.next
            current.next.previous = newNode; // update both nodes on current.next

        }

        newNode.previous = current; // set new node's previous to current
        current.next = newNode; // which mean current's next is new node

        return true; // it worked
    }

    public Node deleteKey(int key) {
        // Must update both previous.next and next.previous

        Node current = first;
        while (current.data != key) {
            current = current.next;
            if (current == null) {
                return null; // did not find the node
            }
        }

        // if we are deleting the first node
        // must set next.previous and first
        if (current == first) {
            first = current.next; // make the first field point to the node following the current
        } else {
            current.previous.next = current.next;
        }

        // if we are deleting the last node
        // must set next.previous and last
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }

        return current;
    }

    public void displayForward() {
        System.out.println("list (first --> last) ");
        Node current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }

        System.out.println();
    }

    public void displayBackward() {
        System.out.println("list (last --> first) ");
        Node current = last;
        while (current != null) {
            current.displayNode();
            current = current.previous;
        }

        System.out.println();
    }
}
