package Clients;

import GameFrame.ChessBoard.ChessGrid;
import GameFrame.ReversiFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientWhite {
    public void Send(Socket client, StringBuilder Info) throws IOException
    {
        PrintWriter printWriter = new PrintWriter(client.getOutputStream());
        printWriter.println(Info);
        printWriter.flush();
    }
    public String Receive(Socket client) throws IOException
    {
        InputStreamReader streamReader = new InputStreamReader(client.getInputStream());
        BufferedReader reader = new BufferedReader(streamReader);
        String Info = reader.readLine();
        return Info;
    }
    public static void run() throws IOException {

        String get = "asd";
        Socket client = new Socket("47.106.201.13",12347);
        ClientWhite c = new ClientWhite();
        System.out.println("接受到服务器的消息 ：" + c.Receive(client) );
        while(get != "-1")
        {
            c = new ClientWhite();
            ChessGrid.reactflag= false;
            String messageReceived= c.Receive(client);
            ChessGrid.reactflag= true;
            System.out.println("接受到服务器的消息 ：" + messageReceived );
            ReversiFrame.controller.receiveMessage(messageReceived);
            StringBuilder sb = new StringBuilder();
            String messageToSend= ReversiFrame.controller.sendMessage();
            sb.append(messageToSend);
            c.Send(client,sb);
            System.out.println("发送到服务器的消息 ：" + sb);
        }
    }

}