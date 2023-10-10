package org.example.numericall.image;

import org.example.numericall.algorithms.vector_algorithms.VectorOperations;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

public class Image
{
    /**
     * converts image to the rgb matrix
     * @param file File object of the image
     * @return double[][][] rgbMatrix
     */
    public static double[][][] convertImageToMatrixRGB(File file)
    {

        try
        {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage != null)
            {
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();

                double[][][] imageMatrix = new double[height][width][3]; // RGB components

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int pixel = bufferedImage.getRGB(x, y);

                        // Extract RGB components
                        double red = (pixel >> 16) & 0xFF;
                        double green = (pixel >> 8) & 0xFF;
                        double blue = pixel & 0xFF;

                        // Store RGB values in the matrix
                        imageMatrix[y][x][0] = red;
                        imageMatrix[y][x][1] = green;
                        imageMatrix[y][x][2] = blue;
                    }
                }


                // Now 'imageMatrix' contains the RGB values of the image as a 3D matrix.
                // You can process or analyze the image data as needed.

                return imageMatrix;
            }

            else
            {
                throw new IOException("Failed to read the image.");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        throw new RuntimeException("Something went wrong in class 'Image' method 'convertImageToMatrixRGB' ");
    }

    /**
     * this method converts rgbmatrix to the buffered image
     * @param imageMatrix rgbMatrix of type double
     * @return corresponding buffered image
     */
    public static BufferedImage convertRGBMatrixToBufferedImage(double[][][] imageMatrix) {
        int height = imageMatrix.length;
        int width = imageMatrix[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int red = (int) imageMatrix[y][x][0];
                int green = (int) imageMatrix[y][x][1];
                int blue = (int) imageMatrix[y][x][2];

                int rgb = (red << 16) | (green << 8) | blue;
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;
    }


    /**
     * converts rgb matrix to grey scale matrix and returns it
     * @param rgbMatrix rgb matrix
     * @return greyscale matrix
     */
    public static double[][] convertRGBMatrixToGrayScaleMatrix(double[][][] rgbMatrix)
    {
        final int columnCount = rgbMatrix.length;
        if (columnCount < 1)
        {
            return new double[][] { };
        }
        final int rowCount = rgbMatrix[0].length;
        double[][] greyScaleMatrix = new double[columnCount][rowCount];
        for (int i = 0; i < columnCount; i++)
        {
            for (int j = 0; j < rowCount; j++)
            {
                greyScaleMatrix[i][j] = VectorOperations.mapRGBVectorToGreyscale(rgbMatrix[i][j]);
            }
        }

        return greyScaleMatrix;
    }




    /**
     * this method converts rgbmatrix into the string representation
     * @param rgbMatrix rgb matrix of type double
     * @return string representation of the rgbmatrix
     */
    public static String RGBMatrixToString(double[][][] rgbMatrix)
    {
        StringBuilder builder = new StringBuilder("");

        for (double[][] column : rgbMatrix)
        {
            builder.append("[");
            int rowLength = column.length;
            for (int row = 0; row < rowLength; row++)
            {
                if (row != rowLength - 1)
                {
                    builder.append(Arrays.toString(column[row])).append(", ");
                }
                else
                {
                    builder.append(Arrays.toString(column[row]));
                }
            }
            builder.append("]");
            builder.append("\n");


        }
        return builder.toString();

    }


    /**
     * converts grayscale matrix to the buffered image
     * @param imageMatrix grayscale matrix
     * @return buffered image of that matrix
     */
    public static BufferedImage convertGrayscaleMatrixToBufferedImage(double[][] imageMatrix)
    {
        int height = imageMatrix.length;
        int width = imageMatrix[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int gray = (int) imageMatrix[y][x];

                // Create an RGB color with the same grayscale value for all components
                int rgb = (gray << 16) | (gray << 8) | gray;

                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;
    }



    /**
     * this method displays JFrame with image
     * @param bufferedImage buffered image of image
     */
    public static void imageDisplay(BufferedImage bufferedImage)
    {
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        JLabel image = new JLabel(new ImageIcon(bufferedImage));
        JLabel infoLabel = new JLabel();
        contentPane.add(infoLabel);
        image.addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            if (x >= 0 && x < bufferedImage.getWidth() && y >= 0 && y < bufferedImage.getHeight()) {
                int rgb = bufferedImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                infoLabel.setText("RGB: " + red + ", " + green + ", " + blue + " GrayScale "
                        + VectorOperations.mapRGBVectorToGreyscale(new double[] { red, green, blue }));
            } else {
                infoLabel.setText(""); // Clear the info label if mouse is outside the image
            }
        }
        });
        contentPane.add(image);
        frame.pack();
        frame.setVisible(true);
    }




    /**
     * detects where the edges are
     * @param rgbImageMatrix matrix of which the edge detection should happen
     * @return array of corresponding matrix with two values 1, 0; 1 if there is edge 0 if no edge is found
     */
    public static double[][][] detectedEdges(double rgbImageMatrix)
    {
        return null;
    }




}
