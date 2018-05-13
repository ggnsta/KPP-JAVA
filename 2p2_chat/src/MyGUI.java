import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyGUI extends JFrame {

    private JTextField jtfMessage;
    private JTextField jtfName;
    private JTextArea jtaTextAreaMessage;
    private JButton bAdd;
    private JFrame add_windows;

    private JTextField jtfIP;
    private JTextField jtfport;




    public MyGUI()
    {

        Container my_panel=getContentPane();
        setBounds(20,20,500,600);
        my_panel.setLayout(null);

        jtaTextAreaMessage=new JTextArea();
        jtaTextAreaMessage.setEditable(false);
        jtaTextAreaMessage.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jtaTextAreaMessage);
        jtaTextAreaMessage.setBounds(20,20,250,400);
        my_panel.add(jtaTextAreaMessage);

        jtfMessage = new JTextField("Введите ваше сообщение: ");
        jtfMessage.setBounds(20,420,250,50);
        my_panel.add(jtfMessage);

        jtfIP = new JTextField("IP: ");
        jtfIP.setBounds(350,60,100,50);
        my_panel.add(jtfIP);

        jtfport = new JTextField("port: ");
        jtfport.setBounds(350,120,100,50);
        my_panel.add(jtfport);

        bAdd=new JButton("Начать чат");
        bAdd.setBounds(350,30,100,25);
        my_panel.add(bAdd);
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {


            }
        });



    }

}
