package task2;

public class Node {
    private int data;
    private Node left;
    private Node right;
    private Node parent;
    private boolean color;

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getData() {
        return data;
    }

/*    public Node getGrandparent (){
        if ((getParent() != null)){
            return getParent().getParent();
        } else{
            return null;
        }
    }

    public Node getUncle(){
        Node grandparent = getGrandparent();

        if (grandparent == null){
            return null; // No grandparent means no uncle
        }
        if (getParent() == grandparent.getLeft()){
            return grandparent.getRight();
        } else {
            return grandparent.getLeft();
        }
    }

    public Node getSibling(){
        if (this == getParent().getLeft())
            return getParent().getRight();
        else
            return getParent().getLeft();
    }*/



}