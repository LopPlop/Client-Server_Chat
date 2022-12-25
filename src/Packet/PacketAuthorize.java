package Packet;

import Server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAuthorize extends OPacket{

    private String nickname;

    public PacketAuthorize(){

    }
    PacketAuthorize(String _nickname){
        nickname = _nickname;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        nickname = dis.readUTF();
    }

    @Override
    public void handle() {
        Server.getHandler(getSocket()).setNickname(nickname);
        System.out.println("К серверу приcоединился пользователь: " + Server.getHandler(getSocket()).getNickname());
    }
}
