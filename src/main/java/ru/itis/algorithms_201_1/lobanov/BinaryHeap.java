package ru.itis.algorithms_201_1.lobanov;

import java.util.Arrays;

public class BinaryHeap<T> {
    private final int INITIAL_CAPACITY = 10;
    private final double MULTIPLIER = 1.5;
    private Object[] heap;
    private int size = 0;
    private int countOfIterations = 0;

    public BinaryHeap() {
        this.heap = new Object[INITIAL_CAPACITY];
    }

    public BinaryHeap(int initialCapacity) {
        this.heap = new Object[initialCapacity];
    }

    private void increaseCapacity() {
        int capacity = (int) (heap.length * MULTIPLIER);
        heap = Arrays.copyOf(heap, capacity);
    }

    public boolean add(T element) {
        if (element == null) throw new NullPointerException();

        if (size == 0) {
            heap[0] = element;
            size++;
        } else {
            if (size >= heap.length) increaseCapacity();

            heap[size] = element;
            size++;

            int i = size - 1;
            swapUp(i, element);
        }
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        return size == 0 ? null : (T) heap[0];
    }

    public boolean remove(Object element) {
        int index = indexOf(element);
        if (index != -1) {
            removeByIndex(index);
            return true;
        } else return false;
    }

    public int getCountOfIterations() {
        return countOfIterations;
    }

    private T removeByIndex(int index) {
        int s = --size;
        if (s == index) heap[index] = null;
        else {
            T moved = (T) heap[s];
            heap[s] = null;
            swapDown(index, moved);
            if (heap[index] == moved) {
                swapUp(index, moved);
                if (heap[index] != moved)
                    return moved;
            }
        }
        return null;
    }

    private void swapUp(int i, T elem) {
        Comparable<? super T> element = (Comparable<? super T>) elem;
        while (i > 0) {
            countOfIterations++;
            int parentIndex = (i - 1) / 2;
            Object parentElem = heap[parentIndex];
            if (element.compareTo((T) parentElem) >= 0) {
                break;
            }
            heap[i] = parentElem;
            i = parentIndex;
        }
        heap[i] = element;
    }

    private void swapDown(int i, T elem) {
        Comparable<? super T> element = (Comparable<? super T>) elem;
        int half = size / 2;
        while (i < half) {
            countOfIterations++;
            int child = 2 * i + 1;
            Object c = heap[child];
            int right = child + 1;
            if (right < size &&
                    ((Comparable<? super T>) c).compareTo((T) heap[right]) > 0) {
                child = right;
                c = heap[child];
            }
            if (element.compareTo((T) c) <= 0)
                break;
            heap[i] = c;
            i = child;
        }
        heap[i] = element;
    }

    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                countOfIterations++;
                if (o.equals(heap[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BinaryHeap{");
        for (int i = 0; i < size; i++) {
            Object element = heap[i];
            sb.append(element);
            if (i != size - 1) sb.append(", ");
        }
        return sb.append('}').toString();
    }
}
