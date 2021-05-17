package lock.map;

public class Person {
    private  String name ;
    private   int  age ;
    private    int  hashcode ;


    public Person(String name, int age, int hashcode) {
        this.name = name;
        this.age = age;
        this.hashcode = hashcode;
    }


    @Override
    public int hashCode() {
        return  hashcode;
    }
}
