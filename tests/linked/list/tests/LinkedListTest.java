package linked.list.tests;

import linked.list.LinkedList;
import linked.list.LinkedListNode;
import linked.list.OOSELinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private final Double firstElement = 1.0;
    private final Double secondElement = 2.0;
    private final Double thirdElement = 3.0;
    private final Double fourthElement = 4.0;
    private final Double fifthElement = 5.0;
    private final Double sixthElement = 6.0;
    private final Double seventhElement = 7.0;
    private final Double eighthElement = 8.0;
    private OOSELinkedList list = null;

    private Field headField;
    private Field tailField;
    private Field sizeField;
    private LinkedListNode headNode;
    private LinkedListNode tailNode;
    private int size;


    @BeforeEach
    void before() throws NoSuchFieldException {
        list = new LinkedList();
        headField = LinkedList.class.getDeclaredField("head");
        headField.setAccessible(true);

        tailField = LinkedList.class.getDeclaredField("tail");
        tailField.setAccessible(true);

        sizeField = LinkedList.class.getDeclaredField("size");
        sizeField.setAccessible(true);

        headNode = null;
        tailNode = null;
        size=0;
    }

    @Test
    void getFirst_should_check_if_head_is_null(){
        assertDoesNotThrow(() -> list.getFirst());
    }
    @Test
    void getLast_should_check_if_tail_is_null(){
        assertDoesNotThrow(() -> list.getLast());
    }

    @Test
    void addFirst_should_set_firstElement_correctly() throws IllegalAccessException {
        LinkedList list = new LinkedList();

        list.addFirst(firstElement);
        headNode = (LinkedListNode) headField.get(list);

        assertEquals(firstElement, headNode.getValue());

        list.addFirst(secondElement);
        headNode = (LinkedListNode) headField.get(list);

        assertEquals(secondElement, headNode.getValue());
    }

    @Test
    void addFirst_should_set_lastElement_if_lastElement_is_null() throws IllegalAccessException {
        list.addFirst(firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());

        list.addFirst(secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());
    }

    @Test
    void addFirst_should_update_previous_and_next_nodes_elements_correctly() throws IllegalAccessException {
        list.addFirst(firstElement);
        list.addFirst(secondElement);
        list.addFirst(thirdElement);

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);


        assertEquals(thirdElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(firstElement, headNode.getNext().getNext().getValue());
        assertEquals(firstElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(thirdElement, tailNode.getPrevious().getPrevious().getValue());

    }

    @Test
    void addFirst_should_increase_size_when_adding_element() throws IllegalAccessException {

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);


        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);


        list.addFirst(secondElement);
        assertEquals(2, calculateSizeForward(list));
        assertEquals(2, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(2, size);

    }

    @Test
    void get_should_return_correct_element() {

        list.addFirst(thirdElement);
        list.addFirst(secondElement);
        list.addFirst(firstElement);

        assertEquals(firstElement, list.get(0));
        assertEquals(secondElement, list.get(1));
        assertEquals(thirdElement, list.get(2));
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void get_should_throw_exception_if_index_out_of_bounds() {

        list.addFirst(thirdElement);
        list.addFirst(secondElement);
        list.addFirst(firstElement);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
    }


    @Test
    void addLast_should_set_lastElement_correctly() throws IllegalAccessException {
        list.addLast(firstElement);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, tailNode.getValue());

        list.addLast(secondElement);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void addLast_should_set_firstElement_if_firstElement_is_null() throws IllegalAccessException {
        list.addLast(firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());

        list.addLast(secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void addLast_should_increase_size_when_adding_element() throws IllegalAccessException {

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addLast(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.addLast(secondElement);
        assertEquals(2, calculateSizeForward(list));
        assertEquals(2, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(2, size);
    }

    @Test
    void addLast_should_update_previous_and_next_nodes_elements_correctly() throws IllegalAccessException {
        list.addLast(firstElement);
        list.addLast(secondElement);
        list.addLast(thirdElement);

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);


        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(thirdElement, headNode.getNext().getNext().getValue());
        assertEquals(thirdElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getValue());
    }
    @Test
    void addLast_should_check_if_tail_null_before_trying_to_remove() throws IllegalAccessException {
        LinkedListNode firstNode = new LinkedListNode<>(firstElement);
        headNode= firstNode;
        size = 1;
        sizeField.set(list,size);
        headField.set(list,headNode);
        tailField.set(list,tailNode);

        assertDoesNotThrow(() -> list.addLast(secondElement));
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void add_should_throw_exception_if_index_out_of_bounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, firstElement));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, firstElement));

        assertDoesNotThrow(() -> list.add(0, firstElement));
    }

    @Test
    void add_should_fill_first_element_correctly_when_list_empty() throws IllegalAccessException {
        list.add(0, firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());
    }

    @Test
    void add_should_fill_last_element_correctly_when_index_equals_size() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void add_should_increase_size_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, thirdElement);
        list.add(1, secondElement);

        assertEquals(3, calculateSizeForward(list));
        assertEquals(3, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(3, size);
    }

    @Test
    void add_should_set_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, thirdElement);
        list.add(1, secondElement);

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(thirdElement, headNode.getNext().getNext().getValue());

        assertEquals(thirdElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getValue());

    }
    @Test
    void add_should_put_element_on_correct_position() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);
        list.add(2, thirdElement);
        list.add(3, fourthElement);
        list.add(4, fifthElement);
        list.add(1, sixthElement);
        list.add(3, seventhElement);
        list.add(2, eighthElement);

        //1-6-8-2-7-3-4-5

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertNull(headNode.getPrevious());
        assertEquals(firstElement, headNode.getValue());
        assertEquals(sixthElement, headNode.getNext().getValue());
        assertEquals(eighthElement, headNode.getNext().getNext().getValue());
        assertEquals(secondElement, headNode.getNext().getNext().getNext().getValue());
        assertEquals(seventhElement, headNode.getNext().getNext().getNext().getNext().getValue());
        assertEquals(thirdElement, headNode.getNext().getNext().getNext().getNext().getNext().getValue());
        assertEquals(fourthElement, headNode.getNext().getNext().getNext().getNext().getNext().getNext().getValue());
        assertEquals(fifthElement, headNode.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getValue());
        assertNull(headNode.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());

        assertNull(tailNode.getNext());
        assertEquals(fifthElement, tailNode.getValue());
        assertEquals(fourthElement, tailNode.getPrevious().getValue());
        assertEquals(thirdElement, tailNode.getPrevious().getPrevious().getValue());
        assertEquals(seventhElement, tailNode.getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(secondElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(eighthElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(sixthElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertNull( tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());

    }

    @Test
    void removeFirst_should_test_if_head_is_null_not_if_size_is_0() throws IllegalAccessException {
        size=3;
        sizeField.set(list,size);
        assertDoesNotThrow(()->list.removeFirst());
    }

    @Test
    void removeFirst_should_decrease_size() throws IllegalAccessException {
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeFirst();
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);
    }

    @Test
    void removeFirst_should_clear_both_head_and_tail_when_list_contains_only_one_element() throws IllegalAccessException {
        list.addFirst(firstElement);
        headNode = (LinkedListNode<Double>) headField.get(list);
        tailNode = (LinkedListNode<Double>) tailField.get(list);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeFirst();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        assertNull(headNode);
        assertNull(tailNode);
    }

    @Test
    void removeFirst_should_update_head_if_more_than_one_element_exits() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);
        list.removeFirst();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);
    }

    @Test
    void removeFirst_should_update_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);

        list.removeFirst();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertNull(headNode.getPrevious());
        assertNull(headNode.getNext());

        list.add(1, thirdElement);
        list.add(2, fourthElement);
        list.removeFirst();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(thirdElement, headNode.getValue());
        assertEquals(fourthElement, headNode.getNext().getValue());
        assertNull(headNode.getNext().getNext());

        assertEquals(fourthElement, tailNode.getValue());
        assertEquals(thirdElement, tailNode.getPrevious().getValue());
        assertNull(tailNode.getPrevious().getPrevious());

    }

    @Test
    void removeLast_should_test_if_head_is_null_not_if_size_is_0() throws IllegalAccessException {
        size=3;
        sizeField.set(list,size);
        assertDoesNotThrow(()->list.removeLast());
    }

    @Test
    void removeLast_should_decrease_size() throws IllegalAccessException {
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeLast();
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);
    }

    @Test
    void removeLast_should_clear_both_head_and_tail_when_list_contains_only_one_element() throws IllegalAccessException {
        list.addFirst(firstElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(firstElement, tailNode.getValue());
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.removeLast();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertNull(headNode);
        assertNull(tailNode);

    }

    @Test
    void removeLast_should_should_update_tail_if_more_than_one_element_exits() throws IllegalAccessException {
        list.addFirst(firstElement);
        list.addLast(secondElement);
        list.addLast(thirdElement);

        list.removeLast();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, tailNode.getValue());
    }

    @Test
    void removeLast_should_update_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);

        list.removeLast();

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertNull(tailNode.getNext());
        assertNull(tailNode.getPrevious());

        list.add(1, thirdElement);
        list.add(2, fourthElement);
        list.removeLast();
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(thirdElement, headNode.getNext().getValue());
        assertNull(headNode.getNext().getNext());

        assertEquals(thirdElement, tailNode.getValue());
        assertEquals(firstElement, tailNode.getPrevious().getValue());
        assertNull(tailNode.getPrevious().getPrevious());
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void remove_should_throw_exception_if_index_out_of_bounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertDoesNotThrow(() -> {
            list.add(0, firstElement);
            list.remove(0);
        });
    }


    @Test
    void remove_should_remove_head_and_tail_when_list_will_be_empty() throws IllegalAccessException {
        list.add(0, firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.remove(0);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        assertNull(headNode);
        assertNull(tailNode);
    }


    @Test
    void remove_should_set_previous_and_next_nodes_correctly() throws IllegalAccessException {
        list.add(0, firstElement);
        list.add(1, secondElement);
        list.add(2, thirdElement);
        list.add(3, fourthElement);

        list.remove(2);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(3, calculateSizeForward(list));
        assertEquals(3, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(3, size);

        assertEquals(firstElement, headNode.getValue());
        assertEquals(secondElement, headNode.getNext().getValue());
        assertEquals(fourthElement, headNode.getNext().getNext().getValue());
        assertNull(headNode.getNext().getNext().getNext());

        assertEquals(fourthElement, tailNode.getValue());
        assertEquals(secondElement, tailNode.getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getValue());
        assertNull(tailNode.getPrevious().getPrevious().getPrevious());

    }

    @Test
    void remove_should_decrease_size() throws IllegalAccessException {
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);

        list.addFirst(firstElement);
        assertEquals(1, calculateSizeForward(list));
        assertEquals(1, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(1, size);

        list.remove(0);
        assertEquals(0, calculateSizeForward(list));
        assertEquals(0, calculateSizeBackwards(list));
        size = (int) sizeField.get(list);
        assertEquals(0, size);    }

    @Test
    void remove_should_remove_correct_element() throws IllegalAccessException {
        LinkedListNode firstNode = new LinkedListNode<>(firstElement);
        LinkedListNode secondNode = new LinkedListNode<>(secondElement);
        LinkedListNode thirdNode = new LinkedListNode<>(thirdElement);
        LinkedListNode fourthNode = new LinkedListNode<>(fourthElement);
        LinkedListNode fifthNode = new LinkedListNode<>(fifthElement);
        LinkedListNode sixthNode = new LinkedListNode<>(sixthElement);
        LinkedListNode seventhNode = new LinkedListNode<>(seventhElement);
        LinkedListNode eightNode = new LinkedListNode<>(eighthElement);
        //1-6-8-2-7-3-4-5

        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        headNode= firstNode;
        headNode.setNext(sixthNode);
        headNode.getNext().setNext(eightNode);
        headNode.getNext().getNext().setNext(secondNode);
        headNode.getNext().getNext().getNext().setNext(seventhNode);
        headNode.getNext().getNext().getNext().getNext().setNext(thirdNode);
        headNode.getNext().getNext().getNext().getNext().getNext().setNext(fourthNode);
        headNode.getNext().getNext().getNext().getNext().getNext().getNext().setNext(fifthNode);

        tailNode = fifthNode;
        tailNode.setPrevious(fourthNode);
        tailNode.getPrevious().setPrevious(thirdNode);
        tailNode.getPrevious().getPrevious().setPrevious(seventhNode);
        tailNode.getPrevious().getPrevious().getPrevious().setPrevious(secondNode);
        tailNode.getPrevious().getPrevious().getPrevious().getPrevious().setPrevious(eightNode);
        tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().setPrevious(sixthNode);
        tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().setPrevious(firstNode);
        assertEquals(1.0, headNode.getValue());
        assertEquals(6.0, headNode.getNext().getValue());
        assertEquals(8.0, headNode.getNext().getNext().getValue());
        assertEquals(2.0, headNode.getNext().getNext().getNext().getValue());
        assertEquals(7.0, headNode.getNext().getNext().getNext().getNext().getValue());
        assertEquals(3.0, headNode.getNext().getNext().getNext().getNext().getNext().getValue());
        assertEquals(4.0, headNode.getNext().getNext().getNext().getNext().getNext().getNext().getValue());
        assertEquals(5.0, headNode.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getValue());
        assertNull(headNode.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());

        assertNull(tailNode.getNext());
        assertEquals(5.0, tailNode.getValue());
        assertEquals(4.0, tailNode.getPrevious().getValue());
        assertEquals(3.0, tailNode.getPrevious().getPrevious().getValue());
        assertEquals(7.0, tailNode.getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(2.0, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(8.0, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(6.0, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(1.0, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertNull( tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());

        size = 8;
        sizeField.set(list,size);
        headField.set(list,headNode);
        tailField.set(list,tailNode);

        //start of test

        //1-6-8-2-7-3-4-5
        list.remove(2);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);


        //1-6-2-7-3-4-5
        assertEquals(firstElement, headNode.getValue());
        assertEquals(sixthElement, headNode.getNext().getValue());
        assertEquals(secondElement, headNode.getNext().getNext().getValue());
        assertEquals(seventhElement, headNode.getNext().getNext().getNext().getValue());
        assertEquals(thirdElement, headNode.getNext().getNext().getNext().getNext().getValue());
        assertEquals(fourthElement, headNode.getNext().getNext().getNext().getNext().getNext().getValue());
        assertEquals(fifthElement, headNode.getNext().getNext().getNext().getNext().getNext().getNext().getValue());

        assertEquals(fifthElement, tailNode.getValue());
        assertEquals(fourthElement, tailNode.getPrevious().getValue());
        assertEquals(thirdElement, tailNode.getPrevious().getPrevious().getValue());
        assertEquals(seventhElement, tailNode.getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(secondElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(sixthElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());

        list.remove(4);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        //1-6-2-7-4-5
        assertEquals(firstElement, headNode.getValue());
        assertEquals(sixthElement, headNode.getNext().getValue());
        assertEquals(secondElement, headNode.getNext().getNext().getValue());
        assertEquals(seventhElement, headNode.getNext().getNext().getNext().getValue());
        assertEquals(fourthElement, headNode.getNext().getNext().getNext().getNext().getValue());
        assertEquals(fifthElement, headNode.getNext().getNext().getNext().getNext().getNext().getValue());

        assertEquals(fifthElement, tailNode.getValue());
        assertEquals(fourthElement, tailNode.getPrevious().getValue());
        assertEquals(seventhElement, tailNode.getPrevious().getPrevious().getValue());
        assertEquals(secondElement, tailNode.getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(sixthElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getValue());
        assertEquals(firstElement, tailNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getValue());

    }

    @Test
    void set_should_replace_an_existing_value() throws IllegalAccessException {
        list.add(0, firstElement);

        list.set(0, secondElement);
        headNode = (LinkedListNode) headField.get(list);
        tailNode = (LinkedListNode) tailField.get(list);

        assertEquals(secondElement, headNode.getValue());
        assertNotEquals(firstElement, headNode.getValue());
    }

    //@Disabled
    //TODO: Turn off if not using exception
    @Test
    void set_should_throw_index_out_of_bounds_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, firstElement));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, firstElement));
        assertDoesNotThrow(() -> {
            list.add(0, firstElement);
            list.set(0, secondElement);
        });
    }

    @Test
    void getSize_should_return_correct_size() throws IllegalAccessException {
        assertEquals(0,list.getSize());
        assertEquals(0,calculateSizeForward(list));
        assertEquals(0,calculateSizeBackwards(list));

        list.add(0,firstElement);
        assertEquals(1,list.getSize());
        assertEquals(1,calculateSizeForward(list));
        assertEquals(1,calculateSizeBackwards(list));

        list.add(1,secondElement);
        assertEquals(2,list.getSize());
        assertEquals(2,calculateSizeForward(list));
        assertEquals(2,calculateSizeBackwards(list));
    }

    @Test
    void sort_should_return_null_when_argument_null(){
        OOSELinkedList argument = null;
        OOSELinkedList result = LinkedList.sort(argument);
        assertNull(result);
    }

    @Test
    void sort_should_order_elements_correctly(){
        OOSELinkedList list = new LinkedList();
        list.add(0,eighthElement);
        list.add(1,fifthElement);
        list.add(2,fourthElement);
        list.add(3,firstElement);
        list.add(4,seventhElement);
        list.add(5,thirdElement);
        list.add(6,sixthElement);
        list.add(7,secondElement);
        //8-5-4-1-7-3-6-2
        OOSELinkedList result = LinkedList.sort(list);
        assertEquals(firstElement,result.get(0));
        assertEquals(secondElement,result.get(1));
        assertEquals(thirdElement,result.get(2));
        assertEquals(fourthElement,result.get(3));
        assertEquals(fifthElement,result.get(4));
        assertEquals(sixthElement,result.get(5));
        assertEquals(seventhElement,result.get(6));
        assertEquals(eighthElement,result.get(7));


    }


    /*** UTILITY METHODS   ***/
    private int calculateSizeForward(OOSELinkedList list) throws IllegalAccessException {
        int counter = 0;
        if (list != null) {

            LinkedListNode headNode = (LinkedListNode) headField.get(list);
            if (headNode == null)
                return counter;
            counter++;

            while (headNode.hasNext()) {
                counter++;
                headNode = headNode.getNext();
            }
        }
        return counter;
    }

    private int calculateSizeBackwards(OOSELinkedList list) throws IllegalAccessException {
        int counter = 0;
        if (list != null) {
            LinkedListNode tailNode = (LinkedListNode) tailField.get(list);
            if (tailNode == null)
                return counter;
            counter++;


            while (tailNode.hasPrevious()) {
                counter++;
                tailNode = tailNode.getPrevious();
            }
        }
        return counter;
    }


}