package testtest;

import com.sun.prism.shader.Solid_TextureYV12_Loader;
import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

public class MyGUI extends JFrame {

    protected JTextField jtfMessage;
    protected JTextField jtfName;
    protected JTextArea jtaTextAreaMessage;// поле чата
    protected JButton bAdd;
    protected JFrame add_windows;

    protected JTextField jtfIP;
    protected JTextField jtfport;
    protected JButton bAdd2;
    protected JButton jb3;
    private JScrollPane chatScroll;

    public JTextField getJtfMessage() {
        return jtfMessage;
    }

    public MyGUI() {

        MultiServer server = new MultiServer(2222,this);
        new Thread(server).start();


        Container my_panel = getContentPane();
        setBounds(20, 20, 500, 600);
        my_panel.setLayout(null);

        jtaTextAreaMessage = new JTextArea();
        chatScroll = new JScrollPane(jtaTextAreaMessage);
        jtaTextAreaMessage.setEditable(false);
        jtaTextAreaMessage.setLineWrap(true);
        jtaTextAreaMessage.setWrapStyleWord(true);
        chatScroll.setBounds(20, 20, 250, 400);
        my_panel.add(chatScroll);

        jtfMessage = new JTextField("Введите ваше сообщение: ");
        jtfMessage.setBounds(20, 420, 250, 50);
        my_panel.add(jtfMessage);

        jtfIP = new JTextField("IP: ");
        jtfIP.setBounds(350, 60, 100, 50);
        my_panel.add(jtfIP);

        jtfport = new JTextField("");
        jtfport.setBounds(350, 120, 100, 50);
        my_panel.add(jtfport);



        bAdd = new JButton("Ожидать");
        bAdd.setBounds(350, 30, 100, 25);
        my_panel.add(bAdd);
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {



            }

        });
        bAdd2 = new JButton("Добавить");
        bAdd2.setBounds(350, 280, 100, 25);
        my_panel.add(bAdd2);
        bAdd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                server.runClient();
            }

        });
        jtfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {

                    List<Worker> nikita;
                    nikita=server.getContacts();
                    Worker a= nikita.get(0);
                   // String str=a.getName();
                    //System.out.print(str);
                    a.send(jtfMessage.getText());
                    jtfMessage.setText("");

                }
            }
        });
        jb3 = new JButton("Ожидать");
        jb3.setBounds(350, 930, 100, 25);
        my_panel.add(jb3);

    }


}
