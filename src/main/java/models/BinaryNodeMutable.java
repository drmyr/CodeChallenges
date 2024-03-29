package models;

public class BinaryNodeMutable {
    final int id;
    int value;
    BinaryNodeMutable left;
    BinaryNodeMutable right;

    public BinaryNodeMutable(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public BinaryNodeMutable getLeft() {
        return left;
    }

    public void setLeft(final BinaryNodeMutable left) {
        this.left = left;
    }

    public BinaryNodeMutable getRight() {
        return right;
    }

    public void setRight(final BinaryNodeMutable right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }
}
