package task2;

public class RedBlackTree {
    private Node root;
    private Node TNULL;

    public Node getTNULL() {
        return TNULL;
    }

    private String inOrderHelper(Node node) {
        if (node != TNULL) {
            String str1 = inOrderHelper(node.getLeft());
            String str2 = node.getData() + " ";
            String str3 = inOrderHelper(node.getRight());
            return str1 + str2 + str3;
        }
        return "";
    }


    private Node searchTreeHelper(Node node, int key) {
        if (node == TNULL || key == node.getData()) {
            return node;
        }

        if (key < node.getData()) {
            return searchTreeHelper(node.getLeft(), key);
        }

        return searchTreeHelper(node.getRight(), key);
    }

    private void fixDelete(Node x) {
        Node s;
        while (x != root && !x.getColor()) {
            if (x == x.getParent().getLeft()) {
                s = x.getParent().getRight();


                if (!s.getLeft().getColor() && !s.getRight().getColor()) {
                    s.setColor(true);
                    x = x.getParent();
                } else {


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
                    s.setColor(true);
                    x = x.getParent();
                } else {


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
                    u.setColor(false);
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getLeft()) {
                        k = k.getParent();
                        rightRotate(k);
                    }
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    leftRotate(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight(); // uncle

                if (u.getColor()) {
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
            if (k == root) {
                break;
            }
        }
        root.setColor(false);
    }

    private void printHelper(Node root, String indent, boolean last) {
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


    public String inorder() {
        return inOrderHelper(this.root);
    }

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

    public void insert(int key) {
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

    public void deleteNode(int data) throws IllegalAccessException {
        deleteNodeHelper(this.root, data);
    }

    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

}
