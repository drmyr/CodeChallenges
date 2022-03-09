package models;

public class BinaryNode {
    final int id;
    final int val;
    final BinaryNode left;
    final BinaryNode right;

    public BinaryNode(final int id, final int val, final BinaryNode left, final BinaryNode right) {
        this.id = id;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public int getVal() {
        return val;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public BinaryNode getRight() {
        return right;
    }
}
