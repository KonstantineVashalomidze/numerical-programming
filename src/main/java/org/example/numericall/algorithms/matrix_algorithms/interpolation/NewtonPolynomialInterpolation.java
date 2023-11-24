package org.example.numericall.algorithms.matrix_algorithms.interpolation;

/**
 * fits a polynomial curve through data points using Newton's interpolation
 * Newton's interpolation is an alternative method for polynomial interpolation
 * it can be more computationally efficient than Lagrange interpolation in some cases
 */

public class NewtonPolynomialInterpolation
        implements InterpolationStrategy
{
    double[][] dataPoints;

    public NewtonPolynomialInterpolation(double[][] dataPoints)
    {
        this.dataPoints = dataPoints;
    }





    private double[] calculateCoefficients(double[] x, double[] y)
    {
        int m = x.length;

        double[] a = y.clone();

        for (int k = 1; k < m; k++)
        {
            for (int i = m - 1; i >= k; i--)
            {
                a[i] = (a[i] - a[i - 1]) / (x[i] - x[i - k]);
            }
        }

        return a;
    }

    @Override
    public double interpolate(double x)
    {
        double[] a = calculateCoefficients(dataPoints[0], dataPoints[1]);
        int n = dataPoints[0].length - 1;
        double p = a[n];

        for (int k = 1; k <= n; k++)
        {
            p = a[n - k] + (x - dataPoints[0][n - k]) * p;
        }

        return p;
    }



}