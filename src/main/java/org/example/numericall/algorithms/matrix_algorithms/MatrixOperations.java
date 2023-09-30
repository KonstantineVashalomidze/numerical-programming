package org.example.numericall.algorithms.matrix_algorithms;

public class MatrixOperations
{





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
