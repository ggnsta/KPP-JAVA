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
    private String message=null;
    MyGUI spasi;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public Worker(Socket clientSocket, MyGUI spasi) {
        this.clientSocket = clientSocket;
        this.spasi=spasi;

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
                this.get();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void get()
    {
        System.out.println("проверочка");

        try {
            String line=in.readUTF();
            System.out.println("Клиент : " + line);
            spasi.jtaTextAreaMessage.setText("abc");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void send(String messag){
        try {

            spasi.jtaTextAreaMessage.setText("abc");
            System.out.println("send class worker");

            out.writeUTF(messag);
            out.flush();


        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public Worker meme()
    {
        return this;
    }

}