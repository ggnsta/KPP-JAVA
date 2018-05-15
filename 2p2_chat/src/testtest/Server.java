package testtest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Server {

    private int port;
    private DataInputStream in;
    private DataOutputStream out;
    private String message;

    public Server(int port) {
        this.port = port; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Жду подключений");
            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Кто-то подключился ко мне");
            System.out.println();
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
             in = new DataInputStream(sin);
             out = new DataOutputStream(sout);



        } catch (Exception x) {
            x.printStackTrace();
        }

    }
    public void get()
    {
        try {
        String line=in.readUTF();
            System.out.println("The server was very polite. It sent me this : " + line);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}