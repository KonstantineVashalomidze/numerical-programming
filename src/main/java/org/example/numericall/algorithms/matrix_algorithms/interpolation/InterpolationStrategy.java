package org.example.numericall.algorithms.matrix_algorithms.interpolation;

public interface InterpolationStrategy
{
    /**
     * interpolates the function
     * @return returns y at point x
     */
    double interpolate(double x);
}
