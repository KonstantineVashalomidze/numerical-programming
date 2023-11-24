package org.example;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.InterpolationStrategy;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LagrangePolynomialInterpolation;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.NewtonPolynomialInterpolation;
import org.example.numericall.functions.MathFunction;
import org.example.numericall.plotter.Plotter;

import java.awt.*;

public class test
{
    public static void main(String[] args)
    {
        double[][] dataPoints = new double[][] { new double[] { 3, 4, 5, 7 }, new double[] { 0, 2, 3, 3 } };

        Plotter plotter = new Plotter();


        InterpolationStrategy interpolationStrategy = new NewtonPolynomialInterpolation(dataPoints);
        double[][] interpolatedDataPoints = new double[2][71];
        int counter = 0;
        for (double i = 0; i < 7; i += 0.1)
        {
            interpolatedDataPoints[0][counter] = i;
            interpolatedDataPoints[1][counter] = interpolationStrategy.interpolate(i);
            counter++;
        }


        plotter.drawPoints("legend", Color.GREEN, interpolatedDataPoints);




    }




}
