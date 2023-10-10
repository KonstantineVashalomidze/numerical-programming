package org.example;

import org.example.edge_detection.EdgeDetectionStrategy;
import org.example.edge_detection.SobelEdgeDetection;
import org.example.numericall.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessing
{
    public static void main(String[] args)
    {

        File file = new File("src/main/resources/img_1.png");
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
