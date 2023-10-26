package org.example;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.CubicSplineInterpolation;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.InterpolationStrategy;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LagrangePolynomialInterpolation;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.NewtonPolynomialInterpolation;
import org.example.numericall.plotter.Plotter;

import java.awt.*;

public class  InterpolationTest
{
    public static void main(String[] args)
    {
        double[][] dataPoints = { new double[] { 1, 2, 3 }, new double[] { 2, 3, 5 } };
        Plotter plotter = new Plotter();
        CubicSplineInterpolation cubicSplineInterpolation = new CubicSplineInterpolation(dataPoints);
        cubicSplineInterpolation.interpolate(3);
    }
}
