package heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private final List<Integer> heap;

    /**
     * This will initialize our heap with default size.
     *
     * parent:      arr[(i - 1) / 2]
     * left child:  arr[(2 * i) + 1]
     * right child: arr[(2 * i) + 2]
     */
    public MaxHeap(){
        this.heap = new ArrayList<>();
    }

    public int poll() {
        return siftDown();
    }

    public void offer(final int value) {
        this.heap.add(value);
        siftUp();
    }

    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    private void siftUp() {
        int i = this.heap.size() - 1;
        while(i > 0 && this.heap.get((i - 1) / 2) < this.heap.get(i)) {
            final int temp = this.heap.get(i);
            this.heap.set(i, this.heap.get((i - 1) / 2));
            this.heap.set((i - 1) / 2, temp);
            i = (i - 1) / 2;
        }
    }

    private int siftDown() {
        final int result = this.heap.get(0);
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);

        int i = 0, leftChildIdx;
        while((leftChildIdx = (2 * i) + 1) < this.heap.size()) {
            int largestChildIdx = leftChildIdx;
            final int rightChildIdx = (2 * i) + 2;
            if(rightChildIdx < this.heap.size()) {
                largestChildIdx = this.heap.get(leftChildIdx) > this.heap.get(rightChildIdx) ? leftChildIdx : rightChildIdx;
            }
            if(this.heap.get(largestChildIdx) > this.heap.get(i)) {
                final int temp = this.heap.get(i);
                this.heap.set(i, this.heap.get(largestChildIdx));
                this.heap.set(largestChildIdx, temp);
                i = largestChildIdx;
            } else {
                break;
            }
        }

        return result;
    }

}
