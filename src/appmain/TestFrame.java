/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appmain;


import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author smart
 */
public class TestFrame {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                appFrame frame = new appFrame();
                frame.setTitle("Testing...");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                /*
                 Client client = new Client();
                 client.setName("aboElseed Gameeed");
                 client.setDamageDescription("Not Working");
                 client.setDeviceNumber("68951112");
                 client.setReceiveDate("10/10/20");
                 client.setPhoneNumber("01255588655");
                 client.setWhatsappNumber("543545461");
                 client.setId(4); 
                 */
                /*
                try {
                    //frame.add_client(client);

                    ConnectionSettings settings = new ConnectionSettings();
                    settings.setUser("jhgjhg82_oman");
                    settings.setPassword("@aRBJi429g");
                    settings.setDriver("com.mysql.jdbc.Driver");
                    settings.setPort("3306");

                    DatabaseSettings dbsettings = new DatabaseSettings();
                    dbsettings.setDatabaseName("jhgjhg82_oman");
                    dbsettings.setTableName("client_data");
                    Table tbl = new Table();
                    tbl.setId_alias("id_alias");
                    tbl.setName_alias("name_alias");
                    tbl.setDamageDescription_alias("damage_description");
                    tbl.setDeviceNumber_alias("device_number");
                    tbl.setPhoneNumber_alias("phone_number");
                    tbl.setReceiveDate_alias("receive_date");
                    tbl.setWhatsappNumber_alias("whatsapp_number");
                    dbsettings.setTableInfo(tbl);
                    appFrame.setup_connection(settings, dbsettings);

                    //frame.update_client(client);
                } catch (DAOException ex) {
                    Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    frame.delete_client(4);
                } catch (DAOException ex) {
                    Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Client cl = frame.get_client(1);
                List<Client> clients = null;
                try {
                    clients = frame.get_all_clients();
                } catch (DAOException ex) {
                    Logger.getLogger(TestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Client cl : clients) {
                    System.out.println(cl.getId() + " " + cl.getName() + " " + cl.getDamageDescription() + " " + cl.getDeviceNumber() + " " + cl.getPhoneNumber() + " " + cl.getReceiveDate() + " " + cl.getWhatsappNumber());
                }*/
            }
        });

    }
}
