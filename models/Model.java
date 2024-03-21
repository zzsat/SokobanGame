package models;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import engine.Controller;

public class Model {
	
	private static final int SPRITE_SIZE = 48;

    protected int dx, dy, screenX, screenY;

    private Tile[] tiles;
    private int levelLayout[][];
    private BufferedImage crateImage, crateMarkedImage;
    private Player player;

    public ArrayList<Crate> crates = new ArrayList<>(10);

    List<Controller> controllers;
    /**
     * Constructor of the class which iniates the map, and images. 
     * 
     */
    public Model() {
        tiles = new Tile[10];
        levelLayout = new int[8][9];
        loadMap("/img/levelsMap/level_03");
        player = new Player();
        getImages();
        for (Crate crate : crates)
            crate.setImage(crateImage);
        controllers = new ArrayList<>();
    }

    public void update(int direction) {

        updatePlayerPosition(direction);
        isCratesOnMarkedTile();
    }

    public void attach(Controller controller) {
        controllers.add(controller);
    }

    /**
     * marks the crates at same pos as a marked tile
     */
    public void isCratesOnMarkedTile() {

        for (int i = 0; i < crates.size(); i++) {
            Crate crate = crates.get(i);

            if (tileType(crate.getRow(), crate.getColumn()) == 1) {
                crate.setMarked(1);
                crate.setImage(crateMarkedImage);
            } else {
                crate.setMarked(0);
                crate.setImage(crateImage);
            }
        }
    }

    /**
     * returns the type of tile (0, 1, 2, 3)
     * 
     * @param row int representing tile pos
     * @param col
     * @return
     */
    public int tileType(int row, int col) {

        return levelLayout[col][row];
    }

    /**
     * sets player collided if moving towards wall
     * 
     * @param direction direction (right, left, up, down) of the player
     */
    public void checkWallCollision(int direction) {
        int wallTile;
        int playerX = player.getScreenX();
        int playerY = player.getScreenY();

        int entityLeftCol = playerX / Model.SPRITE_SIZE;
        int entityRightCol = playerX / Model.SPRITE_SIZE;
        int entityTopRow = playerY / Model.SPRITE_SIZE;
        int entityBottomRow = playerY / Model.SPRITE_SIZE;

        switch (direction) {
            case 1:
                entityTopRow = (playerY - player.getSpeed()) / Model.SPRITE_SIZE;
                wallTile = levelLayout[player.getScreenX() / Model.SPRITE_SIZE][entityTopRow];

                if (tiles[wallTile].getCollision() == true)
                    player.setCollided(true);

                for (int i = 0; i < crates.size(); i++) {
                    if (crates.get(i).getColumn() == entityLeftCol && crates.get(i).getRow() == entityTopRow) {
                        // else move crate
                        if (!checkCrateWallCollision(crates.get(i), direction)
                                && !checkCrateCrateCollision(crates.get(i), direction)) {
                            crates.get(i).setRow(crates.get(i).getRow() - 1);
                        } else
                            player.setCollided(true);
                        return;
                    }
                }

                break;
            case 2:

                entityBottomRow = (playerY + player.getSpeed()) / Model.SPRITE_SIZE;

                wallTile = levelLayout[entityLeftCol][entityBottomRow];

                if (tiles[wallTile].getCollision() == true) {
                    player.setCollided(true);
                    return;
                }

                for (int i = 0; i < crates.size(); i++) {

                    if (crates.get(i).getColumn() == entityLeftCol && crates.get(i).getRow() == entityBottomRow) {

                        // else move crate
                        if (!checkCrateWallCollision(crates.get(i), direction)
                                && !checkCrateCrateCollision(crates.get(i), direction))
                            crates.get(i).setRow(crates.get(i).getRow() + 1);
                        else
                            player.setCollided(true);
                        return;
                    }
                }

                break;

            case 3:

                entityLeftCol = (playerX - player.getSpeed()) / Model.SPRITE_SIZE;

                wallTile = levelLayout[entityLeftCol][entityTopRow];

                if (tiles[wallTile].getCollision() == true) {
                    player.setCollided(true);
                }

                for (int i = 0; i < crates.size(); i++) {

                    if (crates.get(i).getColumn() == entityLeftCol && crates.get(i).getRow() == entityTopRow) {

                        // else move crate
                        if (!checkCrateWallCollision(crates.get(i), direction)
                                && !checkCrateCrateCollision(crates.get(i), direction))
                            crates.get(i).setColumn(crates.get(i).getColumn() - 1);
                        else
                            player.setCollided(true);
                        return;
                    }
                }

                break;

            case 4:

                entityRightCol = (playerX + player.getSpeed()) / Model.SPRITE_SIZE;

                wallTile = levelLayout[entityRightCol][entityTopRow];

                // entity bottom row

                if (tiles[wallTile].getCollision() == true) {
                    player.setCollided(true);

                }

                for (int i = 0; i < crates.size(); i++) {

                    if (crates.get(i).getColumn() == entityRightCol && crates.get(i).getRow() == entityTopRow) {

                        checkCrateCrateCollision(crates.get(i), direction);
                        // else move crate
                        if (!checkCrateWallCollision(crates.get(i), direction)
                                && !checkCrateCrateCollision(crates.get(i), direction))
                            crates.get(i).setColumn(crates.get(i).getColumn() + 1);
                        else
                            player.setCollided(true);
                        return;
                    }
                }

                break;
        }
        // isCratesOnMarkedTile();
    }

