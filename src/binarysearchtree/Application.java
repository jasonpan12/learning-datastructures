package binarysearchtree;

/**
 *
 */
public class Application {
    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(40, "Ten");
        tree.insert(20, "Twenty");
        tree.insert(15, "Fifteen");
        tree.insert(67, "Fifteen");
        tree.insert(3, "Fifteen");
        tree.insert(2, "Fifteen");
        tree.insert(9, "Fifteen");
        tree.insert(18, "Fifteen");
        tree.insert(22, "Fifteen");
        tree.insert(38, "Fifteen");
        tree.insert(42, "Fifteen");
        tree.insert(51, "Fifteen");
        tree.insert(23, "Fifteen");
        tree.insert(25, "Fifteen");
        tree.displayTreeJPan();

//        System.out.println(tree.findMax().key);
//        System.out.println(tree.findMin().key);
//
//        System.out.println(tree.remove(10));
//        System.out.println(tree.findMin().key);
    }

}
