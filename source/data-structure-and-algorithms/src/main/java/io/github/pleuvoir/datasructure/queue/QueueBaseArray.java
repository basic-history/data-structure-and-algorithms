package io.github.pleuvoir.datasructure.queue;

/**
 * 基于数组实现的普通队列 <br>
 * 
 * 当 tail 指针到达队列尾部（tail==capacity）， head 也不在头部时会发生数据搬移，时间复杂度为 O(n)，更优的做法是循环队列
 * 
 * @author pleuvoir
 *
 */
public class QueueBaseArray {

	private static final int DEFAULT_CAPACITY = 10;
	private int capacity;

	private Object[] elements;

	private int head = 0; // 标记头部
	private int tail = 0; // 标记尾部

	public QueueBaseArray() {
		this.capacity = DEFAULT_CAPACITY;
		this.elements = new Object[capacity];
	}

	public QueueBaseArray(int capacity) {
		this.capacity = capacity;
		this.elements = new Object[capacity];
	}

	// 入队
	public void enqueue(Object o) {
		if (tail == capacity) { // 已到达尾部
			if (head == 0) { // 代表数组被占满了
				System.out.println("warn >>> 队列已满，capacity=" + capacity + " 此次操作被忽略。");
				return;
			} else {
				// 数据搬移，eg head=1 tail=5 [null,2,3,4,5] -> [2,3,4,5,null] head=0,tail=5
				for (int i = head; i < tail; i++) {
					elements[i - head] = elements[i];
				}
				// 调整指针
				tail = tail - head;
				head = 0;
			}
		}
		this.elements[tail++] = o;
	}

	// 出队
	public Object dequeue() {
		if (head == tail) {
			return null;
		}
		Object object = this.elements[head];
		this.elements[head] = null;
		head++;
		return object;
	}

	// help
	public void printAll() {
		StringBuffer sb = new StringBuffer("head=" + this.head + "	tail=" + this.tail + "  [");
		for (Object object : elements) {
			sb.append(object).append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

}
