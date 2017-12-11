package com.jwt.funny;

public interface B extends A
{
     default void p(){
        System.out.println("print p b");
    }
    void print();

    default void printB(){
        System.out.println("print B");
    }
}
