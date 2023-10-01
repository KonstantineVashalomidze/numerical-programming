package org.example.numericall.plotter;

import org.math.plot.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Plotter
{
    // create your PlotPanel (you can use it as a JPanel)
    Plot2DPanel plot = new Plot2DPanel();
    JFrame frame = new JFrame("a plot panel");

    public Plotter()
    {
        // put the PlotPanel in a JFrame, as a JPanel
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(20, 20, 1000, 800);
        frame.setVisible(true);
    }


    public void drawPoints(String legend, Color color, double[][] xy, double[] centerCoordinateXY)
    {
        // add a line plot to the PlotPanel
        plot.addScatterPlot(legend, color, xy);
        var lastElementIndex = xy.length - 1;
        plot.addLabel(legend, color,  centerCoordinateXY[0] + 2, centerCoordinateXY[1] + 2);
    }

    public void drawLine(String legend, Color color, double[][] xy, double[] centerCoordinateXY)
    {
        // draw line between two points
        plot.addLinePlot(legend, xy[0], xy[1]);
        plot.addLabel(legend, color, centerCoordinateXY);
    }


}
