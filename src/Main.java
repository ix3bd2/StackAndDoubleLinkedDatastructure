import linked.list.LinkedList;
import stack.OOSEStack;
import stack.Stack;

/**
 * This class performs test on the Stack and LinkedList classes.
 * It can show real world differences in operations. For example the add operation.
 * STUDENTS: Try running this class when both classes are fully implemented and all test succeed.
 * You can try to add other operations.
 */

public class Main {
    public static void main(String[] args) {
        //Create a stack and a Linked list
        OOSEStack stack;
        LinkedList linkedList;

        int numberOfElements = 100000; //Change this to change the number of elements in the stack and linked list

        //Add random elements to the stack and print the time it took to add them.
        long startTime;
        long endTime;
        long averageTime = 0;

        //Add random elements to the stack numberOfElements times. Repeat this task 100 times and print the average time. Reset the stack each iteration.
        System.out.println("Stack.push()...");
        for (int i = 0; i < 100; i++) {
            stack = new Stack();
            startTime = System.currentTimeMillis();
            for (int j = 0; j < numberOfElements; j++) {
                stack.push(Math.random());
            }
            endTime = System.currentTimeMillis();
            averageTime += endTime - startTime;
        }
        System.out.println("Average time to add " + numberOfElements + " elements to the stack: " + averageTime / 100 + " milliseconds\n");

        //Add random elements to the linked list using the addFirst method numberOfElements times. Repeat this task 100 times and print the average time. Reset the linked list each iteration.
        System.out.println("LinkedList.addFirst()...");
        averageTime = 0;
        for (int i = 0; i < 100; i++) {
            linkedList = new LinkedList();
            startTime = System.currentTimeMillis();
            for (int j = 0; j < numberOfElements; j++) {
                linkedList.addFirst(Math.random());
            }
            endTime = System.currentTimeMillis();
            averageTime += endTime - startTime;
        }
        System.out.println("Average time to add " + numberOfElements + " elements to the linked list using the addFirst method: " + averageTime / 100 + " milliseconds\n");

        //Add random elements to the linked list using the addLast method numberOfElements times. Repeat this task 100 times and print the average time. Reset the linked list each iteration.
        System.out.println("LinkedList.addLast()...");
        averageTime = 0;
        for (int i = 0; i < 100; i++) {
            linkedList = new LinkedList();
            startTime = System.currentTimeMillis();
            for (int j = 0; j < numberOfElements; j++) {
                linkedList.addLast(Math.random());
            }
            endTime = System.currentTimeMillis();
            averageTime += endTime - startTime;
        }
        System.out.println("Average time to add " + numberOfElements + " elements to the linked list using the addLast method: " + averageTime / 100 + " milliseconds\n");

        //Add random elements to the linked list using the add method numberOfElements times. The index is a random number between 0 and the linked lists size. Repeat this task 100 times and print the average time. Reset the linked list each iteration.
        System.out.println("LinkedList.add()...");
        averageTime = 0;
        System.out.println("\n\nYou weren't expecting this to be fast right? Try to figure out why.");
        System.out.println("Try changing the numberOfElements to 10.000 and 1.000.000.");
        for (int i = 0; i < 100; i++) {
            linkedList = new LinkedList();
            startTime = System.currentTimeMillis();
            for (int j = 0; j < numberOfElements; j++) {
                int randomIndex = (int) (Math.random() * linkedList.getSize());
                linkedList.add(randomIndex, Math.random());
            }
            endTime = System.currentTimeMillis();
            averageTime += endTime - startTime;
        }
        System.out.println("Average time to add " + numberOfElements + " elements to the linked list using the add method: " + averageTime / 100 + " milliseconds");

        //Try out the other operations yourself.

    }
}
