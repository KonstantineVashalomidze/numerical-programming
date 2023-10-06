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
        MathFunction parabola = x -> x * x * x;

        double[] x = new double[200];
        double[] y = new double[200];

        for (int i = -100; i < 100; i++)
        {
            x[i + 100] = i;
            y[i + 100] = parabola.apply(i);
        }


        plotter.drawLine("parabola", Color.RED, new double[][] { x, y });

        double[] derivativeOfParabolaY = new double[200];

        MathFunction function = FiniteDifference.firstDerivativeOf(parabola, 0.1);
        for (int i = -100; i < 100; i++)
        {
            derivativeOfParabolaY[i + 100] = function.apply(i);
        }

        plotter.drawLine("first derivative of parabola", Color.blue, new double[][] { x, derivativeOfParabolaY });



    }




}
