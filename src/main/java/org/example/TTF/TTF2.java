package org.example.TTF;

import org.example.edge_detection.EdgeDetectionStrategy;
import org.example.edge_detection.EdgeDetection;
import org.example.numericall.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;

public class TTF2
{
    public static void main(String[] args)
    {

        /*
        * SEE THE FILE edge_detection SobelEdgeDectection for concrete implementation of edge detection algorithm
        * */

        // read input image
        File file = new File("src/main/resources/img.png");
        // create corresponding matrix of rgb image
        double[][][] rgbMatrix = Image.convertImageToMatrixRGB(file);
        // convert that rgb matrix to image to display it
        BufferedImage bufferedImage = Image.convertRGBMatrixToBufferedImage(rgbMatrix);
        // convert the rgb matrix to grayscale matrix
        double[][] grayscaleMatrix = Image.convertRGBMatrixToGrayScaleMatrix(rgbMatrix);
        // display original image
        Image.imageDisplay(bufferedImage);
        // create edge detection algorithm
        EdgeDetectionStrategy sobelEdgeDetectionAlgorithm = new EdgeDetection();
        // matrix after edge detection
        double[][] gradientMatrix = sobelEdgeDetectionAlgorithm.detectEdges(grayscaleMatrix);
        // convert grayscale matrix to image
        BufferedImage gradientBufferedImage = Image.convertGrayscaleMatrixToBufferedImage(gradientMatrix);
        // display edge detected image
        Image.imageDisplay(gradientBufferedImage);

    }
}
