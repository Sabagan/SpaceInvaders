import java.awt.*;

public class Shield extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public static int xVelocity;
	public static int yVelocity;

	public static final int w = 110;
	public static final int h = 100;
	
	public Image img;

	//constructor sets shield image icon and establishes location of shield respective to game window
	public Shield(int x, int y, final String img){
		super(x,y,w,h);
		this.img = getImage(img);
	}

	//updates the current location of the shield
	public void move(){
		y = y + yVelocity;
		x = x + xVelocity;
	}
	
	//called frequently from GamePanel class
	//updates the current location and draws it to the screen
	public void draw(Graphics g){
		g.drawImage(img, x-27, y-60, w, h, null);

	}

	// Converts file name into the actual image
	public Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}
}

