package testtest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;


public class Worker extends Thread implements Runnable {

    protected Socket clientSocket = null;
    protected DataInputStream in;
    protected DataOutputStream out;
    protected MyGUI gui;


    public Worker(Socket clientSocket, MyGUI gui) {
        this.clientSocket = clientSocket;
        this.gui=gui;

    }

    @Override
    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            in = new DataInputStream(input);
            out = new DataOutputStream(output);
            //long time = System.currentTimeMillis();
            //output.write("jdevnotes multithreaded server runs\n".getBytes());
            //output.close();
           // input.close();
           // System.out.println("Request processed: " + time);
            String str=this.getName();
            System.out.println("Есть контакт : "+str);

            while(true)
            {
                this.get();// собственно эти потоки создаются только для того, чтобы постоянно получать сообщения

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void get()
    {
        System.out.println("проверочка");

        try {
            String inMessage=in.readUTF();
            System.out.println("Клиент : " + inMessage);
            gui.jtaTextAreaMessage.append(inMessage+"\n");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void send(String outMessage){
        try {


            gui.jtaTextAreaMessage.append(outMessage+"\n");
            System.out.println("send class worker");
            out.writeUTF(outMessage);
            out.flush();


        } catch (Exception x) {
            x.printStackTrace();
        }
    }



}