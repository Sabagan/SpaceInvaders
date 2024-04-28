/*
 * This class is responsible for the health bars that appear over the boss. It decide what the bar looks like, how it moves and what the total
   health for the boss is.
 */

import java.awt.*;

public class BossHealth extends Rectangle{

	private static final long serialVersionUID = 1L;

	public static int xVelocity;
	public static int yVelocity;
	public static int Bhealth = 10; //health of the boss
	public static final int w = 12;
	public static final int h = 10;
	public Image img;

	//constructor sets boss health and establishes dimensions of game window
	public BossHealth(int x, int y, final String img) {
		super(x,y,w,h);
		this.img = getImage(img);
	}

	//updates the current location of the boss health bar
	public void move(){
		y = y + yVelocity;
		x = x + xVelocity;
	}

	//updates the current location and draws it to the screen
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.setFont(new Font("Consolas", Font.PLAIN, 30));
		g.drawString( "BOSS" , x+12, y-40);
		g.setColor(Color.green);
		
		// Placement of each individual health bar
		if (Bhealth >= 1)
		{
			g.drawImage(img, x-13, y-25, w, h, null);
			if (Bhealth >= 2)
			{
				g.drawImage(img, x-1, y-25, w, h, null);
				if (Bhealth >= 3)
				{
					g.drawImage(img, x+11, y-25, w, h, null);
					if (Bhealth >= 4)
					{
						g.drawImage(img, x+23, y-25, w, h, null);
						if (Bhealth >= 5)
						{
							g.drawImage(img, x+35, y-25, w, h, null);
							if (Bhealth >= 6)
							{
								g.drawImage(img, x+47, y-25, w, h, null);
								if (Bhealth >= 7)
								{
									g.drawImage(img, x+59, y-25, w, h, null);
									if (Bhealth >= 8)
									{
										g.drawImage(img, x+71, y-25, w, h, null);
										if (Bhealth >= 9)
										{
											g.drawImage(img, x+83, y-25, w, h, null);
											if (Bhealth >= 10)
											{
												g.drawImage(img, x+95, y-25, w, h, null);
											}
										}
									}
								}
							}
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
