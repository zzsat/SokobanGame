package models;

/**
 * Contains collision variable for object
 * either set zero or one depending of tile
 */
public class Tile extends Entity {
    private boolean collision = false;

	public boolean getCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}
