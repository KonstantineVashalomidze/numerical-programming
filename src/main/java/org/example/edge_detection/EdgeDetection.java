package org.example.edge_detection;

public class EdgeDetection
    implements EdgeDetectionStrategy
{
    private double[][] horizontalKernelMatrix =
            {
                    new double[] { 1, 0, -1 },
                    new double[] { 2, 0, -2 },
                    new double[] { 1, 0, -1 }
            };

    private double[][] verticalKernelMatrix =
            {
                    new double[] { 1, 2, 1 },
                    new double[] { 0, 0,  0 },
                    new double[] { -1, -2, -1 }
            };


    /**
     * applies edge detection algorithm
     * @param grayScaleImageMatrix grayscale image representation as matrix
     * @param using who's kernel do we want to apply, Sobel, Prewitt, Scharr
     * @return return the edge detected matrix
     */
    @Override
    public double[][] detectEdges(double[][] grayScaleImageMatrix, String using)
    {

        if (using.equals("Prewitt"))
        {
            this.horizontalKernelMatrix = new double[][]
                    {
                            new double[] { -3, 0, 3 },
                            new double[] { -10, 0, 10 },
                            new double[] { -3, 0, 3 }
                    };

            this.verticalKernelMatrix = new double[][]
                    {
                            new double[] { -3, -10, -3 },
                            new double[] { 0, 0,  0 },
                            new double[] { 3, 10, 3 }
                    };
        }

        if (using.equals("Scharr"))
        {
            this.horizontalKernelMatrix = new double[][]
                    {
                            new double[] { -1, 0, 1},
                            new double[] { -1, 0, 1 },
                            new double[] { -1, 0, 1}
                    };

            this.verticalKernelMatrix = new double[][]
                    {
                            new double[] { -1, -1, -1 },
                            new double[] { 0, 0,  0 },
                            new double[] { 1, 1, 1 }
                    };
        }


        if (grayScaleImageMatrix.length < 1)
            throw new IndexOutOfBoundsException();

        int rowLength = grayScaleImageMatrix[0].length - 1;
        int columnLength = grayScaleImageMatrix.length - 1;
        double[][] outputImage = new double[columnLength + 1][rowLength + 1];

        for (int i = 1; i < columnLength - 1; i++)
        {
            // apply gx gy to the matrix
            for (int j = 1; j < rowLength - 1; j++)
            {
                double gx = (
                            grayScaleImageMatrix[i - 1][j - 1] * horizontalKernelMatrix[0][0] +
                            grayScaleImageMatrix[i - 1][j] * horizontalKernelMatrix[0][1] +
                            grayScaleImageMatrix[i - 1][j + 1] * horizontalKernelMatrix[0][2]
                        ) +
                         (
                            grayScaleImageMatrix[i][j - 1] * horizontalKernelMatrix[1][0] +
                            grayScaleImageMatrix[i][j] * horizontalKernelMatrix[1][1] +
                            grayScaleImageMatrix[i][j + 1] * horizontalKernelMatrix[1][2]
                        ) +
                         (
                            grayScaleImageMatrix[i + 1][j - 1] * horizontalKernelMatrix[2][0] +
                            grayScaleImageMatrix[i + 1][j] * horizontalKernelMatrix[2][1] +
                            grayScaleImageMatrix[i + 1][j + 1] * horizontalKernelMatrix[2][2]
                        );

                double gy = (
                            grayScaleImageMatrix[i - 1][j - 1] * verticalKernelMatrix[0][0] +
                            grayScaleImageMatrix[i - 1][j] * verticalKernelMatrix[0][1] +
                            grayScaleImageMatrix[i - 1][j + 1] * verticalKernelMatrix[0][2]
                        ) +
                         (
                            grayScaleImageMatrix[i][j - 1] * verticalKernelMatrix[1][0] +
                            grayScaleImageMatrix[i][j] * verticalKernelMatrix[1][1] +
                            grayScaleImageMatrix[i][j + 1] * verticalKernelMatrix[1][2]
                        ) +
                         (
                            grayScaleImageMatrix[i + 1][j - 1] * verticalKernelMatrix[2][0] +
                            grayScaleImageMatrix[i + 1][j] * verticalKernelMatrix[2][1] +
                            grayScaleImageMatrix[i + 1][j + 1] * verticalKernelMatrix[2][2]
                        );
                // computation of the gradient
                double gradientMagnitude = Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2));
                outputImage[i][j] = gradientMagnitude;
            }
        }

        return outputImage;

    }

    @Override
    public double[][] detectEdges(double[][] grayScaleImageMatrix)
    {
        return detectEdges(grayScaleImageMatrix, "Sobel");
    }
}
