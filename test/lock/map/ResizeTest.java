package lock.map;

import java.util.concurrent.ConcurrentHashMap;

public class ResizeTest {
    public static void main(String[] args) {


        ConcurrentHashMap<Person,String> test=new ConcurrentHashMap<>();
        Person  person0  = new Person("李四1",24,0);
        Person  person1  = new Person("李四1",24,1);
        Person  person2  = new Person("李四2",24,2);
        Person  person3  = new Person("李四3",24,3);
        Person  person4  = new Person("李四4",24,4);
        Person  person5  = new Person("李四5",24,5);
        Person  person6  = new Person("李四6",24,6);
        Person  person7  = new Person("李四7",24,7);
        Person  person8  = new Person("李四8",24,8);
        Person  person9  = new Person("李四9",24,9);
        Person  person10  = new Person("李四10",24,10);
        Person  person11  = new Person("李四11",24,11);
        Person  person12 =  new Person("李四12",24,12);
        Person  person13 =  new Person("李四12",24,13);
        Person  person14 =  new Person("李四12",24,14);

        test.put(person0,"第0个元素");
        test.put(person1,"第1个元素");
        test.put(person2,"第2个元素");
        test.put(person3,"第3个元素");
        test.put(person4,"第4个元素");
        test.put(person5,"第5个元素");
        test.put(person6,"第6个元素");
        test.put(person7,"第7个元素");
        test.put(person8,"第8个元素");
        test.put(person9,"第9个元素");
        test.put(person10,"第10个元素");
        test.put(person11,"第11个元素");
        test.put(person12,"第12个元素");
        test.put(person13,"第13个元素");
        test.put(person14,"第14个元素");


    }
}
