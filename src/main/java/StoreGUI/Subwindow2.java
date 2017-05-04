package StoreGUI;

import Constractor.Record;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by mash4 on 5/4/2017.
 */
public class Subwindow2 extends JFrame{
    private JTextField artitstextField2;
    private JTextField titletextField3;
    private JTextField salesPrxtextField;
    private JPanel recordPanel;
    private JButton addConsignmentButton;
    private JTextField quantitytextField1;

    public Subwindow2(MainWindow parentWindow ){
        setContentPane(recordPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        parentWindow.setEnabled(false);

        addConsignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String artist = artitstextField2.getText();
                String title = titletextField3.getText();
                if(artist.trim().length()== 0||title.trim().length()  == 0){
                    JOptionPane.showMessageDialog(Subwindow2.this, "Please enter fill both artist name and song's title ");
                    return;
                }
                int quantity ;
                try{
                    quantity = Integer.parseInt(quantitytextField1.getText());
                }catch (NumberFormatException nf){
                    JOptionPane.showMessageDialog(Subwindow2.this, "Please quantities here ");
                    return;
                }
                double salePrx;
                try{
                    salePrx = Double.parseDouble(salesPrxtextField.getText());

                    if(salePrx < 0){
                        JOptionPane.showMessageDialog(Subwindow2.this, "Sale price cannt be less than Zero");
                        return;

                    }
                }catch (NumberFormatException nf){
                    JOptionPane.showMessageDialog(Subwindow2.this, "Sale price cannt be less than Zero");
                    return;
                }
                Date date = new Date();
                java.sql.Date smDAte = new java.sql.Date(date.getTime());
                Record r = new Record(quantity, artist, title, salePrx, smDAte);
                parentWindow.sendData(quantity, artist, title, salePrx, smDAte);
                parentWindow.setEnabled(false);
                Subwindow2.this.dispose();
            }
        });
    }
}
