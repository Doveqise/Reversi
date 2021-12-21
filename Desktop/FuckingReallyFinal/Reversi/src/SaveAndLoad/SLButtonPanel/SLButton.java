package SaveAndLoad.SLButtonPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import GameFrame.AIFoolish.*;
import GameFrame.GameController.*;
import GameFrame.ReversiFrame;
import GameFrame.StepRecorder.Step;
import GameFrame.StepRecorder.StepList;
import MainMenu.GameMenu;
import SaveAndLoad.SaveLoadFrame;
import SkinsAndSounds.Skins;

public class SLButton extends JButton {
    public int idex;
    public static String filePath= "./SaveData/";
    public String fileName;
    public File imageFile;
    public File dataFile;
    public File file;
    public BufferedImage image;
    public BasicController controller;
    public StepList stepList;

    public SLButton(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onMouseClicked();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                onMouseEntered();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                onMouseExited();
            }
        });
    }

    public void onMouseExited(){
        SaveLoadFrame.imagePanel.changeImage(null);
        SaveLoadFrame.imagePanel.repaint();
    }

    public void onMouseEntered(){
        SaveLoadFrame.imagePanel.changeImage(image);
        SaveLoadFrame.imagePanel.repaint();
    }

    public void saveHere(){
        try{
            if(!imageFile.exists() ){
                imageFile.getParentFile().mkdirs();
                dataFile.getParentFile().mkdirs();
            }
            imageFile.createNewFile();
            dataFile.createNewFile();
            ImageIO.write(SaveLoadFrame.image, "png", imageFile);
            stepList= SaveLoadFrame.currentFrame.stepState.stepList;
            controller= ReversiFrame.controller;
            BufferedWriter writer= new BufferedWriter(new FileWriter(dataFile) );
            writer.write(controller.name+"\n");
            if(controller.name.equals("PVE") ){
                writer.write(controller.ai.aiName+"\n");
            }
            writer.write(controller.blackPlayerName+ "\n");
            writer.write(controller.whitePlayerName+ "\n");
            writer.write(""+ controller.currentChess+ "\n");
            writer.write(""+ stepList.cnt+ "\n");
            writer.write(stepList.outPut() );
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println("Save fail");
        }
    }

    public void onMouseClicked(){
        if(SaveLoadFrame.saveOrLoad== 1){
            if(image== null){
                int result = JOptionPane.showConfirmDialog(
                        GameMenu.reversiFrame,
                        "Save the game here?",
                        "Information",
                        JOptionPane.YES_NO_OPTION
                );
                if(result== 0){
                    saveHere();
                    load();
                }
                System.out.println(result);
            }else{
                int result = JOptionPane.showConfirmDialog(
                        GameMenu.reversiFrame,
                        "Cover this game?",
                        "Information",
                        JOptionPane.YES_NO_OPTION
                );
                if(result== 0){
                    saveHere();
                    load();
                }
            }
        }
        if(SaveLoadFrame.saveOrLoad== 0){
            if(image== null){
                return;
            }
            int result = JOptionPane.showConfirmDialog(
                    GameMenu.reversiFrame,
                    "Load this game?",
                    "Information",
                    JOptionPane.YES_NO_OPTION
            );
            if(result== 0){
                GameMenu.reversiFrame= new ReversiFrame(1200, 900, stepList, controller);
                GameMenu.reversiFrame.setVisible(true);
                GameMenu.saveLoadFrame.setVisible(false);
            }
        }
    }

    public void load(){
        stepList= new StepList();
        controller= new PVPController("q", "w", 1);
        fileName= "Save"+ idex;
        file= new File(filePath+ fileName);
        imageFile= new File(filePath+ fileName+ "/"+ "Image.png");
        dataFile= new File(filePath+ fileName+ "/"+ "Data.txt");

        if(!file.exists() ){
            this.setFont(new Font("Calibri", Font.BOLD, 50)  );
            this.setText("Empty");
            image= null;
            controller= null;
            stepList= null;
        }else{
            this.setFont(new Font("Calibri", Font.BOLD, 20)  );
            try{
                image= ImageIO.read(imageFile);
            }catch (Exception e){
                System.out.println("Load image error");
            }
            readReversi(dataFile);
            String name= "";
            name= name+ controller.name;
            name= name+ " ";
            if(controller.name.equals("PVE") ){
                name= name+ controller.ai.aiName+ " ";
                if(controller.blackPlayerName.equals("BOT") ){
                    name= name+ controller.whitePlayerName;
                }
                if(controller.whitePlayerName.equals("BOT") ){
                    name= name+ controller.blackPlayerName;
                }
                name+= " ";
            }else{
                name= name+ controller.blackPlayerName+ " ";
                name= name+ controller.whitePlayerName+ " ";
            }
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = format.format(date);
            name= "<html>"+ name+ "<br>"+ str+ "</html>";
            this.setText(name);
        }
    }

    public void readReversi(File dataFile){
        try{
            image= ImageIO.read(imageFile);
            BufferedReader reader= new BufferedReader(new FileReader(dataFile.getPath() ) );
            String modeName= reader.readLine();
            AI ai= new Monkey();
            if(modeName.equals("PVE") ){
                String aiName= reader.readLine();
                if(aiName.equals("Monkey") ){
                    ai= new Monkey();
                }
                if(aiName.equals("Easy") ){
                    ai= new Greedy();
                }
                if(aiName.equals("Hard") ){
                    ai= new DeepSearch();
                }
            }
            String blackName= reader.readLine();
            String whiteName= reader.readLine();
            int currentChess= Integer.parseInt(reader.readLine() );
            if(modeName.equals("PVE") ){
                controller= new PVEController(blackName, whiteName, currentChess, ai);
            }
            if(modeName.equals("PVP") ){
                controller= new PVPController(blackName, whiteName, currentChess);
            }
            if(modeName.equals("PVPOL") ){
                controller= new PVPOLController(blackName, whiteName, currentChess);
            }
            stepList= new StepList();
            stepList.cnt= Integer.parseInt(reader.readLine() );
            for(int i= 1;i<= stepList.cnt;i++){
                int x= Integer.parseInt(reader.readLine() );
                int y= Integer.parseInt(reader.readLine() );
                int chess= Integer.parseInt(reader.readLine() );
                String name= reader.readLine();
                int cheat= Integer.parseInt(reader.readLine() );
                stepList.steps[i]= new Step(x, y, chess, name, cheat== 1);
            }
            reader.close();
        }catch (Exception e){
            System.out.println("File reading error");
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        Image buttonImage= Skins.buttonImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(buttonImage) );
        setHorizontalTextPosition(SwingConstants.CENTER);
        super.paintComponent(g);
    }
}
