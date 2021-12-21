package SaveAndLoad.SLButtonPanel;

import javax.swing.*;
import java.awt.*;

public class SLButtonPanel extends JScrollPane {
    public static int num= 20;
    public static SLButton[] slButtons;
    public static int buttonWidth;
    public static int buttonHeight;

    public SLButtonPanel(){
        super();
        this.setVisible(true);
        slButtons= new SLButton[num+ 2];
        buttonWidth= 250;
        buttonHeight= buttonWidth/3;
        JPanel panel= new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(250, 2200) );
        for(int i= 1;i<= num;i++){
            slButtons[i]= new SLButton();
            slButtons[i].setVisible(true);
            slButtons[i].setSize(buttonWidth, buttonHeight);
            slButtons[i].setLocation(0, (i- 1)* buttonHeight+ i* buttonHeight/ 4);
            slButtons[i].idex= i;
            panel.add(slButtons[i]);
            slButtons[i].load();
        }
        this.setViewportView(panel);
        this.getViewport().setOpaque(false);
    }
}
