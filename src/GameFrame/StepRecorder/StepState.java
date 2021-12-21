package GameFrame.StepRecorder;

import javax.swing.*;
import java.awt.*;

public class StepState extends JScrollPane{
    public StepList stepList;
    public JTextArea textArea= new JTextArea();

    public StepState(){
        super();
        getViewport().setView(textArea);
        textArea.setEditable(false);
        textArea.setFont(new Font(null, Font.PLAIN, 20) );
        this.setVisible(true);
    }

    public void addStep(Step s){
        stepList.addStep(s);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        String s= String.format("%-15s%-8s%-8s%-8s%-8s\n", "name", "chess", "x", "y", "cheat");
        s= s+ "\n"+ stepList.outPutState();
        textArea.setText(s);
    }
}
