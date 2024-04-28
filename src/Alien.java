/*
 * We can use this class to create multiple aliens in the GamePanel. The specific image is not specified because the images
   varies between the aliens/invaders
 */

import java.awt.*;

public class Alien extends Rectangle{

	private static final long serialVersionUID = 1L;

	public int yVelocity = 0;
	public int xVelocity = 3;
	public static final int w = 70; 
	public static final int h = 40;
	public Image img;

	//constructor creates alien
	public Alien(int x, int y, String img){
		super(x, y, w, h);
		this.img = getImage(img);
	}

	//called whenever the movement of the alien changes in the x-direction (up/down)
	public void setXDirection(int xDirection){
		xVelocity = xDirection;
	}

	public void setYDirection(int yDirection){
		yVelocity = yDirection;
	}

	//updates the current location of the alien
	public void move(){
		y = y + yVelocity;
		x = x + xVelocity;
	}

	//draws the current location of the alien to the screen
	public void draw (Graphics g) {

		g.drawImage(img, x, y, w, h, null);
	}

	// Converts file name into the actual image 
	public Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}

}