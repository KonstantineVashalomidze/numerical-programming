package org.example;

import org.example.numericall.functions.MathFunction;

public class test
{
    public static void main(String[] args)
    {
        System.out.println(gcd(18, 64));
    }

    public static int gcd(int a, int b)
    {
        if (b == 0)
        {
            return Math.abs(a);
        }

        else
        {
            return gcd(Math.abs(b), a % Math.abs(b));
        }
    }


}
