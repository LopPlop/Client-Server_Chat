package Server;

import Packet.OPacket;
import Packet.PacketAuthorize;
import Packet.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread{

    private String nickname = "Unknown";
    private final Socket client;

    ClientHandler(Socket _client){
        client = _client;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void run(){
        while (true){
            try {
                readData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readData()  {
        try{
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if(dis.available() <= 0){
                return;
            }
            int id = dis.readInt();
            OPacket packet = PacketManager.getPacket(id);
            packet.setSocket(client);
            packet.read(dis);
            packet.handle();
        }catch (IOException ez){
            ez.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void invalidate(){
        Server.invalidate(client);
    }

}
