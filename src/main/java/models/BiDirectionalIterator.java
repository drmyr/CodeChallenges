package models;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public abstract sealed class BiDirectionalIterator permits BiDirectionalIterator.LeftToRightIterator, BiDirectionalIterator.RightToLeftIterator {

    public abstract AtomicReference<Integer> getStart();

    public abstract Supplier<Boolean> getStopCondition();

    public abstract Runnable getStep();

    public static final class LeftToRightIterator extends BiDirectionalIterator {

        private final AtomicReference<Integer> start;
        private final Supplier<Boolean> stopCondition;
        private final Runnable step;

        public LeftToRightIterator(final int length) {
            this.start = new AtomicReference<>(0);
            this.stopCondition = () -> start.get() < length;
            this.step = () -> start.set(start.get() + 1);
        }

        @Override
        public AtomicReference<Integer> getStart() {
            return start;
        }

        @Override
        public Supplier<Boolean> getStopCondition() {
            return stopCondition;
        }

        @Override
        public Runnable getStep() {
            return step;
        }
    }

    public static final class RightToLeftIterator extends BiDirectionalIterator {
        private final AtomicReference<Integer> start;
        private final Supplier<Boolean> stopCondition;
        private final Runnable step;

        public RightToLeftIterator(final int length) {
            this.start = new AtomicReference<>(length - 1);
            this.stopCondition = () -> start.get() >= 0;
            this.step = () -> start.set(start.get() - 1);
        }

        @Override
        public AtomicReference<Integer> getStart() {
            return start;
        }

        @Override
        public Supplier<Boolean> getStopCondition() {
            return stopCondition;
        }

        @Override
        public Runnable getStep() {
            return step;
        }
    }

}
