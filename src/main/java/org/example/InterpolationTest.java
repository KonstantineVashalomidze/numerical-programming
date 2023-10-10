package org.example;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.InterpolationStrategy;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LinearInterpolation;

public class InterpolationTest
{
    public static void main(String[] args)
    {

        // linear interpolation
        double[][] dataPoints = { new double[] { 0, 1, 2, 3, 4, 5, 6 }, new double[] { 0, 0.8, 1, 0.2, -0.6, -0.9, -0.2 } };
        InterpolationStrategy linearInterpolation = new LinearInterpolation(dataPoints);
        System.out.println(linearInterpolation.interpolate(2.5));


    }
}
