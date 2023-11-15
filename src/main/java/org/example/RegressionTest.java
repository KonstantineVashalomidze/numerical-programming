package org.example;

import org.example.numericall.functions.MathFunction;
import org.example.numericall.plotter.Plotter;

import java.awt.*;
import java.util.Random;

public class RegressionTest
{

    public static double[][] generateCorrelatedData(int n) {
        double[][] xy = new double[2][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            double studyHours = random.nextDouble() * 5 + 1; // Random study hours between 1 and 6
            double examScores = 60 + 5 * studyHours + random.nextGaussian() * 5; // Correlated scores with some noise
            xy[0][i] = studyHours;
            xy[1][i] = examScores;
        }

        return xy;
    }

    public static void main(String[] args)
    {

        int n = 500;

        double[][] xy = generateCorrelatedData(n);



        double sumXY = 0;
        double sumX = 0;
        double sumY = 0;
        double sumXSquared = 0;

        for (int i = 0; i < n; i++)
        {
            sumXY += xy[0][i] * xy[1][i];
            sumX += xy[0][i];
            sumY += xy[1][i];
            sumXSquared += Math.pow(xy[0][i], 2);
        }

        System.out.println(sumX + " " + sumY + " " + sumXY + " " + sumXSquared);

        double k = (n * sumXY - sumX * sumY) / (n * sumXSquared - Math.pow(sumX, 2));
        double b = (sumY - k * sumX) / n;

        System.out.println(k + " * X + " + b);

        MathFunction regressionLine = x -> k * x + b;

        double[][] plotXY = new double[2][200];
        int j = 0;
        for (double i = 0; i < 20; i += 0.1)
        {
            plotXY[0][j] = i;
            plotXY[1][j] = regressionLine.apply(i);
            j++;
        }

        Plotter plotter = new Plotter();
        plotter.drawPoints("data points", Color.RED, xy);
        plotter.drawPoints("regressionLine", Color.green, plotXY);


    }



}
