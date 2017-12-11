package com.jwt.funny;

public class Impl implements A, B
{
    private static int t = 0;
    public static void main(String[] args)
    {
        A i = new Impl();
        /*i.print();
        i.printB();*/
         i.p();
    }

    @Override
    public void print()
    {
        System.out.println("in print");
    }
}
