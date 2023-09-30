package stack;

import java.util.EmptyStackException;

/**
 * This class is supposed to represent a stack
 * Finish the methods below
 * The tests can guide you in the right direction but are not absolute infallible
 */
public class Stack implements OOSEStack {
    /***
     * Leave these two variables
     */
    private StackNode<Double> top;
    int size = 0;

    /**
     * @param d Pushes element to the top of the stack.
     *          If an element already exists on the top. Update the reference (Stacknode.next)
     *          Also increases size
     */
    @Override
    public void push(Double d) {
        StackNode<Double> newNode = new StackNode<>(d);

        if (size != 0) {
            newNode.setNext(top);
        }

        top = newNode;
        size++;
    }

    /**
     * @return The element on top of the stack but does NOT remove it.
     * If stack is empty throw an EmptyStackException
     * Also checks if top is null. If it is, throw an exception.
     */
    @Override
    public Double peek() {
        if (top == null) {
            throw new EmptyStackException();
        } else return top.getValue();
    }

    /**
     * @return The element on top of the stack and removes it.
     * If stack is empty throw an EmptyStackException.
     * Also checks if top is null. If it is, throw an exception.
     */
    @Override
    public Double pop() {
        if (top == null) {
            throw new EmptyStackException();
        } else {
            double oldTopValue = top.getValue();
            top = top.getNext();
            size--;
            return oldTopValue;
        }
    }

    /**
     * @return the size of the stack
     */
    @Override
    public Integer getSize() {
        return size;
    }

    /**
     * Sorts the stack in ascending order.
     * You can use any existing sorting algorithm like Bubble sort, Merge sort, quick sort etc.
     * Or you can create your own...
     * You cannot use build in sort methods like Collection.sort() or Arrays.sort()
     * The sorting algorithm should be a different from the one used for the LinkedList.
     */
    public static OOSEStack sort(OOSEStack stack) {
        if (stack == null) {
            return null;
        }

        Stack tmpStack = new Stack();
        while (stack.getSize() > 0) {
            double currentElement = stack.pop();

            while (tmpStack.getSize() > 0 && tmpStack.peek() < currentElement) {
                stack.push(tmpStack.pop());
            }

            tmpStack.push(currentElement);
        }

        return tmpStack;
    }

}
