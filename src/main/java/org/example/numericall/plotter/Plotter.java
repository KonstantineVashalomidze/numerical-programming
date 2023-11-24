package org.example.numericall.plotter;

import org.math.plot.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Plotter
{
    // create your PlotPanel (you can use it as a JPanel)
    Plot2DPanel plot;
    JFrame frame = new JFrame("a plot panel");

    public Plotter()
    {
        plot = new Plot2DPanel();

        // put the PlotPanel in a JFrame, as a JPanel
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(20, 20, 1000, 800);
        frame.setVisible(true);
    }


    /**
     * sets the bounds of the axis as desired
     * @param axis 0 is x axis 1 is y axis
     * @param min lower bound
     * @param max upper bound
     */
    public void setAxisBounds(int axis, int min, int max)
    {
        plot.setFixedBounds(axis, min, max);
    }

    // repaints the window
    public void repaint()
    {
        plot.repaint();
    }

    /**
     * draws point on oxy
     * @param legend what title do you want the points to have
     * @param color what color do you want to be the points
     * @param xy array of type [[x1, x2, x3...], [y1, y2, y3...]]
     * @param centerCoordinateXY center coordinate of the legend to appear
     */
    public void drawPoints(String legend, Color color, double[][] xy, double[] centerCoordinateXY)
    {
        // add a line plot to the PlotPanel
        plot.addScatterPlot(legend, color, xy);
        var lastElementIndex = xy.length - 1;
        plot.addLabel(legend, color,  centerCoordinateXY[0] + 2, centerCoordinateXY[1] + 2);
    }

    /**
     * draws point on oxy
     * @param legend what title do you want the points to have
     * @param color what color do you want to be the points
     * @param xy array of type [[x1, x2, x3...], [y1, y2, y3...]]
     */
    public void drawPoints(String legend, Color color, double[][] xy)
    {
        // add a line plot to the PlotPanel
        plot.addScatterPlot(legend, color, xy);
    }


    /**
     * draws line (x1, y1) - (x2, y2)
     * @param legend text to appear on the line
     * @param color color of line
     * @param xy array of type [[x1, x2, x3...], [y1, y2, y3...]]
     * @param centerCoordinateXY coordinate of the label to appear [x1, y1]
     */
    public void drawLine(String legend, Color color, double[][] xy, double[] centerCoordinateXY)
    {
        // draw line between two points
        plot.addLinePlot(legend, xy[0], xy[1]);
        plot.addLabel(legend, color, centerCoordinateXY);
    }


    /**
     * draws line (x1, y1) - (x2, y2)
     * @param legend text to appear on the line
     * @param color color of line
     * @param xy array of type [[x1, x2, x3...], [y1, y2, y3...]]
     */
    public void drawLine(String legend, Color color, double[][] xy)
    {
        // draw line between two points
        plot.addLinePlot(legend, xy[0], xy[1]);
    }


}
