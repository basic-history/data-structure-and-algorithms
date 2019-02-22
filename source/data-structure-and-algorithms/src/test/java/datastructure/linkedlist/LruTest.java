package datastructure.linkedlist;

import io.github.pleuvoir.datasructure.linkedlist.Lru;

import java.util.concurrent.TimeUnit;

public class LruTest {

    public static void main(String[] args) throws InterruptedException {

        Lru lru = Lru.create();

        lru.put("1",1);
        lru.put("2",2);

        lru.printAll();

        Object o = lru.get("1");
        System.out.println("key=1，value=" + o);

        Lru of = Lru.of(3);

        for (int i = 1; i <= 3; i++) {
            of.put(String.valueOf(i), i);
        }

        of.printAll();

        // 触发调整
        of.put("4", "原来的4");
        of.printAll();


        of.put("4","修改后的4");
        of.printAll();

        of.put("5","我是新增的5");
        of.printAll();

        of.get("4"); // 查询一次热点数据，会发现移动到了尾端

        of.printAll();

        Lru moreSizelru = Lru.of(5);

        for (int i = 0; i <= 10; i++) {
            moreSizelru.put(String.valueOf(i), "我是值" + i);
        }

        for (;;){
            TimeUnit.SECONDS.sleep(1);
            final long l = System.currentTimeMillis() % 3;

            moreSizelru.get(String.valueOf(l));
            moreSizelru.printAll();
        }


    }
}
