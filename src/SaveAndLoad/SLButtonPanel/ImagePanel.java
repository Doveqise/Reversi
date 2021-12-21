package SaveAndLoad.SLButtonPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImagePanel extends JScrollPane {
    public BufferedImage image;

    public ImagePanel(){
        this.setLayout(null);
    }

    public void changeImage(BufferedImage image){
        this.image= image;
    }

    @Override
    public void paintComponent(Graphics g) {
        if(image== null){
            g.setColor(new Color(94, 94, 94) );
            g.fillRect(0, 0 , 1200, 900);
            return;
        }
        g.drawImage(image, 0, 0, null);
    }

}

