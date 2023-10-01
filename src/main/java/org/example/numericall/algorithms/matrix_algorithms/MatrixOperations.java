package org.example.numericall.algorithms.matrix_algorithms;

public class MatrixOperations
{



    public static double calculateFirstNorm(double[][] matrix)
    {
        if (matrix.length < 1)
        {
            return -1;
        }

        int numCols = matrix[0].length;

        double maxColumnSum = Double.NEGATIVE_INFINITY;

        for (int col = 0; col < numCols; col++)
        {
            double columnSum = 0.0;

            for (double[] doubles : matrix)
            {
                columnSum += Math.abs(doubles[col]);
            }

            if (columnSum > maxColumnSum)
            {
                maxColumnSum = columnSum;
            }
        }

        return maxColumnSum;
    }



    public static String toStringMatrix(double[][] matrix)
    {
        StringBuilder buildMatrix = new StringBuilder("");
        for (int i = 0; i < matrix.length; i++)
        {
            buildMatrix.append("[");
            for (int j = 0; j < matrix[0].length; j++)
            {
                if (j != matrix[0].length - 1)
                {
                    buildMatrix.append(matrix[i][j]).append(" ");
                }
                else
                {
                    buildMatrix.append(matrix[i][j]);
                }
            }
            buildMatrix.append("]");
            buildMatrix.append("\n");
        }


        return buildMatrix.toString();
    }

}
