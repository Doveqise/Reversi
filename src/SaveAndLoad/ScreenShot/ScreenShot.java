package SaveAndLoad.ScreenShot;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenShot {
    public static void SaveTheImage(String filePath, String fileName, BufferedImage image){
        try{
            // 截图保存的路径
            File screenFile = new File(filePath + fileName);
            // 如果文件夹路径不存在，则创建
            if (!screenFile.getParentFile().exists()) {
                screenFile.getParentFile().mkdirs();
            }
            ImageIO.write(image, "png", screenFile);
        }catch (Exception e){

        }
    }

    public static void captureScreenAndSave(String filePath, String fileName, int x, int y, int w, int h){
        BufferedImage image = captureScreen(x, y, w, h);
        SaveTheImage(filePath, fileName, image);
    }

    public static BufferedImage captureScreen(int x, int y, int w, int h){
        try{
            Rectangle screenRectangle = new Rectangle();
            Robot robot = new Robot();
            screenRectangle.setLocation(x, y);
            screenRectangle.setSize(w, h);
            BufferedImage image = robot.createScreenCapture(screenRectangle);
            return image;
        }catch(Exception e){
            return null;
        }
    }
}