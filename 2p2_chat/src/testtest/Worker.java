package testtest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import static java.lang.Math.toIntExact;

public class Worker extends Thread implements Runnable {

    protected Socket clientSocket = null;
    protected DataInputStream in;
    protected DataOutputStream out;
    protected MyGUI gui;


    public Worker(Socket clientSocket, MyGUI gui) {
        this.clientSocket = clientSocket;
        this.gui = gui;

    }

    @Override
    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            in = new DataInputStream(input);
            out = new DataOutputStream(output);
            String str = this.getName();
            System.out.println("Есть контакт : " + str);
            while (true) {
                this.get();// собственно эти потоки создаются только для того, чтобы постоянно получать сообщения

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get() {
        System.out.println("проверочка");

        try {
            String inMessage = in.readUTF();
            if (inMessage != "*$%%%NUMBER_OF_FILES%%%$*") {
                getFile();
            } else {
                System.out.println("Клиент : " + inMessage);
                gui.jtaTextAreaMessage.append(inMessage + "\n");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void send(String outMessage) {
        try {


            gui.jtaTextAreaMessage.append(outMessage + "\n");
            System.out.println("send class worker");
            out.writeUTF(outMessage);
            out.flush();


        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void sendFile(ArrayList<String> list) {
        int countFiles = list.size();
        String indeficator = "*$%%%NUMBER_OF_FILES%%%$*"; // эта строка отсылается первой, выглядит так, чтобы клиент мог отличить просто сообщение от передачи файлов.
        try {
            out.writeUTF(indeficator);// даём понять клиенту, что дальше будет передача файлов
            out.writeInt(countFiles);//отсылаем количество файлов

            for (int i = 0; i < countFiles; i++) {
                File file = new File(list.get(i));
                out.writeLong(file.length());//отсылаем размер файла
                out.writeUTF(file.getName());//отсылаем имя файла

                FileInputStream fileIn = new FileInputStream(file);
                byte[] buffer = new byte[32 * 1024]; // размер буфера будет 32кб

                //  while ((count = in.read(buffer, 0, Math.min(buffer.length, toIntExact(fileSize)-total))) != -1) {

                int count;//количество прочитанных байтов (=размеры буфера)
                while ((count = fileIn.read(buffer)) != -1) {//read вернет -1, когда дойдет до конца файла

                    int progress=(toIntExact(file.length())/count);//делим размре файла на размер буфера, получаем количество необходимых итераций(надо только для прогресс бара)
                    System.out.print(progress);
                    int currentProgress=0;
                    gui.model.setValue(100*(currentProgress++)/progress);//отображаем прогресс передачи
                    out.write(buffer);
                }

                gui.jtaTextAreaMessage.append(file.getName()+" передан");
                out.flush();
                fileIn.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFile() {
        try {
            int filesCount = in.readInt();//получаем количество файлов
            gui.jtaTextAreaMessage.setText("#####Передается " + filesCount + " файлов#####\n");
            Properties properties=System.getProperties();
            String userHome= properties.getProperty("user.home");//получаем путь к домашенй папке пользователя

            for (int i = 0; i < filesCount; i++) {
                long fileSize = in.readLong(); // получаем размер файла
                String fileName = in.readUTF(); //прием имени файла
                byte[] buffer = new byte[32 * 1024];


                FileOutputStream outFile = new FileOutputStream(userHome+"\\downloads\\"+fileName);
                int count, total = 0;

                while ((count = in.read(buffer, 0, Math.min(buffer.length, toIntExact(fileSize)-total))) != -1) {

                    gui.model.setValue(100*total/toIntExact(fileSize));//отображаем прогресс передачи
                    total += count;
                    outFile.write(buffer, 0, count);

                    if (total == fileSize) {
                        break;
                    }
                }
                gui.jtaTextAreaMessage.append(fileName+" принят");

                outFile.flush();
                outFile.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}