package fr.iut.td01.models;


public class Person {
    private String name;
    private int age;
    private String photo;

    public Person(String name, int age, String photo){
        this.name=name;
        this.age=age;
        this.photo=photo;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getPhoto(){
        return photo;
    }
}


