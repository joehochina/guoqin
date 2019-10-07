package com.duoduo.study.thread;

public class TestTransforValue {
    public void changeValue1(int age){
        age = 40;
    }
    public void changeValue2(Person person){
        person.setPersonName("xxx");
    }
    public void changeValue3(String str){
        str = "abc";
    }

    public static void main(String[] args) {
        TestTransforValue test = new TestTransforValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age-----------"+age);

        Person person = new Person();
        person.setPersonName("ZHANGSAN");
        test.changeValue2(person);
        System.out.println("personName======="+person.getPersonName());

        String str = "lisi";
        test.changeValue3(str);
        System.out.println("string=========="+str);

    }
}
