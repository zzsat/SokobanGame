package engine;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Crate;
import models.Model;
import models.Player;
import models.Tile;

public class Paint extends JPanel {
    public void render(Model model) {
    	
        Graphics g = getGraphics();

        paintLevel(g, model);

        g.dispose();
    }

    /**
     * 
     * 
     * @param g graphics object
     * @param model contains the data
     */
    public void paintLevel(Graphics g, Model model) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        int levelLayout[][] = model.getLevelLayout();
        Tile tile[] = model.getTiles();
        ArrayList<Crate> crates = model.getCrates();
        Player player = model.getPlayer();

        while (col < 8 && row < 9) {
            int tileNum = levelLayout[col][row];

            g.drawImage(tile[tileNum].getImage(), x, y, Model.getSpriteSize(), Model.getSpriteSize(), null);
            
            Crate tempCrate = new Crate(row, col);

            if (tempCrate.crateEquals(crates, tempCrate))
                crates.get(model.getCrateIndex(row, col)).render(g, x, y);

            col++;

            x += Model.getSpriteSize();

            if (col != 8)
                continue;

            col = 0;
            x = 0;
            row++;
            y += Model.getSpriteSize();
        }

        player.render(g);
    }
}
