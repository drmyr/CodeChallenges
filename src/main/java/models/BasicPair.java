package models;

public record BasicPair<L, R>(L left, R right) implements Pair<L, R> {
    public BasicPair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public L getLeft() {
        return this.left;
    }

    @Override
    public R getRight() {
        return this.right;
    }
}
