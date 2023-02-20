package task2;

public class RedBlackTree {
    private Node root;
    private Node TNULL;

    public Node getTNULL() {
        return TNULL;
    }

/*    private void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.getData() + " ");
            preOrderHelper(node.getLeft());
            preOrderHelper(node.getRight());
        }
    }*/

    private String inOrderHelper(Node node) {
        if (node != TNULL) {
            String str1 = inOrderHelper(node.getLeft());
            String str2 = node.getData() + " ";
            String str3 = inOrderHelper(node.getRight());
            return str1 + str2 + str3;
        }
        return "";
    }

/*    private void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.getLeft());
            postOrderHelper(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }*/

    private Node searchTreeHelper(Node node, int key) {
        if (node == TNULL || key == node.getData()) {
            return node;
        }

        if (key < node.getData()) {
            return searchTreeHelper(node.getLeft(), key);
        }

        return searchTreeHelper(node.getRight(), key);
    }

    // fix the rb tree modified by the delete operation
    private void fixDelete(Node x) {
        Node s;
        while (x != root && !x.getColor()) {
            if (x == x.getParent().getLeft()) {
                s = x.getParent().getRight();
                if (s.getColor()) {
                    // case 3.1
                    s.setColor(false);
                    x.getParent().setColor(true);
                    leftRotate(x.getParent());
                    s = x.getParent().getRight();
                }

                if (!s.getLeft().getColor() && !s.getRight().getColor()) {
                    // case 3.2
                    s.setColor(true);
                    x = x.getParent();
                } else {
                    if (!s.getRight().getColor()) {
                        // case 3.3
                        s.getLeft().setColor(false);
                        s.setColor(true);
                        rightRotate(s);
                        s = x.getParent().getRight();
                    }

                    // case 3.4
                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(false);
                    s.getRight().setColor(false);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                s = x.getParent().getLeft();
                if (s.getColor()) {
                    // case 3.1
                    s.setColor(false);
                    x.getParent().setColor(true);
                    rightRotate(x.getParent());
                    s = x.getParent().getLeft();
                }

                if (!s.getRight().getColor()  && !s.getRight().getColor()) {
                    // case 3.2
                    s.setColor(true);
                    x = x.getParent();
                } else {
                    if (s.getLeft().getColor()) {
                        // case 3.3
                        s.getRight().setColor(false);
                        s.setColor(true);
                        leftRotate(s);
                        s = x.getParent().getLeft();
                    }

                    // case 3.4
                    s.setColor( x.getParent().getColor());
                    x.getParent().setColor(false);
                    s.getLeft().setColor(false);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(false);
    }


    private void rbTransplant(Node u, Node v){
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()){
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private void deleteNodeHelper(Node node, int key) throws IllegalAccessException {
        // find the node containing key
        Node z = TNULL;
        Node x, y;
        while (node != TNULL){
            if (node.getData() == key) {
                z = node;
            }

            if (node.getData() <= key) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        if (z == TNULL) {
            throw new IllegalAccessException("Couldn't find key in the tree");

        }

        y = z;
        boolean yOriginalColor = y.getColor();
        if (z.getLeft() == TNULL) {
            x = z.getRight();
            rbTransplant(z, z.getRight());
        } else if (z.getRight() == TNULL) {
            x = z.getLeft();
            rbTransplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }

            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (!yOriginalColor){
            fixDelete(x);
        }
    }

    // fix the red-black tree
    private void fixInsert(Node k){
        Node u;
        while (k.getParent().getColor()) {
            if (k.getParent() == k.getParent().getParent().getRight()) {
                u = k.getParent().getParent().getLeft(); // uncle
                if (u.getColor()) {
                    // case 3.1
                    u.setColor(false);
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getLeft()) {
                        // case 3.2.2
                        k = k.getParent();
                        rightRotate(k);
                    }
                    // case 3.2.1
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    leftRotate(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight(); // uncle

                if (u.getColor()) {
                    // mirror case 3.1
                    u.setColor(false);
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getRight()) {
                        // mirror case 3.2.2
                        k = k.getParent();
                        leftRotate(k);
                    }
                    // mirror case 3.2.1
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    rightRotate(k.getParent().getParent());
                }
            }
            if (k == root) {
                break;
            }
        }
        root.setColor(false);
    }

    private void printHelper(Node root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.getColor()?"RED":"BLACK";
            System.out.println(root.getData() + "(" + sColor + ")");
            printHelper(root.getLeft(), indent, false);
            printHelper(root.getRight(), indent, true);
        }
    }

    public RedBlackTree() {
        TNULL = new Node();
        TNULL.setColor(false);
        TNULL.setLeft(null);;
        TNULL.setRight(null);
        root = TNULL;
    }

    // Pre-Order traversal
    // Node.Left Subtree.Right Subtree
/*    public void preorder() {
        preOrderHelper(this.root);
    }*/

    // In-Order traversal
    // Left Subtree . Node . Right Subtree
    public String inorder() {
        return inOrderHelper(this.root);
    }

    // Post-Order traversal
    // Left Subtree . Right Subtree . Node
    /*public void postorder() {
        postOrderHelper(this.root);
    }*/

    // search the tree for the key k
    // and return the corresponding node
    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    // find the node with the minimum key
    public Node minimum(Node node) {
        while (node.getLeft() != TNULL) {
            node = node.getLeft();
        }
        return node;
    }

    // find the node with the maximum key
    public Node maximum(Node node) {
        while (node.getRight() != TNULL) {
            node = node.getRight();
        }
        return node;
    }

    // find the successor of a given node
    public Node successor(Node x) {
        // if the right subtree is not null,
        // the successor is the leftmost node in the
        // right subtree
        if (x.getRight() != TNULL) {
            return minimum(x.getRight());
        }

        // else it is the lowest ancestor of x whose
        // left child is also an ancestor of x.
        Node y = x.getParent();
        while (y != TNULL && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    // find the predecessor of a given node
    public Node predecessor(Node x) {
        // if the left subtree is not null,
        // the predecessor is the rightmost node in the
        // left subtree
        if (x.getLeft() != TNULL) {
            return maximum(x.getLeft());
        }

        Node y = x.getParent();
        while (y != TNULL && x == y.getLeft()) {
            x = y;
            y = y.getParent();
        }

        return y;
    }

    // rotate left at node x
    public void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != TNULL) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    // rotate right at node x
    public void rightRotate(Node x) {
        Node y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != TNULL) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    // insert the key to the tree in its appropriate position
    // and fix the tree
    public void insert(int key) {
        // Ordinary Binary Search Insertion
        Node node = new Node();
        node.setParent(null);
        node.setData(key);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        node.setColor(true); // new node must be red

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.getData() < x.getData()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        // y is parent of x
        node.setParent(y);
        if (y == null) {
            root = node;
        } else if (node.getData() < y.getData()) {
            y.setLeft(node);
        } else {
            y.setRight(node);
        }

        // if new node is a root node, simply return
        if (node.getParent() == null){
            node.setColor(false);
            return;
        }

        // if the grandparent is null, simply return
        if (node.getParent().getParent() == null) {
            return;
        }

        // Fix the tree
        fixInsert(node);
    }

    public Node getRoot(){
        return this.root;
    }

    // delete the node from the tree
    public void deleteNode(int data) throws IllegalAccessException {
        deleteNodeHelper(this.root, data);
    }

    // print the tree structure on the screen
/*
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }
*/

/*    public static void main(String [] args) throws IllegalAccessException {
        RedBlackTree bst = new RedBlackTree();
        bst.insert(18);
        bst.insert(5);
        bst.insert(15);
        bst.insert(17);
        bst.insert(25);
        bst.insert(40);
        bst.insert(80);
        bst.insert(80);
        bst.deleteNode(25);
        Node node = bst.searchTree(9);
        System.out.println(bst.getTNULL() == node);
        System.out.println("НОДААААААА!!" + node.getData());
        System.out.println(bst.inorder());
    }*/

//
//    private void rotate_left(Node node) {
//        Node pivot = node.getRight();
//
//        pivot.setParent(node.getParent()); /* при этом, возможно, pivot становится корнем дерева */
//        if (node.getParent() != null) {
//            if (node.getParent().getLeft() == node)
//                node.getParent().setLeft(pivot);
//            else
//                node.getParent().setRight(pivot);
//        }
//
//        node.setRight(pivot.getLeft());
//        if (pivot.getLeft() != null)
//            pivot.getLeft().setParent(node);
//
//        node.setParent(pivot);
//        pivot.setLeft(node);
//    }
//
//    private void rotate_right(Node node) {
//        Node pivot = node.getLeft();
//
//        pivot.setParent(node.getParent()); /* при этом, возможно, pivot становится корнем дерева */
//        if (node.getParent() != null) {
//            if (node.getParent().getLeft() == node)
//                node.getParent().setLeft(pivot);
//            else
//                node.getParent().setRight(pivot);
//        }
//
//        node.setLeft(pivot.getRight());
//        if (pivot.getRight() != null)
//            pivot.getRight().setParent(node);
//
//        node.setParent(pivot);
//        pivot.setRight(node);
//    }
//
//    public void insert_case1(Node node) {
//        if (node.getParent() == null)
//            node.setColor(NodeColor.BLACK);
//        else
//            insert_case2(node);
//    }
//
//    private void insert_case2(Node node) {
//        if (node.getParent().getColor() == NodeColor.BLACK)
//            return; /* Tree is still valid */
//        else
//            insert_case3(node);
//    }
//
//    private void insert_case3(Node node) {
//        Node uncle = node.getUncle();
//        Node grandparent = null;
//
//        if ((uncle != null) && (uncle.getColor() == NodeColor.RED)) {
//            // && (node.getParent().getColor() == NodeColor.RED) Второе условие проверяется в insert_case2, то есть родитель уже является красным.
//            node.getParent().setColor(NodeColor.BLACK);
//            uncle.setColor(NodeColor.BLACK);
//            grandparent = node.getGrandparent();
//            grandparent.setColor(NodeColor.RED);
//            insert_case1(grandparent);
//        } else {
//            insert_case4(node);
//        }
//    }
//
//    private void insert_case4(Node node) {
//        Node grandparent = node.getGrandparent();
//
//        if ((node == node.getParent().getRight()) && (node.getParent() == grandparent.getLeft())) {
//            rotate_left(node.getParent());
//            node = node.getLeft();
//
//        } else if ((node == node.getParent().getLeft()) && (node.getParent() == grandparent.getRight())) {
//            rotate_right(node.getParent());
//            node = node.getRight();
//
//        }
//        insert_case5(node);
//    }
//
//    private void insert_case5(Node node) {
//        Node grandparent = node.getGrandparent();
//
//        node.getParent().setColor(NodeColor.BLACK);
//        grandparent.setColor(NodeColor.RED);
//        if ((node == node.getParent().getLeft()) && (node.getParent() == grandparent.getLeft())) {
//            rotate_right(grandparent);
//        } else {
//            rotate_left(grandparent);
//        }
//    }
//
//    private void replace_node(Node node, Node child) {
//        child.setParent(node.getParent());
//        if (node == node.getParent().getLeft())
//            node.getParent().setLeft(child);
//        else
//            node.getParent().setRight(child);
//    }
//
//    public void delete_one_child(Node node) {
//        /*
//         * Условие: n имеет не более одного ненулевого потомка.
//         */
//        Node child;
//        if (node.getRight() != null) {
//            child = node.getRight();
//        } else {
//            child = node.getLeft();
//        }
//
//        replace_node(node, child);
//        if (node.getColor() == NodeColor.BLACK) {
//            if (child.getColor() == NodeColor.RED)
//                child.setColor(NodeColor.BLACK);
//            else
//                delete_case1(child);
//        }
//    }
//
//    private void delete_case1(Node node) {
//        if (node.getParent() != null)
//            delete_case2(node);
//    }
//
//    private void delete_case2(Node node) {
//        Node sibling = node.getSibling();
//
//        if (sibling.getColor() == NodeColor.RED) {
//            node.getParent().setColor(NodeColor.RED);
//            sibling.setColor(NodeColor.BLACK);
//            if (node == node.getParent().getLeft())
//                rotate_left(node.getParent());
//            else
//                rotate_right(node.getParent());
//        }
//        delete_case3(node);
//    }
//
//    private void delete_case3(Node node) {
//        Node sibling = node.getSibling();
//
//        if (
//                (node.getParent().getColor() == NodeColor.BLACK) &&
//                        (sibling.getColor() == NodeColor.BLACK) &&
//                        (sibling.getLeft().getColor() == NodeColor.BLACK) &&
//                        (sibling.getRight().getColor() == NodeColor.BLACK)
//        ) {
//            sibling.setColor(NodeColor.RED);
//            delete_case1(node.getParent());
//        } else
//            delete_case4(node);
//    }
//
//    private void delete_case4(Node node) {
//        Node sibling = node.getSibling();
//
//        if (
//                (node.getParent().getColor() == NodeColor.RED) &&
//                        (sibling.getColor() == NodeColor.BLACK) &&
//                        (sibling.getLeft().getColor() == NodeColor.BLACK) &&
//                        (sibling.getRight().getColor() == NodeColor.BLACK)
//        ) {
//            sibling.setColor(NodeColor.RED);
//            node.getParent().setColor(NodeColor.BLACK);
//        } else
//            delete_case5(node);
//    }
//
//    private void delete_case5(Node node) {
//        Node sibling = node.getSibling();
//
//        if (sibling.getColor() == NodeColor.BLACK) {
//            if (
//                    (node == node.getParent().getLeft()) &&
//                            (sibling.getRight().getColor() == NodeColor.BLACK) &&
//                            (sibling.getLeft().getColor() == NodeColor.RED)
//            ) {
//                sibling.setColor(NodeColor.RED);
//                sibling.getLeft().setColor(NodeColor.BLACK);
//                rotate_right(sibling);
//            } else if (
//                    (node == node.getParent().getRight()) &&
//                            (sibling.getLeft().getColor() == NodeColor.BLACK) &&
//                            (sibling.getRight().getColor() == NodeColor.RED)
//            ) {
//                sibling.setColor(NodeColor.RED);
//                sibling.getRight().setColor(NodeColor.BLACK);
//                rotate_left(sibling);
//            }
//        }
//        delete_case6(node);
//    }
//
//    private void delete_case6(Node node)
//    {
//        Node sibling = node.getSibling();
//
//        sibling.setColor(node.getParent().getColor());
//        node.getParent().setColor(NodeColor.BLACK);
//
//        if (node == node.getParent().getLeft()) {
//            sibling.getRight().setColor(NodeColor.BLACK);
//            rotate_left(node.getParent());
//        } else {
//            sibling.getLeft().setColor(NodeColor.BLACK);
//            rotate_right(node.getParent());
//        }
//    }
//
//    private void inOrderHelper(Node node) {
//        if (node != null) {
//            inOrderHelper(node.getLeft());
//            System.out.print(node.getData() + " ");
//            inOrderHelper(node.getRight());
//        }
//    }
//
//    public void inorder(Node node) {
//        inOrderHelper(node);
//    }
}
