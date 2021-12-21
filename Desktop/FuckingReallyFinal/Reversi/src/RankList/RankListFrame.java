package RankList;

import MusicPlayer.MusicPlayer;
import RankList.Buttons.*;
import Reversi.Reversi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static java.awt.Image.SCALE_DEFAULT;

public class RankListFrame extends JFrame {
    public static ScoreListPanel scoreListPanel;
    public static BackButton backButton;
    public static RankMonkeyButton rankMonkeyButton;
    public static RankEasyButton rankEasyButton;
    public static RankPVPButton rankPVPButton;
    public static RankHardButton rankHardButton;
    public static Image backImage;
    public static JLabel imageLabel;

    public void reNew(){
        try{
            backImage= ImageIO.read(new File(Reversi.themeFilePath+ Reversi.themeFile+ Reversi.backGroundImage) );
            backImage= backImage.getScaledInstance(this.getWidth(), this.getHeight(), SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(backImage) );
            this.repaint();
        }catch(Exception e){
            System.out.println("GameMenu Change Theme renew error");
        }
    }

    public RankListFrame(int w, int h){
        this.setLayout(null);
        this.setVisible(false);
        this.setSize(w, h);
        this.setResizable(false);

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
            System.out.println("RankList theme load error");
        }

        scoreListPanel= new ScoreListPanel();
        scoreListPanel.setLocation(30, 150);
        scoreListPanel.setSize(500, 700);
        this.add(scoreListPanel);

        backButton= new BackButton(250, 100);
        backButton.setVisible(true);
        backButton.setLocation(120, 850);
        this.add(backButton);

        rankPVPButton= new RankPVPButton(250, 50);
        rankPVPButton.setVisible(true);
        rankPVPButton.setLocation(0, 0);
        this.add(rankPVPButton);

        rankMonkeyButton= new RankMonkeyButton(250, 50);
        rankMonkeyButton.setVisible(true);
        rankMonkeyButton.setLocation(0, 70);
        this.add(rankMonkeyButton);

        rankEasyButton = new RankEasyButton(250, 50);
        rankEasyButton.setVisible(true);
        rankEasyButton.setLocation(300, 0);
        this.add(rankEasyButton);

        rankHardButton= new RankHardButton(250, 50);
        rankHardButton.setVisible(true);
        rankHardButton.setLocation(300, 70);
        this.add(rankHardButton);
    }
}
