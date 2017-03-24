/*
 * TCSS 305 - Assignment 5: PowerPaint
 */
package gui;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * Class to generate a tool bar with buttons for Pencil, Line, Rectangle and Ellipse.
 * 
 * @author Ben Russell
 * @version 11/8/2015
 */
public class GUIToolBar extends JToolBar {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -6969282661802905468L;

    /** A button group for the buttons on the tool bar. */ 
    private final ButtonGroup myGroup;
    
    /**
     * Construct the ToolBar.
     */
    public GUIToolBar() {
        super();
        myGroup = new ButtonGroup();
    }
    /**
     * Create a JToggleButton for the ToolBar.
     * 
     * @param theAction to associate with the created JToggleButton
     */
    public void createToolBarButton(final Action theAction) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        myGroup.add(toggleButton);
        myGroup.clearSelection();
        add(toggleButton);
    }
    
}
