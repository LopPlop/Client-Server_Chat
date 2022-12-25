package Packet;

import Server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketMessage extends OPacket{

    private String message, sender;

    PacketMessage(){

    }

    public PacketMessage(String sender, String message){
        this.message = message;
        this.sender = sender;
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(sender);
        dos.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        sender = dis.readUTF();
        message = dis.readUTF();
    }
    @Override
    public void handle() {
        Server.handlers.keySet().forEach(s -> Server.sendPacket(s, this));
    }
}
