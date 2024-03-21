package models;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Object class for the player displayed
 * 
 * @author filip
 *
 */

public class Player extends Entity {

    private int direction;

    private boolean collided = false;
    private int screenX = 0;
    private int screenY = 0;

    private int startX = Model.getSpriteSize() * 2;
    private int startY = Model.getSpriteSize() * 2;
    private int speed;

    /**
     * Constructor class for player object
     * sets coordinates and speed
     * gets player rectangle
     */
    public Player() {
        this.screenX = startX;
        this.screenY = startY;
        this.speed = Model.getSpriteSize();
        area = new Rectangle(screenX, screenY, 47, 47);
    }

    public void render(Graphics g) {
        // System.out.println(image);
        g.drawImage(getImage(), getScreenX(), getScreenY(), Model.getSpriteSize(), Model.getSpriteSize(), null);
    }
    
    /**
     * sets the collided boolean for player
     * @param boolean to set to
     */
    public void setCollided(boolean b){
    	this.collided = b;
    }
    /**
     * gets collided boolean of player
     * @return	collided variable
     */
    public boolean getCollided() {
    	 return collided;
    }
    
    /**
     * gets the speed of player
     * @return	int speed Model.SPRITE_SIZE
     */
    public int getSpeed() {
    	return speed;
    }

    /**
     * gets player X coordinate
     * 
     * @return
     */
    public int getScreenX() {
        return screenX;
    }

    /**
     * gets player Y coordinate
     * 
     * @return
     */
    public int getScreenY() {
        return screenY;
    }

    /**
     * gets starting Row
     * 
     * @return
     */
    public int getScreenRow() {
        return screenY / Model.getSpriteSize();
    }

    /**
     * gets starting Column
     * 
     * @return
     */
    public int getScreenCol() {
        return screenX / Model.getSpriteSize();
    }

    /**
     * set X coordinate of Player
     * 
     * @param x
     */

    public void setScreenX(int x) {
        screenX = x;
    }

    /**
     * sets Y coordinate of Player
     * 
     * @param y
     */
    public void setScreenY(int y) {
        screenY = y;
    }
}
