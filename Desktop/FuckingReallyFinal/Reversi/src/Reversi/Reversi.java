package Reversi;

import MainMenu.GameMenu;
import RankList.RankListFrame;
import SkinsAndSounds.Skins;
import SkinsAndSounds.Sounds;

import javax.swing.*;
import java.awt.*;

public class Reversi {
    public static GameMenu mainMenu;
    public static RankListFrame rankListFrame;

    public static String themeFilePath = "./Resources";
    public static String backGroundMusic = "/BGM.wav";
    public static String themeFile= "/switchTheme";
    public static String blackChessImage= "/blackChessImage.png";
    public static String whiteChessImage= "/whiteChessImage.png";
    public static String placingChessMusic= "/placingChessMusic.wav";
    public static String countDownMusic= "/countDownMusic.wav";
    public static String backGroundImage= "/backGroundImage.png";
    public static String buttonImage= "/buttonImage.png";
    public static String gridImage= "/gridImage.png";
    public static Skins skins;
    public static Sounds sounds;

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            skins= new Skins();
            sounds= new Sounds();
            mainMenu= new GameMenu(800);
            int windowWidth = mainMenu.getWidth();
            int windowHeight = mainMenu.getHeight();
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            mainMenu.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
            mainMenu.setVisible(true);

            rankListFrame= new RankListFrame(600, 1000);
            windowWidth = rankListFrame.getWidth();
            windowHeight = rankListFrame.getHeight();
            kit = Toolkit.getDefaultToolkit();
            screenSize = kit.getScreenSize();
            screenWidth = screenSize.width;
            screenHeight = screenSize.height;
            rankListFrame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
            rankListFrame.setVisible(false);

//            //test
//            rankListFrame.setVisible(true);
//            mainMenu.setVisible(false);


//            StepList s = new StepList();
//            PVPController c = new PVPController("nqw", "asd", 1);
//            GameMenu.reversiFrame= new ReversiFrame(1200, 900, s, c);

        });
    }
}
