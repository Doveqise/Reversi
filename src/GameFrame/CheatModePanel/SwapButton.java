package GameFrame.CheatModePanel;

import GameFrame.ReversiFrame;
import MainMenu.Button.BasicButton;

import java.awt.*;

public class SwapButton extends BasicButton {

    public SwapButton(){
        super();
        this.setFont(new Font("Calibri", Font.BOLD, 25) );
        setText("Switch Chess");
    }

    @Override
    public void onMouseClicked() {
        ReversiFrame.controller.swapPlayer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        CheatPanel.swapButton.setVisible(ReversiFrame.controller.cheat);
    }
}
