/*
 * GameFrame class establishes the frame (window) for the game.It is a child of JFrame because JFrame manages frames
   Runs the constructor in GamePanel class
 */

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	GamePanel panel;
	
	public GameFrame()
	{
		panel = new GamePanel(); //run GamePanel constructor
		this.add(panel);
		this.setTitle("SPACE INVADERS!"); 
		this.setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setSize(1200, 750);
		this.setBackground(Color.BLACK);
		this.setVisible(true); 
		this.setLocationRelativeTo(null);

	}

}