package models;

public class BinaryNode {
    final int id;
    final BinaryNode left;
    final BinaryNode right;

    public BinaryNode(final int id, final BinaryNode left, final BinaryNode right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public BinaryNode getRight() {
        return right;
    }
}
