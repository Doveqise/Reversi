package SaveAndLoad;

import GameFrame.ReversiFrame;
import MusicPlayer.MusicPlayer;
import Reversi.Reversi;
import SaveAndLoad.SLButtonPanel.BackButton;
import SaveAndLoad.SLButtonPanel.ImagePanel;
import SaveAndLoad.SLButtonPanel.SLButtonPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import SaveAndLoad.ScreenShot.ScreenShot;

import static java.awt.Image.SCALE_DEFAULT;

public class SaveLoadFrame extends JFrame{
    public static ImagePanel imagePanel;
    public static SLButtonPanel slButtonPanel;
    public static BackButton backButton;
    public static BufferedImage image;
    public static ReversiFrame currentFrame;
    public static int saveOrLoad;//1= save 0= load
    public static Image backImage;
    public static JLabel imageLabel;

    public void reNew(){
        try{
            backImage= ImageIO.read(new File(Reversi.themeFilePath+ Reversi.themeFile+ Reversi.backGroundImage) );
            backImage= backImage.getScaledInstance(this.getWidth(), this.getHeight(), SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(backImage) );
            this.repaint();
        }catch(Exception e){
            System.out.println("Change Theme renew error");
        }
    }

    public SaveLoadFrame(){
        super();
        this.setTitle("2021F CS102A Project Reversi");
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1500, 1000);
        this.setVisible(true);

        imageLabel= new JLabel();
        imageLabel.setSize(this.getWidth(), this. getHeight() );
        imageLabel.setLocation(0, 0);
        JPanel contentPane= (JPanel) this.getContentPane();
        contentPane.setOpaque(false);
        this.getLayeredPane().setLayout(null);
        this.getLayeredPane().add(imageLabel, new Integer(Integer.MIN_VALUE) );
        try{
            backImage= ImageIO.read(new File(Reversi.themeFilePath+ Reversi.themeFile+ Reversi.backGroundImage) );
            backImage= backImage.getScaledInstance(this.getWidth(), this.getHeight(), SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(backImage) );
        }catch (Exception e){
            System.out.println("ReversiFrame theme load error");
        }

        imagePanel= new ImagePanel();
        imagePanel.setLocation(300, 20);
        imagePanel.setSize(1200, 900);
        imagePanel.setVisible(true);
        imagePanel.repaint();
        imagePanel.setOpaque(false);
        this.add(imagePanel);

        slButtonPanel= new SLButtonPanel();
        slButtonPanel.setLocation(0, 0);
        slButtonPanel.setSize(300, 800);
        slButtonPanel.setOpaque(false);
        this.add(slButtonPanel);

        backButton= new BackButton();
        backButton.setLocation(0, 825);
        backButton.setSize(300, 100);
        this.add(backButton);
    }
}
