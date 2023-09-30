package linked.list;

public class LinkedList implements OOSELinkedList {

    private LinkedListNode<Double> head;
    private LinkedListNode<Double> tail;
    private Integer size = 0;

    /**
     * @return The value of the head of the linked list.
     */
    @Override
    public Double getFirst() {
        return head != null ? head.getValue() : null;
    }

    /**
     * @return The value of the tail of the linked list.
     */
    @Override
    public Double getLast() {
        return tail != null ? tail.getValue() : null;
    }

    /**
     * @return The size of the linked list.
     */
    @Override
    public Integer getSize() {
        return size;
    }

    /**
     * @param index Value of the index you want to return.
     * @return An element of the LinkedList based on an index.
     */
    @Override
    public Double get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not present.");
        }

        LinkedListNode<Double> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getValue();
    }

    /**
     * dds an element to head of the linked list
     *
     * @param d Node you want to add.
     */
    @Override
    public void addFirst(Double d) {
        LinkedListNode<Double> newNode = new LinkedListNode<>(d);

        if (head != null) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            head = newNode;
            tail = newNode;
        }

        size++;
    }

    /**
     * Adds an element to tail of the linked list
     *
     * @param d Node you want to add.
     */
    @Override
    public void addLast(Double d) {
        LinkedListNode<Double> newNode = new LinkedListNode<>(d);

        if (tail != null) {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
        } else {
            head = newNode;
        }

        tail = newNode;
        size++;
    }

    /**
     * Adds an element to the linked list based on an index.
     *
     * @param index Where you want to add the index in the list.
     * @param d     Node you want to add.
     */
    @Override
    public void add(int index, Double d) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not present.");
        }

        if (head == null) {
            addFirst(d);
        } else if (index == size) {
            addLast(d);
        } else {
            LinkedListNode<Double> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }

            LinkedListNode<Double> previousNode = currentNode.getPrevious();
            LinkedListNode<Double> newNode = new LinkedListNode<>(d);

            currentNode.setPrevious(newNode);
            newNode.setNext(currentNode);
            newNode.setPrevious(previousNode);
            previousNode.setNext(newNode);

            size++;
        }
    }

    /**
     * Removes the head of the linked list.
     */
    @Override
    public void removeFirst() {
        if (head != null) {
            if (!head.hasNext()) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setPrevious(null);
            }

            size--;
        }
    }

    /**
     * Removes the tail of the linked list.
     */
    @Override
    public void removeLast() {
        if (tail != null) {
            if (!tail.hasPrevious()) {
                head = null;
                tail = null;
            } else {
                tail = tail.getPrevious();
                tail.setNext(null);
            }

            size--;
        }
    }

    /**
     * Removes an element based on an index.
     *
     * @param index The index you want to remove.
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not present.");
        }

        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            LinkedListNode<Double> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }

            currentNode.getPrevious().setNext(currentNode.getNext());
            currentNode.getNext().setPrevious(currentNode.getPrevious());
            size--;
        }

    }


    /**
     * Change the value of an index in the list.
     *
     * @param index The index you want to change.
     * @param value New value of the index.
     */
    @Override
    public void set(int index, Double value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not present.");
        }

        LinkedListNode<Double> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        currentNode.setValue(value);
    }

    /**
     * Implements the merge sort algorithm to efficiently sort a doubleLinked linked list.
     * The sort function recursively divides the list into smaller sublists, sorts them, and then merges them back together.
     *
     * @param list the OOSELinkedList you want to sort.
     * @return A linked list sorted in ascending order.
     */
    public static OOSELinkedList sort(OOSELinkedList list) {
        if (list == null || list.getSize() <= 1) {
            return list;
        }

        // Split the list into two halves
        int mid = list.getSize() / 2;
        OOSELinkedList leftHalf = new LinkedList();
        OOSELinkedList rightHalf = new LinkedList();

        // add into the left and right
        for (int i = 0; i < mid; i++) {
            leftHalf.addLast(list.get(i));
        }

        for (int i = mid; i < list.getSize(); i++) {
            rightHalf.addLast(list.get(i));
        }

        /* There is magic happening here, to explain it i'll use the values given in the test: [8-5-4-1-7-3-6-2]
         * 1ste leftHalf(line 245) runs sort() again, before running the 2nd leftHalf the leftList will look like this [8-5-4-1]. When leftHalf on line 245 runs it will split it again which will make it [8-5] and run sort() for 3rd time.
         * When sort is ran it will return the list since the size is = 1.
         * Now our third leftHalf is finally ready with the following values [8-5]. now the same is done by rightHalf which will be [4,1]
         * Now the code moves to the return merge().
         * merge() will return the following values [1, 4, 5, 8] which will be returned in 1st leftHalf. now we move to rightHalf which will return [2, 3, 6, 7].
         * How did this happen? 3de sort() returned -> [8,1] to 2nd leftHalf -> merge() round [1, 4, 5, 8] to 1st left half.(same goes for rightHalf)
         * After that we merge them again to finish the sort
         */
        leftHalf = sort(leftHalf);
        rightHalf = sort(rightHalf);

        // Merge the sorted halves back together
        return merge(leftHalf, rightHalf);
    }

    /**
     * Implements the merge sort algorithm to efficiently sort a doubleLinked linked list.
     * The merge function combines two sorted lists into a single sorted list.
     *
     * @param left  The left OOSELinkedList list you want to compare.
     * @param right The right OOSELinkedList list you want to compare.
     * @return A new linked list containing all elements from 'left' and 'right', sorted in ascending order.
     */
    private static OOSELinkedList merge(OOSELinkedList left, OOSELinkedList right) {
        OOSELinkedList mergedList = new LinkedList();

        while (left.getSize() > 0 && right.getSize() > 0) {
            // Compare the first elements of left and right
            if (left.getFirst() <= right.getFirst()) {
                // Add the smaller element to mergedList and remove it from left
                mergedList.addLast(left.getFirst());
                left.removeFirst();
            } else {
                // Add the smaller element to mergedList and remove it from right
                mergedList.addLast(right.getFirst());
                right.removeFirst();
            }
        }

        // Append any remaining elements from left and right, if any
        while (left.getSize() > 0) {
            mergedList.addLast(left.getFirst());
            left.removeFirst();
        }

        while (right.getSize() > 0) {
            mergedList.addLast(right.getFirst());
            right.removeFirst();
        }

        return mergedList;
    }

}


