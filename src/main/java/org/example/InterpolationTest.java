package org.example;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.CubicSplineInterpolation;


public class  InterpolationTest
{
    public static void main(String[] args)
    {
        double[][] dataPoints = { new double[] { 1, 2, 3 }, new double[] { 2, 3, 5 } };
        CubicSplineInterpolation cubicSplineInterpolation = new CubicSplineInterpolation(dataPoints);
        System.out.println(cubicSplineInterpolation.interpolate(2.5));



    }
}
