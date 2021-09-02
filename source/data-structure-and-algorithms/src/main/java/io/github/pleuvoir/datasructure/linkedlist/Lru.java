package io.github.pleuvoir.datasructure.linkedlist;

/**
 * 基于链表Lru缓存实现
 * <p>
 * 维护一个固定大小的单向链表，保证热数据永远在最后
 * <ol>
 * <li>新增的元素增加到链表的末尾，如果空间不足则删除第一个元素节点后再增加</li>
 * <li>修改的元素移动到末尾，并删除原节点</li>
 * <li>查找时如果之前已经有这个元素，则移动到末尾</li>
 * </ol>
 */
public class Lru {

    private static final int DEFAULT_CAPACITY = 10;

    private InnerNode head;
    private int itemSize = 0;
    private int capacity = DEFAULT_CAPACITY;

    private Lru() {
        this.head = new InnerNode(null, null, null);
    }

    private Lru(int capacity) {
        this.capacity = capacity;
        this.head = new InnerNode(null, null, null);
    }

    public static Lru create() {
        return new Lru();
    }

    public static Lru of(int capacity) {
        return new Lru(capacity);
    }


    public void put(String key, Object value) {
        final InnerNode newNode = new InnerNode(key, value, null);

        // 如果是修改则考虑移动
        InnerNode old = getNodeBykey(key);
        if (old != null) {
            if (old.next != null) {  // 如果有下一个，当然也代表有上一个
                // 调整前继节点指针
                InnerNode p = this.head;
                while (p.next != null && p.next != old) {
                    p = p.next;
                }
		 //断开连接
                p.next = p.next.next;
		    
	      //追加到尾部
	       InnerNode q = this.head;

		while (q.next != null) {
		    q = q.next;
		}
			
		q.next = old;
		old.next = null;
		old.data = value;
            } else {
                // 代表此节点是末尾节点，直接修改值
                old.data = value;
                return;
            }
        }

        // 新增
        else {
            if (itemSize == this.capacity) { // 动态调整
                removeFirst();
            }
            addLast(newNode);
        }
    }


    /**
     * 根据 key 获取 value
     */
    public Object getValueByKey(String key) {
        InnerNode node = getNodeBykey(key);
        return node == null ? null : node.data;
    }


    /**
     * 根据 key 获取 value
     */
    public Object get(String key) {

        InnerNode node = getNodeBykey(key);
        if (node == null) {
            return null;
        }

        // 移动到末尾

        // 找到前继节点移动指针，并将此节点加入最后

        InnerNode p = this.head;
        while (p.next != null && p.next != node) {
            p = p.next;
        }

        p.next = p.next.next;

        InnerNode q = this.head;

        while (q.next != null) {
            q = q.next;
        }

        q.next = node;
        node.next = null;

        return node.data;
    }


    /**
     * 根据 key 获取节点
     */
    public InnerNode getNodeBykey(String key) {
        InnerNode p = this.head;
        while (p.next != null) {
            if (p.next.key.equals(key)) {
                return p.next;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 末尾添加节点
     */
    private void addLast(InnerNode node) {
        InnerNode p = this.head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = node;
        itemSize++;
    }

    /**
     * 移除首位节点
     */
    private void removeFirst() {
        if (this.itemSize == 0 || this.head.next == null) {
            return;
        }
	    //断开连接
        this.head.next = this.head.next.next;
        itemSize--;
    }

    public void printAll() {
        InnerNode p = this.head;
        StringBuffer sb = new StringBuffer("size=" + this.itemSize + ".  ");
        while (p.next != null) {
            sb.append(" [key=").append(p.next.key).append(" value=" + p.next.data + "]");
            p = p.next;
        }
        System.out.println(sb.toString());
    }

    private static class InnerNode {
        private String key;
        private Object data;
        private InnerNode next;
		public InnerNode(String key, Object data, InnerNode next) {
			super();
			this.key = key;
			this.data = data;
			this.next = next;
		}
    }
}
