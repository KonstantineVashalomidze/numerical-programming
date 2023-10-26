package org.example.numericall.algorithms.matrix_algorithms.interpolation;

import java.util.Arrays;

public class CubicSplineInterpolation
    implements InterpolationStrategy
{

    int n;
    double[] xData, yData;



    public CubicSplineInterpolation(double[][] dataPoints)
    {
        xData = dataPoints[0];
        yData = dataPoints[1];
        n = dataPoints[0].length;
    }


    @Override
    public double[] interpolate(double x)
    {
        double[] h = new double[n - 1];
        for (int i = 0; i <= n - 2; i++)
            h[i] = xData[i + 1] - xData[i];

        double[] alpha = new double[n];
        double[] beta = new double[n];

        for (int i = 1; i <= n - 1; i++)
        {
            alpha[i + 1] = h[i] / (h[i] + h[i+1]);
            beta[i+1] = 3 * ((1 - alpha[i+1]) / h[i]) * (yData[i+2] - yData[i+1]) - 3 * (alpha[i+1] / h[i+1]) * (yData[i+1] - yData[i]);
        }

        double[] c = new double[n];
        double[] l = new double[n];
        double[] mu = new double[n];
        double[] z = new double[n];

        for (int i = 0; i < n; i++)
        {
            c[i] = 0;
            l[i] = 1;
            mu[i] = 0;
            z[i] = 0;
        }

        for (int i = 1; i <= n - 1; i++)
        {
            l[i] = 2 * (xData[i+1] - xData[i-1]) - h[i-1] * mu[i-1];
            mu[i] = h[i] / l[i];
            z[i] = (alpha[i] * (z[i-1] - h[i-1] * z[i-1]) - beta[i]) / l[i];
        }

        c[n - 1] = 0;
        double[] b = new double[n];
        double[] d = new double[n];
        for (int i = 0; i < n; i++)
        {
            b[i] = 0;
            d[i] = 0;
        }


        for (int j = n - 2; j <= 0;)
        {
            c[j] = z[j] - mu[j] * c[j+1];
            b[j] = (yData[j+1] - yData[j]) / h[j] - h[j] * (c[j+1] + 2 * c[j]) / 3;
            d[j] = (c[j+1] - c[j]) / (3 * h[j]);

            j--;
        }

        for (int i = 0; i < yData.length; i++)
        {
            System.out.println(yData[i] + " " + b[i] + " " + c[i] + " " + d[i]);
        }


        return null;
    }
}
