package Client;

import Packet.OPacket;
import Packet.PacketAuthorize;
import Packet.PacketManager;
import Packet.PacketMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static String nickname = "Unknown";
    private static boolean sendNickname = false;

    public Client(){
        try{
            connect();
            handle();
            end();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void sendPacket(OPacket packet) throws IOException{
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeInt(packet.getId());
        packet.write(dos);
        packet.handle();
        dos.flush();
    }

    private static void connect() throws IOException{
        socket = new Socket("localhost", 8888);
    }

    private static void handle() throws IOException{
        Thread handle = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        DataInputStream dis = new DataInputStream(Client.socket.getInputStream());
                        if(dis.available() <= 0){
                            Thread.sleep(10);
                            continue;
                        }
                        int id = dis.readInt();
                        OPacket packet = PacketManager.getPacket(id);
                        packet.read(dis);
                        packet.handle();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        handle.start();
        readChat();
    }

    private static void end() throws IOException{
        socket.close();
        System.exit(0);
    }

    private static void readChat() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("~Вы вошли в чат!~");
        while (true){
            String mes = in.nextLine();
            if(!mes.isEmpty()){
                if(mes.equals("/end")){
                    end();
                }
                if(!sendNickname){
                    System.out.println("Введите ник: ");
                    nickname = in.nextLine();
                    sendNickname = true;
                    sendPacket(new PacketAuthorize(nickname));
                }
                sendPacket(new PacketMessage(nickname, mes));
            }
        }
    }
}
