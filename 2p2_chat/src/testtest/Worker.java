package testtest;
import java.io.*;
import java.net.Socket;


public class Worker implements Runnable {

    protected Socket clientSocket = null;
    protected DataInputStream in;
    protected DataOutputStream out;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
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
            System.out.println("Есть контакт : ");
            while(true)
            this.get();
        } catch (IOException e) {
            e.printStackTrace();
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
}