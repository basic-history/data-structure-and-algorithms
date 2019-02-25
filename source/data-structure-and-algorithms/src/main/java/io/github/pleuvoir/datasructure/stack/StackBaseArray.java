package io.github.pleuvoir.datasructure.stack;

/**
 * 基于数组实现的顺序栈
 */
public class StackBaseArray {

    private static final int DEFAULT_CAPACITY = 10;

    private int capacity = DEFAULT_CAPACITY;

    private int count = 0;

    private Object[] elements;


    public StackBaseArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.elements = new Object[capacity];
    }

    public StackBaseArray(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    /**
     * 入栈
     */
    public void push(Object o) {
        if (count == capacity) {
            throw new IllegalStateException("栈已满，capacity=" + capacity);
        }
        elements[count++] = o;
    }

    /**
     * 出栈
     */
    public Object pop() {
        if (count == 0) return null;
        Object element = elements[count - 1];
        elements[count - 1] = null;
        count--;
        return element;
    }
    
    public void printAll(){
        StringBuffer sb = new StringBuffer();
        for (Object element : this.elements) {
            sb.append(element).append(" | ");
        }
        System.out.println(sb);
    }
}
