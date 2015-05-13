package srjmh;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BinaryHeap {
    //BinaryHeap bh;
    private static final int d = 2;
    private int heapSize;
    private int[] heap;

    public BinaryHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    public void makeEmpty() {
        heapSize = 0;
    }

    /**
     * Function to get index parent of i *
     */
    private int parent(int i) {
        return (i - 1) / d;
    }

    /**
     * Function to get index of k th child of i *
     */
    private int kthChild(int i, int k) {
        return d * i + k;
    }

    /**
     * Function to find least element *
     */
    public int findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow Exception");
        }
        return heap[0];
    }

    /**
     * Function to delete min element *
     */
    public int deleteMin() {

        int keyItem = heap[0];

        delete(0);

        return keyItem;

    }

    /**
     * Function to delete element at an index *
     */
    public int delete(int ind) {

//        if (isEmpty()) {
//            throw new NoSuchElementException("Underflow Exception");
//        }
        int keyItem = heap[ind];

        heap[ind] = heap[heapSize - 1];

        heapSize--;

        heapifyDown(ind);

        return keyItem;

    }

    /**
     * Function heapifyUp *
     */
    private void heapifyUp(int childInd) {

        int tmp = heap[childInd];

        while (childInd > 0 && tmp < heap[parent(childInd)]) {

            heap[childInd] = heap[parent(childInd)];

            childInd = parent(childInd);

        }

        heap[childInd] = tmp;

    }

    /**
     * Function heapifyDown *
     */
    private void heapifyDown(int ind) {

        int child;

        int tmp = heap[ind];

        while (kthChild(ind, 1) < heapSize) {

            child = minChild(ind);

            if (heap[child] < tmp) {
                heap[ind] = heap[child];
            } else {
                break;
            }

            ind = child;

        }

        heap[ind] = tmp;

    }

    /**
     * Function to get smallest child *
     */
    private int minChild(int ind) {

        int bestChild = kthChild(ind, 1);

        int k = 2;

        int pos = kthChild(ind, k);

        while ((k <= d) && (pos < heapSize)) {

            if (heap[pos] < heap[bestChild]) {
                bestChild = pos;
            }

            pos = kthChild(ind, k++);

        }

        return bestChild;

    }

    /**
     * Function to insert element
     */
    public void insert(int x) {
        if (isFull()) {
            throw new NoSuchElementException("Overflow Exception");
        }
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    public void insertall(int max) {
        Random r = new Random();
        for (int i = 0; i < heap.length - 1; i++) {
            insert(r.nextInt(max));
        }

    }

    /**
     * Function to print heap *
     */
    public void printHeap() {

        System.out.print("\nHeap = ");

        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }

        System.out.println();

    }
        
     public BinaryHeap setB(int size) {
        BinaryHeap bh1 = new BinaryHeap(size);
        bh1.insertall(100);
        return bh1;
     }
      

}
