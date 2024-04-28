/*
 * This class will control the activities of the player based on keyboard input. It will draw the player on the bottom of the screen and will
   move it based on required keyboard commands.
 */

import java.awt.*;
import java.awt.event.*;

public class Player extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public int xVelocity;
	public final int SPEED = 4;
	public static final int height = 60;
	public static final int width = 40;
	public Image img;
	
	//constructor creates Player at given location with given dimensions
	public Player(int x, int y, final String img) {
		super(x, y, width, height);
		this.img = getImage(img);
	}
	
	//called from GamePanel when any keyboard input is detected
	//updates the direction of the player based on user input
	//if the keyboard input isn't any of the options (left arrow, right arrow), then nothing happens
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == e.VK_LEFT){
			setXDirection(SPEED*-1);
			move();
		}

		if(e.getKeyCode() == e.VK_RIGHT){
			setXDirection(SPEED);
			move();
		}
	}

	//called from GamePanel when any key is released (no longer being pressed down)
	//Makes the Player stop moving in that direction
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == e.VK_LEFT){
			setXDirection(0);
			move();
		}

		if(e.getKeyCode() == e.VK_RIGHT){
			setXDirection(0);
			move();
		}
	}

	//called whenever the movement of the Player changes in the x-direction (left/right)
	public void setXDirection(int xDirection){
		xVelocity = xDirection;
	}

	//updates the current location of the player
	public void move(){
		x = x + xVelocity;
	}
	
	//called frequently from the GamePanel class
	//draws the current location of the player to the screen
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	// Converts file name into the actual image
	public Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
	
}
