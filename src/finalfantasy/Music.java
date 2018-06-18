package finalfantasy;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class Music {
    ReentrantLock lock = new ReentrantLock();
    Music(String musicFileName){
      try {
        //https://stackoverflow.com/tags/javasound/info
        File musicFile = new File(musicFileName);
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(musicFile);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            lock.lock();
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
    
    public void terminate(){
      lock.unlock();
    }
}