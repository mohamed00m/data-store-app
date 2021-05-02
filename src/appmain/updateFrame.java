/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appmain;

import dao.DAOException;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Mohamed Sayed
 */
public class updateFrame extends JFrame {

    private JButton update_button;
    private JButton print_button;
    private JLabel id_label;
    private JLabel name_label;
    private JLabel device_number_label;
    private JLabel receive_date_label;
    private JLabel phone_number_label;
    private JLabel whatsapp_number_label;
    private JLabel damage_description_label;
    private JTextField id_field;
    private JTextField name_field;
    private JTextField device_number_field;
    private JTextField receive_date_field;
    private JTextField phone_number_field;
    private JTextField whatsapp_number_field;
    private JTextArea damage_description_area;
    private PrintRequestAttributeSet attributes;
    private JPanel buttons_panel;
    
    private MyPrintedPanel printed_panel;

    public updateFrame(Client client) {
        this.setSize(500, 300);
        this.setLayout(new GridBagLayout());
        
        printed_panel = new MyPrintedPanel();
        printed_panel.setLayout(new GridBagLayout());
        buttons_panel = new JPanel();
        
        update_button = new JButton();
        print_button = new JButton();
        id_label = new JLabel();
        name_label = new JLabel();
        device_number_label = new JLabel();
        receive_date_label = new JLabel();
        phone_number_label = new JLabel();
        whatsapp_number_label = new JLabel();
        damage_description_label = new JLabel();
        id_field = new JTextField();
        name_field = new JTextField();
        device_number_field = new JTextField();
        receive_date_field = new JTextField();
        phone_number_field = new JTextField();
        whatsapp_number_field = new JTextField();
        damage_description_area = new JTextArea(5, 5);
        id_label.setText("ID :");
        name_label.setText("Name :");
        device_number_label.setText("Device Number :");
        receive_date_label.setText("Receive Date :");
        phone_number_label.setText("Phone Number :");
        whatsapp_number_label.setText("Whatsapp Number :");
        damage_description_label.setText("Damage Description :");
        id_field.setText(String.valueOf(client.getId()));
        id_field.setEditable(false);
        name_field.setText(client.getName());
        device_number_field.setText(client.getDeviceNumber());
        receive_date_field.setText(client.getReceiveDate());
        phone_number_field.setText(client.getPhoneNumber());
        whatsapp_number_field.setText(client.getWhatsappNumber());
        damage_description_area.setText(client.getDamageDescription());
        damage_description_area.setFont(new Font("Dialog", Font.PLAIN, 20));
        update_button.setText("Update");
        attributes = new HashPrintRequestAttributeSet();
        update_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Client cl = new Client();
                cl.setId(Integer.parseInt(id_field.getText()));
                cl.setName(name_field.getText());
                cl.setDeviceNumber(device_number_field.getText());
                cl.setReceiveDate(receive_date_field.getText());
                cl.setPhoneNumber(phone_number_field.getText());
                cl.setWhatsappNumber(whatsapp_number_field.getText());
                cl.setDamageDescription(damage_description_area.getText());
                try {
                    appFrame.update_client(cl);
                    updateFrame.this.setVisible(false);
                } catch (DAOException ex) {
                    JOptionPane.showMessageDialog(null, "problem updating,setup connection!");
                    updateFrame.this.setVisible(false);
                }

            }
        });
        print_button.setText("Print");
        print_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
               try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(printed_panel);
                if (job.printDialog(attributes)) {
                    job.print(attributes);
                }
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(updateFrame.this, "Printing Problem !");
            }
            }
        });

        buttons_panel.add(update_button);
        buttons_panel.add(print_button);
        
        printed_panel.add(id_label, new GBC(0, 0).setAnchor(GBC.EAST));
        printed_panel.add(id_field, new GBC(1, 0).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        printed_panel.add(name_label, new GBC(0, 1).setAnchor(GBC.EAST));
        printed_panel.add(name_field, new GBC(1, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        printed_panel.add(device_number_label, new GBC(0, 2).setAnchor(GBC.EAST));
        printed_panel.add(device_number_field, new GBC(1, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        printed_panel.add(receive_date_label, new GBC(0, 3).setAnchor(GBC.EAST));
        printed_panel.add(receive_date_field, new GBC(1, 3).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        printed_panel.add(phone_number_label, new GBC(0, 4).setAnchor(GBC.EAST));
        printed_panel.add(phone_number_field, new GBC(1, 4).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        printed_panel.add(whatsapp_number_label, new GBC(0, 5).setAnchor(GBC.EAST));
        printed_panel.add(whatsapp_number_field, new GBC(1, 5).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        printed_panel.add(damage_description_label, new GBC(0, 6).setAnchor(GBC.EAST));
        printed_panel.add(damage_description_area, new GBC(1, 6).setWeight(100, 100).setFill(GBC.BOTH));
        this.add(printed_panel, new GBC(0,0).setWeight(100, 100).setFill(GBC.BOTH));
        this.add(buttons_panel, new GBC(0, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
    }

}
