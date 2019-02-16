package datastructure.array;

import io.github.pleuvoir.datasructure.array.MyArrayList;

public class MyArrayListTest {

	public static void main(String[] args) {
		
		MyArrayList<Integer> myArrayList = new MyArrayList<Integer>(4);
		
		myArrayList.add(1);
		myArrayList.add(2);
		myArrayList.add(3);
		
		myArrayList.printAll();
		// 当前元素为：[1, 2, 3, null]，当前元素个数：3
		
		myArrayList.add(4);
		myArrayList.add(5);
		// 触发扩容，大小变为 6
		myArrayList.printAll();
		// 当前元素为：当前元素为：[1, 2, 3, 4, 5, null]，当前元素个数：5
		
		System.out.println("下标为4的元素为：" + myArrayList.get(4));		
		// output 下标为4的元素为：5
		
		System.out.println("下标为6的元素为：" + myArrayList.get(6));	
		// output 下标为6的元素为：null
		
		// 往指定位置插入
		myArrayList.add(3, 100);
		myArrayList.printAll();
		// 当前元素为：[1, 2, 3, 100, 4, 5]，当前元素个数：6
		
		myArrayList.add(3, 200);
		myArrayList.printAll();
		// 当前元素为：[1, 2, 3, 200, 100, 4, 5, null, null]，当前元素个数：7
		
		// 测试容器边界
		myArrayList.add(5, 200);
		myArrayList.printAll();
		//myArrayList.add(9, 200); //该行会触发错误
		//myArrayList.printAll();
		
		// 测试删除
		// myArrayList.remove(-1); //该行会触发错误
		
		myArrayList.remove(6);
		myArrayList.printAll();
	}
}
