package io.github.pleuvoir.datasructure.linkedlist;


import lombok.Data;

/**
 * 单向链表实现
 */
@Data
public class SingleLinkedlist {

    // 头结点，不存储数据
    private Node head = new Node(null);

    // 链表大小
    private int size;


    // 在尾部添加数据
    public void add(Object o) {
        Node newNode = new Node(o);

        // 从头部开始遍历找到最后一个并赋值
        Node headNodeAdd = head;

        while (headNodeAdd.next != null) {
            headNodeAdd = headNodeAdd.next;
        }

        headNodeAdd.next = newNode;
        size++;
    }


    // 根据值查找结点，会返回第一个先查找到的节点
    public Node getByVal(Object o) {
        Node p = this.head;
        while (p.next != null && p.next.data != o) {
            p = p.next;

        }
        return p.next;
    }


    // 根据下标访问节点
    public Node getByIndex(int index) {

        if (index < 0 || index > size - 1) {
            return null;
        }

        Node p = this.head;

        int pos = 0;
        while (p.next != null && index >= pos) {
            p = p.next;
            pos++;
        }

        return p;

    }

    // 删除第一个结点
    public Object removeFirst() {

        Node headToUse = head;
        if (headToUse.next != null) {
            final Object second = headToUse.next.data;  // 首节点后的第一个元素
            headToUse.next = headToUse.next.next;

            return second;
        }

        size--;
        return null;
    }


    public Object removeLast() {
        if (size == 0) return null;

        // 寻找尾结点
        Node lastNode = head;

        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        // 找到倒数第二个结点 设置 next 为 null
        Node prevLastNode = head;

        // 从头开始遍历，停止时 prevLastNode 即为倒数第二个结点
        while (prevLastNode.next != null && prevLastNode.next != lastNode) {
            prevLastNode = prevLastNode.next;
        }

        prevLastNode.next = null;
        size--;

        return lastNode.data;
    }


    public static class Node {
        Node next;
        Object data;

        public Node(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public void printAll() {
        StringBuffer stringBuffer = new StringBuffer("[");

        Node temp = head;
        while (temp.next != null) {
            stringBuffer.append(temp.next.data).append(",");
            temp = temp.next;
        }

        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append("]");
        System.out.println(stringBuffer);
    }

}
