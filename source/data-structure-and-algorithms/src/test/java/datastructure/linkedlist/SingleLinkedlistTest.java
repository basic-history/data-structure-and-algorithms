package datastructure.linkedlist;

import io.github.pleuvoir.datasructure.linkedlist.SingleLinkedlist;

public class SingleLinkedlistTest {

    public static void main(String[] args) {
        SingleLinkedlist list = new SingleLinkedlist();

        for (int i = 0; i < 10; i++) {
            list.add(i + 1);

        }

        list.printAll();
        // [1,2,3,4,5,6,7,8,9,10]

        final Object o = list.removeFirst();

        System.out.println(o);
        // 1


        list.printAll();
        // [2,3,4,5,6,7,8,9,10]

        list.removeLast();

        list.printAll();


        SingleLinkedlist.Node oneByVal = list.getByVal(1);
        System.out.println(oneByVal);   // null
        SingleLinkedlist.Node threeByVal = list.getByVal(3);
        System.out.println(threeByVal);  // Node{data=3}


        System.out.println("list.getByIndex(0)=" + list.getByIndex(0));  // Node{data=2}
        System.out.println("list.getByIndex(2)=" + list.getByIndex(2));  // Node{data=4}


        list.printAll();
        // 删除倒数第四个结点
        list.deleteNodeByReverseIndex(4);

        list.printAll();

        new SingleLinkedlist().deleteNodeByReverseIndex(2);


        // 反转链表

        SingleLinkedlist test3 = new SingleLinkedlist();
        test3.add(1);
        test3.add(2);
     //   test3.add(3);

        test3.printAll();
        test3.reverse();

        test3.printAll();


        test3.add(3);
        test3.add(4);
        test3.add(5);
        test3.add(6);
        test3.add(7);
        test3.printAll();
        SingleLinkedlist.Node middle = test3.middle();
        System.out.println(middle);


        // 环测试

        SingleLinkedlist circleContainer = new SingleLinkedlist();
        circleContainer.add(1);
        circleContainer.add(2);
        circleContainer.add(3);
        circleContainer.add(4);

        // 设置最后一个节点指向第一个
        SingleLinkedlist.Node last = circleContainer.getByIndex(3);

        SingleLinkedlist.Node first = circleContainer.getByIndex(0);

        last.setNext(first);


        System.out.println(circleContainer.checkCircle());
    }
}
