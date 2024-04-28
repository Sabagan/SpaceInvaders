/*
 * This class is responsible for the invaders that suddenly appears and grants one of two powerups at random. The invader is disguised like 
   the rest but one shoot grants an extra health or a shield.
 */

import java.awt.*;

public class PowerUps extends Rectangle{

	private static final long serialVersionUID = 1L;

	public int yVelocity = 0;
	public int xVelocity = 0;
	public static final int w = 70;
	public static final int h = 40;

	public Image img;

	//constructor creates alien that grants powerups
	public PowerUps(int x, int y, final String img){
		super(x,y,w,h);

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
