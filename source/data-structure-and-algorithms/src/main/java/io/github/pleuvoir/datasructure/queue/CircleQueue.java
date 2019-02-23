package io.github.pleuvoir.datasructure.queue;

/**
 * 循环队列 基于数组实现，避免数据搬移
 * @author pleuvoir
 *<pre>
 *		 tail
 *		  |
 *    0 - 7 - 6 - 5 
 *	  |			  |
 *    1 - 2 - 3 - 4 
 *				  |
 *				 head
 *
 * 图中队列大小为 8 ， head 在 4 ，tail 在 7。当 tail 到达临界值时，head ++ ，入队操作 不在 tail ++ 而是将元素放入回退一位 的地址。
 * 注意，图中 1、2、3 这些数组仅仅代表是数组位置，不代表实际数据， 1 处的值也可能为 null 
 * 这样就成功避免了数据搬移的缺点，关口是 确定好队空和队满的判定条件：
 * 
 * 队空 head == tail 
 * 队满(tail+1)%n=head (7+1)%8=0  {0,1,2,3,4,5,6,7}
 * 回退一位 的地址计算方式  tail==(tail+1)%capacity (7+1)%8 == 0
 * 				   head==(head+1)%capacity;
 * 
 * 注意：这几个公式是通过数学计算推导出来的，不必去纠结。当队列满时，图中的 tail 指向的位置实际上是没有存储数据，所以会浪费一个内存空间
 * （优化：可以通过定义一个记录队列大小的值size，当这个值与数组大小相等时，表示队列已满，当tail达到最底时，size不等于数组大小时，tail就指向数组第一个位置。当出队时，size—，入队时size++）

 *<pre>
 */ 
public class CircleQueue {
	
	
	private static final int DEFAULT_CAPACITY = 10;
	private int capacity;

	private Object[] elements;

	private int head = 0; // 标记头部
	private int tail = 0; // 标记尾部

	
	public CircleQueue() {
		this.capacity = DEFAULT_CAPACITY;
		this.elements = new Object[capacity];
	}

	public CircleQueue(int capacity) {
		this.capacity = capacity;
		this.elements = new Object[capacity];
	}
	
	// 入队
	public void enqueue(Object o) {
		// 队列满的条件
		if ((tail + 1) % capacity == head) {
			System.out.println("warn >>> 循环队列已满，capacity=" + capacity + " 此次操作被忽略。");
			return;	
		}
		elements[tail] = o;
		tail = (tail + 1) % capacity;
	}

	// 出队
	public Object dequeue() {
		if (head == tail) {
			return null;
		}
		Object object = this.elements[head];
		this.elements[head] = null;
		head = (head + 1) % capacity;
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
