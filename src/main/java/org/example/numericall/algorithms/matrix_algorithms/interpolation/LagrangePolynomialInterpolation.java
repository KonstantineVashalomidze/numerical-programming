package org.example.numericall.algorithms.matrix_algorithms.interpolation;


import org.example.numericall.functions.MathFunction;

/**
 * Fits a polynomial curve through data points.
 * Higher-degree polynomials can capture more complex relationships.
 * May lead to overfitting with high-degree polynomials.
 */
public class LagrangePolynomialInterpolation
    implements InterpolationStrategy
{
    double[][] dataPoints;

    public LagrangePolynomialInterpolation(double[][] dataPoints)
    {
        this.dataPoints = dataPoints;

    }


    @Override
    public double interpolate(double x)
    {
        double y = 0;
        int dataPointsLength = dataPoints[0].length;
        for (int i = 0; i < dataPointsLength; i++)
        {
            y += dataPoints[1][i] * lix(i, x, dataPointsLength);
        }

        return y;
    }

    private double lix(int i, double x, int dataPointsLength)
    {
        double product = 1.;
        for (int j = 0; j < dataPointsLength; j++)
        {
            if (i != j)
            {
                double xj = dataPoints[0][j];
                product *= (x - xj) / (dataPoints[0][i] - xj);
            }
        }
        return product;
    }



}
