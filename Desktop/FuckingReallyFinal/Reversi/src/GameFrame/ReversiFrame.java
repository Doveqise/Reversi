package GameFrame;

import GameFrame.ButtonBelow.*;
import GameFrame.CheatModePanel.CheatPanel;
import GameFrame.ChessBoard.ChessBoard;
import GameFrame.GameController.*;
import GameFrame.StepRecorder.*;
import GameFrame.StatusPanel.*;
import Reversi.Reversi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static java.awt.Image.SCALE_DEFAULT;

public class ReversiFrame extends JFrame {
    public ChessBoard chessBoard;
    public static BasicController controller;
    public String blackPlayerName;
    public String whitePlayerName;
    public int currentChess;
    public StepState stepState;
    public StatusPanel statusPanel;
    public static CheatPanel cheatPanel;
    public ReStartButton reStartButton;
    public UnDoButton unDoButton;
    public SaveButton saveButton;
    public BackButton backButton;
    public SurroundButton surroundButton;
    public static Thread thread;

    public ReversiFrame(int width, int height, StepList stepList, BasicController controller){
        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(width, height);
        Insets inset = this.getInsets();
        this.setSize(width + inset.left + inset.right, height + inset.top + inset.bottom);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        JLabel imageLabel= new JLabel();
        imageLabel.setSize(this.getWidth(), this. getHeight() );
        imageLabel.setLocation(0, 0);
        JPanel contentPane= (JPanel) this.getContentPane();
        contentPane.setOpaque(false);
        this.getLayeredPane().setLayout(null);
        this.getLayeredPane().add(imageLabel, new Integer(Integer.MIN_VALUE) );
        try{
            Image backImage= ImageIO.read(new File(Reversi.themeFilePath+ Reversi.themeFile+ Reversi.backGroundImage) );
            backImage= backImage.getScaledInstance(this.getWidth(), this.getHeight(), SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(backImage) );
        }catch (Exception e){
            System.out.println("ReversiFrame theme load error");
        }

        this.controller= controller;
        blackPlayerName= controller.blackPlayerName;
        whitePlayerName= controller.whitePlayerName;
        currentChess= controller.currentChess;

        chessBoard= BasicController.generateChessBoard((int)(height* 0.8), stepList);
        chessBoard.setLocation(this.getWidth()- chessBoard.getWidth()- 50, (this.getHeight()- chessBoard.getHeight() )/ 3);
        this.add(chessBoard);
        controller.chessBoard= chessBoard;
        chessBoard.setOpaque(false);

        stepState= new StepState();
        stepState.stepList= stepList;
        stepState.setLocation(50, (this.getHeight()- chessBoard.getHeight() )/ 3+ 120);
        stepState.setSize(350, 600);
        stepState.setOpaque(false);
        this.add(stepState);
        controller.stepState= stepState;

        statusPanel= new StatusPanel(this.getWidth(), (this.getHeight()- chessBoard.getHeight() )/ 3, blackPlayerName, whitePlayerName);
        statusPanel.setLocation(0, 0);
        controller.getBoard();
        controller.getNum();
        statusPanel.setChess(controller.blackNum, controller.whiteNum);
        statusPanel.currentChess= currentChess;
        controller.statusPanel= statusPanel;
        statusPanel.setOpaque(false);
        this.add(statusPanel);

        cheatPanel= new CheatPanel();
        cheatPanel.setVisible(true);
        cheatPanel.setSize(400, 100);
        cheatPanel.setLocation(0, 80);
        cheatPanel.setOpaque(false);
        this.add(cheatPanel);

        reStartButton= new ReStartButton(200, 80);
        reStartButton.setVisible(true);
        reStartButton.setLocation(0, 810);
        this.add(reStartButton);

        unDoButton= new UnDoButton(200, 80);
        unDoButton.setVisible(true);
        unDoButton.setLocation(240, 810);
        this.add(unDoButton);

        saveButton= new SaveButton(200, 80);
        saveButton.setVisible(true);
        saveButton.setLocation(480, 810);
        this.add(saveButton);

        backButton= new BackButton(200, 80);
        backButton.setVisible(true);
        backButton.setLocation(720, 810);
        this.add(backButton);

        surroundButton= new SurroundButton(240, 80);
        surroundButton.setVisible(true);
        surroundButton.setLocation(960, 810);
        this.add(surroundButton);

        controller.reSetTime();
        thread= new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(100);
                        statusPanel.label4.setText(controller.calTime() );
                    } catch (InterruptedException e) {
                        System.out.println("Time error");
                    }
                }
            }
        });
        thread.start();

        controller.generatePreDot();
        repaint();
    }
}
