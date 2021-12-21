package MusicPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {
    String musicLocation;
    private Clip clip;
    public MusicPlayer(String musicLocation)
    {
        this.musicLocation = musicLocation;
        try
        {
            File musicPath = new File(musicLocation);
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            }
            else
            {
                System.out.println("播放失败！");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void playMusic(int playSettings) {
        try{
            switch (playSettings)
            {
                case 1:
                    clip.start();
                    break;
                case 2:
                    clip.wait();
                    break;
                case 3:
                    clip.stop();
                    break;
                case 4:
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}