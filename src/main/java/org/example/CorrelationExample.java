package org.example;



import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.example.numericall.functions.MathFunction;

public class CorrelationExample {
    public static void main(String[] args) {
        // Sample data
        double[] variable1 = new double[100];
        double[] variable2 = new double[100];


        MathFunction func = x -> x + 1 - x * Math.sqrt(x);
        for (int i = 0; i < variable1.length; i++)
        {
            variable1[i] = i;
            variable2[i] = func.apply(i);
        }

        // Create a 2D matrix with your data
        RealMatrix matrix = new Array2DRowRealMatrix(new double[][] { variable1, variable2 });

        // Calculate the correlation
        PearsonsCorrelation correlation = new PearsonsCorrelation(matrix);
        RealMatrix correlationMatrix = correlation.getCorrelationMatrix();

        // The correlation between variable1 and variable2 is in the (0, 1) position of the correlation matrix
        double pearsonCorrelation = correlationMatrix.getEntry(0, 1);

        System.out.println("Pearson Correlation Coefficient: " + pearsonCorrelation);
    }
}
