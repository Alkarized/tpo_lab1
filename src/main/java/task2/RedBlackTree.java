package task2;

public class RedBlackTree {
    private Node root;
    private final Node tNULL;

    public Node gettNULL() {
        return tNULL;
    }

    private String inOrderHelper(Node node) {
        if (node != tNULL) {
            String str1 = inOrderHelper(node.getLeft());
            String str2 = node.getData() + " ";
            final String str3 = inOrderHelper(node.getRight());
            return str1 + str2 + str3;
        }
        return "";
    }


    private Node searchTreeHelper(final Node node, final int key) {
        if (node == tNULL || key == node.getData()) {
            return node;
        }

        if (key < node.getData()) {
            return searchTreeHelper(node.getLeft(), key);
        }

        return searchTreeHelper(node.getRight(), key);
    }

    private void fixDelete(Node x) {
        Node s;
        while (!x.equals(root) && !x.isColor()) {
            if (x.equals(x.getParent().getLeft())) {
                s = x.getParent().getRight();


                if (!s.getLeft().isColor() && !s.getRight().isColor()) {
                    s.setColor(true);
                    x = x.getParent();
                } else {


                    s.setColor(x.getParent().isColor());
                    x.getParent().setColor(false);
                    s.getRight().setColor(false);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                s = x.getParent().getLeft();
                if (s.isColor()) {
                    // case 3.1
                    s.setColor(false);
                    x.getParent().setColor(true);
                    rightRotate(x.getParent());
                    s = x.getParent().getLeft();
                }

                if (!s.getRight().isColor()  && !s.getRight().isColor()) {
                    s.setColor(true);
                    x = x.getParent();
                } else {


                    s.setColor( x.getParent().isColor());
                    x.getParent().setColor(false);
                    s.getLeft().setColor(false);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(false);
    }


    private void rbTransplant(final Node u, final Node v){
        if (u.getParent() == null) {
            root = v;
        } else if (u.equals(u.getParent().getLeft())){
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private void deleteNodeHelper(Node node, final int key) throws IllegalAccessException {
        Node z = tNULL;

        while (node != tNULL){
            if (node.getData() == key) {
                z = node;
            }

            if (node.getData() <= key) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        if (z == tNULL) {
            throw new IllegalAccessException("Couldn't find key in the tree");

        }
        Node x;
        Node y = z;
        boolean yOriginalColor = y.isColor();
        if (z.getLeft() == tNULL) {
            x = z.getRight();
            rbTransplant(z, z.getRight());
        } else if (z.getRight() == tNULL) {
            x = z.getLeft();
            rbTransplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.isColor();
            x = y.getRight();
            if (y.getParent().equals(z)) {
                x.setParent(y);
            } else {
                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }

            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.isColor());
        }
        if (!yOriginalColor){
            fixDelete(x);
        }
    }

    // fix the red-black tree
    private void fixInsert(Node k){
        Node u;
        while (k.getParent().isColor()) {
            if (k.getParent().equals(k.getParent().getParent().getRight())) {
                u = k.getParent().getParent().getLeft(); // uncle
                if (u.isColor()) {
                    u.setColor(false);
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    k = k.getParent().getParent();
                } else {
                    if (k.equals(k.getParent().getLeft())) {
                        k = k.getParent();
                        rightRotate(k);
                    }
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    leftRotate(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight(); // uncle

                if (u.isColor()) {
                    u.setColor(false);
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    k = k.getParent().getParent();
                } else {

                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    rightRotate(k.getParent().getParent());
                }
            }
            if (k.equals(root)) {
                break;
            }
        }
        root.setColor(false);
    }

    private void printHelper(final Node root, String indent, final boolean last) {
        if (root != tNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            final String sColor = root.isColor()?"RED":"BLACK";
            System.out.println(root.getData() + "(" + sColor + ")");
            printHelper(root.getLeft(), indent, false);
            printHelper(root.getRight(), indent, true);
        }
    }

    public RedBlackTree() {
        tNULL = new Node();
        tNULL.setColor(false);
        tNULL.setLeft(null);
        tNULL.setRight(null);
        root = tNULL;
    }


    public String inorder() {
        return inOrderHelper(this.root);
    }

    public Node searchTree(final int k) {
        return searchTreeHelper(this.root, k);
    }

    // find the node with the minimum key
    public Node minimum(Node node) {
        while (node.getLeft() != tNULL) {
            node = node.getLeft();
        }
        return node;
    }

    public void leftRotate(final Node x) {
        final Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != tNULL) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x.equals(x.getParent().getLeft())) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    public void rightRotate(final Node x) {
        final Node y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != tNULL) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x.equals(x.getParent().getRight())) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    public void insert(final int key) {
        final Node node = new Node();
        node.setParent(null);
        node.setData(key);
        node.setLeft(tNULL);
        node.setRight(tNULL);
        node.setColor(true); // new node must be red

        Node y = null;
        Node x = this.root;

        while (x != tNULL) {
            y = x;
            if (node.getData() < x.getData()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        node.setParent(y);
        if (y == null) {
            root = node;
        } else if (node.getData() < y.getData()) {
            y.setLeft(node);
        } else {
            y.setRight(node);
        }

        if (node.getParent() == null){
            node.setColor(false);
            return;
        }

        if (node.getParent().getParent() == null) {
            return;
        }

        fixInsert(node);
    }

    public Node getRoot(){
        return this.root;
    }

    public void deleteNode(final int data) throws IllegalAccessException {
        deleteNodeHelper(this.root, data);
    }

    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

}
