package finalfantasy;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public final class Music {
    //ReentrantLock lock = new ReentrantLock();
    private Music(){}
    public static void play(String musicFileName){
      terminateCurrentTrack();
      try {
        //https://stackoverflow.com/tags/javasound/info
        File musicFile = new File("music/"+musicFileName);
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(musicFile);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            //lock.lock();
          }
        });
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
    }
}