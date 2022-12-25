package Packet;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

public class PacketManager {
    private static Map<Integer, OPacket> packets = new HashMap<>();

    static {
        packets.put(1, new PacketAuthorize());
        packets.put(2, new PacketMessage());
    }

    public static OPacket getPacket(int id) throws Exception{
        return packets.get(id);
    }

    public static void read(int id, DataInputStream dis){
        try {
            OPacket packet = packets.get(id);
            packet.read(dis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
