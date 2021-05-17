package lock.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 链表到红黑树转化演示
 */

public class ToTreeTest {
    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<Person,String>    test=new ConcurrentHashMap<>();

        Person  person1  = new Person("李四1",24,1);
        Person  person2  = new Person("李四2",24,1);
        Person  person3  = new Person("李四3",24,1);
        Person  person4  = new Person("李四4",24,1);
        Person  person5  = new Person("李四5",24,1);
        Person  person6  = new Person("李四6",24,1);
        Person  person7  = new Person("李四7",24,1);
        Person  person8  = new Person("李四8",24,1);
        Person  person9  = new Person("李四9",24,1);


        test.put(person1,"第1个元素");
        test.put(person2,"第2个元素");
        test.put(person3,"第3个元素");
        test.put(person4,"第4个元素");
        test.put(person5,"第5个元素");
        test.put(person6,"第6个元素");
        test.put(person7,"第7个元素");
        test.put(person8,"第8个元素");
        test.put(person9,"第9个元素");






    }
}
