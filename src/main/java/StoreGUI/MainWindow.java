package StoreGUI;

import Constractor.Record;
import Main.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

/**
 * Created by mash4 on 5/4/2017.
 */
public class MainWindow  extends JFrame{
    private JTextField consgrPhnetextField;
    private JTextField congrNametextField2;
    private JRadioButton newConsignorRadioButton;
    private JRadioButton existingConsignorRadioButton;
    private JTextField emailtextField3;
    private JButton continueNewButton;
    private JPanel rootPanel;
    private JButton continueWithAnExistingButton;
    private JButton showConsignmentButton;
    static Controller ctr;
    public MainWindow(){
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(500, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);



        newConsignorRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(newConsignorRadioButton.isSelected()){
                    existingConsignorRadioButton.setEnabled(false);
                    continueWithAnExistingButton.setEnabled(false);
                }
                else {
                    existingConsignorRadioButton.setEnabled(true);
                    continueWithAnExistingButton.setEnabled(true);
                }

            }
        });
        existingConsignorRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(existingConsignorRadioButton.isSelected()){
                    newConsignorRadioButton.setEnabled(false);
                   continueNewButton.setEnabled(false);
                   congrNametextField2.setEnabled(false);
                   consgrPhnetextField.setEnabled(false);
                   emailtextField3.setEnabled(false);

                }

                else {
                    newConsignorRadioButton.setEnabled(true);
                    continueNewButton.setEnabled(true);
                    congrNametextField2.setEnabled(true);
                    consgrPhnetextField.setEnabled(true);
                    emailtextField3.setEnabled(true);
                }
            }

        });
        continueWithAnExistingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubWindow1 getUserInput = new SubWindow1(MainWindow.this);
                Controller.getDateFromDatabase();

            }
        });

        continueNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newConsignorRadioButton.isSelected()){
                    String name = congrNametextField2.getText();
                    if(name.trim().length() == 0){
                        JOptionPane.showMessageDialog(MainWindow.this, "Please enter consignor name ");
                        return;
                    }
                    String phone = consgrPhnetextField.getText();
                    if(phone.trim().length()== 0){
                        JOptionPane.showMessageDialog(MainWindow.this, "Please enter consignor phone number !!!");
                        return;
                    }
                    String email = emailtextField3.getText();
                    if(email.trim().length() == 0){
                        JOptionPane.showMessageDialog(MainWindow.this, "Please enter consignor's E-mail !!!!");
                        return;
                    }
                    Record rcd = new Record(name, phone, email);
                    Controller.addConsignrDetails(rcd);


                    Subwindow2 record = new Subwindow2(MainWindow.this);
                }



            }
        });


        showConsignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }




    public void sendData(int quantity, String artist, String title, double salePrx, Date smDAte) {
        Controller.saveConsgrInfo(quantity, artist, title, salePrx, smDAte);
    }

}
