package src;

import controllers.GameController;
import engine.Controller;
import models.Model;
import views.GameView;
import views.TextView;

public class Main {
    public static void main(String[] args) {
        Controller gameController = new GameController();
        GameView gameView = new GameView("Sokoban", 400, 471);
        TextView textView = new TextView();
        Model model = new Model();

        gameController.attach(gameView);
        gameController.attach(textView);

        gameController.attach(model);
        model.attach(gameController);

        gameView.attach(gameController);
        textView.attach(gameController);

        gameController.update();
    }
}
