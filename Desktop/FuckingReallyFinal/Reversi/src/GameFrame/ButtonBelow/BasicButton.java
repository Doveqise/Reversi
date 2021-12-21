package GameFrame.ButtonBelow;

import SkinsAndSounds.Skins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BasicButton extends JButton {
    public BasicButton(int width, int height){
        this.setSize(width, height);
        this.setFont(new Font("Calibri", Font.BOLD, 50) );

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onMouseClicked();
            }
        });
    }
    public abstract void onMouseClicked();

    @Override
    protected void paintComponent(Graphics g){
        Image buttonImage= Skins.buttonImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(buttonImage) );
        setHorizontalTextPosition(SwingConstants.CENTER);
        super.paintComponent(g);
    }
}
