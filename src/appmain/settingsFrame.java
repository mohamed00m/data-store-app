/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appmain;
import dao.ConnectionSettings;
import dao.DAOException;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author smart
 */
public class settingsFrame extends JFrame {

    private JLabel user_label;
    private JLabel password_label;
    private JLabel port_label;
    private JTextField user_text;
    private JTextField password_text;
    private JTextField port_text;
    private JButton connect_button;
    private JLabel dbname_lbl;
    private JTextField dbname_txt;
    private JLabel ip_lbl;
    private JTextField ip_field;

    public settingsFrame() {
        this.setSize(447, 220);
        this.setLayout(new GridBagLayout());
        user_label = new JLabel("Username :");
        password_label = new JLabel("Password :");
        port_label = new JLabel("Port Number :");
        user_text = new JTextField("jhgjhg82_oman");
        dbname_lbl = new JLabel("Database Name :");
        dbname_txt = new JTextField("jhgjhg82_oman");
        //dbname_txt.setEditable(false);
        password_text = new JTextField("@aRBJi429g");
        port_text = new JTextField("3306");
        port_text.setEditable(false);
        ip_lbl = new JLabel("IP Adress :");
        ip_field = new JTextField("10.123.0.78");
        
        connect_button = new JButton("Connect");
        connect_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                connect_action();
            }
        });
        this.add(user_label, new GBC(0, 0).setAnchor(GBC.EAST));
        this.add(user_text, new GBC(1, 0).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(password_label, new GBC(0, 1).setAnchor(GBC.EAST));
        this.add(password_text, new GBC(1, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(dbname_lbl, new GBC(0, 2).setAnchor(GBC.EAST));
        this.add(dbname_txt, new GBC(1, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(port_label, new GBC(0, 3).setAnchor(GBC.EAST));
        this.add(port_text, new GBC(1, 3).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(ip_lbl, new GBC(0,4).setAnchor(GBC.EAST));
        this.add(ip_field, new GBC(1,4).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(connect_button, new GBC(0, 5).setAnchor(GBC.EAST));
    }

    private void connect_action() {
        ConnectionSettings connSettings = new ConnectionSettings();
        connSettings.setUser(user_text.getText());
        connSettings.setPassword(password_text.getText());
        connSettings.setPort(port_text.getText());
        connSettings.setDbname(dbname_txt.getText());
        connSettings.setIp(ip_field.getText());
        appFrame.settings.put("user", user_text.getText());
        appFrame.settings.put("password", password_text.getText());
        appFrame.settings.put("port", port_text.getText());
        appFrame.settings.put("ip", ip_field.getText());
        appFrame.settings.put("dbName", dbname_txt.getText());
        try {
            appFrame.setup_connection(connSettings);
            appFrame.dao.makeTableInDatabase();
            appFrame.model.setClients(appFrame.get_all_clients());
            appFrame.table.updateUI();
            this.setVisible(false);
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
        }
    }

}
