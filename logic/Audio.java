//import java.applet.Applet;
//import java.applet.AudioClip;
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import java.io.File;
import  sun.audio.*;    //import the sun.audio package
import  java.io.*;

public class Audio {
	InputStream in;
	AudioStream as;        

	public Audio(String songname) {
		try {
			in = new FileInputStream(songname);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			as = new AudioStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
		
	}
	public static void main(String args[]) {
		Audio zinnia = new Audio("zinnia.wav");
		zinnia.playAudio();
		
	}
	
	public void playAudio() {
		AudioPlayer.player.start(as);            
	}
	public void stopAudio() {
		AudioPlayer.player.stop(as);            

	}

}
