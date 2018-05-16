package testtest;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;
import java.net.InetAddress;

public class SC  {

    private int serverPort;
    private String serverIP;
    private String message;
    Socket socket;


    public void set_mes(String mes)
    {
        this.message=mes;
    }

    public void connect(int serverPort, String serverIP) {
        this.serverPort=serverPort; // здесь обязательно нужно указать порт к которому привязывается сервер.

        this.serverIP=serverIP; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.
        try {
            InetAddress ipAddress = InetAddress.getByName(serverIP); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Ищу IP " + serverIP + " порт " + serverPort + "?");
            this.socket= new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Я подключился");

        } catch (Exception x) {
            x.printStackTrace();
            System.out.print("1");
        }
    }
    public void send(){
        try {
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            System.out.println("Есть подключение.Шлю сообщения");
            System.out.println();

            out.writeUTF(this.message);
            out.flush();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
