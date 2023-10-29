package org.example.numericall.algorithms.matrix_algorithms.interpolation;

/**
 * Simple and computationally efficient.
 * Connects two data points with a straight line.
 * Suitable for data with a linear trend between points.
 */
public class LinearInterpolation
    implements InterpolationStrategy
{
    private double[][] dataPoints;

    /**
     *
     * @param dataPoints points of the function that should be interpolated ([[x1, x2, x3....], [y1, y2, y3...]])
     */
    public LinearInterpolation(double[][] dataPoints)
    {
        this.dataPoints = dataPoints;
    }


    @Override
    public double interpolate(double x)
    {
        double y = 0; // should be enough
        int length = dataPoints[0].length;
        int counter = 0;
        for (int i = 0; i < length - 1; i++)
        {
            double x0 = dataPoints[0][i];
            double x1 = dataPoints[0][i + 1];
            double y0 = dataPoints[1][i];
            double y1 = dataPoints[1][i + 1];

            if (x0 <= x && x1 >= x)
            {
                y = (y0 * (x1 - x) + y1 * (x - x0)) / (x1 - x0);
            }
        }

        return y;

    }
}
