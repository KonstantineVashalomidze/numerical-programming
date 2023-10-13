package org.example;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.InterpolationStrategy;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LagrangePolynomialInterpolation;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LinearInterpolation;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.NewtonPolynomialInterpolation;
import org.example.numericall.plotter.Plotter;

import java.awt.*;
import java.util.Arrays;

public class InterpolationTest
{
    public static void main(String[] args)
    {
        double[][] dataPoints = { new double[] { 1, 8, 11, -2 }, new double[] { 7, 9, 12, 3 } };
        Plotter plotter = new Plotter();
        InterpolationStrategy polynomialInterpolation = new LagrangePolynomialInterpolation(dataPoints);
        InterpolationStrategy newtonsInterpolation = new NewtonPolynomialInterpolation(dataPoints);


        // Sample code of lagrange interpolation visual
        double[] x = new double[401];
        double[] y = new double[401];
        int counter = 0;
        for (double i = -20; i <= 20; i+=0.1)
        {
            var polynomialInterpolationInterpolated = newtonsInterpolation.interpolate(i);
            for (int j = 0; j < polynomialInterpolationInterpolated.length; j++)
            {
                x[counter] = i;
                y[counter] = polynomialInterpolationInterpolated[j];
                counter++;
            }
        }

        plotter.drawPoints("polinomial", Color.GREEN, new double[][] { x, y });
        plotter.drawPoints("data points", Color.RED, dataPoints);

    }
}
