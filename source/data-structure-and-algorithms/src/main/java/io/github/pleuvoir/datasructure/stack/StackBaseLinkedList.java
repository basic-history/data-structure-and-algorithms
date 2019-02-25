package io.github.pleuvoir.datasructure.stack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基于链表实现的链式栈
 */
public class StackBaseLinkedList {

    private Node head;

    private int size;

    public StackBaseLinkedList() {
        head = new Node(null, null);
    }

    public void push(Object o) {
        Node p = this.head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node(null, o);
        size++;
    }

    public Object pop() {
        Node p = this.head;
        while (p.next != null) {
            p = p.next;
        }
        Node q = this.head;
        while (q.next != null && q.next != p) {
            q = q.next;
        }
        q.next = null;
        size--;
        return p;
    }

    public void printAll() {
        StringBuffer sb = new StringBuffer();
        Node p = this.head;
        while (p.next != null) {
            sb.append(p.next.data).append(" | ");
            p = p.next;
        }
        System.out.println(sb);
    }

    @AllArgsConstructor
    @Data
    private class Node {
        private Node next;
        private Object data;
    }
}
