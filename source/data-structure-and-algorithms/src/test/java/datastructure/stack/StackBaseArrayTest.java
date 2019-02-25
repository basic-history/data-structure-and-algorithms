package datastructure.stack;

import io.github.pleuvoir.datasructure.stack.StackBaseArray;

public class StackBaseArrayTest {

    public static void main(String[] args) {


        StackBaseArray stack = new StackBaseArray(3);
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
