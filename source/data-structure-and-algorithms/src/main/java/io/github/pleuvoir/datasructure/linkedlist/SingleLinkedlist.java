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


    // 单链表反转
    public void reverse() {

        Node current = this.head.next;
        Node temp = null;   // 临时变量用于保存下一个需要切换的节点地址
        Node dummy = null;

        while (current != null) {
            temp = current.next;

            /**
             *  假设数据为 [1,2]
             *  第一次循环 Node1->next = null
             *  第二次循环 Node2->next = Node1(Node1->next = null)
             */

            current.next = dummy;   // 每次遍历将下一个节点设置为上一个操作过的元素
            dummy = current;  // 下次遍历时 dummy 即为上一个操作过的元素

            current = temp;
        }
        this.head.next = dummy;
    }


    // 删除链表倒数第 n 个结点
    public void deleteNodeByReverseIndex(int index) {

        if (this.size == 0 || this.size < index) {
            return;
        }
        // 先将倒数转为正数索引
        int i = this.size - index ;

        Node p = this.head;

        int pos = 0;
        while (p.next != null && i != pos) {
            p = p.next;
            pos++;
        }
        // 此时 p 即为要删除的节点，接下里寻找上一个节点
        Node prevP = head;

        // 从头开始遍历，停止时 prevLastNode 即为倒数第二个结点
        while (prevP.next != null && prevP.next != p) {
            prevP = prevP.next;
        }

        // 此时 prevP 为要删除节点的上一个节点，移动指针
        prevP.next = p.next;
        p = null; // help gc
    }


    // 求链表的中间结点，使用快慢指针此种解法较为巧妙
    public Node middle() {
        Node p = this.head;
        Node middle = this.head.next;
        while (p.next != null && p.next.next != null) {
            p = p.next.next;
            middle = middle.next;
        }
        return middle;
    }

    // 链表中环的检测
    public boolean checkCircle() {

        if (size == 0) return false;

        Node fast = this.head.next;  // 使用快慢指针
        Node slow = this.head.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == null) {
                // 已经到达末尾停止。如果存在环则不会停止，需要判断快慢指针相遇后停止
                return false;
            } else if (fast == slow) {
                System.out.println("快慢指针相遇.. 是环状链表");
                return true;
            }
        }
        return false;
    }

    @Data
    public static class Node {
        private Node next;
        private Object data;

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
