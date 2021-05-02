/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appmain;

import dao.ClientInfoDAO;
import dao.ConnectionSettings;
import dao.DAOException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smart
 */
final class appFrame extends JFrame {

    private JButton add_button;
    private JButton view_button;
    private JButton delete_button;
    private JPanel buttons_panel;

    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    public static final ClientInfoDAO dao = new ClientInfoDAO();
    public static JTable table;
    public static MyTableModel model;

    private int left;
    private int top;
    private int width;
    private int height;

    private static ConnectionSettings connSettings;
    private File propertiesFile;
    public static Properties settings;

    public appFrame() {

        this.setSize(700, 200);
        this.setLayout(new GridBagLayout());

        // get position, size, title from properties
        String userDir = System.getProperty("user.home");
        File propertiesDir = new File(userDir, ".clientdata");
        if (!propertiesDir.exists()) {
            propertiesDir.mkdir();
        }
        propertiesFile = new File(propertiesDir, "program.properties");
        Properties defaultSettings = new Properties();
        defaultSettings.put("left", "0");
        defaultSettings.put("top", "0");
        defaultSettings.put("width", "" + DEFAULT_WIDTH);
        defaultSettings.put("height", "" + DEFAULT_HEIGHT);
        defaultSettings.put("user", "jhgjhg82_oman");
        defaultSettings.put("password", "@aRBJi429g");
        defaultSettings.put("port", "3306");
        defaultSettings.put("ip", "10.123.0.78");
        defaultSettings.put("dbName", "jhgjhg82_oman");

        settings = new Properties(defaultSettings);

        if (propertiesFile.exists()) {
            try {
                FileInputStream in = new FileInputStream(propertiesFile);
                settings.load(in);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        left = Integer.parseInt(settings.getProperty("left"));
        top = Integer.parseInt(settings.getProperty("top"));
        width = Integer.parseInt(settings.getProperty("width"));
        height = Integer.parseInt(settings.getProperty("height"));

        this.setBounds(left, top, width, height);

        model = new MyTableModel();

        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        buttons_panel = new JPanel();

        add_button = new JButton();
        add_button.setText("Add Client");
        add_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //add_client(null);
                openAdd();
            }
        });
        buttons_panel.add(add_button);

        view_button = new JButton();
        view_button.setText("View Client");
        view_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //open the update frame with the current selection
                    //method name: openUpdate();
                    openUpdate();
                } catch (DAOException ex) {
                    JOptionPane.showMessageDialog(null, "Select a Row");
                }
            }
        });
        buttons_panel.add(view_button);
        delete_button = new JButton();
        delete_button.setText("Delete Selected Client");
        delete_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedIndex = table.getSelectedRow();
                Client cl = model.getClients().get(selectedIndex);
                try {
                    delete_client(cl.getId());
                } catch (DAOException ex) {
                    JOptionPane.showMessageDialog(null, "Please setup your connection From Settings menu!");
                }
            }
        });
        buttons_panel.add(delete_button);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Settings");
        JMenuItem connectionSettingsItem = new JMenuItem("Connection Settings");
        connectionSettingsItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                settingsFrame frame = new settingsFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("settings_icon.png")));
                frame.setVisible(true);
            }
        });
        menu.add(connectionSettingsItem);
        bar.add(menu);
        this.setJMenuBar(bar);

        this.add(new JScrollPane(table), new GBC(0, 0).setWeight(100, 100).setFill(GBC.BOTH));
        this.add(buttons_panel, new GBC(0, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                settings.put("left", "" + getX());
                settings.put("top", "" + getY());
                settings.put("width", "" + getWidth());
                settings.put("height", "" + getHeight());
                settings.put("title", getTitle());
                try {
                    FileOutputStream out = new FileOutputStream(propertiesFile);
                    settings.store(out, "Program Properties");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }

        });
        try {
            setup_connection(null);
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(null, "Please Setup Connection");
        }
    }

    public void openUpdate() throws DAOException {
        int selectedIndex = table.getSelectedRow();
        Client cl = model.clients.get(selectedIndex);
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                updateFrame frame = new updateFrame(cl);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("update_icon.png")));
                frame.setVisible(true);
            }
        });

    }

    public void openAdd() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                addFrame frame = new addFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("add_icon.png")));
                frame.setVisible(true);
            }
        });

    }

    public static void setup_connection(ConnectionSettings conn) throws DAOException {
        connSettings = new ConnectionSettings();
        /////////////////////////////////////////
        /*
         node.put("user", "jhgjhg82_oman");
         node.put("password", "@aRBJi429g");
         node.put("ip", "10.123.0.78");
         node.put("port", "3306");
         node.put("dbName", "jhgjhg82_oman");
         */
        /////////////////////////////////////////
        connSettings.setUser(settings.getProperty("user", "jhgjhg82_oman"));
        connSettings.setPassword(settings.getProperty("password", "@aRBJi429g"));
        connSettings.setPort(settings.getProperty("port", "3306"));
        connSettings.setDbname(settings.getProperty("dbName", "jhgjhg82_oman"));
        connSettings.setIp(settings.getProperty("ip", "10.123.0.78"));
        if (conn != null
                && (conn.getUser() != null && conn.getUser() != "")
                && (conn.getPassword() != null && conn.getPassword() != "")
                && (conn.getPort() != null && conn.getPort() != "")
                && (conn.getIp() != null && conn.getIp() != "")
                && (conn.getDbname() != null && conn.getDbname() != "")) {
            connSettings.setUser(conn.getUser());
            connSettings.setPassword(conn.getPassword());
            connSettings.setPort(conn.getPort());
            connSettings.setDbname(conn.getDbname());
            connSettings.setIp(conn.getIp());

            settings.put("user", conn.getUser());
            settings.put("password", conn.getPassword());
            settings.put("port", conn.getPort());
            settings.put("ip", conn.getIp());
            settings.put("dbName", conn.getDbname());
        }
        dao.setConnSettings(connSettings);
    }

    public static void add_client(Client client) throws DAOException {
        dao.add_client(client);
        System.out.println("client added...");
        model.setClients(get_all_clients());
        table.updateUI();
    }

    public static void update_client(Client client) throws DAOException {
        dao.update_client(client);
        System.out.println("client updated...");
        model.setClients(get_all_clients());
        table.updateUI();
    }

    public static Client get_client(int id) throws DAOException {
        return dao.get_client(id);
    }

    public static void delete_client(int id) throws DAOException {

        dao.delete_client(id);
        System.out.println("client deleted...");
        model.setClients(get_all_clients());
        table.updateUI();

    }

    public static List<Client> get_all_clients() throws DAOException {
        return dao.get_all_clients();
    }

    class MyTableModel extends AbstractTableModel {

        private ArrayList<Client> list = new ArrayList<>();

        private List<Client> clients = list;

        public void setClients(List<Client> clients) {
            this.clients = clients;
        }

        public MyTableModel() {
            list.add(new Client());
        }

        @Override
        public int getRowCount() {
            return clients.size();
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public Object getValueAt(int r, int c) {
            if (c == 0) {
                return clients.get(r).getId();
            } else if (c == 1) {
                return clients.get(r).getName();
            } else if (c == 2) {
                return clients.get(r).getReceiveDate();
            } else if (c == 3) {
                return clients.get(r).getDeviceNumber();
            } else if (c == 4) {
                return clients.get(r).getPhoneNumber();
            } else if (c == 5) {
                return clients.get(r).getWhatsappNumber();
            } else {
                return clients.get(r).getDamageDescription();
            }
        }

        @Override
        public String getColumnName(int c) {
            if (c == 0) {
                return "ID";
            } else if (c == 1) {
                return "Name";
            } else if (c == 2) {
                return "Receive Date";
            } else if (c == 3) {
                return "Device Number";
            } else if (c == 4) {
                return "Phone Number";
            } else if (c == 5) {
                return "Whatsapp Number";
            } else {
                return "Damage Description";
            }
        }

        private List<Client> getClients() {
            return clients;
        }

    }
}

