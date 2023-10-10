package org.example;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.InterpolationStrategy;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LagrangePolynomialInterpolation;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LinearInterpolation;
import org.example.numericall.plotter.Plotter;

import java.awt.*;
import java.util.Arrays;

public class InterpolationTest
{
    public static void main(String[] args)
    {
        double[][] dataPoints = { new double[] { 0, 1, 2, 3, 4, 5, 6 }, new double[] { 0, 0.8, 1, 0.2, -0.6, -0.9, -0.2 } };
        Plotter plotter = new Plotter();

        // linear interpolation
        InterpolationStrategy linearInterpolation = new LinearInterpolation(dataPoints);
        System.out.println(linearInterpolation.interpolate(2.5));



        // lagrange polynomial interpolation
        InterpolationStrategy polynomialInterpolation = new LagrangePolynomialInterpolation(dataPoints);
        System.out.println(polynomialInterpolation.interpolate(1.85));

        // Sample code of lagrange interpolation visual
        /*double[] x = new double[61];
        double[] y = new double[61];
        int counter = 0;
        for (double i = 0; i <= 6; i+=0.1)
        {
            x[counter] = i;
            y[counter] = polynomialInterpolation.interpolate(i);
            counter++;
        }

        plotter.drawPoints("polinomial", Color.GREEN, new double[][] { x, y });
        plotter.drawPoints("data points", Color.RED, dataPoints);*/

    }
}
