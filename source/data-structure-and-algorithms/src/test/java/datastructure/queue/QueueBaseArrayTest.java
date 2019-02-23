package datastructure.queue;

import io.github.pleuvoir.datasructure.queue.QueueBaseArray;

public class QueueBaseArrayTest {

	public static void main(String[] args) {

		QueueBaseArray queue = new QueueBaseArray(5);
		Object no = queue.dequeue();
		System.out.println(no);
		
		int i = 1;

		System.out.println("依次插入 1-5");
		for (; i <= 5; i++) {
			queue.enqueue(i);
		}
		
		queue.printAll();
		System.out.println("增加 6");
		queue.enqueue(6);
		
		Object dequeue = queue.dequeue();
		System.out.println("取出第一个元素："+ dequeue);
		
		queue.printAll();
		
		System.out.println("增加 6");
		queue.enqueue(6);
		queue.printAll();
		
		System.out.println("增加 7");
		queue.enqueue(7);
		queue.printAll();
	}
}
