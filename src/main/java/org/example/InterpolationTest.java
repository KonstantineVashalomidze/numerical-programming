package org.example;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.*;
import org.example.numericall.functions.MathFunction;
import org.example.numericall.plotter.Plotter;

import java.awt.*;


public class  InterpolationTest
{
    public static void main(String[] args)
    {


        Plotter plotter = new Plotter();
        /*// create function data points to apply cubic spline interpolation
        // lets take this function
        MathFunction rungesFunction = x -> 1 / (1 + 25 * x * x);
        double[][] dataPoints = new double[2][400];
        int counter = 0;
        for (double i = -20; i <= 20; i += 0.1)
        {
            if (i != 0)
            {
                dataPoints[0][counter] = i;
                dataPoints[1][counter] = rungesFunction.apply(i);
                counter++;
            }
        }

        plotter.drawPoints("Actual points", Color.RED, dataPoints);


//        // interpolation
        InterpolationStrategy lagrangePolynomialInterpolation = new LagrangePolynomialInterpolation(dataPoints);
        double[][] interpolatedDataPoints = new double[2][7];
        interpolatedDataPoints[0][0] = -3;
        interpolatedDataPoints[0][1] = -2;
        interpolatedDataPoints[0][2] = -1;
        interpolatedDataPoints[0][3] = 1;
        interpolatedDataPoints[0][4] = 2;
        interpolatedDataPoints[0][5] = 3;
        interpolatedDataPoints[0][6] = -20;


        interpolatedDataPoints[1][0] = lagrangePolynomialInterpolation.interpolate(-3);
        interpolatedDataPoints[1][1] = lagrangePolynomialInterpolation.interpolate(-2);
        interpolatedDataPoints[1][2] = lagrangePolynomialInterpolation.interpolate(-1);
        interpolatedDataPoints[1][3] = lagrangePolynomialInterpolation.interpolate(1);
        interpolatedDataPoints[1][4] = lagrangePolynomialInterpolation.interpolate(2);
        interpolatedDataPoints[1][5] = lagrangePolynomialInterpolation.interpolate(3);
        interpolatedDataPoints[1][6] = lagrangePolynomialInterpolation.interpolate(-20);*/

        /*var interpolatedDataPoints = new double[][] { new double[] { 6.0, 22.0, 39.0, 54.0, 59.0, 76.0, 86.0, 100.0, 114.0, 125.0 }
                                                    , new double[] { 50.0, 25.0, 18.0, 14.0, 12.0, 88.0, 86.0, 82.0, 75.0, 35.0 } };

        InterpolationStrategy interpolationStrategy = new CubicSplineInterpolation(interpolatedDataPoints);
        double[] x = new double[1191];
        double[] y = new double[1191];
        var counter = 0;
        for (double i = 6; i <= 125; i += 0.1)
        {
            x[counter] = i;
            y[counter] = interpolationStrategy.interpolate(i);
            counter++;
        }


        plotter.drawPoints("interpolted", Color.GREEN, new double[][] { x, y });
        plotter.drawPoints("interpolted", Color.RED, interpolatedDataPoints);*/


        // Testing cubic spline interpolation function







    }
}
