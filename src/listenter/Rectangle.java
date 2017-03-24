/*
 * TCSS 305 - Assignment 5: PowerPaint 
 */
package listenter;

import gui.PaintPanel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;



/**
 * class that listens for someone to select the rectangle button and then implements the 
 * actions associated with creating a rectangle.
 * 
 * @author Ben Russell
 * @version 11/8/2015
 */
public class Rectangle extends AbstractAction {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 5959325849437523778L;
    /** constant to represent the Rectangle. */
    private static final int RECT = 3;
    /** The JPanel to associate with this Action. */
    private final PaintPanel myPanel;

    /**
     * Construct this Action.
     * 
     * @param thePanel a JPanel to associate with this Action.
     * @param theIcon the icon that will be attached to the rectangle button.
     */
    public Rectangle(final PaintPanel thePanel, final ImageIcon theIcon) {
        super("Rectangle", theIcon);
       
        myPanel = thePanel;
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(Action.SELECTED_KEY, true);
    }
    @Override
    public void actionPerformed(final ActionEvent thePaint) {
        PaintPanel.setToolChecker(RECT); 
        myPanel.repaint();
    }
    
}
