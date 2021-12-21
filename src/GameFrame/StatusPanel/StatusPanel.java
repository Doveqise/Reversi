package GameFrame.StatusPanel;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    public int black, white, currentChess;
    public String blackName, whiteName;
    public JLabel label1, label2, label3, label4;
    private final int cnt= 500;

    public StatusPanel(int width, int height, String name1, String name2) {
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(width, height);

        currentChess= 1;
        black= white= 2;
        blackName= name1;
        whiteName= name2;
        Font f= new Font("Calibri", Font.BOLD, 30);

        label1= new JLabel();
        label1.setSize(400, height);
        label1.setLocation(cnt, 0);
        label1.setFont(f);
        this.add(label1);

        label2= new JLabel();
        label2.setSize(400, height);
        label2.setLocation(cnt+ 350, 0);
        label2.setFont(f);
        this.add(label2);

        label3= new JLabel();
        label3.setSize(width/2, height);
        label3.setLocation(0, 0);
        label3.setFont(f);
        this.add(label3);

        label4= new JLabel();
        label4.setSize(200, height);
        label4.setLocation(370, 0);
        label4.setFont(new Font("Calibri", Font.ITALIC, 50) );
        label4.setText("QQQQ");
        this.add(label4);

        setChess(black, white);
        repaint();
    }

    public void swap(){
        currentChess= -currentChess;
        repaint();
    }

    public void setChess(int x, int y){
        black= x;
        white= y;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        label1.setText(String.format("BlackPlayer:  %-8s: %d", blackName, black) );
        label2.setText(String.format("WhitePlayer:  %-8s: %d", whiteName, white) );
        if(currentChess== 1){
            label3.setText(String.format("Black:  %-15s'Turn",blackName) );
        } else{
            label3.setText(String.format("White:  %-15s'Turn",whiteName) );
        }
        super.paintComponent(g);
    }
}
