package finalfantasy;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public final class Music {
    //ReentrantLock lock = new ReentrantLock();
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
        
//        Runnable player = new Runnable() { public void run() {  }  };
//        SwingUtilities.invokeLater(player);
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
      //lock.unlock();
//      System.out.println("TERMINATING");
      musicThread.interrupt();
      clip.stop();
    }
}