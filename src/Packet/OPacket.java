package Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class OPacket {

    private Socket socket;

    public Socket getSocket(){
        return socket;
    }

    public void setSocket(Socket _socket){
        socket = _socket;
    }

    public abstract int getId();
    public abstract void write(DataOutputStream dos) throws IOException;
    public abstract void read(DataInputStream dis) throws IOException;

    public abstract void handle();
}
