
package finalfantasy;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import sun.audio.*;

/**
 *
 * @author Krishna
 */
public class Music{

   
    
    public void music(){

    AudioPlayer AP = AudioPlayer.player;
    AudioStream AS;
    AudioData AD;
    ContinuousAudioDataStream loopMusic = null;

    try{
    AS = new AudioStream(new FileInputStream("edison.wav"));
    AD = AS.getData();
    loopMusic = new ContinuousAudioDataStream(AD);
    }catch(IOException error){}
    
    AP.start(loopMusic);
   
}
}