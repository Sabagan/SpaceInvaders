/*
 * This class handles the score for the player, making sure it is visible on the screen and placing it in an appropriate and convenient 
   position on the screen. The text side, type and color are decided here. The value of the score will increase in the GamePanel based 
   on set out conditions
 */

import java.awt.*;

public class Score extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	public static int GAME_WIDTH; //width of the window
	public static int GAME_HEIGHT; //height of the window
	public static int score = 0; //score of the player

	//constructor sets score and establishes dimensions of game window
	public Score(int w, int h){
		GAME_WIDTH = w;
		GAME_HEIGHT = h;
	}

	//called frequently from GamePanel class
	//updates the current score and draws it to the screen
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.PLAIN, 25));
		g.drawString( "Score: " + String.valueOf(score), (int)(1025), (int)(20));//setting location of score
	}
}