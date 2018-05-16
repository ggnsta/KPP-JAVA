package testtest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Server  {

    private int port;
    private DataInputStream in;
    private DataOutputStream out;
    private String message;
    boolean check=false;
    protected Socket socket;


    public void set_mes(String mes)
    {
        this.message=mes;
    }

    public void set(int port) {
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
             check=true;

        } catch (Exception x) {
            x.printStackTrace();
        }

    }
    public void get()
    {
        try {
        String line=in.readUTF();
            System.out.println("Клиент : " + line);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    public void send(){
        try {

            System.out.println("Есть подключение.Шлю сообщения");
            System.out.println();
            out.writeUTF(this.message);
            out.flush();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}

