package SkinsAndSounds;

import MusicPlayer.MusicPlayer;
import Reversi.Reversi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Sounds {
    public static MusicPlayer backGroundMusic;
    public static MusicPlayer placingChessMusic;
    public static MusicPlayer countDownMusic;
    public static String filePath;

    public static void reNewCountDown(){
        countDownMusic= new MusicPlayer(filePath+ Reversi.countDownMusic);
    }

    public static void reNewPlacingChess(){
        placingChessMusic= new MusicPlayer(filePath+ Reversi.placingChessMusic);
    }

    public static void reNew(){
        try {
            if(backGroundMusic!= null){
                backGroundMusic.playMusic(3);
            }
            filePath= Reversi.themeFilePath+ Reversi.themeFile;
            backGroundMusic= new MusicPlayer(filePath+ Reversi.backGroundMusic);
            reNewPlacingChess();
            reNewCountDown();

        }catch(Exception e){
            System.out.println("Sounds load error");
        }
    }

    public Sounds(){
        reNew();
    }
}
