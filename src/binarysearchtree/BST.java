package binarysearchtree;

import java.io.PrintStream;
import java.util.Stack;

/**
 *
 */
public class BST {
    private Node root;

    public void insert(int key, String value) {
        // create our new node, to be inserted
        Node newNode = new Node(key, value);

        // Handle case where nothing exists yet
        if (root == null) {
            root = newNode;
        } else {
            // if a tree does exist, we need to traverse it

            // Current is the "traversing" var
            Node current = root;
            // in the loop, we will traverse left or right
            // until current is null - when current is null
            // its parent is the last "leaf"
            Node parent;

            // start traversing
            while (true) {
                parent = current;
                if (key < current.key) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node findMin() {
        Node current = root;
        Node last = null;

        while (current != null) {
            last = current;
            current = current.leftChild;
        }

        return last;
    }

    public Node findMax() {
        Node current = root;
        Node last = null;

        while (current != null) {
            last = current;
            current = current.rightChild;
        }

        return last;
    }

    public boolean remove(int key) {

        Node currentNode = root;
        Node parentNode = root;

        boolean isLeftChild = false;

        // searching for node
        while (currentNode.key != key) {
            parentNode = currentNode;
            if (key < currentNode.key) {
                isLeftChild= true;
                currentNode = currentNode.leftChild;
            } else {
                isLeftChild = false;
                currentNode = currentNode.rightChild;
            }
            if (currentNode == null) {
                return false;
            }
        }
        // found the node
        Node nodeToDelete = currentNode;

        // if node is a leaf
        if (nodeToDelete.leftChild == null && nodeToDelete.rightChild == null) {
            if (nodeToDelete == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.leftChild = null;
            } else {
                parentNode.rightChild = null;
            }
        } else if (nodeToDelete.rightChild == null) {
            // if node has one child on left
            if (nodeToDelete == root) {
                root = nodeToDelete.leftChild;
            } else if (isLeftChild) {
                parentNode.leftChild = nodeToDelete.leftChild;
            } else {
                parentNode.rightChild = nodeToDelete.leftChild;
            }
        } else if (nodeToDelete.leftChild == null) {
            // if node has one child on left
            if (nodeToDelete == root) {
                root = nodeToDelete.rightChild;
            } else if (isLeftChild) {
                parentNode.leftChild = nodeToDelete.rightChild;
            } else {
                parentNode.rightChild = nodeToDelete.rightChild;
            }
        } else {
            Node successor = getSuccessor(nodeToDelete);
            // connect parent of nodeToDelete to successor

            if (nodeToDelete == root) {
                root = successor;
            } else if (isLeftChild) {
                parentNode.leftChild = successor;
            } else {
                parentNode.rightChild = successor;
            }

            // move up nodetoDelete's left child to successor's left child
            successor.leftChild = nodeToDelete.leftChild;
        }

        // if nodeToDelete has two children
        // go to right child
        // find minimum of "right child"
        // replace nodeToDelete with minimum

        return true;
    }

    private Node getSuccessor(Node nodeToDelete) {
        Node successorParent = nodeToDelete;
        Node successor = nodeToDelete;

        Node current = nodeToDelete.rightChild;

        // find the min
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        // if successor is not the immediate right child
        if (successor != nodeToDelete.rightChild) {
            // successorParent used to point to successor
            // instead, replace successorParent.leftChild with
            // successor's right child, if it exists
            // so we don't orphan that tree
            successorParent.leftChild = successor.rightChild;

            // when the successor is moved, its right
            // needs to be replaced with the deleted node's right
            // since successor is replacing nodetoDelete
            successor.rightChild = nodeToDelete.rightChild;
        }

        return successor;
    }

    // Builds a big string, all rows in one string
    // separated by "\n" with each recursion
    public void displayTreeJPan() {
        System.out.println(traverseRoot(root));
    }

    public String traverseRoot(Node node) {

        // Print nothing if the tree is empty
        if (node == null) {
           return "";
        }

        // Instantiate the "big string"
        StringBuilder sb = new StringBuilder();

        // Add the root to it
        sb.append(root.key);

        // Define forks
        String pointerLeft = "└──";
        String pointerRight = (root.leftChild != null) ? "├──" : "└──";

        // Start recursion
        traverseNodes(sb,root.rightChild, "", pointerRight,  false);
        traverseNodes(sb,root.leftChild, "", pointerLeft,  root.rightChild != null);

        return sb.toString();
   }

    // StringBuilder -> used to build a string
    // node -> node
    // padding -> this is what will go before the pointer, e.g. how many "│  " we need
    // fork -> what kind of fork we need, depending on whether there's left, right, or both children
    // isLeftNode -> whether the current node is a "left child" of its parent
    // if this is a left child, then
    // each row consists of {padding, fork, node.key}
    public void traverseNodes(StringBuilder sb, Node node, String padding, String fork, boolean isLeftNode) {
        //
        if (node != null) {
            // Begin new row
            sb.append("\n");
            sb.append(padding); // apply padding for current row, built by prev row
            sb.append(fork); // apply the appropriate fork
            sb.append(node.key); // apply current node's key

            // Start building padding for the next row
            // Progressively add padding to previous padding
            StringBuilder paddingBuilder = new StringBuilder(padding);

            // if current node is a left child of its parent, include the line
            if (isLeftNode) {
                paddingBuilder.append("   ");
            } else {
                paddingBuilder.append("│  ");
            }
            String paddingForBoth = paddingBuilder.toString();

            // Define pointers for left and right
            String pointerLeft = "└──";
            String pointerRight = (node.leftChild != null) ? "├──" : "└──";

            // Recursion
            traverseNodes(sb, node.rightChild, paddingForBoth, pointerRight, false);
            traverseNodes(sb, node.leftChild, paddingForBoth, pointerLeft, true);
       }
    }

    // crawl left, then right
    public void treeCrawler (Node node, int depth) {
        if (node != null) {
            String row = Integer.toString(node.key);
            System.out.println(row+ "└──" + node.rightChild.key);

            if (node.rightChild != null || node.leftChild != null) {
                depth = depth + 1;

                if (node.leftChild == null) {
                    // only print right
                    for (int i = 0; i < depth; i++) {
                        row = row + "│  ";
                        treeCrawler(node.rightChild, depth);
                    }
//                    System.out.println(row);
                }
            }
//            if (node.rightChild != null && node.leftChild == null) {
//            }
//            if (node.leftChild != null && node.rightChild == null) {
//                System.out.println( + node.leftChild.key);
//            }
            treeCrawler(node.leftChild, depth);
        }

        // row = {spacer x depth - 2, connector, key}
        // how to get depth

//        if (node != null) {
//            if(node.leftChild != null){
//            }
//            if(node.rightChild != null){
//            }
//        }
    }

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");

        while(isRowEmpty==false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<nBlanks; j++) {
                System.out.print(" ");
            }

            while(globalStack.isEmpty()==false) {
                Node temp = (Node)globalStack.pop();
                if(temp != null) {
                    System.out.print(temp.key);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                for(int j=0; j<nBlanks*2-2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();
            nBlanks = nBlanks/2;

            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
            }
        System.out.println( "......................................................");
    }
}
