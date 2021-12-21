package MainMenu;

import GameFrame.ReversiFrame;
import MainMenu.Button.*;
import MusicPlayer.MusicPlayer;
import Reversi.Reversi;
import SaveAndLoad.SaveLoadFrame;
import SkinsAndSounds.Skins;
import SkinsAndSounds.Sounds;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.Image.SCALE_DEFAULT;

public class GameMenu extends JFrame{
    public static ReversiFrame reversiFrame;
    public static SaveLoadFrame saveLoadFrame;
    public static Image backImage;
    public static JLabel imageLabel;
    public static JComboBox<String> comboBox;
    public static PVPButton pvpButton;
    public static PVPOLButton pvpOLButton;
    public static AboutButton aboutButton;

    public void reNewTheme(){
        try{
            Skins.reNew();
            Sounds.reNew();
            reNew();
            GameMenu.saveLoadFrame.reNew();
            Reversi.rankListFrame.reNew();
            this.repaint();
        }catch(Exception e){
            System.out.println("Change Theme error");
        }
    }

    public void reNew(){
        try{
            Sounds.backGroundMusic.playMusic(4);
            backImage= ImageIO.read(new File(Reversi.themeFilePath+ Reversi.themeFile+ Reversi.backGroundImage) );
            backImage= backImage.getScaledInstance(this.getWidth(), this.getHeight(), SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(backImage) );
            this.repaint();
        }catch(Exception e){
            System.out.println("GameMenu Change Theme renew error");
        }
    }

    public GameMenu(int frameSize) {
        super();
        this.setVisible(true);
        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);
        this.setResizable(false);

        int w= this.getWidth(), h= this.getHeight();
        int bw=w/ 2, bh= h/ 9;

        imageLabel= new JLabel();
        imageLabel.setSize(this.getWidth(), this. getHeight() );
        imageLabel.setLocation(0, 0);
        JPanel contentPane= (JPanel) this.getContentPane();
        contentPane.setOpaque(false);
        this.getLayeredPane().setLayout(null);
        this.getLayeredPane().add(imageLabel, new Integer(Integer.MIN_VALUE) );

        reNew();
        String[] listData = new String[]{"switchTheme", "MC", "HuaQiang"};
        comboBox = new JComboBox<String>(listData);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Reversi.themeFile= "/"+ comboBox.getSelectedItem();
                    reNewTheme();
                }
            }
        });
        comboBox.setSelectedIndex(0);
        comboBox.setLocation(0, 0);
        comboBox.setSize(150, 40);
        comboBox.setFont(new Font("Calibri", Font.BOLD, 20) );
        contentPane.add(comboBox);

        saveLoadFrame= new SaveLoadFrame();
        int windowWidth = saveLoadFrame.getWidth(); // 获得窗口宽
        int windowHeight = saveLoadFrame.getHeight(); // 获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        saveLoadFrame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
        saveLoadFrame.setVisible(false);

        pvpButton = new PVPButton();
        pvpButton.setSize(bw, bh);
        pvpButton.setLocation(w/4, 0* bh+ bh/ 4);
        this.add(pvpButton);

        pvpOLButton= new PVPOLButton();
        pvpOLButton.setSize(bw, bh);
        pvpOLButton.setLocation(w/4, 1* bh+ bh/ 4);
        this.add(pvpOLButton);

        MonkeyButton monkeyButton = new MonkeyButton();
        monkeyButton.setSize(bw, bh);
        monkeyButton.setLocation(w/4, 2* bh+ bh/ 4);
        this.add(monkeyButton);

        EasyButton easyButton = new EasyButton();
        easyButton.setSize(bw, bh);
        easyButton.setLocation(w/4, 3* bh+ bh/ 4);
        this.add(easyButton);

        HardButton hardButton = new HardButton();
        hardButton.setSize(bw, bh);
        hardButton.setLocation(w/4, 4* bh+ bh/ 4);
        this.add(hardButton);

        LoadButton loadButton = new LoadButton();
        loadButton.setSize(bw, bh);
        loadButton.setLocation(w/4, 5* bh+ bh/ 4);
        this.add(loadButton);

        RankButton rankButton= new RankButton();
        rankButton.setSize(bw, bh);
        rankButton.setLocation(w/4, 6* bh+ bh/ 4);
        this.add(rankButton);

        AboutButton aboutButton= new AboutButton();
        aboutButton.setSize(bw, bh);
        aboutButton.setLocation(w/4, 7* bh+ bh/ 4);
        this.add(aboutButton);

        this.repaint();
    }
}
