package Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAuthorize extends OPacket{

    private String nickname;

    PacketAuthorize(){

    }
    public PacketAuthorize(String _nickname){
        nickname = _nickname;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(nickname);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    @Override
    public void handle() {

    }
}
