package org.example;

import org.example.edge_detection.EdgeDetectionStrategy;
import org.example.edge_detection.EdgeDetection;
import org.example.numericall.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessing
{
    public static void main(String[] args)
    {

        File file = new File("src/main/resources/img.png");
        double[][][] rgbMatrix = Image.convertImageToMatrixRGB(file);
        BufferedImage bufferedImage = Image.convertRGBMatrixToBufferedImage(rgbMatrix);
        double[][] grayscaleMatrix = Image.convertRGBMatrixToGrayScaleMatrix(rgbMatrix);
        BufferedImage greyscaleBufferedImage = Image.convertGrayscaleMatrixToBufferedImage(grayscaleMatrix);
        Image.imageDisplay(greyscaleBufferedImage);
        Image.imageDisplay(bufferedImage);

        EdgeDetectionStrategy sobelEdgeDetectionAlgorithm = new EdgeDetection();
        double[][] gradientMatrix = sobelEdgeDetectionAlgorithm.detectEdges(grayscaleMatrix, "Prewitt");
        BufferedImage gradientBufferedImage = Image.convertGrayscaleMatrixToBufferedImage(gradientMatrix);
        Image.imageDisplay(gradientBufferedImage);

        BufferedImage zoomedBufferedImage = Image.convertRGBMatrixToBufferedImage(Image.zoomImage(rgbMatrix, 2, 100, 100));
        Image.imageDisplay(zoomedBufferedImage);

        BufferedImage scaldImage = Image.convertRGBMatrixToBufferedImage(Image.scaleImage(rgbMatrix, 0.5));
        Image.imageDisplay(scaldImage);








    }





}
