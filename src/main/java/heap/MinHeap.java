package heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private final List<Integer> heap;

    /**
     * Find left child:  arr[(2*i) + 1]
     * Find right child: arr[(2*i) + 2]
     * Find parent:      arr[(i-1) / 2]
     *
     *
     */
    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public void offer(final int next) {
        heap.add(next);
        siftUp();
    }

    public int poll() {
        return siftDown();
    }

    public int peek() {
        return this.heap.get(0);
    }

    public int getSize() {
        return this.heap.size();
    }

    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    private int siftDown() {
        final int head = this.heap.get(0);
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);

        int i = 0;
        while(((2 * i) + 1) < this.heap.size()) {
            final int leftChildIdx = ((2 * i) + 1);
            int smallestChildIdx = leftChildIdx;
            final int rightChildIdx = ((2 * i) + 2);
            if(rightChildIdx < this.heap.size()) {
                smallestChildIdx = this.heap.get(leftChildIdx) < this.heap.get(rightChildIdx) ? leftChildIdx : rightChildIdx;
            }
            if(this.heap.get(i) > this.heap.get(smallestChildIdx)) {
                final int temp = this.heap.get(i);
                this.heap.set(i, this.heap.get(smallestChildIdx));
                this.heap.set(smallestChildIdx, temp);
                i = smallestChildIdx;
            } else {
                break;
            }
        }
        return head;
    }


    private void siftUp() {
        int i = this.heap.size() - 1;
        while(i > 0 && this.heap.get(i) < this.heap.get((i - 1) / 2)) {
            final int temp = this.heap.get(i);
            this.heap.set(i, this.heap.get((i - 1) / 2));
            this.heap.set((i - 1) / 2, temp);
            i = (i - 1) / 2;
        }
    }

}