class addFrame extends JFrame {

    private JButton add_button = new JButton();
    private JLabel name_label = new JLabel();
    private JLabel device_number_label = new JLabel();
    private JLabel receive_date_label = new JLabel();
    private JLabel phone_number_label = new JLabel();
    private JLabel whatsapp_number_label = new JLabel();
    private JLabel damage_description_label = new JLabel();
    private JTextField name_field = new JTextField();
    private JTextField device_number_field = new JTextField();
    private JTextField receive_date_field = new JTextField();
    private JTextField phone_number_field = new JTextField();
    private JTextField whatsapp_number_field = new JTextField();
    private JTextArea damage_description_area = new JTextArea();

    public addFrame() {
        this.setSize(350, 250);
        this.setLayout(new GridBagLayout());
        name_label.setText("Name :");
        device_number_label.setText("Device Number :");
        receive_date_label.setText("Receive Date :");
        phone_number_label.setText("Phone Number :");
        whatsapp_number_label.setText("Whatsapp Number :");
        damage_description_label.setText("Damage Description :");
        damage_description_area.setFont(new Font("Dialog", Font.PLAIN, 17));
        add_button.setText("Add");
        add_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Client cl = new Client();
                cl.setName(name_field.getText());
                cl.setDeviceNumber(device_number_field.getText());
                cl.setReceiveDate(receive_date_field.getText());
                cl.setPhoneNumber(phone_number_field.getText());
                cl.setWhatsappNumber(whatsapp_number_field.getText());
                cl.setDamageDescription(damage_description_area.getText());
                try {
                    appFrame.add_client(cl);
                    addFrame.this.setVisible(false);
                } catch (DAOException ex) {
                    JOptionPane.showMessageDialog(null, "problem adding , setup Connection!");
                    addFrame.this.setVisible(false);
                }
                addFrame.this.setVisible(false);
            }
        });
        this.add(name_label, new GBC(0, 1).setAnchor(GBC.EAST));
        this.add(name_field, new GBC(1, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(device_number_label, new GBC(0, 2).setAnchor(GBC.EAST));
        this.add(device_number_field, new GBC(1, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(receive_date_label, new GBC(0, 3).setAnchor(GBC.EAST));
        this.add(receive_date_field, new GBC(1, 3).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(phone_number_label, new GBC(0, 4).setAnchor(GBC.EAST));
        this.add(phone_number_field, new GBC(1, 4).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(whatsapp_number_label, new GBC(0, 5).setAnchor(GBC.EAST));
        this.add(whatsapp_number_field, new GBC(1, 5).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        this.add(damage_description_label, new GBC(0, 6).setAnchor(GBC.EAST));
        this.add(damage_description_area, new GBC(1, 6).setWeight(100, 100).setFill(GBC.BOTH));
        this.add(add_button, new GBC(0, 7).setAnchor(GBC.EAST));
    }
}
