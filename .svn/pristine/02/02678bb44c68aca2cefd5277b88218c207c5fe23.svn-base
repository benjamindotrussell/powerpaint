/*
 * TCSS 305 - Assignment 5: PowerPaint 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import listenter.Ellipse;
import listenter.Line;
import listenter.Pencil;
import listenter.Rectangle;

/**
 * This class implements the JFrame. 
 * 
 * @author Ben Russell
 * @version 11/5/2015
 */
public class PowerPaintGUI {
    /** A size for the JFrame. */
    private static final Dimension FRAME_SIZE = new Dimension(400, 200);
   
    /** start method will call the various components of the gui together. */
    public void start() {
        setUpPaint();
    }
    /** set up the paint area. */
    private void setUpPaint() {
        final JFrame frame = new JFrame("PowerPaint");
        final ImageIcon image = new ImageIcon("images/paint.gif");
        frame.setIconImage(image.getImage());
        final ImageIcon line = new ImageIcon("images/line.gif");
        final ImageIcon pencil = new ImageIcon("images/pencil.gif");
        final ImageIcon rectangle = new ImageIcon("images/rectangle.gif");
        final ImageIcon ellipse = new ImageIcon("images/ellipse.gif");
        
        final GUIMenuBar menuBar = new GUIMenuBar(frame);
        final GUIToolBar toolBar = new GUIToolBar();
        final PaintPanel panel = new PaintPanel();
        
        final Action[] actions = {new Pencil(panel, pencil)
                                  , new Line(panel, line)
                                  , new Rectangle(panel, rectangle)
                                  , new Ellipse(panel, ellipse)};

        for (final Action action : actions) {
            toolBar.createToolBarButton(action);
        }
        frame.setJMenuBar(menuBar);
        
        // set the close operation to exit when you close the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the size of the frame
        frame.setSize(FRAME_SIZE);
        frame.setLocationRelativeTo(null);
       
        frame.add(panel, BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.SOUTH);
        /** A ToolKit. */
        final Toolkit kit = Toolkit.getDefaultToolkit();
        
        frame.setLocation(
            (int) (kit.getScreenSize().getWidth() / 2 - frame.getWidth() / 2),
            (int) (kit.getScreenSize().getHeight() / 2 - frame.getHeight() / 2));
        frame.pack();
        frame.setVisible(true);
    }

}
