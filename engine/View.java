package engine;

import java.util.ArrayList;
import java.util.List;

import models.Model;


/**
 * The viewer class, to be extended for adding views
 * @author Filip Säterberg And Ahmad Aljaafari
 * @version 1.0
 */

public abstract class View {
    private List<Controller> controllers;
    /**
     * creates arraylist
     */
    public View() {
        controllers = new ArrayList<>();
    }
    /**
     *	abstract method to render map 
     * @param model
     */
    
    public abstract void render(Model model);

    /**
     * abstract method to call when won
     */
    public abstract void win();

    /**
     * attaches controllers
     * @param controller added controller
     */
    public void attach(Controller controller) {
        controllers.add(controller);
    }
}
