/*
 * This class will set the number of lives remaining for the player on the screen. The number of lives will increment/decrement in the GamePanel
   based on whether the player is shot or if a powerup granted a life.
 */

import java.awt.*;

public class Health extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public static int health;
	public static final int w = 55;
	public static final int h = 30;
	public Image img;

	//constructor sets health and establishes dimensions of game window
	public Health(int x, int y, final String img){
		super(x,y,w,h);
		health = 3;
		this.img = getImage(img);
	} 

	//called frequently from GamePanel class
	//updates the current health and draws it to the screen
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.PLAIN, 30));
		g.drawString( "LIVES: " , (int)(5), (int)(30));
		
		// location of each heart 
		if (health >= 1)
		{
			g.drawImage(img, 105, 5, w, h, null); 
			if (health >= 2)
			{
				g.drawImage(img, 130, 5, w, h, null);
				if (health >= 3)
				{
					g.drawImage(img, 155, 5, w, h, null);
					if (health >= 4)
					{
						g.drawImage(img, 180, 5, w, h, null);
						if (health >= 5)
						{
							g.drawImage(img, 205, 5, w, h, null);
						}
					}
				}
			}
		}

	}

	// Converts file name into the actual image
	public Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
}