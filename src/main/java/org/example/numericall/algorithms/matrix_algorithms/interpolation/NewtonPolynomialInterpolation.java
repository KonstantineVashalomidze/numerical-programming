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

    @Override
    public double[] interpolate(double x)
    {
        double y = 0;
        int dataPointsLength = dataPoints[0].length;

        double[] coefficients = computeDividedDifferences(dataPoints);

        for (int i = 0; i < dataPointsLength; i++)
        {
            double term = coefficients[i];
            for (int j = 0; j < i; j++) {
                term *= (x - dataPoints[0][j]);
            }
            y += term;
        }

        return new double[]{y};
    }

    private double[] computeDividedDifferences(double[][] dataPoints)
    {
        int n = dataPoints[0].length;
        double[] dividedDifferences = new double[n];

        for (int i = 0; i < n; i++)
        {
            dividedDifferences[i] = dataPoints[1][i];

            for (int j = i - 1; j >= 0; j--)
            {
                dividedDifferences[j] = (dividedDifferences[j + 1] - dividedDifferences[j])
                        / (dataPoints[0][i] - dataPoints[0][j]);
            }
        }

        return dividedDifferences;
    }
}