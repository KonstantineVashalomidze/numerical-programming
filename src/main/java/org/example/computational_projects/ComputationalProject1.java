package org.example.computational_projects;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.*;
import org.example.numericall.image.Image;
import org.example.numericall.plotter.Plotter;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ComputationalProject1
{


    public static void main(String[] args)
    {
        // read image
        File file = new File("src/main/resources/img_2.png");
        // save image as rgb matrix
        double[][][] mainImage = Image.convertImageToMatrixRGB(file);

        // convert matrix to the grayscale matrix
        double[][] grayScaleImage = Image.convertRGBMatrixToGrayScaleMatrix(mainImage);

        // gather function related information from image
        double[][] dataPoints = extractInformation(grayScaleImage);


        // how many evenly distributed data points to pick and interpolate on
        int points = 9;

        var newDataPoints = new double[2][points + 1];
        var counter = 0;
        var spaceBetweenPoints = dataPoints[0].length / points;
        for (int i = 0; i < dataPoints[0].length; i += spaceBetweenPoints)
        {
            newDataPoints[0][counter] = dataPoints[0][i];
            newDataPoints[1][counter] = dataPoints[1][i];
            counter++;
        }

        sortByX(newDataPoints);
        dataPoints = newDataPoints;

        // create plotter to plot data
        Plotter plotter = new Plotter();

        // interpolation using lagrange polynomial
        InterpolationStrategy lagrangePolynomialInterpolation = new LagrangePolynomialInterpolation(dataPoints);

        // interpolation in rage -100 100 inclusive
        double h = 0.1; // h defines how close two x points are
        double lowerBound = newDataPoints[0][0] + h;
        double upperBound = newDataPoints[0][newDataPoints[0].length - 1] - h;
        double[][] lagrangeInterpolatedData = new double[2][(int) (Math.abs((upperBound - lowerBound)) / h) + 1];


        // index
        int index = 0;
        for (double i = lowerBound; i <= upperBound; i += h)
        {
            lagrangeInterpolatedData[0][index] = i;
            lagrangeInterpolatedData[1][index] = lagrangePolynomialInterpolation.interpolate(i);
            index++;
        }
        System.out.println(Arrays.toString(dataPoints[0]));
        System.out.println(Arrays.toString(dataPoints[1]));
        // make index 0 for further usage
        index = 0;

        // plot lagrange polynomial interpolated data
        plotter.drawPoints("linear", Color.GREEN, lagrangeInterpolatedData);
        plotter.drawPoints("points", Color.RED, dataPoints);

        // set bounds for x axis
        plotter.setAxisBounds(0, -200, 200);
        plotter.setAxisBounds(1, -200, 200);

        // repaint
        plotter.repaint();





    }


    // Method to sort the double array based on x values
    public static void sortByX(double[][] array)
    {
        int n = array[0].length;

        // Create an array of indices and sort it based on the x values
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++)
        {
            indices[i] = i;
        }

        // Custom sorting based on x values
        Arrays.sort(indices, Comparator.comparingDouble(a -> array[0][a]));

        // Rearrange the x and y values according to the sorted indices
        double[][] sortedArray = new double[2][n];
        for (int i = 0; i < n; i++)
        {
            sortedArray[0][i] = array[0][indices[i]]; // Sorted x values
            sortedArray[1][i] = array[1][indices[i]]; // Corresponding y values
        }

        // Update the original array with the sorted values
        for (int i = 0; i < n; i++)
        {
            array[0][i] = sortedArray[0][i];
            array[1][i] = sortedArray[1][i];
        }
    }



    public static double[][] extractInformation(double[][] grayScaleImage)
    {
        // matrix dimensions
        var rowCount = grayScaleImage.length;
        var colCount = grayScaleImage[0].length;
        // we save x and y pairs extracted here
        var x = new ArrayList<Integer>();
        var y = new ArrayList<Integer>();

        // collect information
        for (int i = 0; i < rowCount; i++)
        {
            for (int j = 0; j < colCount; j++)
            {
                // if grayscale value is black collect the data
                if (grayScaleImage[i][j] < 75)
                {
                    x.add(j);
                    y.add(i);
                }
            }
        }

        double[][] dataPoints = new double[2][x.size()];
        // format the data
        for (int i = 0; i < x.size(); i++)
        {
            dataPoints[0][i] = x.get(i);
            dataPoints[1][i] = y.get(i);
        }

        return dataPoints;

    }






}
