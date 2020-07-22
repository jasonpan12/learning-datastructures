package ds.singlylinkedlist;

/**
 *
 */
public class SinglyLinkedList {
    private Node first; // Like the first car or engine in a train

    public SinglyLinkedList() {
    }

    public boolean isEmpty() {
        return (first == null);
    }

    // Used to insert at the beginning of the list
    // i.e. create a new first
    public void insertFirst(int data) {
        // Create a new node
        Node newNode = new Node();

        // Set the new node's "next" to equal the old first
        newNode.data = data;
        newNode.next = first;

        // overwrite first with the newNode
        first = newNode;
    }

    public Node deleteFirst() {
        Node temp = first;
        first = first.next;
        return temp;
    }

    public void displayList() {
        System.out.println("list (first --> last) ");
        Node current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }

        System.out.println();
    }

    public void insertLast(int data) {
        Node current = first;
        // Loop until current.next is null
        while (current.next != null) {
            current = current.next;
        }
        Node newNode = new Node();
        newNode.data = data;
        current.next = newNode;
    }
}
