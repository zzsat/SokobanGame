package models;

import java.awt.Graphics;
import java.util.ArrayList;

public class Crate extends Entity {
	
	private int row;
	private int column;
	private int marked;

    /**
     * crate object Constructor
     * 
     * @param row    row coordinate for object
     * @param column column coordinate for object
     */
    public Crate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(getImage(), x, y, Model.getSpriteSize(), Model.getSpriteSize(), null);
    }

    /**
     * Constructor class for object
     * 
     * @param row    row coordinate for object
     * @param column column coordinate for object
     * @param marked notifies of object on correct coordinates (0 or 1)
     */
    public Crate(int row, int column, int marked) {
        this.row = row;
        this.column = column;
        this.setMarked(marked);
    }

    /**
     * gets row for object
     * 
     * @return object row coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * set row for object
     * 
     * @param row object row coordinate
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * gets col for object
     * 
     * @return object column coordinate
     */
    public int getColumn() {
        return column;
    }

    /**
     * sets column for object
     * 
     * @param column coordinate
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     *
     * @param arrCrate Arraylist containing crate objects
     * @param row      sought after row
     * @param col      sought after col
     * @return index in arraylist where row and col matches
     */
    public int getCrateIndex(ArrayList<Crate> arrCrate, int row, int col) {
        int i = 0;
        while (arrCrate.size() > i) {
            if (arrCrate.get(i).column == col && arrCrate.get(i).row == row)
                return i;

            i++;
        }
        return -1;
    }

    /**
     * Checks if there is crate as crate2 in array
     * 
     * @param arrCrate given arraylist with crates objects
     * @param crate2   comparison crate
     * @return boolean if such crate2 exists
     */
    public boolean crateEquals(ArrayList<Crate> arrCrate, Crate crate2) {
        int i = 0;
        while (arrCrate.size() > i) {
            if (arrCrate.get(i).getColumn() == crate2.getColumn() && arrCrate.get(i).row == crate2.getRow())
                return true;

            i++;
        }

        return false;
    }

    /**
     * checks if object is marked
     * 
     * @return zero or one
     */
    public int getMarked() {
        return marked;
    }

    /**
     * sets marked for object
     * 
     * @param marked zero or one
     */
    public void setMarked(int marked) {
        this.marked = marked;
    }
}
