package MainMenu.Button;

import Reversi.Reversi;

import javax.swing.*;

public class AboutButton extends BasicButton{
    public AboutButton(){
        super();
        setText("About");
    }

    @Override
    public void onMouseClicked(){
        JOptionPane.showMessageDialog(
                Reversi.mainMenu,
                "Code by Liu Yichen#12112609\n" +
                        "              Li Zongze#12111802\n" +
                        "   @2021Fall CS102A Sustech\n" +
                        "All rights reserved.\n" +
                        "本程序仅供参考学习，如有疑问请联系QQ：932376634/770871922",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
