package SkinsAndSounds;

import MainMenu.GameMenu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

import Reversi.Reversi;

public class Skins {
    public static Image buttonImage;
    public static Image blackChessImage;
    public static Image whiteChessImage;
    public static Image gridImage;

    public static void reNew(){
        try {
            String filePath= Reversi.themeFilePath+ Reversi.themeFile;
            buttonImage = ImageIO.read(new File(filePath+ Reversi.buttonImage) );
            blackChessImage = ImageIO.read(new File(filePath+ Reversi.blackChessImage) );
            whiteChessImage = ImageIO.read(new File(filePath+ Reversi.whiteChessImage) );
            gridImage = ImageIO.read(new File(filePath+ Reversi.gridImage) );

        }catch(Exception e){
            System.out.println("Skins load error");
        }
    }

    public Skins(){
        reNew();
    }
}
