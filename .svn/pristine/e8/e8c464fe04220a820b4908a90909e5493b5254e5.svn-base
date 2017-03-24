/*
 * TCSS 305 - Assignment 5: PowerPaint
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * class that implements the JPanel.
 * 
 * @author Ben Russell
 * @version 11/5/2015
 */
public class PaintPanel extends JPanel {
    /** Constant used to keep track of the tool being used. */
    private static final int PENCIL = 1;
    /** Constant used to keep track of the tool being used. */
    private static final int LINE = 2;
    /** Constant used to keep track of the tool being used. */
    private static final int RECTANGLE = 3;
    /** Constant used to keep track of the tool being used. */
    private static final int CIRCLE = 4;
    /** switches between lines and circles. */
    private static int myToolChecker;
    /** A generated serialization ID. */
    private static final long serialVersionUID = 810958738395164242L;    
    /** A size for the JPanel. */
    private static final Dimension PANEL_SIZE = new Dimension(400, 200);
    /** to set the distance apart for the grid squares. */
    private static final int GRID_DIST = 10;
    /** The start point for the Shape. */
    private Point myStartPoint;
    /** The end point for the Shape. */
    private Point myEndPoint;
    /** The path being created. */
    private Path2D myPath;
    /** holds the path of the rectangle.  */
    private Rectangle2D myRectanglePath;
    /** holds the path of the line. */
    private Path2D myLinePath;
    /** holds the image on the panel. */
    private Image myImage;
    /** a fresh image to replace the old one when the undo button is used. */
    private BufferedImage myCleanImage;   
    /** check for the sate of the undo button. */
    private boolean myUndo;
    /** holds the cursor so it can be changed. */
    private Cursor myCursor;
    /**
     * Constructor for the panel.
     */
    public PaintPanel() {
        super();
        setPreferredSize(PANEL_SIZE);
        setUpPanel();
    }
    /**
     * sets up the panel and initializes fields.
     * 
     */
    private void setUpPanel() {
        setBackground(Color.WHITE);
        myLinePath = new GeneralPath();
        myPath = new GeneralPath();
        myPath.setWindingRule(GeneralPath.WIND_EVEN_ODD);
        myImage = new BufferedImage(this.getWidth() + 1, this.getHeight() + 1 
                                    , BufferedImage.TYPE_INT_RGB);
        myCleanImage = new BufferedImage(this.getWidth() + 1, this.getHeight() + 1 
                                    , BufferedImage.TYPE_INT_RGB);
        myToolChecker = 1;
        setMyUndo(false);
        final MouseAdapter mouseAdapter = new PaintPanelMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }
    /**
     * Records the start point for a shape.
     * 
     * @param thePoint the start point of a shape.
     */
    public void recordStartPoint(final Point thePoint) {
        myStartPoint = (Point) thePoint.clone();
        repaint();
    }
    /**
     * Records the current position of the mouse during a drag.
     * 
     * @param thePoint the current point of the shape
     */
    public void recordDragPoint(final Point thePoint) {
        myEndPoint = (Point) thePoint.clone();
        repaint();
        
    }
    /** 
     * sets the value of the toolChecker based on the tool that is selected.
     * 
     * @param theTool the current tool
     */
    public static void setToolChecker(final int theTool) {
        myToolChecker = theTool;
    }
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        if (myUndo) {
            GUIMenuBar.setMyUndo(true);
        }
        undo();
        final Graphics2D graphics = (Graphics2D) theGraphics;
        drawAll(theGraphics);
        graphics.setColor(GUIMenuBar.myColor);
        graphics.setStroke(new BasicStroke(GUIMenuBar.getMyThickness()));
        
