/*
 * This class will set out the functions for the bullet, shot by the aliens and the boss. When the bullet is set at the location where the
   invader or boss is located it will shoot.
 */

import java.awt.*;

public class EnemyBullet extends Rectangle{

	private static final long serialVersionUID = 1L;

	public static int yVelocity = 20;
	public static final int height = 20;
	public static final int width = 5;

	//constructor creates enemy bullet at given location with given dimensions
	public EnemyBullet(int x, int y) {
		super(x, y, width, height);
	}

	//called frequently from GamePanel class
	//updates the current location of the bullet
	public void move(){
		y = y + yVelocity;
	}

	//called frequently from the GamePanel class
	//draws the current location of the bullet to the screen
	public void draw(Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, width, height);
	}

}