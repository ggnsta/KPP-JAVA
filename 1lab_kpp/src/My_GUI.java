import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class My_GUI extends JFrame {

    private JButton button_count = new JButton("Посчитать");
    private JTextField field_input = new JTextField("", 5);
    private JLabel label_in = new JLabel("Номер числа Фибаначи ");
    private JLabel label_out = new JLabel("Значение: ");

    public My_GUI() {

        Container my_panel=getContentPane();
        setBounds(20,80,500,150);
        my_panel.setLayout(null);

        button_count.setBounds(20,60,100,25);
        my_panel.add(button_count);

        label_in.setBounds(20,15,150,40);
        my_panel.add(label_in);

        field_input.setBounds(165,25,100,20);
        my_panel.add(field_input);

        label_out.setBounds(275,25,205,20);
        my_panel.add(label_out);

        button_count.addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    int num = Integer.parseInt(field_input.getText());
                    if (num < 1 || num > 92)
                        JOptionPane.showMessageDialog(null, "Введите корректный номер (1-92)", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    else {
                        fibonacci fib = new fibonacci();
                        label_out.setText("Значение: " + Long.toString(fib.calculate(num)));
                    }

                }catch(NumberFormatException excep)
                {
                    JOptionPane.showMessageDialog(null, "Введите порядковый номер", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
            }
        });

    }



}
