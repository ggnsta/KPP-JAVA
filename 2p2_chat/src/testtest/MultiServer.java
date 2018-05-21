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
    private List<Worker> contacts = new ArrayList<Worker>();
    private String msg;
    private int jj = 0;
    Socket socket;

    @Override
    public void run() {
        openServerSocket();

        while (!isStopped()) {
            Socket clientSocket = null;
            try {

                clientSocket = this.serverSocket.accept(); // ждем клиента
                System.out.println("Waiting.");

            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            System.out.println("Как сервер.");
            Worker worker = new Worker(clientSocket, this.spasi);
            contacts.add(worker);
            worker.run();
        }
        System.out.println("Server Stopped.");
    }

    public void runClient() {
        try {
            System.out.println("Как клиент.");
            this.socket = new Socket(spasi.jtfIP.getText(), 2222); // конектимся к серверу
            Worker worker = new Worker(socket, this.spasi);
            contacts.add(worker);
            worker.run();

        } catch (Exception x) {
            x.printStackTrace();
        }

    }


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

    public List<Worker> getNikita() {
        return contacts;
    }

    public void setNikita(List<Worker> contacts) {
        this.contacts = contacts;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MultiServer(int port, MyGUI spasi) {

        this.serverPort = port;
        this.spasi = spasi;
    }

}
