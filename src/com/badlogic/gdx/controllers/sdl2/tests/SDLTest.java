package com.badlogic.gdx.controllers.sdl2.tests;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.sdl2.SDL2Controller;
import com.badlogic.gdx.controllers.sdl2.SDL2ControllerManager;
import org.libsdl.SDL_Error;

import javax.swing.*;
import java.awt.*;

/**
 * A quick and dirty interface to check if a controller is working. I hope you like swing!
 */
public class SDLTest {
    public static int NUM_CONTROLLERS = 4;

    public static void run() {
        JTabbedPane tabbedPane = new JTabbedPane();

        SDL2ControllerManager controllers = new SDL2ControllerManager();


        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testFrame.setLocationRelativeTo(null);
        testFrame.setMinimumSize(new Dimension(640, 350));
        testFrame.setResizable(true);
        testFrame.setVisible(true);

        SDLInfoPanel[] controllerTabs = new SDLInfoPanel[NUM_CONTROLLERS];
        for(int i = 0; i < NUM_CONTROLLERS; i++) {
            controllerTabs[i] = new SDLInfoPanel();
            tabbedPane.add("   Controller " + (i + 1) + "   ", controllerTabs[i]);

        }
        testFrame.setContentPane(tabbedPane);

        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                controllers.pollState();
            } catch (SDL_Error sdl_error) {
                sdl_error.printStackTrace();
            }
            for(int i = 0;  i < controllers.getControllers().size; i++) {
                Controller controllerAtIndex = controllers.getControllers().get(i);
             //   if(controllerAtIndex.)) {
                    controllerTabs[i].updatePanel((SDL2Controller)controllerAtIndex);
              //  } else {
                //    controllerTabs[i].setAsDisconnected();
               // }
            }
            testFrame.repaint();
        }
    }

    public static void main (String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        run();
    }
}
