package com.duoduo.study.thread;

public class Person {
    private String personName;
    private int age;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "personName='" + personName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
