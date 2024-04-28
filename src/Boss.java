/*
 * This class controls the boss invader that spawns when first set of invaders are destroyed. The movement, what it looks like and placement
   of the boss is decided here or referenced here. 
 */

import java.awt.*;

public class Boss extends Rectangle{

	private static final long serialVersionUID = 1L;

	public int yVelocity = 0;
	public int xVelocity = 6;
	public static final int w = 140; 
	public static final int h = 80;
	public Image img;

	//constructor creates boss invaders
	public Boss(int x, int y, final String img){
		super(x, y, w, h);
		this.img = getImage(img);
	}

	//called whenever the movement of the boss changes in the x-direction (left/right)
	public void setXDirection(int xDirection){
		xVelocity = xDirection;
	}

	//called whenever the movement of the boss changes in the y-direction (up/down)
	public void setYDirection(int yDirection){
		yVelocity = yDirection;
	}

	//updates the current location of the boss
	public void move(){
		y = y + yVelocity;
		x = x + xVelocity;
	}

	//updates the current location and draws it to the screen
	public void draw (Graphics g) {

		g.drawImage(img, x, y, w, h, null);
	}

	// Converts file name into the actual image
	public Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}

}