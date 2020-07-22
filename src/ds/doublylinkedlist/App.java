package ds.doublylinkedlist;

import ds.circularlinkedlist.CircularLinkedList;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        DoublyLinkedList myList = new DoublyLinkedList();
        myList.insertFirst(100);
        myList.insertFirst(50);
        myList.insertFirst(99);
        myList.insertFirst(88);
        myList.insertLast(9999999);

        myList.displayBackward();
        myList.displayForward();
        myList.deleteFirst();
        myList.deleteLast();
        myList.deleteKey(50);
        myList.displayForward();
        myList.insertAfter(99, 100);
        myList.displayForward();
    }
}
