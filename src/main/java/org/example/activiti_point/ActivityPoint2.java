package org.example.activiti_point;

import org.example.numericall.algorithms.matrix_algorithms.interpolation.InterpolationStrategy;
import org.example.numericall.algorithms.matrix_algorithms.interpolation.LinearInterpolation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityPoint2
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
          new Window().setVisible(true);
        });
    }
}


class Window
    extends JFrame
{
    DrawArea drawArea;
    JButton interpolateButton;

    public Window()
    {
        setLayout(new GridLayout(2, 1));
        drawArea = new DrawArea();
        interpolateButton = new JButton("interpolate");

        interpolateButton.addActionListener(e ->
                interpolate());



        drawArea.setBackground(Color.blue);
        getContentPane().add(drawArea);
        getContentPane().add(interpolateButton);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(20, 20, 1_000, 800);
    }

    void interpolate()
    {
        // should apply the interpolation logic
        var individualCurves = drawArea.individualCurves;

        // result is stored here
        List<List<Point>> interpolatedIndividualCurves = new ArrayList<>();


        List<double[][]> individualCurvesFormatted = new ArrayList<>();

        // convert data in desired form
        for (List<Point> individualCurve : individualCurves)
        {
            int jIndx = 0;
            double[][] individualCurveFormatted = new double[2][individualCurve.size()];
            for (Point point : individualCurve)
            {
                individualCurveFormatted[0][jIndx] = point.x;
                individualCurveFormatted[1][jIndx] = point.y;
                jIndx++;
            }
            individualCurvesFormatted.add(individualCurveFormatted);
        }

        for (double[][] individualCurveFormatted : individualCurvesFormatted)
        {
            // apply linear interpolation
            InterpolationStrategy linearInterpolation = new LinearInterpolation(individualCurveFormatted);
            List<Point> interpolatedIndividualCurve = new ArrayList<>();
            for (int j = (int) individualCurveFormatted[0][0]; j < individualCurveFormatted[0][individualCurveFormatted[0].length - 1]; j++)
            {
                var resultOfInterpolationY = linearInterpolation.interpolate(j);
                interpolatedIndividualCurve.add(new Point(j, (int) resultOfInterpolationY));
            }
            interpolatedIndividualCurves.add(interpolatedIndividualCurve);
        }

        drawArea.individualCurves = interpolatedIndividualCurves;
        drawArea.repaint();


    }


}


class DrawArea
    extends JPanel
{

    List<List<Point>> individualCurves;
    List<Point> curve;

    public DrawArea()
    {
        individualCurves = new ArrayList<>();

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mousePressed(e);
                if (SwingUtilities.isLeftMouseButton(e))
                {
                    curve = new ArrayList<>();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                if (SwingUtilities.isLeftMouseButton(e))
                {
                    individualCurves.add(curve);
                    repaint();
                }
            }
        });


        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                super.mouseDragged(e);
                if (SwingUtilities.isLeftMouseButton(e))
                {
                    curve.add(new Point(e.getX(), e.getY()));
                }
            }
        });

    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        var g2 = (Graphics2D) g;
        g2.setPaint(Color.RED);
        for (List<Point> individualCurve : individualCurves)
        {
            for (Point point : individualCurve)
            {
                g2.fillOval(point.x, point.y, 5, 5);
            }
        }


    }

}





