package org.example.edge_detection;

@FunctionalInterface
public interface EdgeDetectionStrategy
{
    /**
     * algorithm to detect the edges grayscale image
     * @return returns the array where 1 indicates edge and 0 indicates no edge
     */
    double[][] detectEdges(double[][] grayScaleImageMatrix);
}
