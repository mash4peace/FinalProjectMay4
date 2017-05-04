package StoreGUI;

import Constractor.Record;
import Main.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mash4 on 5/4/2017.
 */
public class SubWindow1 extends JFrame {
    private JTextField consgrIDTextField;
    private JButton submitButton;
    private JPanel consgrIdPanel;
    static Controller cntr;

    public SubWindow1(MainWindow parentWindw) {

        setContentPane(consgrIdPanel);
        pack();
        setVisible(true);
        parentWindw.setEnabled(false);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id ;
                try{
                    id = Integer.parseInt(consgrIDTextField.getText());

                }catch (NumberFormatException nf){
                    JOptionPane.showMessageDialog(SubWindow1.this, "Please enter positive number ");
                    return;
                }
                Record record = new Record(id);
                Controller.getIDfromDatabase(id);
            }
        });
    }
}
