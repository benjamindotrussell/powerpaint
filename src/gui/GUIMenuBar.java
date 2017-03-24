/*
 * TCSS 305 - Assignment 5: PowerPaint 
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * class that generates a menu bar for the JFrame.
 * 
 * @author Ben Russell
 * @version 11/6/2015
 */
public class GUIMenuBar extends JMenuBar {
    
    /** the color for the drawings. */
    protected static Color myColor;
    /** check for the undo button. */
    protected static boolean myUndoAll;
    /** check for the grid button.*/
    protected static boolean isGrid;
    /** A generated serialization ID. */
    private static final long serialVersionUID = -8420058521162304426L;
    /** Maximum thickness of the drawings used for the JSlider. */
    private static final int MAX_THICKNESS = 20;
    /** Initial Thickness selected for the drawings used in the JSlider. */
    private static final int INIT_THICKNESS = 1;
    /** Major ticks on the JSlider. */
    private static final int MAJOR_TICKS = 5;
    /** Minor ticks on the JSlider. */
    private static final int MINOR_TICKS = 1;
    /** field for the thickness of the drawing. */
    private static int myThickness;
//    /** Field for the JSlider to implement. */
//    private JSlider mySlider;
    /** The View menu. */
    
    //private boolean myEnabled;
    /** A button group for radio buttons. */
    private final ButtonGroup myButtonGroup;
    /** A button to exit the program. */
    private final JMenuItem myExitButton;
    /** array to hold the tools that will be set up in the tool bar. */
    private ArrayList<ToolActions> myToolActions;
    /**
     * Construct the menu bar.
     * 
     * @param theFrame the JFrame which will contain this JMenuBar
     */
    public GUIMenuBar(final JFrame theFrame) {
        super();
        setUpToolActions();
        myColor = Color.BLACK;
        myThickness = INIT_THICKNESS;
        
        myButtonGroup = new ButtonGroup();
        myExitButton = new JMenuItem("Exit");
        isGrid = false;
        setUpMenu(theFrame);
        
    }
    /** 
     * sets up the action for the tool bar.
     * 
     */
    private void setUpToolActions() {
        myToolActions = new ArrayList<ToolActions>();
        myToolActions.add(new ToolActions("Pencil"));
        myToolActions.add(new ToolActions("Line"));
        myToolActions.add(new ToolActions("Rectangle"));
        myToolActions.add(new ToolActions("Ellipse"));
        
    }
    /**
     * Sets up the components of the menu.
     * 
     * @param theFrame the JFrame containing this menu.
     */
    private void setUpMenu(final JFrame theFrame) {
        myUndoAll = false;
        //myEnabled = false;
        add(fileMenu(theFrame));
        add(optionMenu(theFrame));
        add(toolsMenu(theFrame));
        add(helpMenu(theFrame));
    }
   
    /**
     * method to generate and return a JMenu.
     * 
     * @param theFrame the JFrame that the menu is placed on.
     * @return fileMenu a Menu with the header File that hold the buttons undo and exit.
     */
    public JMenu fileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        final JMenuItem undo = new JMenuItem("Undo all changes", KeyEvent.VK_U);
        undo.setEnabled(true);
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final PaintPanel p = new PaintPanel();
                p.setMyUndo(true);
            }
        });
        myExitButton.setMnemonic(KeyEvent.VK_X);
        myExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                          WindowEvent.WINDOW_CLOSING));
            }
        });
        fileMenu.add(undo);
        fileMenu.addSeparator();
        fileMenu.add(myExitButton);
        return fileMenu;
    }
    /**
     * method to generate and return a JMenu.
     * 
     * @param theFrame theFrame the JFrame that the menu is placed on.
     * @return myMenu a Menu with the header Option that holds a check box for the Grid a 
     * sub-menu for Thickness and a sub-menu for the color chooser.
     */
    public JMenu optionMenu(final JFrame theFrame) {
        
        final JMenu option = new JMenu("Options");
        option.setMnemonic(KeyEvent.VK_O);
        
        final JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Grid");
        grid.setMnemonic(KeyEvent.VK_G);
        grid.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                isGrid = !isGrid;
            }
        });
        
        final JMenu thick = new JMenu("Thickness");
        thick.setMnemonic(KeyEvent.VK_T);
        thick.add(setSlider());
        
        final JMenuItem color = new JMenuItem("Color...");
        color.setMnemonic(KeyEvent.VK_C);
        color.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                do  {
                    myColor = JColorChooser.showDialog(null, "Color Chooser", null);
                } while (myColor == Color.BLACK);
            }
        });
        option.add(grid);
        option.addSeparator();
        option.add(thick);        
        option.addSeparator();
        option.add(color);
        return option;
    }
    /**
     * method to generate and return a JMenu.
     * 
     * @param theFrame theFrame the JFrame that the menu is placed on.
     * @return tools a Menu with the header tools that holds a radio buttons for Pencil, Line, 
     * Rectangle and Ellipse.
     */
    public JMenu toolsMenu(final JFrame theFrame) {
        final JMenu tools = new JMenu("Tools");
        tools.setMnemonic(KeyEvent.VK_T);
        for (final ToolActions ta : myToolActions) {
            final JRadioButtonMenuItem tool = new JRadioButtonMenuItem(ta);
            tool.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent theEvent) {
                }
            });
            myButtonGroup.add(tool);
            tools.add(tool);
        } 
        return tools;
    }
    /**
     * method to generate and return a JMenu.
     * 
     * @param theFrame theFrame the JFrame that the menu is placed on.
     * @return tools a Menu with the header tools that holds a radio buttons for Pencil, Line, 
     * Rectangle and Ellipse.
     */
    public JMenu helpMenu(final JFrame theFrame) {
        final JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        final ImageIcon aboutIcon = new ImageIcon("images/painticon.png");
        
        about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null
                                      , "Welcome to PowerPaint. Sorry for the huge Icon", null
                                      , JOptionPane.ERROR_MESSAGE, aboutIcon);
            }
        });

        help.add(about);
        return help;
    }
    /** 
     * method to setup a JSlider for the thickness button.
     * 
     * @return slider a JSlider.
     */
    private JSlider setSlider() {
        final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, MAX_THICKNESS
                               , INIT_THICKNESS);
        slider.setMajorTickSpacing(MAJOR_TICKS);
        slider.setMinorTickSpacing(MINOR_TICKS);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                setMyThickness(slider.getValue());                
            }
        });
        return slider;
    }
    /**
     * @return the myThickness
     */
    public static final int getMyThickness() {
        return myThickness;
    }
    /**
     * @param theThickness the myThickness to set
     */
    public void setMyThickness(final int theThickness) {
        myThickness = theThickness;
    }
    /**
     * sets the state of the undo button after something has been drawn on the panel.
     * @param theB the state of the undo panel
     */
    public static void setMyUndo(final boolean theB) {
        myUndoAll = theB;
    }
    /**
     * inner class for the tools loaded onto the tool menu.
     * 
     * @author ben russell
     */
    public final class ToolActions extends AbstractAction {
        /** A generated serialization ID. */
        private static final long serialVersionUID = 5378597116905801274L;
        /**
         * constructs a tool item with a name.
         * 
         * @param theName the name of the tool
         */
        public ToolActions(final String theName) {
            super(theName);
            putValue(Action.SELECTED_KEY, true);
            
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            
        }
    }
}
