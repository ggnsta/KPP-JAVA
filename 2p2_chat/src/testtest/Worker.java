package testtest;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Math.toIntExact;

public class Worker extends Thread implements Runnable {

    protected Socket clientSocket = null;
  //  protected DataInputStream in;
   // protected DataOutputStream out;
    protected ObjectOutputStream oos;
    protected ObjectInputStream oin;

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
            oin = new ObjectInputStream(input);
            oos = new ObjectOutputStream(output);
          //  in = new DataInputStream(input);
           // out = new DataOutputStream(output);
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
            MessageObject buf= (MessageObject) oin.readObject();

                System.out.println(buf.senderName+":"+buf.message);
                gui.jtaTextAreaMessage.append(buf.message + "\n");

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void send() {
        try {

            MessageObject mesObject = new MessageObject();
            mesObject.message=gui.jtfMessage.getText();// заносим сообщения из gui в объект
            System.out.println("send class worker");
            oos.writeObject(mesObject);
            oos.flush();
            oos.close();
            gui.jtaTextAreaMessage.append(mesObject.message + "\n");//отображаем его в своём поле чата


        } catch (Exception x) {
            x.printStackTrace();
        }
    }

   /* public void sendFile(ArrayList<String> list) {

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

                int count, total = 0;//count - количество прочитанных байтов (=размеры буфера)
                while ((count = fileIn.read(buffer)) != -1) {//read вернет -1, когда дойдет до конца файла


                    total += count;
                    out.write(buffer);
                    gui.model.setValue(100 * total / toIntExact(file.length()));//отображаем прогресс передачи
                }

                gui.jtaTextAreaMessage.append(file.getName() + " передан");
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
            Properties properties = System.getProperties();
            String userHome = properties.getProperty("user.home");//получаем путь к домашенй папке пользователя

            for (int i = 0; i < filesCount; i++) {
                long fileSize = in.readLong(); // получаем размер файла
                String fileName = in.readUTF(); //прием имени файла
                byte[] buffer = new byte[32 * 1024];


                FileOutputStream outFile = new FileOutputStream(userHome + "\\downloads\\" + fileName);
                int count, total = 0;

                while ((count = in.read(buffer, 0, Math.min(buffer.length, toIntExact(fileSize) - total))) != -1) {


                    total += count;
                    gui.model.setValue(100 * total / toIntExact(fileSize));//отображаем прогресс передачи
                    outFile.write(buffer, 0, count);

                    if (total == fileSize) {
                        break;
                    }
                }
                gui.jtaTextAreaMessage.append(fileName + " принят");

                outFile.flush();
                outFile.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/

}