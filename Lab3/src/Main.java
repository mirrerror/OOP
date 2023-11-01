import queue.ArrayQueue;
import queue.CustomPriorityQueue;
import queue.LinkedListQueue;
import stack.ArrayStack;
import stack.DualArrayStack;
import stack.LinkedListStack;

public class Main {
    public static void main(String[] args) {
        int min = 0, max = 10;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for(int i = min; i <= max; i++) arrayQueue.enqueue(i);
        System.out.println("Array Queue: " + arrayQueue);

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        for(int i = min; i <= max; i++) linkedListQueue.enqueue(i);
        System.out.println("Linked List Queue: " + linkedListQueue);

        CustomPriorityQueue<Integer> priorityQueueWrapper = new CustomPriorityQueue<>();
        for(int i = min; i <= max; i++) priorityQueueWrapper.enqueue(i);
        System.out.println("Custom Priority Queue: " + priorityQueueWrapper);

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for(int i = min; i <= max; i++) arrayStack.push(i);
        System.out.println("Array Stack: " + arrayStack);

        DualArrayStack<Integer> dualArrayStack = new DualArrayStack<>();
        for(int i = min; i <= max; i++) dualArrayStack.push(i);
        System.out.println("Dual Array Stack: " + dualArrayStack);

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        for(int i = min; i <= max; i++) linkedListStack.push(i);
        System.out.println("Linked List Stack: " + linkedListStack);
    }
}