package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import engine.Controller;
import engine.InputHandler;

public class KeyHandler extends InputHandler implements KeyListener, ActionListener {

    List<Controller> controllers;
    /**
     * Constructor creating new arraylist
     */
    public KeyHandler() {

        controllers = new ArrayList<>();
    }

    /**
     * adds constructors to arraylist controllers
     * @param controller added controller
     */
    public void attach(Controller controller) {

        controllers.add(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        try {
            int direction = 0;
            if (evt.getKeyCode() == getLeft() || evt.getKeyCode() == getArrowLeft()) {
                direction = 3;
            } else if (evt.getKeyCode() == getRight() || evt.getKeyCode() == getArrowRight()) {
                direction = 4;
            } else if (evt.getKeyCode() == getUp() || evt.getKeyCode() == getArrowUp()) {
                direction = 1;
            } else if (evt.getKeyCode() == getDown() || evt.getKeyCode() == getArrowDown()) {
                direction = 2;
            }

            for (Controller controller : controllers)
                controller.update(direction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
