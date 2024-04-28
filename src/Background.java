/*
 * This sets the background image in the game
 */

import java.awt.*;

public class Background extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	protected int xPos;
	protected int yPos;
	protected int height;
	protected int width;
	public Image img;
	
	// constructor creates starting coordinate of the background image, set the dimensions of the image, and chooses an image
	public Background(int xPos, int yPos, final int width, final int height, final String img) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.img = getImage(img);
	}
	
	//draws the current image for the background to the screen
	public void draw (Graphics g) {
		g.drawImage(img, xPos, yPos, width, height, null);
	}
	
	// Converts file name into the actual image
	public Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	
}