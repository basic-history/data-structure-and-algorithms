package datastructure.queue;

import io.github.pleuvoir.datasructure.queue.CircleQueue;

public class CircleQueueTest {

	public static void main(String[] args) {

		CircleQueue queue = new CircleQueue(5);
		Object no = queue.dequeue();
		System.out.println(no);
		
		int i = 1;

		System.out.println("因为本次实现的 循环队列会浪费一个内存，所以内存数组为5的队列我们只 依次插入 1-4");
		for (; i <= 4; i++) {
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
