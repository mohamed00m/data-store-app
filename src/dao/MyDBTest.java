package dao;

import appmain.Client;
import java.util.List;

/**
 *
 * @author Mohamed Sayed
 */
public class MyDBTest {
    public static void main(String[] args) {
        ClientInfoDAO dao = new ClientInfoDAO();
        ConnectionSettings settings = new ConnectionSettings();
        settings.setUser("root");
        settings.setPassword("*1692537481base*");
        settings.setPort("3306");
        settings.setDbname("char1");
        settings.setIp("localhost");
        dao.setConnSettings(settings);

        Client client = new Client();
        client.setId(5);
        client.setName("Asssaaan");
        client.setDamageDescription("screen damage nononono and destruction");
        client.setDeviceNumber("553334343242120");
        client.setPhoneNumber("5434533");
        client.setReceiveDate("21/8/99");
        client.setReceiveDate("15/10/99");
        
        try {
            
             //dao.add_client(client);
            // dao.add_client(client);
             
            //dao.delete_client(3);
            //dao.update_client(client);
           // Client cl = dao.get_client(2);

            dao.makeTableInDatabase(); 
            List<Client> clients = dao.get_all_clients();
            for (Client cl : clients) {
                System.out.println(cl.getId() + " " + cl.getName() + " " + cl.getDamageDescription() + " " + cl.getDeviceNumber() + " " + cl.getPhoneNumber() + " " + cl.getWhatsappNumber());
            }
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }
}
