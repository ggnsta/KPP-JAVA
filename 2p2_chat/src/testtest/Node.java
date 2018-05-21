package testtest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public abstract class Node extends  Thread {

    protected int port;
    protected DataInputStream in;
    protected DataOutputStream out;
    protected String message;
    protected Socket socket;

    //methods

    public void setMessage(String message)
    {
        this.message=message;
    }

    abstract public void send();
}
