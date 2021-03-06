package testtest;

import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class MultiServer implements Runnable {
    protected int serverPort = 9000;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected MyGUI gui;
    private List<Worker> contacts = new ArrayList<Worker>();
    protected Socket socket;



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
            Worker worker = new Worker(clientSocket, this.gui);
            contacts.add(worker);
            worker.run();
        }
        System.out.println("Server Stopped.");
    }

    public void runClient() {
        try {
            System.out.println("Как клиент.");
            this.socket = new Socket(gui.jtfIP.getText(), 49005); // конектимся к серверу
            Worker worker = new Worker(socket, this.gui);
            contacts.add(worker);
            worker.start();

        } catch (Exception x) {
            Error error = new Error();
            error.eConnect();
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

        }
        catch(ConnectException e) {
            Error error = new Error();
            error.eOS();
        }
        catch (IOException e)// (включает в себя SocketTimeoutException )
        {

            Error error = new Error();
            error.eOS2();
        }

    }

    public List<Worker> getContacts() {
        return contacts;
    }

    public void setContacts(List<Worker> contacts) {
        this.contacts = contacts;
    }




    public MultiServer(int port, MyGUI gui) {

        this.serverPort = port;
        this.gui = gui;

    }

}
