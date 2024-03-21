package views;

import java.util.ArrayList;

import engine.View;
import models.Crate;
import models.Model;
import models.Player;

public class TextView extends View {
	
	/**
	 * Constructor prints TextView values
	 */	
    public TextView() {
        System.out.println("W is for Wall");
        System.out.println("b is for blank");
        System.out.println("B is for blankMarked");
        System.out.println("c is for crate");
        System.out.println("C is for crateMarked");
        System.out.println("P is for Player");
        System.out.println("");
    }

    @Override
    public void render(Model model) {
        int col = 0;
        int row = 0;

        Player player = model.getPlayer();
        ArrayList<Crate> crates = model.crates;
        int levelLayout[][] = model.getLevelLayout();

        while (col < 8 && row < 9) {
            col = innerLoop(model, col, row, player, crates, levelLayout);
            if (col == 8) {
                System.out.println();
                col = 0;
                row++;
            }
        }
    }
    /**
     * 
     * @param model model instance
     * @param col current col from above
     * @param row current row
     * @param player the player object
     * @param crates arraylist of object crate
     * @param levelLayout array containing tiles and their coordinates
     * @return
     */
    private int innerLoop(Model model, int col, int row, Player player, ArrayList<Crate> crates, int[][] levelLayout) {
        while (col < 8) {
            if (player.getScreenCol() == col && player.getScreenRow() == row)
                System.out.print("P ");
            else {
                innerIf(model, col, row, crates, levelLayout);
            }

            col++;
        }
        return col;
    }
    /**
     * 
     * @param model model instance
     * @param col current col from above
     * @param row current row
     * @param player the player object
     * @param crates arraylist of object crate
     * @param levelLayout array containing tiles and their coordinates
     * @return
     */
    private void innerIf(Model model, int col, int row, ArrayList<Crate> crates, int[][] levelLayout) {
        if (model.isCrateAtPos(row, col)) {
            if (crates.get(model.getCrateIndex(row, col)).getMarked() == 0)
                System.out.print("c" + " ");
            if (crates.get(model.getCrateIndex(row, col)).getMarked() == 1)
                System.out.print("C" + " ");
        } else {
            if (levelLayout[col][row] == 0)
                System.out.print("  ");
            else if (levelLayout[col][row] == 1)
                System.out.print("* ");
            else if (levelLayout[col][row] == 2)
                System.out.print("| ");
        }
    }
    
    @Override
    public void win() {
        System.out.println("Congratulations, you won the game!");
    }
}
