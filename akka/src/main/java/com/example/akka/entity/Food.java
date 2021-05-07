package com.example.akka.entity;


public class Food {

    private String name;

    public void eat() {
        System.out.println("i eat " + name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