    /**
     * returns boolean dependning if crate can move in direction
     * 
     * @param crate     crate that player collided with
     * @param direction direction (right, left, up, down) of the player
     * @return
     */
    public boolean checkCrateCrateCollision(Crate crate, int direction) {
        int entityTopRow;
        int entityLeftCol;

        switch (direction) {
            case 1:

                entityTopRow = crate.getRow() - 1;

                for (int i = 0; i < crates.size(); i++) {
                    if (crates.get(i).getColumn() == crate.getColumn() &&
                            crates.get(i).getRow() == entityTopRow)
                        return true;
                }

                break;

            case 2:

                entityTopRow = crate.getRow() + 1;

                for (int i = 0; i < crates.size(); i++) {
                    if (crates.get(i).getColumn() == crate.getColumn() &&
                            crates.get(i).getRow() == entityTopRow)
                        return true;
                }

                break;

            case 3:

                entityLeftCol = crate.getColumn() - 1;

                for (int i = 0; i < crates.size(); i++) {
                    if (crates.get(i).getColumn() == entityLeftCol &&
                            crates.get(i).getRow() == crate.getRow())
                        return true;
                }

                break;

            case 4:

                entityLeftCol = crate.getColumn() + 1;

                for (int i = 0; i < crates.size(); i++) {
                    if (crates.get(i).getColumn() == entityLeftCol &&
                            crates.get(i).getRow() == crate.getRow())
                        return true;
                }

                break;
        }

        return false;
    }

    /**
     * returns boolean dependning if crate clashes with wall
     * 
     * @param crate     crate that player collided with
     * @param direction direction (right, left, up, down) of the player
     * @return
     */
    boolean checkCrateWallCollision(Crate crate, int direction) {

        int entityTopRow;
        int entityLeftCol;
        switch (direction) {
            case 1:

                // entityTopRow = (entityTopWorldY - 16)/gp.tileSize;

                entityTopRow = crate.getRow() - 1;

                int wallTile = levelLayout[crate.getColumn()][entityTopRow];

                if (tiles[wallTile].getCollision() == true) {
                    return true;
                }

                break;

            case 2:

                entityTopRow = crate.getRow() + 1;
                wallTile = levelLayout[crate.getColumn()][entityTopRow];

                if (tiles[wallTile].getCollision() == true) {
                    return true;
                }

                break;

            case 3:

                entityLeftCol = crate.getColumn() - 1;
                wallTile = levelLayout[entityLeftCol][crate.getRow()];

                if (tiles[wallTile].getCollision() == true) {
                    return true;
                }

                break;

            case 4:

                entityLeftCol = crate.getColumn() + 1;
                wallTile = levelLayout[entityLeftCol][crate.getRow()];

                if (tiles[wallTile].getCollision() == true) {
                    return true;
                }

                break;
        }

        return false;

    }

