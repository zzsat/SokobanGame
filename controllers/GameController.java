package controllers;

import engine.Controller;

/**
 * game controller class of this game
 * @author filip
 *
 */
public class GameController extends Controller {

	/**
	 * updates the gamestate, calls abstract method update which updates views.
	 */
    public void update(int direction) {

        model.update(direction);

        super.update();
    }
}
