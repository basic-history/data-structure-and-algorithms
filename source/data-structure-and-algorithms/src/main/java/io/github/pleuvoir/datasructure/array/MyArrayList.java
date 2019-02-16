package io.github.pleuvoir.datasructure.array;

import java.util.Arrays;

/**
 * 一个基于数组实现的容器，提供了插入，删除，按标访问功能
 * 
 * @author pleuvoir
 *
 */
public class MyArrayList<T> {

	private static final int DEFAULT_CAPACITY = 10;	//默认的容器大小
	
	private Object[] elementData;  // 保存数据

	private int capacity; // 容器大小
	
	private int size; // 目前保存的元素个数

	public MyArrayList() {
		this.capacity = DEFAULT_CAPACITY;
		this.elementData = new Object[capacity]; // 初始化容器
	}

	public MyArrayList(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException("capacity 不能小于 1");
		}
		this.elementData = new Object[capacity]; // 初始化容器
		this.capacity = capacity;
	}
	
	// 插入
	public void add(T data){
		// 1. 检查容器是否有位置，如果已经满了则扩容为 1.5 倍大小
		if (this.size >= this.capacity) {
			this.capacity = (int) (this.capacity * 1.5);
			Object[] temp = new Object[capacity];
			for (int i = 0; i < this.elementData.length; i++) {
				temp[i] = this.elementData[i];
			}
			this.elementData = temp;
		}
		// 插入
		elementData[size++] = data;
	}
	
	// 往指定位置插入，可能存在扩容和数据搬移
	public void add(int index, T data) {
		
		if (index >= capacity) {
			throw new IllegalArgumentException(
					"当前容器容量" + capacity + "个，最大索引是" + (capacity - 1) + "无法在" + index + "索引处插入数据");
		}
		/**
		 * 假设原数据为  1, 2, 3, 4, 5  则存在如下3种情况
		 * 1) 插入第一位，此时移动所有数据，时间复杂度为 O(n)
		 * 2) 插入末尾，无需移动，时间复杂度 O(1)
		 * 3） 插入中间位置 ，如在到3 和 4之间插入100（索引位置为3），数据变为 1,2,3,100,4,5；此时有数据搬移的问题、如果存在扩容的情况，也需要数据搬移
		 * 	     
		 * 综上所述，大概率需要搬移数据，所以直接一开始便声明一个数组用来处理
		 */
		
		// 1. 检查容器是否有位置，如果已经满了则扩容为 1.5 倍大小
		if (this.size >= this.capacity ) {
			this.capacity = (int) (this.capacity * 1.5);
		}
		
		// 初始化空数组
		Object[] temp = new Object[this.capacity];
		
		// 2. 赋值以及搬移
		
		// 搬移前半段，拷贝索引前数组
		for (int i = 0; i < index; i++) {
			temp[i] = this.elementData[i];
		}
		
		// 赋值
		temp[index] = data;
		
		// 这里要特别注意，数组越界以及 null 的访问，不可直接使用 elementData.length 因为这个长度遍历可能会超出 temp 的范围
		for (int i = index; i < size; i++) { 
			temp[i + 1] = this.elementData[i];
		}
		
		this.elementData = temp;
		size++;
	}
	
	
	// 删除
	public void remove(int index){
		if (index >= capacity || index < 0) {
			throw new IllegalArgumentException(
					"当前容器容量" + capacity + "个，最大索引是" + (capacity - 1) + "无法在" + index + "索引处删除数据");
		}
		// 向左搬移
		for (int i = index; i < capacity - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		size--;
	}
	
	// 按标访问
	@SuppressWarnings("unchecked")
	public T get(int index){
		if (index < 0 || index >= this.capacity) {  // 当数组大小为6时，最大索引为5，通过6访问是会报错的，使用 = 减少一次运算
			return null;
		}
		return (T) this.elementData[index];
	}
	
	
	// 输出所有元素
	public void printAll(){
		System.out.println("当前元素为：" + Arrays.asList(this.elementData) + "，当前元素个数：" + this.size + "，容器大小" + capacity);
	}

}
