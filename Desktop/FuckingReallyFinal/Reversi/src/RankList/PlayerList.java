package RankList;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class PlayerList {
    public static int cnt;
    public static Player[] players;
    public static final File file= new File("./PlayerData/PlayerData.txt");

    public static void rank(int num){
        Arrays.sort(players, 1, cnt + 1, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                int ans= o2.scores[num]- o1.scores[num];
                if(ans== 0){
                    return o1.scores[num+ 1]- o2.scores[num+ 1];
                }else{
                    return ans;
                }
            }
        });
        RankListFrame.scoreListPanel.repaint();
    }

    public static void playerSaveResult(String name, int num){
        Player player= null;
        for(int i= 1;i<= cnt;i++){
            if(players[i].name.equals(name) ){
                player= players[i];
                break;
            }
        }
        if(player== null){
            cnt++;
            players[cnt]= player= new Player(name);
        }
        player.scores[num]++;
        RankListFrame.scoreListPanel.repaint();
        savePlayerList();
    }

    public String outPut(){
        String ans= "";
        for(int i= 1;i<= cnt;i++){
            Player player= players[i];
            ans= ans+ String.format("%-22s", player.name);
            for(int j= 1;j<= 4;j++){
                ans= ans+ String.format("%d/%-10d", player.scores[j* 2- 1], player.scores[j* 2]);
            }
            ans= ans+ "\n";
        }
        return ans;
    }

    public static void savePlayerList(){
        try{
            BufferedWriter writer= new BufferedWriter(new FileWriter(file) );
            writer.write(cnt+ "\n");
            for(int i= 1;i<= cnt;i++){
                Player player= players[i];
                writer.write(player.name+ "\n");
                for(int j= 1;j<= 8;j++){
                    writer.write(player.scores[j]+ "\n");
                }
            }
            writer.flush();
            writer.close();
        }catch(Exception e){
            System.out.println("Save PlayerList error");
        }

    }

    public PlayerList(){
        if(!file.exists() ){
            try{
                file.getParentFile().mkdirs();
                file.createNewFile();
                BufferedWriter writer= new BufferedWriter(new FileWriter(file) );
                writer.write("0");
                writer.flush();
                writer.close();
                cnt= 0;
                players= new Player[200];
            }catch (Exception e){
                System.out.println("PlayerList initial error");
            }
        }else{
            try{
                BufferedReader reader= new BufferedReader(new FileReader(file) );
                cnt= Integer.parseInt(reader.readLine() );
                players= new Player[200];
                for(int i= 1;i<= cnt;i++){
                    String name= reader.readLine();
                    int pvpW= Integer.parseInt(reader.readLine() );
                    int pvpL= Integer.parseInt(reader.readLine() );
                    int monkeyW= Integer.parseInt(reader.readLine() );
                    int monkeyL= Integer.parseInt(reader.readLine() );
                    int easyW= Integer.parseInt(reader.readLine() );
                    int easyL= Integer.parseInt(reader.readLine() );
                    int hardW= Integer.parseInt(reader.readLine() );
                    int hardL= Integer.parseInt(reader.readLine() );
                    players[i]= new Player(name, pvpW, pvpL, monkeyW, monkeyL, easyW, easyL, hardW, hardL);
                }
                reader.close();
            }catch(Exception e){
                System.out.println("PlayerList load error");
            }
        }
    }
}