    /**
     * (Winning conditions) checks if all crates are marked
     * 
     * @return
     */

    public boolean isAllCratesMarked() {

        int i = 0;
        int count = 0;
        while (crates.size() > i) {
            if (crates.get(i).getMarked() == 1) {

                count++;
                if (count == crates.size())
                    return true;
            }
            i++;
        }

        return false;
    }

    /**
     * sets/gets the different images used in game
     */
    public void getImages() {

        try {
            player.setImage(ImageIO.read(getClass().getResourceAsStream("/img/player/player.png")));
            									
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/img/tiles/blank.png")));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(getClass().getResourceAsStream("/img/tiles/blankmarked.png")));

            tiles[2] = new Tile();
            tiles[2].setImage(ImageIO.read(getClass().getResourceAsStream("/img/tiles/wall.png")));
            tiles[2].setCollision(true);

            tiles[3] = new Tile();
            tiles[3].setImage(ImageIO.read(getClass().getResource("/img/tiles/crate.png")));
            tiles[3].setCollision(true);

            crateImage = ImageIO.read(getClass().getResourceAsStream("/img/tiles/crate.png"));
            crateMarkedImage = ImageIO.read(getClass().getResourceAsStream("/img/tiles/crateMarked.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the textmap and stores the string values as different tiles at
     * positions
     * 
     * @param file text map in levelsmap
     */
    private void loadMap(String file) {

        try {
            InputStream is = getClass().getResourceAsStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < 8 && row < 9) {

                String line = br.readLine();

                while (col < 8) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    if (num == 3) {

                        Crate newCrate = new Crate(row, col, 0);
                        crates.add(newCrate);

                        num = 0;
                    }

                    levelLayout[col][row] = num;

                    col++;
                }
                if (col == 8) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if there is a granted crate at position
     * 
     * @param row coordinate
     * @param col coordinate
     * @return
     */
    public boolean isCrateAtPos(int row, int col) {
        int i = 0;
        while (crates.size() > i) {
            if (crates.get(i).getColumn() == col && crates.get(i).getRow() == row)
                return true;

            i++;
        }
        return false;
    }

    /**
     * gets the crate index in crates array if granted there is crate at position
     * 
     * @param row coordinate
     * @param col coordinate
     * @return boolean if there is crate a coordinates
     */
    public int getCrateIndex(int row, int col) {
        int i = 0;
        while (crates.size() > i) {
            if (crates.get(i).getColumn() == col && crates.get(i).getRow() == row)
                return i;

            i++;
        }
        return -1;
    }

    /**
     * updates player screenX and screenY depending on direction
     * 
     * @param direction player key direction
     */
    public void updatePlayerPosition(int direction) {
        // wall collision
    	player.setCollided(false); 
        checkWallCollision(direction);
        // body can move when not collided
        if (player.getCollided() == false) {
            switch (direction) {
                case 1:
                    player.setScreenY(player.getScreenY() - SPRITE_SIZE);
                    break;
                case 2:
                    player.setScreenY(player.getScreenY() + SPRITE_SIZE);
                    break;
                case 3:
                    player.setScreenX(player.getScreenX() - SPRITE_SIZE);
                    break;
                case 4:
                    player.setScreenX(player.getScreenX() + SPRITE_SIZE);
                    break;
            }

        }
        player.setCollided(false); 
    }
    
    /**
     * Setter and getter methods
     */
    
	public int[][] getLevelLayout() {
		return levelLayout;
	}

	public void setLevelLayout(int levelLayout[][]) {
		this.levelLayout = levelLayout;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public ArrayList<Crate> getCrates() {
		return crates;
	}

	public void setCrates(ArrayList<Crate> crates) {
		this.crates = crates;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public static int getSpriteSize() {
		return SPRITE_SIZE;
	}
}
