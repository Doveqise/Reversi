package GameFrame.GameController;

import GameFrame.ChessBoard.ChessBoard;
import GameFrame.ReversiFrame;
import GameFrame.StepRecorder.*;
import GameFrame.StatusPanel.*;
import MainMenu.GameMenu;
import RankList.PlayerList;
import Reversi.Reversi;
import GameFrame.AIFoolish.*;
import SkinsAndSounds.Sounds;

import javax.swing.*;
import java.util.Calendar;

public abstract class BasicController {
    public String name;
    public ChessBoard chessBoard;
    public StepState stepState;
    public String blackPlayerName;
    public String whitePlayerName;
    public int currentChess;
    public StatusPanel statusPanel;
    public int[][] preDot= new int[10][10];
    public int[][] preGrid= new int[10][10];
    public int[][] board= new int[10][10];
    public boolean cheat;
    public int blackNum, whiteNum;
    public AI ai;
    public boolean isSurround= false;
    public Calendar zeroTime;
    public Calendar Time;

    public void generatePreDot(){
        eraser(preDot);
        eraser(board);
        int [][] temp= new int[10][10];

        getBoard();

        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                if(board[i][j]!= 0){
                    continue;
                }
                notIdentify(temp, board);
                placingChess(temp, i, j, currentChess);
                temp[i][j]= 0;
                if(notIdentify(temp, board) ){
                    preDot[i][j]= 1;
                }
            }
        }
    }

    public void eraserPreGrid(){
        eraser(preGrid);
    }

    public void eraserPreDot(){
        eraser(preDot);
    }

    public void eraser(int[][] a){
        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                a[i][j]= 0;
            }
        }
    }

    public void getBoard(){
        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                board[i][j]= chessBoard.chessGrids[i][j].chess;
            }
        }
    }

    public void recordStep(int x, int y){
        String name= currentChess== 1? blackPlayerName: whitePlayerName;
        Step s= new Step(x, y, currentChess, name, cheat);
        stepState.addStep(s);
    }

    public void paintPreGrid(){
        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                System.out.print(preGrid[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public void surround(int chess){
        if(chess== 1){
            blackNum= 0;
            whiteNum= 64;
        }else{
            blackNum= 64;
            whiteNum= 0;
        }
        JOptionPane.showMessageDialog(
                GameMenu.reversiFrame,
                String.format("%s surrounded!", chess== 1? blackPlayerName:whitePlayerName),
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
        isSurround= true;
        GameOver();
    }

    public void generatePreGrid(int x, int y){
        eraser(preGrid);
        eraser(board);
        int [][] temp= new int[10][10];

        getBoard();
        notIdentify(temp, board);
        placingChess(temp, x, y, currentChess);

        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                if(temp[i][j]!= board[i][j]){
                    preGrid[i][j]= 1;
                }
            }
        }
    }

    public static boolean notIdentify(int[][] a, int[][] mode){
        boolean ans= false;

        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                if(a[i][j]!= mode[i][j]){
                    ans= true;
                    a[i][j]= mode[i][j];
                }
            }
        }
        return ans;
    }

    public static int[][] generateBoard(StepList stepList){
        int n= stepList.cnt;
        Step[] steps= stepList.steps;
        int[][] board= new int[10][10];
        board[4][4]= board[5][5]= 1;
        board[5][4]= board[4][5]= -1;
        for(int i= 1;i<= n;i++){
            Step step= steps[i];
            placingChess(board, step.x, step.y, step.chess);
        }
        return board;
    }

    public static ChessBoard generateChessBoard(int size, StepList stepList){
        int[][] board= generateBoard(stepList);
        return new ChessBoard(size, board);
    }

    public void swapPlayer(){
        currentChess= -currentChess;
        statusPanel.swap();
        generatePreDot();
        eraserPreGrid();
        ReversiFrame.cheatPanel.repaint();
        chessBoard.repaint();
    }

    public void getNum(){
        getBoard();
        blackNum= whiteNum= 0;
        for(int i= 1;i<= 8;i++){
            for(int j= 1;j<= 8;j++){
                if(board[i][j]== 1){
                    blackNum++;
                }
                if(board[i][j]== -1){
                    whiteNum++;
                }
            }
        }
    }

    public void reSetTime(){
        statusPanel.label4.setText("0S");
        zeroTime= Calendar.getInstance();
    }

    public String calTime(){
        Time= Calendar.getInstance();
        int ans= 0;
        ans+= (Time.get(Calendar.HOUR_OF_DAY)- zeroTime.get(Calendar.HOUR_OF_DAY) )* 3600;
        ans+= (Time.get(Calendar.MINUTE)- zeroTime.get(Calendar.MINUTE) )* 60;
        ans+= (Time.get(Calendar.SECOND)- zeroTime.get(Calendar.SECOND) );
        if(ans%15== 13){
            Sounds.reNewCountDown();
        }
        if(ans% 15== 0&&ans!= 0){
            Sounds.countDownMusic.playMusic(1);
        }
        String ansString= ans+ "S";
        return ansString;
    }

    public void GameOver(){
        if(!isSurround){
            getNum();
        }
        String winner= "none";
        if(blackNum> whiteNum){
            winner= blackPlayerName;
        }
        if(blackNum== whiteNum){
            winner= "none";
        }
        if(blackNum< whiteNum){
            winner= whitePlayerName;
        }

        JOptionPane.showMessageDialog(
                GameMenu.reversiFrame,
                String.format("Winner is %s", winner),
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );

        if(hasCheat() ){
            JOptionPane.showMessageDialog(
                    GameMenu.reversiFrame,
                    "You used CheatMode! Can't save to RankList!",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }else {
            int result = JOptionPane.showConfirmDialog(
                    GameMenu.reversiFrame,
                    "Save it to RankList?",
                    "Information",
                    JOptionPane.YES_NO_OPTION
            );
            if(result== 0){
                SaveTheResult();
            }
        }

        GameMenu.reversiFrame.setVisible(false);
        Reversi.mainMenu.setVisible(true);
    }

    public abstract String sendMessage();

    public abstract void receiveMessage(String message);

    public void reNew(){
        reSetTime();
        if(stepState.stepList.cnt== 0){
            return;
        }
        while(stepState.stepList.cnt>= 2){
            unDoOnce();
        }
        if(stepState.stepList.steps[1].name.equals("BOT")&& stepState.stepList.steps[1].cheat== false){
            cheat= false;
            ReversiFrame.cheatPanel.repaint();
            return;
        }
        unDo();
        cheat= false;
        ReversiFrame.cheatPanel.repaint();
    }

    public abstract void unDo();

    public void unDoOnce(){
        reSetTime();
        if(stepState.stepList.cnt== 0){
            JOptionPane.showMessageDialog(
                    GameMenu.reversiFrame,
                    "Already no step to undo!",
                    "Infromation",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        int cnt= stepState.stepList.cnt--;
        Step lastStep= stepState.stepList.steps[cnt];
        board= generateBoard(stepState.stepList);
        chessBoard.renewChessBoard(board);
        getNum();
        statusPanel.setChess(blackNum, whiteNum);
        statusPanel.currentChess= currentChess= lastStep.chess;
        cheat= lastStep.cheat;
        generatePreDot();
        eraserPreGrid();
        statusPanel.repaint();
        chessBoard.repaint();
        stepState.repaint();
        ReversiFrame.cheatPanel.repaint();
    }

    public boolean hasCheat(){
        StepList stepList= stepState.stepList;
        int cnt= stepList.cnt;
        boolean cheated= false;
        for(int i= 1;i<= cnt;i++){
            if(stepList.steps[i].cheat){
                cheated= true;
                break;
            }
        }
        return cheated;
    }

    public void SaveTheResult(){
        if(!isSurround){
            getNum();
        }
        if(blackNum== whiteNum){
            return;
        }

        if(name.equals("PVP") ){
            if(blackNum> whiteNum){
                PlayerList.playerSaveResult(blackPlayerName, 1);
                PlayerList.playerSaveResult(whitePlayerName, 2);
            }
            if(blackNum< whiteNum){
                PlayerList.playerSaveResult(blackPlayerName, 2);
                PlayerList.playerSaveResult(whitePlayerName, 1);
            }
        }

        if(name.equals("PVPOL") ){
            if(blackPlayerName.equals("unknown") ){
                PlayerList.playerSaveResult(whitePlayerName, blackNum> whiteNum? 2: 1);
            }
            if(whitePlayerName.equals("unknown") ){
                PlayerList.playerSaveResult(blackPlayerName, blackNum> whiteNum? 1: 2);
            }
        }

        if(name.equals("PVE") ){
            if(ai.aiName.equals("Monkey") ){
                if(blackPlayerName.equals("BOT") ){
                    PlayerList.playerSaveResult(whitePlayerName, blackNum> whiteNum? 4: 3);
                }
                if(whitePlayerName.equals("BOT") ){
                    PlayerList.playerSaveResult(blackPlayerName, blackNum> whiteNum? 3: 4);
                }
            }
            if(ai.aiName.equals("Easy") ){
                if(blackPlayerName.equals("BOT") ){
                    PlayerList.playerSaveResult(whitePlayerName, blackNum> whiteNum? 6: 5);
                }
                if(whitePlayerName.equals("BOT") ){
                    PlayerList.playerSaveResult(blackPlayerName, blackNum> whiteNum? 5: 6);
                }
            }
            if(ai.aiName.equals("Hard") ){
                if(blackPlayerName.equals("BOT") ){
                    PlayerList.playerSaveResult(whitePlayerName, blackNum> whiteNum? 8: 7);
                }
                if(whitePlayerName.equals("BOT") ){
                    PlayerList.playerSaveResult(blackPlayerName, blackNum> whiteNum? 7: 8);
                }
            }
        }
    }

    public void placeChessAndThen(int x, int y){
        Sounds.reNewPlacingChess();
        Sounds.placingChessMusic.playMusic(1);
        reSetTime();
        getBoard();
        placingChess(board, x, y, currentChess);
        recordStep(x, y);
        chessBoard.renewChessBoard(board);
        getNum();
        statusPanel.setChess(blackNum, whiteNum);
        swapPlayer();
        if(!notIdentify(new int[10][10], preDot) ){
            JOptionPane.showMessageDialog(
                    GameMenu.reversiFrame,
                    String.format("Nowhere to Place Chess\n Change Chess to %s", currentChess== 1? whitePlayerName: blackPlayerName),
                    "Infromation",
                    JOptionPane.INFORMATION_MESSAGE
            );
            swapPlayer();
            if(!notIdentify(new int[10][10], preDot) ){
                JOptionPane.showMessageDialog(
                        GameMenu.reversiFrame,
                        String.format("Nowhere to Place Chess\n Change Chess to %s", currentChess== 1? whitePlayerName: blackPlayerName),
                        "Infromation",
                        JOptionPane.INFORMATION_MESSAGE
                );
                GameOver();
            }
        }
    }

    public static void placingChess(int[][] board, int x, int y, int chess){
        board[x][y]= chess;
        flipChess(board, x, y, chess);
    }

    public static void flipChess(int[][] board, int x, int y, int chess){
        for(int i= -1;i<= 1;i++){
            for(int j= -1;j<= 1;j++){
                if(i== 0&&j== 0){
                    continue;
                }
                if(board[x+ i][y+ j]== -chess){
                    flipDir(board, x, y, chess, i, j);
                }
            }
        }
    }

    public static void flipDir(int[][] board, int x, int y, int chess, int dx, int dy){
        x+= dx;
        y+= dy;
        while(board[x][y]== -chess){
            x+= dx;
            y+= dy;
        }
        if(board[x][y]== 0){
            return;
        }
        x-= dx;
        y-= dy;
        while(board[x][y]== -chess){
            board[x][y]= chess;
            x-=dx;
            y-=dy;
        }
    }

    public abstract void click(int x, int y);

    public void leaveThere(int x, int y){
        eraserPreGrid();
        ReversiFrame.controller.chessBoard.repaint();
    }

    public void onThere(int x, int y){
        generatePreGrid(x, y);
        ReversiFrame.controller.chessBoard.repaint();
    }

    // getter setter below
    public void setBlackPlayerName(String blackPlayerName) {
        this.blackPlayerName = blackPlayerName;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public String getBlackPlayerName() {
        return blackPlayerName;
    }

    public String getWhitePlayerName() {
        return whitePlayerName;
    }

    public void setWhitePlayerName(String whitePlayerName) {
        this.whitePlayerName = whitePlayerName;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
