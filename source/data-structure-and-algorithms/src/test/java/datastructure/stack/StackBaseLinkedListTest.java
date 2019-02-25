package datastructure.stack;

import io.github.pleuvoir.datasructure.stack.StackBaseArray;
import io.github.pleuvoir.datasructure.stack.StackBaseLinkedList;

public class StackBaseLinkedListTest {

    public static void main(String[] args) {


        StackBaseLinkedList stack = new StackBaseLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);


        stack.printAll();

        Object pop = stack.pop();
        System.out.println(pop);

        stack.printAll();

        stack.push(4);
        stack.printAll();
    }
}
