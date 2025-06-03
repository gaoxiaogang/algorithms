package org.gxg.sort;

import org.gxg.tools.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<E> implements Iterable<E> {
    private E[] Data;                    // store items at indices 1 to n
    private int N;                       // number of items on priority queue

    @SuppressWarnings("unchecked")
    public MaxPQ() {
        Data = (E[]) new Object[8];
        N = 0;
    }

    @SuppressWarnings("unchecked")
    public MaxPQ(E[] keys) {
        N = keys.length;
        Data = (E[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++)
            Data[i+1] = keys[i];
        for (int k = N/2; k >= 1; k--)
            sink(k);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public E max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return Data[1];
    }

    @SuppressWarnings("unchecked")
    // resize the underlying array to have the given capacity
    private void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = Data[i];
        }
        Data = temp;
    }

    public void insert(E x) {
        // double size of array if necessary
        if (N == Data.length - 1) resize(2 * Data.length);

        // add x, and percolate it up to maintain heap invariant
        Data[++N] = x;
        swim(N);
    }

    public E delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        E max = Data[1];
        exch(1, N--);
        sink(1);
        Data[N+1] = null;     // to avoid loitering and help with garbage collection
        if ((Data.length > 8) && (N + 1) == Data.length/4) resize(Data.length / 2);
        return max;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    @SuppressWarnings("unchecked")
    private boolean less(int i, int j) {
        return ((Comparable<E>) Data[i]).compareTo(Data[j]) < 0;
    }

    private void exch(int i, int j) {
        E swap = Data[i];
        Data[i] = Data[j];
        Data[j] = swap;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<E> iterator() {
        return new HeapIterator();
    }
    private class HeapIterator implements Iterator<E> {

        // create a new pq
        private MaxPQ<E> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new MaxPQ<E>();
            for (int i = 1; i <= N; i++)
                copy.insert(Data[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }



    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>();
        In in = new In("testData/stack_test.txt");
        while (!in.isEmpty()) {
            String item = in.readString();
            if (item.equals("-")) {
                System.out.print(pq.delMax() + " ");
            } else {
                pq.insert(item);
            }
        }
        System.out.println(" ");
        System.out.println("pq size: " + pq.size());
        System.out.println("Iterator test:");
        System.out.print("[");
        for (String str : pq) {
            System.out.print(str + ", ");
        }
        System.out.println("]");

        MaxPQ<Character> pqchar = new MaxPQ<>();
        in = new In("testData/stack_test.txt");
        while (!in.isEmpty()) {
            char item = in.readChar();
            pqchar.insert(item);
        }
        System.out.println(" ");
        System.out.println("Iterator test:");
        System.out.print("[");
        for (Character c : pqchar) {
            System.out.print(c + ", ");
        }
        System.out.println("]");

        while (!pqchar.isEmpty()) {
            char c = pqchar.delMax();
            System.out.println("delMax value: " + c);
            System.out.printf("pq size: %d\r\n", pqchar.size());
        }

        in = new In("testData/sort_words.txt");
        String[] words = in.readAllStrings();
        MaxPQ<String> pqStrs = new MaxPQ<>(words);
        System.out.println(" ");
        System.out.println("Iterator test:");
        System.out.print("[");
        for (String str : pqStrs) {
            System.out.print(str + ", ");
        }
        System.out.println("]");


        in.close();
    }
}
