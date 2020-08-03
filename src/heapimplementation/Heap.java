package heapimplementation;

/**
 *
 */
public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize; // number of nodes in the array

    public Heap(int size) {
        this.maxSize = size;
        currentSize = 0;
        heapArray = new Node[size];
    }

    public int getSize() {
        return currentSize;
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean insert(int key) {
        if (currentSize == maxSize) {
            return false;
        }

        Node newNode = new Node(key);
        heapArray[currentSize] = newNode; // insert new node at end of array

        trickleUp(currentSize);

        currentSize++;
        return true;
    }

    // index is where the new node goes
    private void trickleUp(int idx) {
        int parentIdx = (idx - 1) / 2; // formula for getting index of parent
        Node nodeToInsert = heapArray[idx];

        // loop as long as we haven't reached root and
        // key of nodeToInsert's parent is less than  new node
        while (idx > 0 && heapArray[parentIdx].getKey() < nodeToInsert.getKey()) {
            heapArray[idx] = heapArray[parentIdx]; // move parent down
            idx = parentIdx; // set currentlocation to "parent"
            parentIdx = (parentIdx - 1) / 2; // set parent back to actually being a parent
            // and loop will continue
        }
        heapArray[idx] = nodeToInsert;
    }

    public Node remove() {
        // get the root
        Node root = heapArray[0];
        // get the latest value (to be brought up)
        heapArray[0] = heapArray[--currentSize];

        trickleDown(0);
        return root;
    }

    // send current value at idx
    // down until it is smaller than its parent
    private void trickleDown(int idx) {
        int largerChildIdx; // which child is larger - left or right
        Node top = heapArray[idx]; // save last into top variable

        // will run as long as idx is not on the bottom row (at least one child)
        while (idx < currentSize / 2) {
            int leftChildIdx = 2 * idx + 1;
            int rightChildIdx = leftChildIdx + 1;

            // which child is larger?
            if (leftChildIdx < currentSize &&
                    heapArray[leftChildIdx].getKey() < heapArray[rightChildIdx].getKey()) {
                largerChildIdx = rightChildIdx;
            } else {
                largerChildIdx = leftChildIdx;
            }

            // Break when we're done
            if (top.getKey() >= heapArray[largerChildIdx].getKey()) {
                // successfully made root the largest key
                break;
            }

            // actually do the swap
            heapArray[idx] = heapArray[largerChildIdx]; // move child up
            idx = largerChildIdx; // move idx down

            // keep going down and "swapping" items until top is greater than the largest descendent
            // in the entire tree
        }
        // set the original "top" value (which needed to be moved down)
        // into the pos at idx, where it belongs
        heapArray[idx] = top;
    }

    public void displayHeap() {
        System.out.println(traverseRoot(0));
    }

    // root - index of item to traverse
    private String traverseRoot(int root) {
        // Print nothing if the tree is empty
        if (heapArray[root] == null) {
           return "";
        }

        // Instantiate the "big string"
        StringBuilder sb = new StringBuilder();

        // Add the root to it
        sb.append(heapArray[root].getKey());

        // Define forks
        String pointerLeft = "└──";
        String pointerRight = "├──"; // there will always be a left if a right exists

        // get children
        int leftChildIdx = 2 * root + 1;
        int rightChildIdx = leftChildIdx + 1;

        // Start recursion
        traverseNodes(sb,leftChildIdx, "", pointerRight,  false);
        traverseNodes(sb,rightChildIdx, "", pointerLeft,  true);

        return sb.toString();
    }

    // StringBuilder -> used to build a string
    // node -> node
    // padding -> this is what will go before the pointer, e.g. how many "│  " we need
    // fork -> what kind of fork we need, depending on whether there's left, right, or both children
    // isLeftNode -> whether the current node is a "left child" of its parent
    // if this is a left child, then
    // each row consists of {padding, fork, node.key}
    public void traverseNodes(StringBuilder sb, int idx, String padding, String fork, boolean isLeftNode) {
        //
        if (heapArray[idx] != null) {
            // Begin new row
            sb.append("\n");
            sb.append(padding); // apply padding for current row, built by prev row
            sb.append(fork); // apply the appropriate fork
            sb.append(heapArray[idx].getKey()); // apply idx's key

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

            // Define forks
           String pointerLeft = "└──";
           String pointerRight = "├──"; // there will always be a left if a right exists

            // get children
            int leftChildIdx = 2 * idx + 1;
            int rightChildIdx = leftChildIdx + 1;

            // Recursion
            traverseNodes(sb, leftChildIdx, paddingForBoth, pointerRight, false);
            traverseNodes(sb, rightChildIdx, paddingForBoth, pointerLeft, true);
       }
    }

    public void displayHeapClassAnswer() {
            System.out.println("Heap Array: ");
            for(int m = 0; m < currentSize; m++) {
                if(heapArray[m] != null) {
                    System.out.print( heapArray[m].getKey() + " ");
                }
                System.out.println();

                int nBlanks = 32;
                int itemsPerRow = 1;
                int column = 0;
                int j = 0; // current item

                String dots = "...............................";
                System.out.println(dots+dots);
                while(currentSize > 0) {
                    if(column == 0) {
                        for(int k = 0; k < nBlanks; k++) {
                            System.out.print(" ");
                        }
                    }
                    System.out.print(heapArray[j].getKey());
                    j++;
                    if(j == currentSize) {
                        break;
                    }

                    column++;
                    // end of row
                    if(column == itemsPerRow) {
                        nBlanks = nBlanks/2; // half the blanks
                        itemsPerRow = itemsPerRow * 2;     // twice the items
                        column = 0;
                        System.out.println();
                    } else {
                        for(int k=0; k<nBlanks*2; k++) {
                            System.out.print(" ");
                        }
                    }

                }
                System.out.println("\n"+dots+dots);
            }


        }
}
