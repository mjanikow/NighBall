import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound
{
   void play(String audioLoc){
       try{
           File audioPath = new File(audioLoc);
           if (audioPath.exists()){
               AudioInputStream audioInput =
                       AudioSystem.getAudioInputStream(audioPath);
               Clip clip = AudioSystem.getClip();
               clip.open(audioInput);
               clip.start();
           }
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
   }
}
