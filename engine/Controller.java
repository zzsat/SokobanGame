package engine;

import java.util.ArrayList;
import java.util.List;

import models.Model;

/**
 * Game Controller Class
 * 
 * @author Filip Säterberg And Ahmad Aljaafari
 *         Date: 2022 / 05 / 22
 * @version 1.0
 */

public abstract class Controller {
    private List<View> views;
    protected Model model;

    /**
     * constructor creating arraylist
     */
    public Controller() {
        views = new ArrayList<>();
    }

    /**
     * adds view to arraylist views
     * @param view added view
     */
    public void attach(View view) {
        views.add(view);
    }
    /**
     * sets model to model
     * @param model 
     */
    public void attach(Model model) {
        this.model = model;
    }
    
    /**
     * updates the views 
     */
    public void update() {
        for (View view : views) {
            view.render(model);

            if (model.isAllCratesMarked())
                view.win();
        }
    }
    /**
     * abstract method used in GameView to update game (model and view) 
     * @param direction
     */
    public abstract void update(int direction);
}
