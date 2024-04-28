/*
 * We have the yellow plasma bullet in this Bullet Class. This class will set out the functions for the bullet shot by the player so that
   when the required key is pressed player will shoot
 */

import java.awt.*;
import java.awt.event.*;

public class Bullet extends Rectangle{

	private static final long serialVersionUID = 1L;

	public int yVelocity;
	public final int SPEED = 20;
	public static final int height = 20;
	public static final int width = 5;

	//constructor creates player bullet at given location with given dimensions
	public Bullet(int x, int y) {
		super(x, y, width, height);
	}

	//called frequently from the GamePanel class
	//draws the current location of the bullet to the screen
	public void draw (Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);
	}

	// left empty because commands are set out in GamePanel
	public void keyPressed(KeyEvent e){
		
	}

	//called from GamePanel when any key is released (no longer being pressed down)
	//Makes the bullet move
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == e.VK_SPACE){
			yVelocity = SPEED*-1;
		}
	}

	
	//called whenever the movement of the bullet changes in the y-direction (up/down)
	public void setYDirection(int yDirection){
		yVelocity = yDirection;
	}


	//called frequently from GamePanel class
	//updates the current location of the bullet
	public void move(){
		y = y + yVelocity;
	}

}