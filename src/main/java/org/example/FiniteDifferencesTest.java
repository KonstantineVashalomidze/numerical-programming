package org.example;

import org.example.numericall.algorithms.derivatives.FiniteDifference;
import org.example.numericall.functions.MathFunction;
import org.example.numericall.plotter.Plotter;

import java.awt.*;

public class FiniteDifferencesTest
{
    public static void main(String[] args)
    {
        Plotter plotter = new Plotter();
        MathFunction function = x -> x * x * x * x * x * x * x * x;

        double[] x = new double[100];
        double[] y = new double[100];

        int index = 0;
        for (double i = -0.5; i < 0.5; i += 0.01)
        {
            x[index] = i;
            y[index] = FiniteDifference.derivativeOf(function, 3, 0.001).apply(i);
            index++;
        }

        plotter.drawPoints("Function", Color.RED, new double[][] { x, y });










    }




}
