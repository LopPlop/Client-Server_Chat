package Server;

import Packet.OPacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.ServerError;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
    public static Map<Socket, ClientHandler> handlers = new HashMap<>();
    private static ServerHandler serverHandler;
    private static ServerSocket server;

    public Server(){
        try{
            start();
            handle();
            end();
        }catch (Exception ex){

        }
    }
    private static void start() throws IOException {
        server = new ServerSocket(8888);    // Создание Сокета
    }

    private static void handle() throws Exception{
        serverHandler = new ServerHandler(server);
        serverHandler.start();
        readChat();
    }

    private static void readChat(){
        Scanner in = new Scanner(System.in);
        while (true){
            if(in.hasNextLine()){
                String mes = in.nextLine();
                if(mes.equals("/end")){
                    try {
                        Server.end();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else System.out.println("Неизвестная команда");
            }
        }
    }

    public static void sendPacket(Socket reciever, OPacket packet){
        try {
            DataOutputStream dos = new DataOutputStream(reciever.getOutputStream());
            dos.writeInt(packet.getId());
            packet.write(dos);
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ServerHandler getServerHandler(){
        return serverHandler;
    }

    public static void end() throws IOException {
        server.close();             // Закрываем сокет сервера
    }

    public static ClientHandler getHandler(Socket socket){
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket){
        handlers.remove(socket);
    }
}
