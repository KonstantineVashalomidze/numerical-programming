package org.example;

import org.example.edge_detection.EdgeDetectionStrategy;
import org.example.edge_detection.SobelEdgeDetection;
import org.example.numericall.algorithms.matrix_algorithms.MatrixOperations;
import org.example.numericall.algorithms.vector_algorithms.VectorOperations;
import org.example.numericall.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessing
{
    public static void main(String[] args)
    {
        /*  */
        /*
         example code of using Image class

        File file = new File("src/main/resources/img.png");
        double[][][] rgbMatrix = Image.convertImageToMatrixRGB(file);
        BufferedImage bufferedImage = Image.convertRGBMatrixToBufferedImage(rgbMatrix);
        double[][] grayscaleMatrix = Image.convertRGBMatrixToGrayScaleMatrix(rgbMatrix);
        BufferedImage greyscaleBufferedImage = Image.convertGrayscaleMatrixToBufferedImage(grayscaleMatrix);
        Image.imageDisplay(greyscaleBufferedImage);
        Image.imageDisplay(bufferedImage);

         */

        File file = new File("src/main/resources/img.png");
        double[][][] rgbMatrix = Image.convertImageToMatrixRGB(file);
        BufferedImage bufferedImage = Image.convertRGBMatrixToBufferedImage(rgbMatrix);
        double[][] grayscaleMatrix = Image.convertRGBMatrixToGrayScaleMatrix(rgbMatrix);
        BufferedImage greyscaleBufferedImage = Image.convertGrayscaleMatrixToBufferedImage(grayscaleMatrix);
        Image.imageDisplay(greyscaleBufferedImage);
        Image.imageDisplay(bufferedImage);

        EdgeDetectionStrategy sobelEdgeDetectionAlgorithm = new SobelEdgeDetection();
        double[][] gradientMatrix = sobelEdgeDetectionAlgorithm.detectEdges(grayscaleMatrix);
        BufferedImage gradientBufferedImage = Image.convertGrayscaleMatrixToBufferedImage(gradientMatrix);
        Image.imageDisplay(gradientBufferedImage);










    }





}
