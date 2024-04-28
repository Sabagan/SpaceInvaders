/*
 * GamePanel class acts as the main "game loop" - continuously runs the game and calls whatever needs to be called. Child of JPanel 
   because JPanel contains methods for drawing to the screen. Implements KeyListener interface to listen for keyboard input
   Implements Runnable interface to use "threading" - let the game do two things at once
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int GAME_WIDTH = 1200;
	public static final int GAME_HEIGHT = 750;
	public static int count = 0; // used to count how many bullets hit boss
	public static int countP = 0; // counts how many times powerup appears on screen
	public static int Power; // decides the type of powerup (math random implemented)

	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public Player player1;
	public Bullet bullet;
	public Alien bots[][] = new Alien[3][8]; 
	public Background back;
	public EnemyBullet enemyKill;
	public Score score;
	public Health health;
	public Boss boss;
	public BossHealth bosshealth;
	public PowerUps powerup;
	public Shield shield;

	public GamePanel() {
		player1 = new Player(GAME_WIDTH/2, (int)(GAME_HEIGHT*0.8), "player.png");
		bullet = new Bullet(-10, -10);
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		health = new Health(GAME_WIDTH, GAME_HEIGHT, "heart.png");
		boss = new Boss(GAME_WIDTH/2, (int)(GAME_HEIGHT*0.3), "BOSS.png");
		powerup = new PowerUps (-80, 40, "SpecialInvader.png");
		shield = new Shield(GAME_WIDTH, GAME_HEIGHT, "FlashShield.png"); 
		bosshealth = new BossHealth(GAME_WIDTH, GAME_HEIGHT, "greenhealth.png");
		back = new Background(0, 0, GAME_WIDTH, GAME_HEIGHT, "Background2.PNG");
		enemyKill = new EnemyBullet(-10, -10);

		//bottom row
		bots[2][7] = new Alien (1000, 250, "Invader1.png");
		bots[2][6] = new Alien (900, 250, "Invader2.png");
		bots[2][5] = new Alien(800, 250, "Invader1.png");
		bots[2][4] = new Alien(700, 250, "Invader2.png");
		bots[2][3] = new Alien(600, 250, "Invader1.png");
		bots[2][2] = new Alien(500, 250, "Invader2.png");
		bots[2][1] = new Alien(400, 250, "Invader1.png");
		bots[2][0]= new Alien(300, 250, "Invader2.png");

		//middle row
		bots[1][7] = new Alien(1000, 150, "Invader2.png");
		bots[1][6] = new Alien (900, 150, "Invader1.png");
		bots[1][5]= new Alien(800, 150, "Invader2.png");
		bots[1][4] = new Alien(700, 150, "Invader1.png");
		bots[1][3] = new Alien(600, 150, "Invader2.png");
		bots[1][2] = new Alien(500, 150, "Invader1.png");
		bots[1][1] = new Alien(400, 150, "Invader2.png");
		bots[1][0] = new Alien(300, 150, "Invader1.png");

		//top row
		bots[0][7] = new Alien(1000, 50, "Invader1.png");
		bots[0][6] = new Alien (900, 50, "Invader2.png");
		bots[0][5]= new Alien(800, 50, "Invader1.png");
		bots[0][4] = new Alien(700, 50, "Invader2.png");
		bots[0][3] = new Alien(600, 50, "Invader1.png");
		bots[0][2] = new Alien(500, 50, "Invader2.png");
		bots[0][1] = new Alien(400, 50, "Invader1.png");
		bots[0][0] = new Alien(300, 50, "Invader2.png");

		this.setFocusable(true); //make everything in this class appear on the screen
		this.addKeyListener(this); //start listening for keyboard input
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

		//make this class run at the same time as other classes (without this each class would "pause" while another class runs). By using threading we can remove lag, and also allows us to do features like display timers in real time!
		gameThread = new Thread(this);
		gameThread.start();
	}

	//paint is a method in java.awt library that we are overriding. It is a special method - it is called automatically in the background in order to update what appears in the window. You NEVER call paint() yourself
	public void paint(Graphics g){
		//we are using "double buffering here" - if we draw images directly onto the screen, it takes time and the human eye can actually notice flashes of lag as each pixel on the screen is drawn one at a time. Instead, we are going to draw images OFF the screen, then simply move the image on screen as needed.

		image = createImage(GAME_WIDTH, GAME_HEIGHT);
		graphics = image.getGraphics();
		draw(graphics);//update the positions of everything on the screen
		g.drawImage(image, 0, 0, null);
	}

	//call the draw methods in each class to update positions as things move
	public void draw(Graphics g){
		back.draw(g);
		player1.draw(g);
		bullet.draw(g);
		powerup.draw(g);
		shield.draw(g);
		for (int i = 0; i < bots.length; i++)
		{
			for (int j = 0; j < bots[0].length; j++)
			{
				bots[i][j].draw(g);
			}
		}
		enemyKill.draw(g);
		if (Health.health != 0) // don't draw on game over and winning screen
		{
			score.draw(g);
			health.draw(g);
		}
		if (Score.score >= 360) {
			boss.draw(g);
			bosshealth.draw(g);
		}

	}


	//call the move methods in other classes to update positions
	//this method is constantly called from run(). By doing this, movements appear fluid and natural. If we take this out the movements appear sluggish and laggy
	public void move(){
		player1.move();
		bullet.move();
		shield.move();
		powerup.move();
		for (int i = 0; i < bots.length; i++)
		{
			for (int j = 0; j < bots[0].length; j++)
			{
				bots[i][j].move();
			}
		}
		enemyKill.move();
		if (Score.score >= 360) {
			boss.move();
			bosshealth.move();
		}
	}


	//handles all collision detection and responds accordingly
	public void checkCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException{

		//force player to remain on screen
		if (player1.x <= 20) {
			player1.x = 20;
		}
		if (player1.x >= 1140)
		{
			player1.x = 1140;
		}

		//force boss to move side to side on screen
		if (boss.x <= 700)
		{
			boss.xVelocity = -boss.xVelocity;
		}
		if (boss.x >= 400) 
		{
			boss.xVelocity = -boss.xVelocity;
		}

		// Occurences for the boss level
		if (Score.score >= 360)
		{
			back.img = Toolkit.getDefaultToolkit().getImage("Background1.png"); // changing the backdrop

			// Making the boss and player bullet disappear if they comes in contact with each other
			if (bullet.intersects(boss))
			{
				bullet.x = -10;
				bullet.y = -10;
				BossHealth.Bhealth = BossHealth.Bhealth-1;
				Score.score = Score.score + 20;
				SoundEffect.playInvadersShotAudio();
				count++;
				if (count == 10)
				{
					boss.x = -1000;
					boss.y = -1000;
				}
			}

			// Make Boss Shoot
			if (boss.x == player1.x && enemyKill.y > GAME_HEIGHT)
			{
				enemyKill.x = boss.x;
				enemyKill.y = boss.y;
			}

			// Making sure the health bar follows the player
			bosshealth.x = boss.x;
			bosshealth.y = boss.y;

			// sets location of alien minions against boss
			if (count < 5)
			{
				bots[0][1].x = boss.x + 140;
				bots[0][1].y = boss.y;
				bots[0][2].x = boss.x - 100;
				bots[0][2].y = boss.y;
			}

			if (count < 10)
			{
				bots[0][3].x = boss.x + 250;
				bots[0][3].y = boss.y;
				bots[0][4].x = boss.x - 230;
				bots[0][4].y = boss.y;
				bots[1][2].x = -1111;
			}

			// makes alien minions disappear from screen after boss health reaches a certain point
			if (count == 5)
			{
				bots[0][1].x = -10000;
				bots[0][2].x = -10000;;
			}

			if (count == 10)
			{
				bots[0][3].x = -10000;
				bots[0][4].x = -10000;;
			}
		}

		//force aliens to move side to side following a rectangular path on screen
		for (int i = 0; i < bots.length; i++)
		{
			for (int j = 0; j < bots[0].length; j++)
			{
				if (bots[i][j].x - 20 <= j*100+25)
				{
					bots[i][j].xVelocity = 0;
					bots[i][j].yVelocity = 3;

					if (bots[0][j].y == 200 ||  bots[1][j].y == 300 || bots[2][j].y == 400 )
					{

						bots[i][j].xVelocity = 3;
						bots[i][j].yVelocity = 0;
					}

				}
				if (bots[i][j].x + 40 >= 400+(j*100))
				{
					bots[i][j].xVelocity = 0;
					bots[i][j].yVelocity = -3;
					if (bots[2][j].y == 250 ||  bots[1][j].y == 150 || bots[0][j].y == 50)
					{

						bots[i][j].xVelocity = -3;
						bots[i][j].yVelocity = 0;
					}
				}
			}
		}

		// Making the bots and player bullet disappear if they comes in contact with each other
		for (int i = 0; i < bots.length; i++)
		{
			for (int j = 0; j < bots[0].length; j++)
			{
				if (bullet.intersects(bots[i][j]))
				{
					bullet.x = -10;
					bullet.y = -10;
					SoundEffect.playInvadersShotAudio();
					if (bots[i][j].img == Toolkit.getDefaultToolkit().getImage("Invader1.png") || bots[i][j].img == Toolkit.getDefaultToolkit().getImage("Invader2.png")) // first hit, alien changes
					{
						bots[i][j].img = Toolkit.getDefaultToolkit().getImage("Invader3.png");
						Score.score = Score.score + 5;
					}
					else if (bots[i][j].img == Toolkit.getDefaultToolkit().getImage("Invader3.png")) // second hit, alien dies
					{
						bots[i][j].x = -1000000000;
						bots[i][j].y = -1000000000;

						if (bots[1][2].x != -1111)
						{
							if (i == 0) // row 1
							{
								Score.score = Score.score + 15;
							}
							if (i == 1) // row 2
							{
								Score.score = Score.score + 10;
							}
							if (i == 2) // row 3
							{
								Score.score = Score.score + 5;
							}
						}
					}
				}
			}
		}

		// Make invaders shoot
		for (int i = 0; i < bots.length; i++)
		{
			for (int j = 0; j < bots[0].length; j++)
			{
				if(bots[i][j].x == player1.x && bots[i][j].x > 0 && bots[i][j].y > 0)
				{
					enemyKill.x = bots[i][j].x;
					enemyKill.y = bots[i][j].y;
				}
			}
		} 

		// When enemy bullets come in contact with player
		if (enemyKill.intersects(player1))
		{
			enemyKill.x = -10;
			enemyKill.y = -10;
			Health.health = health.health-1; 
			SoundEffect.playPlayerShotAudio();
		}

		//makes power up move
		if(Score.score >= 180 && countP == 0 || Score.score >= 360 && countP == 1 ) {
			powerup.xVelocity = 3;
		}

		//resets power up position after it leaves screen
		if(powerup.x > 1200) {
			countP++;
			powerup.x = -80;
			powerup.xVelocity = 0;
		}

		// determines the type of power up you will receive
		if (bullet.intersects(powerup))
		{
			countP++;
			bullet.x = -10;
			bullet.y = -10;
			powerup.x = -80;
			powerup.y = 40;
			powerup.xVelocity = 0;
			Power =  (int)(Math.random()*2)+1;

			//if power ==1, you gain an extra life
			if(Power == 1) {
				Health.health = Health.health+1;
			}
		}

		// if power == 2, you get a shield that lasts for one hit
		if(Power == 2 ) {
			shield.x = player1.x;
			shield.y = player1.y;

			if (enemyKill.intersects(shield))
			{
				enemyKill.x = -10;
				enemyKill.y = -10;
				shield.x = -500;
				shield.y = -500;
				//resets power to 0 so the shield goes away
				Power = 0;
			}
		}

		/*
		 When the player health gets to 0:
		 * the location of the boss, invaders and the player will be set out of the window borders
		 * the game will head to the game over screen
		 */
		if (Health.health == 0)
		{
			back.img = Toolkit.getDefaultToolkit().getImage("GameOver.jpg");
			for (int i = 0; i < bots.length; i++)
			{
				for (int j = 0; j < bots[0].length; j++)
				{
					bots[i][j].x = -10000;
					bots[i][j].y = -10000;
				}
			}
			player1.x = -1000;
			player1.y = -1000;
			boss.x = -1000;
			boss.y = -1000;
		}

		/*
		 When the player reaches the designated score (that indicates the winning condition
		 */
		if (Score.score == 560)
		{
			back.img = Toolkit.getDefaultToolkit().getImage("Victory.jpg");
		}
	}

	//run() method is what makes the game continue running without end. It calls other methods to move objects,  check for collision, and update the screen
	public void run() {
		//the CPU runs our game code too quickly - we need to slow it down! The following lines of code "force" the computer to get stuck in a loop for short intervals between calling other methods to update the screen.
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long now;

		while(true){ //this is the infinite game loop
			now = System.nanoTime();
			delta = delta + (now-lastTime)/ns;
			lastTime = now;

			//only move objects around and update screen if enough time has passed
			if(delta >= 1){
				move();
				try {
					checkCollision();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
				repaint();
				delta--;
			}
		}
	}

	public void keyPressed(KeyEvent e){
		player1.keyPressed(e);
		if (bullet.y <= 0) // Only allows one shot per time so if bullet hits an invader or exits screen, reload bullet
		{
			if (e.getKeyCode() == e.VK_SPACE) // this is where bullet location is reset
			{
				bullet.x = player1.x+5;
				bullet.y = player1.y+5;
			}
		}
	}

	public void keyReleased(KeyEvent e){
		player1.keyReleased(e);
		bullet.keyReleased(e); // this is when the bullet is shot
	}

	//left empty because we don't need it; must be here because it is required to be overridded by the KeyListener interface
	public void keyTyped(KeyEvent e){

	}

}