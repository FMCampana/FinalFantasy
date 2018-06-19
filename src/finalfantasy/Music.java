package finalfantasy;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private static Thread musicThread;
    private static Clip clip;
    private Music(){}
    public static void play(String musicFileName){
      if(musicThread != null) terminateCurrentTrack();
      try {
        //https://stackoverflow.com/tags/javasound/info
        File musicFile = new File("music/"+musicFileName);
        clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(musicFile);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        musicThread = new Thread(new Runnable() { public void run() {  }  });
        musicThread.run();
        
      } catch (LineUnavailableException ex) {
        System.out.println("LineUnavailableException");
      } catch (UnsupportedAudioFileException ex) {
        System.out.println("UnsupportedAudioFileException");
      } catch (IOException ex) {
        System.out.println("IOException");
      }
    }
    
    private static void terminateCurrentTrack(){
      musicThread.interrupt();
      clip.stop();
    }
}