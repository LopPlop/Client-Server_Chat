package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerHandler extends Thread{

    private final ServerSocket server;

    ServerHandler(ServerSocket server){
        this.server = server;
    }

    @Override
    public void run() {
        while(true){
            try{
                Socket client = server.accept();    // Подключение нового пользователя
                ClientHandler handler = new ClientHandler(client);
                Server.handlers.put(client, handler);
                handler.start();
                Thread.sleep(10);
            }catch (SocketException ex){
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
