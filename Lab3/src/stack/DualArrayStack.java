package stack;

import java.util.EmptyStackException;

public class DualArrayStack<E> implements Stack<E> {
    private Object[] frontArray;
    private Object[] backArray;
    private int frontSize;
    private int backSize;
    private static final int DEFAULT_CAPACITY = 10;

    public DualArrayStack() {
        frontArray = new Object[DEFAULT_CAPACITY];
        backArray = new Object[DEFAULT_CAPACITY];
        frontSize = 0;
        backSize = 0;
    }

    @Override
    public void push(E element) {
        if (frontSize + backSize == frontArray.length + backArray.length) {
            resizeArray();
        }
        if (frontSize > backSize) {
            backArray[backSize++] = element;
        } else {
            frontArray[frontSize++] = element;
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element;
        if (frontSize > 0) {
            element = (E) frontArray[--frontSize];
            frontArray[frontSize] = null;
        } else {
            element = (E) backArray[--backSize];
            backArray[backSize] = null;
        }
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        if (frontSize > 0) {
            return (E) frontArray[frontSize - 1];
        } else {
            return (E) backArray[backSize - 1];
        }
    }

    @Override
    public boolean isEmpty() {
        return frontSize + backSize == 0;
    }

    @Override
    public int size() {
        return frontSize + backSize;
    }

    private void resizeArray() {
        int totalSize = frontSize + backSize;
        int newCapacity = totalSize * 2;
        Object[] newArray = new Object[newCapacity];

        // Copy frontArray elements to the beginning of the new array
        System.arraycopy(frontArray, 0, newArray, 0, frontSize);
        // Copy backArray elements to the end of the new array
        System.arraycopy(backArray, 0, newArray, totalSize - backSize, backSize);

        frontArray = newArray;
        backArray = new Object[newCapacity];
        frontSize = totalSize;
        backSize = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (i < frontSize) {
                sb.append(frontArray[i]);
            } else {
                sb.append(backArray[i - frontSize]);
            }
            if (i < size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}


