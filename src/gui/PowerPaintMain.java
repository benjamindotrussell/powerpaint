/*
 * TCSS 305 - Assignment 5: PowerPaint 
 */
package gui;

import java.awt.EventQueue;

/**
 * Main class that runs the PowerPaint program.
 * @author Ben Russell
 * @version 11/5/2015
 */
public final class PowerPaintMain {
    /**
     * private constructor that prevents instantiation.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }
    /**
     * start of the program. calls PowerPaintGUI.
     * @param theArgs Command line arg.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI().start();
            }
        });
    }

}
