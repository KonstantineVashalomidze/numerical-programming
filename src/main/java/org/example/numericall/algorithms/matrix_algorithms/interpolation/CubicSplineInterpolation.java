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
    public double interpolate(double x)
    {
        double[] a = new double[n + 1];
        if (n >= 0) System.arraycopy(yData, 0, a, 0, n);

        double[] b = new double[n], d = new double[n], h = new double[n];

        for (int i = 0; i < n - 1; i++)
        {
            h[i] = xData[i + 1] - xData[i];
        }

        double[] alfa = new double[n];

        for (int i = 1; i < n - 1; i++)
        {
            alfa[i] = (3 * (a[i + 1] - a[i])) / h[i] - (3 * (a[i] - a[i - 1]) / h[i - 1]);
        }

        double[] c = new double[n + 1], l = new double[n + 1], u = new double[n + 1], z = new double[n + 1];

        l[0] = 1;
        u[0] = 0;
        z[0] = 0;


        for (int i = 1; i < n - 1; i++)
        {
            l[i] = 2 * (xData[i + 1] - xData[i - 1]) - h[i - 1] * u[i - 1];
            u[i] = h[i] / l[i];
            z[i] = (alfa[i] - h[i - 1] * z[i - 1]) / l[i];
        }


        l[n] = 1;
        z[n] = 0;
        c[n] = 0;

        for (int j = n - 1; j > 0; j--)
        {
            c[j] = z[j] - u[j] * c[j];
            b[j] = (a[j + 1] - a[j]) / h[j] - (h[j] * (c[j + 1] + 2 * c[j])) / 3;
            d[j] = (c[j + 1] - c[j]) / 3 * h[j];
        }


        double result = 0;
        for (int i = 0; i < n - 1; i++)
        {
            if (x > xData[i] && x < xData[i + 1])
            {
                result = a[i] + b[i] * (x - xData[i]) + c[i] * Math.pow((x - xData[i]), 2) + d[i] * Math.pow((x - xData[i]), 3);
            }
        }

        return result;







    }
}
