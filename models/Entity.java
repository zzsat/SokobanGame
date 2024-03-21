package models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * to be extended by objects 
 * 
 * @author filip
 *
 */
public class Entity {
	
	protected BufferedImage image;
	protected Rectangle area;
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
