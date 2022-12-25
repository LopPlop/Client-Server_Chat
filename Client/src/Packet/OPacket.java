package Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class OPacket {
    public abstract int getId();
    public abstract void write(DataOutputStream dos) throws IOException;
    public abstract void read(DataInputStream dis) throws IOException;
    public abstract void handle();
}
