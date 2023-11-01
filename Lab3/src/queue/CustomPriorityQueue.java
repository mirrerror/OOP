package queue;

import java.util.*;

public class CustomPriorityQueue<E> {
    private final List<E> elements;
    private Comparator<E> comparator;

    public CustomPriorityQueue() {
        this.elements = new ArrayList<>();
    }

    public CustomPriorityQueue(Comparator<E> comparator) {
        this();
        this.comparator = comparator;
    }

    public void enqueue(E element) {
        elements.add(element);
        if (comparator != null) {
            elements.sort(comparator);
        }
        else {
            if (element instanceof Comparable) {
                elements.sort(null);
            }
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        return elements.remove(0);
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        return elements.get(0);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

}


