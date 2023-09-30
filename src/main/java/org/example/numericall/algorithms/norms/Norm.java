package org.example.numericall.algorithms.norms;

public class Norm
{



    public static double calculateVectorNorm(double[] vector, int p)
    {
        if (vector.length > 0)
        {
            var sum = 0.;
            for (double v : vector)
            {
                sum += Math.pow(Math.abs(v), p);

            }

            return Math.pow(sum, 1.0 / p);
        }
        else
        {
            return -1.;
        }
    }

    public static double calculateVectorInfNorm(double[] vector)
    {
        if (vector.length > 0)
        {
            var max = Math.abs(vector[0]);
            for (double v : vector)
            {
                if (max < v) {
                    max = Math.abs(v);
                }
            }
            return max;
        }
        else
        {
            return -1.;
        }
    }




}
