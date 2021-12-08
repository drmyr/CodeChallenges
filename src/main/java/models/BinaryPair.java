package models;

public record BinaryPair<T>(T left, T right) implements Pair<T, T> {
    public BinaryPair(final T left, final T right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public T getLeft() {
        return this.left;
    }

    @Override
    public T getRight() {
        return this.right;
    }
}
