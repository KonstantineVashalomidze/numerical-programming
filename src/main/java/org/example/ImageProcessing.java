package org.example;

import org.example.numericall.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessing
{
    public static void main(String[] args)
    {
        /* example code of using Image class */

        File file = new File("src/main/resources/img.png");
        double[][][] rgbMatrix = Image.convertImageToMatrixRGB(file);
        BufferedImage bufferedImage = Image.convertRGBMatrixToBufferedImage(rgbMatrix);
        double[][] grayscaleMatrix = Image.convertRGBMatrixToGrayScaleMatrix(rgbMatrix);
        BufferedImage greyscaleBufferedImage = Image.convertGrayscaleMatrixToBufferedImage(grayscaleMatrix);
        Image.imageDisplay(greyscaleBufferedImage);
        Image.imageDisplay(bufferedImage);


    }





}
