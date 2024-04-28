/*
 * This class manages the the sound effects for the class, the background music isn't set here. The music files and the methods which will
   access (play) them are done in a "static way."
 */

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundEffect {

	static File InvaderKilled = new File("PlasmaBomb.wav");
	static File PlayerKilled = new File("Explosion.wav");
	
	// This method plays the sound when the invaders and boss are shot
	public static void playInvadersShotAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(InvaderKilled);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	}
	
	// This method plays the sound when the player is shot
	public static void playPlayerShotAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(PlayerKilled);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	}
	
}
