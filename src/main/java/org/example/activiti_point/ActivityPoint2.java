package org.example.activiti_point;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.LinearInterpolation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;

/**
 * Connecting points drawn by the MouseListener, but they aren't connected due to the delay of the MouseListener.
 */
public class ActivityPoint2
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
                new Window().setVisible(true));
    }

}



class Window
    extends JFrame
{

    public Window()
    {
        setLayout(new GridLayout(2, 1));
        var interpolateButton = new Button("interpolate");
        var drawingArea = new DrawingArea();
        add(drawingArea);
        interpolateButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                double[][] reducedArray = new double[2][drawingArea.indexCounter + 1];
                for (int i = 0; i < drawingArea.indexCounter + 1; i++)
                {
                    reducedArray[0][i] = drawingArea.dataPoints[0][i];
                    reducedArray[1][i] = drawingArea.dataPoints[1][i];
                }
                var linearInterpolation = new LinearInterpolation(reducedArray);
                for (int i = 0; i < 800; i++)
                {
                    var y = linearInterpolation.interpolate(i);
                    for (int j = 0; j < y.length; j++)
                    {
                        drawingArea.dataPoints[0][i + drawingArea.indexCounter + 1] = i;
                        drawingArea.dataPoints[1][i + drawingArea.indexCounter + 1] = y[j];
                    }
                }
                drawingArea.interpolateButtonIsClicked = true;
                repaint();

            }
        });
        add(interpolateButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(20, 20, 800, 800);
        setResizable(false);
    }


}


class DrawingArea
    extends JPanel
{
    // set of data points [[x1, x2, ...], [y1, y2, ...]]
    double[][] dataPoints = new double[2][800 * 800];
    boolean interpolateButtonIsClicked = false;
    int[] currentDataPoint = { 0, 0 };

    // global counter for inserting elements at index
    int indexCounter = 0;


    public DrawingArea()
    {
        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                super.mouseDragged(e);
                currentDataPoint[0] = e.getX();
                currentDataPoint[1] = e.getY();
                dataPoints[0][indexCounter] = e.getX();
                dataPoints[1][indexCounter] = e.getY();
                indexCounter++;
                repaint();
            }
        });
    }


    @Override
    public void paintComponent(Graphics g)
    {
        var g2 = (Graphics2D) g;
        g2.setPaint(Color.red);
        g2.fillOval(currentDataPoint[0], currentDataPoint[1], 5, 5);
        if (interpolateButtonIsClicked)
        {
            for (int i = 0; i < dataPoints.length; i++)
            {
                g2.fillOval((int) dataPoints[0][i], (int) dataPoints[1][i], 5, 5);
            }
        }

    }

}





