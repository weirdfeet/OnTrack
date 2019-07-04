package click.nullpointer.tplayout;

import java.awt.Graphics2D;

/**
 * Specifies an Object that has some graphical representation on
 * the two-dimentional plane.
 */
public interface IDrawable {

	/**
	 * Draw this Object on a two-dimentional plane. 
	 * @param g The graphics context to draw on.
	 */
	public void draw(Graphics2D g);

}
