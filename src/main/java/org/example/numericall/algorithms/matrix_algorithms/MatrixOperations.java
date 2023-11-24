package org.example.numericall.algorithms.matrix_algorithms;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.LagrangePolynomialInterpolation;

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


    /**
     * Solves a tridiagonal matrix equation using the Thomas algorithm.
     *
     * @param matrix the tridiagonal matrix represented as a 2D array of doubles
     * @param vector the vector representing the right-hand side of the equation
     * @return the solution array of doubles if successful, or null if the algorithm is not applicable or input is invalid
     */
    public static double[] solveTridiagonalMatrixUsingThomas(double[][] matrix, double[] vector)
    {
        // If matrix or vector is empty, return null
        if (matrix.length == 0 || vector.length == 0 || matrix.length != vector.length)
        {
            System.out.println("Invalid input: Empty matrix or vector or mismatched dimensions");
            return null;
        }

        int dimension = matrix.length;

        // Create copies of the matrix and vector
        double[][] matrixCopy = new double[dimension][dimension];
        double[] vectorCopy = new double[dimension];
        for (int i = 0; i < dimension; i++)
        {
            System.arraycopy(matrix[i], 0, matrixCopy[i], 0, dimension);
            vectorCopy[i] = vector[i];
        }

        // If matrix is not a tridiagonal matrix, return null
        for (int i = 0; i < dimension; i++)
        {
            if (Math.abs(i - (i > 0 ? 1 : 0)) > 1 && !isNearlyZero(matrixCopy[i][i - 2]))
            {
                System.out.println("Matrix is not tridiagonal");
                return null;
            }
        }

        double[] solution = new double[dimension];
        double[] cPrime = new double[dimension];
        double[] dPrime = new double[dimension];

        cPrime[0] = matrixCopy[0][1] / matrixCopy[0][0];
        dPrime[0] = vectorCopy[0] / matrixCopy[0][0];

        for (int i = 1; i < dimension - 1; i++)
        {
            double m = 1.0 / (matrixCopy[i][i] - matrixCopy[i][i - 1] * cPrime[i - 1]);
            cPrime[i] = matrixCopy[i][i + 1] * m;
            dPrime[i] = (vectorCopy[i] - matrixCopy[i][i - 1] * dPrime[i - 1]) * m;
        }

        dPrime[dimension - 1] = (vectorCopy[dimension - 1] - matrixCopy[dimension - 1][dimension - 2] * dPrime[dimension - 2]) /
                (matrixCopy[dimension - 1][dimension - 1] - matrixCopy[dimension - 1][dimension - 2] * cPrime[dimension - 2]);

        solution[dimension - 1] = dPrime[dimension - 1];
        for (int i = dimension - 2; i >= 0; i--)
        {
            solution[i] = dPrime[i] - cPrime[i] * solution[i + 1];
        }

        return solution;
    }



    /**
     * Solves a system of linear equations represented by a matrix using Cramer's Rule.
     *
     * @param matrix the coefficient matrix of the system of equations
     * @param vector the vector representing the right-hand side of the equation
     * @return the solution array of doubles if successful, or null if the matrix is singular or input is invalid
     */
    public static double[] solveLinearSystemUsingCramersRule(double[][] matrix, double[] vector)
    {
        if (matrix.length == 0 || vector.length == 0 || matrix.length != vector.length)
        {
            System.out.println("Invalid input: Empty matrix or vector or mismatched dimensions");
            return null;
        }

        int dimension = matrix.length;
        double determinant = determinant(matrix);

        if (determinant == 0)
        {
            System.out.println("Matrix is singular, Cramer's Rule cannot be applied");
            return null;
        }

        double[] solution = new double[dimension];
        for (int i = 0; i < dimension; i++)
        {
            // Create a copy of the matrix for modification
            double[][] modifiedMatrix = new double[dimension][dimension];
            for (int j = 0; j < dimension; j++)
            {
                System.arraycopy(matrix[j], 0, modifiedMatrix[j], 0, dimension);
            }

            // Replace the column with the vector in the copy
            for (int j = 0; j < dimension; j++)
            {
                modifiedMatrix[j][i] = vector[j];
            }

            double modifiedDeterminant = determinant(modifiedMatrix);
            solution[i] = modifiedDeterminant / determinant;
        }

        return solution;
    }


    // Method to calculate the determinant of a square matrix using recursive approach
    private static double determinant(double[][] matrix)
    {
        int dimension = matrix.length;
        if (dimension == 1)
        {
            return matrix[0][0];
        }
        double det = 0;
        int sign = 1;
        for (int i = 0; i < dimension; i++)
        {
            double[][] subMatrix = new double[dimension - 1][dimension - 1];
            for (int j = 1; j < dimension; j++)
            {
                int k = 0;
                for (int l = 0; l < dimension; l++)
                {
                    if (l != i) {
                        subMatrix[j - 1][k++] = matrix[j][l];
                    }
                }
            }
            det += sign * matrix[0][i] * determinant(subMatrix);
            sign = -sign;
        }
        return det;
    }

    // Method to replace a column in a matrix with a vector
    private static double[][] replaceColumn(double[][] matrix, double[] vector, int columnIndex) {
        int dimension = matrix.length;
        double[][] result = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result[i][j] = (j == columnIndex) ? vector[i] : matrix[i][j];
            }
        }
        return result;
    }


    public static double[][][] performLUDecomposition(double[][] matrix)
    {
        int n = matrix.length;
        if (n == 0)
        {
            System.out.println("Empty matrix");
            return null;
        }

        double[][] lower = new double[n][n];
        double[][] upper = new double[n][n];

        // Decomposing matrix into Upper and Lower
        // triangular matrix
        for (int i = 0; i < n; i++)
        {
            // Upper Triangular
            for (int k = i; k < n; k++)
            {
                // Summation of L(i, j) * U(j, k)
                int sum = 0;
                for (int j = 0; j < i; j++)
                    sum += (lower[i][j] * upper[j][k]);

                // Evaluating U(i, k)
                upper[i][k] = matrix[i][k] - sum;
            }

            // Lower Triangular
            for (int k = i; k < n; k++)
            {
                if (i == k)
                    lower[i][i] = 1; // Diagonal as 1
                else
                {
                    // Summation of L(k, j) * U(j, i)
                    int sum = 0;
                    for (int j = 0; j < i; j++)
                        sum += (lower[k][j] * upper[j][i]);

                    // Evaluating L(k, i)
                    lower[k][i]
                            = (matrix[k][i] - sum) / upper[i][i];
                }
            }
        }


        return new double[][][] { lower, upper };


    }


    /**
     * Solves a system of linear equations represented by an ordinary matrix using Gaussian Elimination.
     *
     * @param matrix the matrix of coefficients of the system of equations
     * @param vector the vector representing the right-hand side of the equation
     * @return the solution array of doubles if successful, or null if the matrix is singular or input is invalid
     */
    public static double[] solveLinearSystemUsingGaussianElimination(double[][] matrix, double[] vector)
    {
        if (matrix.length == 0 || vector.length == 0 || matrix.length != vector.length)
        {
            System.out.println("Invalid input: Empty matrix or vector or mismatched dimensions");
            return null;
        }

        int dimension = matrix.length;

        // Create copies of the matrix and vector
        double[][] matrixCopy = new double[dimension][dimension];
        double[] vectorCopy = new double[dimension];
        for (int i = 0; i < dimension; i++)
        {
            System.arraycopy(matrix[i], 0, matrixCopy[i], 0, dimension);
            vectorCopy[i] = vector[i];
        }

        // Forward Elimination Phase on the copies
        for (int pivot = 0; pivot < dimension - 1; pivot++)
        {
            if (matrixCopy[pivot][pivot] == 0)
            {
                System.out.println("Matrix is singular, cannot proceed with Gaussian Elimination");
                return null;
            }
            for (int i = pivot + 1; i < dimension; i++)
            {
                double factor = matrixCopy[i][pivot] / matrixCopy[pivot][pivot];
                for (int j = pivot; j < dimension; j++)
                {
                    matrixCopy[i][j] -= factor * matrixCopy[pivot][j];
                }
                vectorCopy[i] -= factor * vectorCopy[pivot];
            }
        }

        // Back Substitution Phase on the copies
        double[] solution = new double[dimension];
        for (int i = dimension - 1; i >= 0; i--)
        {
            double sum = 0;
            for (int j = i + 1; j < dimension; j++)
            {
                sum += matrixCopy[i][j] * solution[j];
            }
            solution[i] = (vectorCopy[i] - sum) / matrixCopy[i][i];
        }

        return solution;
    }





    // Utility method to check if a value is nearly zero
    private static boolean isNearlyZero(double value)
    {
        return Math.abs(value) < 1e-10; // Adjust tolerance as needed
    }



}