        if (GUIMenuBar.isGrid) {
            for (int i = 0; i < getWidth(); i += GRID_DIST) {
                graphics.drawLine(i, 0, i, getHeight());                
            }
            for (int i = 0; i < getHeight(); i += GRID_DIST) {
                graphics.drawLine(0, i, getWidth(), i);                
            }
        }
        repaint();
        // draw the selected shape
        if (myToolChecker == PENCIL) {
            graphics.draw(myPath);  
            repaint();
        } else if (myToolChecker == LINE) { 
            drawALine(theGraphics);            
        } else if (myToolChecker == RECTANGLE) {
            drawRectangle(theGraphics);
        } else if (myToolChecker == CIRCLE) {
            drawEllipse(theGraphics);  
        }
    }

    /** 
     * Draw all the shapes that are saved thus far.
     * 
     * @param theGraphics the graphics.
     */
    private void drawAll(final Graphics theGraphics) {
        
        final Graphics2D graphics = (Graphics2D) theGraphics;
        graphics.drawImage(myImage, 0, 0, null);
        repaint();
    }
    /**
     * draws a line.
     * @param theGraphics the graphics passed from the paintComponent.
     */
    private void drawALine(final Graphics theGraphics) {
        final Graphics2D graphics = (Graphics2D) theGraphics;
        graphics.draw(myLinePath);
        repaint();
    }
    /**
     * draws a rectangle.
     * @param theGraphics the graphics passed from the paintComponent.
     */
    private void drawRectangle(final Graphics theGraphics) {
        final Graphics2D graphics = (Graphics2D) theGraphics;
        if (myRectanglePath != null) {
            graphics.draw(myRectanglePath);
        }
        repaint();
    }
    /**
     * draws a circle.
     * @param theGraphics the graphics passed from the paintComponent.
     */
    private void drawEllipse(final Graphics theGraphics) {
        
        final Graphics2D graphics = (Graphics2D) theGraphics;
        if (myStartPoint != null && myEndPoint != null) {
            if (myStartPoint.x > myEndPoint.x && myStartPoint.y > myEndPoint.y) {
                graphics.drawOval(myEndPoint.x, myEndPoint.y
                              , myStartPoint.x - myEndPoint.x
                              , myStartPoint.y - myEndPoint.y);
            } else if (myStartPoint.x > myEndPoint.x) {
                graphics.drawOval(myEndPoint.x, myStartPoint.y
                                  , myStartPoint.x - myEndPoint.x
                                  , myEndPoint.y - myStartPoint.y);
            } else if (myStartPoint.y > myEndPoint.y) {
                graphics.drawOval(myStartPoint.x, myEndPoint.y
                                  , myEndPoint.x - myStartPoint.x
                                  , myStartPoint.y - myEndPoint.y);
            } else {
                graphics.drawOval(myStartPoint.x, myStartPoint.y
                                  , myEndPoint.x - myStartPoint.x 
                                  , myEndPoint.y - myStartPoint.y);
            }
        }
    }
    /**
     * method to clear the image when the undoAll button is used. 
     */
    private void undo() {
        if (GUIMenuBar.myUndoAll) {
//            final Graphics2D g = ((BufferedImage) myImage).createGraphics();
//            g.setBackground(Color.WHITE);
//            g.clearRect(0, 0, getWidth(), getHeight());
//            g.dispose();
//            repaint();
            myImage = myCleanImage;
        }
    }  
    /**
     * @param theUndo the myUndo to set
     */
    public void setMyUndo(final boolean theUndo) {
        this.myUndo = theUndo;
    }
    /** 
     * Mouse listener for the paint panel.
     * 
     * @author ben russell
     * @version 11/18/2015
     */
    public class PaintPanelMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            recordStartPoint(theEvent.getPoint());
            myPath.moveTo(theEvent.getPoint().x, theEvent.getPoint().y);
            repaint();
        } 
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            recordDragPoint(theEvent.getPoint());
            myLinePath.reset();
            myLinePath.moveTo(myStartPoint.x, myStartPoint.y);
            myPath.lineTo(theEvent.getPoint().x, theEvent.getPoint().y);
            myLinePath.lineTo(theEvent.getPoint().x, theEvent.getPoint().y);
            
            if (myToolChecker == RECTANGLE) {                
                myRectanglePath = myPath.getBounds2D();
            }
        }
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            
            if (myToolChecker == LINE) {
                myLinePath.lineTo(theEvent.getPoint().x, theEvent.getPoint().y);
                repaint();
            }
            final BufferedImage image = new BufferedImage(getWidth(), getHeight()
                                                          , BufferedImage.TYPE_INT_RGB);
            paintAll(image.getGraphics());
            myImage = image;
            myPath.reset();
            myLinePath.reset();
            myStartPoint = myEndPoint;
        }
        @Override
        public void mouseEntered(final MouseEvent theEvent) {            
            myCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
            setCursor(myCursor);
            repaint();
        }
    }
    
}
