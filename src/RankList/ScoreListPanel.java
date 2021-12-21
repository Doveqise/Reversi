package RankList;

import GameFrame.StepRecorder.Step;
import GameFrame.StepRecorder.StepList;

import javax.swing.*;
import java.awt.*;

public class ScoreListPanel extends JScrollPane {
    public PlayerList playerList;
    public JTextArea textArea= new JTextArea();

    public ScoreListPanel(){
        super();
        playerList= new PlayerList();
        this.getViewport().setView(textArea);
        textArea.setEditable(false);
        textArea.setFont(new Font(null, Font.PLAIN, 20) );
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        String s= String.format("%-20s%-10s%-10s%-10s%-10s\n", "Name", "PVP", "Monkey", "Easy", "Hard");
        s= s+ "\n"+ playerList.outPut();
        textArea.setText(s);
    }
}
