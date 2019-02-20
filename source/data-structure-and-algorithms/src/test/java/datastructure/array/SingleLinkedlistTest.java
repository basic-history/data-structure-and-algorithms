package datastructure.array;

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


    }
}
