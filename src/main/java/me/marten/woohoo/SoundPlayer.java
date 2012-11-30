package me.marten.woohoo;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer{
	
	private final static SoundPlayer INSTANCE = new SoundPlayer();
	private boolean isPlaying = false;
	private Clip clip;
	
	private SoundPlayer(){
		
	}
	
	public static SoundPlayer getInstance(){
		return INSTANCE;
	}

	public void play(final String path){
		if(!isPlaying){
		  isPlaying = true;
		  new Thread(new Player(path)).start();
		  new Thread(new Stopper(3000)).start();
		}
	}
	
	private class Player implements Runnable{
		private final String path;
		
		public Player(final String path){
			this.path = path;
		}
		@Override
		public void run() {
			try{
				final AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
				clip = AudioSystem.getClip();
				clip.open(inputStream);
				clip.start();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private class Stopper implements Runnable{

		private final int quietPeriod;
		
		public Stopper(int quietPeriod){
			this.quietPeriod = quietPeriod;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(quietPeriod);
			} catch (InterruptedException e) {
				// Interupted, don't care.
			}
			clip.stop();
			clip.close();
			isPlaying = false;
		}
	}
}