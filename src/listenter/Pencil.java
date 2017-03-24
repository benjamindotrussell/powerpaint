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
 * class that listens for someone to select the pencil and then implements the actions
 * associated with writing with a pencil.
 * 
 * @author Ben Russell
 * @version 11/8/2015
 */
public class Pencil extends AbstractAction {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 5959325849437523778L;
    
    /** The JPanel to associate with this Action. */
    private final PaintPanel myPanel;
    /**
     * Construct this Action.
     * 
     * @param thePanel a JPanel to associate with this Action.
     * @param theIcon icon that will be attached to the pencil button.
     */
    public Pencil(final PaintPanel thePanel, final ImageIcon theIcon) {
        super("Pencil", theIcon);
       
        myPanel = thePanel;
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(Action.SELECTED_KEY, true);
    }
    @Override
    public void actionPerformed(final ActionEvent thePaint) {
        PaintPanel.setToolChecker(1);
        myPanel.repaint();
    }
    
}
