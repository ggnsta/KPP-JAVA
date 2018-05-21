package testtest;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiServer implements Runnable {
    protected int serverPort = 9000;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    private MyGUI spasi;
    private List<Worker> nikita = new ArrayList<Worker>();
    private String msg;


    public List<Worker> getNikita() {
        return nikita;
    }

    public void setNikita(List<Worker> nikita) {
        this.nikita = nikita;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MultiServer(int port, MyGUI spasi) {

        this.serverPort = port;
        this.spasi=spasi;
    }

    @Override
    public void run() {
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket = null;
            try {

                clientSocket = this.serverSocket.accept();
                System.out.println("Waiting.");


            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            Worker worker= new Worker(clientSocket,this.spasi);
            nikita.add(worker);
            //TODO:SET MESSAGE INTO WORKER
            worker.setMessage(msg);

            worker.run();



        }
        System.out.println("Server Stopped.");
    }
//   new Thread(new Worker(clientSocket)).start());


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        System.out.println("Opening server socket...");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }

}
