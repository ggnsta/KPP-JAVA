import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Server {

    private int port;

    public Server(int port) {
        this.port = port; // случайный порт (может быть любое число от 1025 до 65535)

        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Жду подключений");
            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Кто-то подключился ко мне");
            System.out.println();
        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}