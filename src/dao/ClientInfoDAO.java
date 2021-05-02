package dao;

import appmain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed Sayed
 */
public class ClientInfoDAO extends DAOBase {

    private String get_all_query;
    private String get_client_query;
    private String add_client_query;
    private String delete_client_query;
    private String update_client_query;

    public String getGet_all_query() {
        get_all_query = "select * from client_data";
        return get_all_query;
    }

    public String getGet_client_query() {
        get_client_query = "select * from client_data where id=?";
        return get_client_query;
    }

    public String getAdd_client_query() {
        add_client_query = "insert into client_data (name, device_number, damage_description, receive_date, phone_number, whatsapp_number) values(?,?,?,?,?,?)";
        return add_client_query;
    }

    public String getDelete_client_query() {
        delete_client_query = "delete from client_data where id=?";
        return delete_client_query;
    }

    public String getUpdate_client_query() {
        update_client_query = "update client_data set name=?, device_number=?, damage_description=?, receive_date=?, phone_number=?, whatsapp_number=? where id=?";
        return update_client_query;
    }
    ////////////////////////////////////////////////////////////////////////////

    public void add_client(Client client) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            // Prepare a statement to insert a record
            pStatement = connection.prepareStatement(getAdd_client_query());
            pStatement.setString(1, client.getName());
            pStatement.setString(2, client.getDeviceNumber());
            pStatement.setString(3, client.getDamageDescription());
            pStatement.setString(4, client.getReceiveDate());
            pStatement.setString(5, client.getPhoneNumber());
            pStatement.setString(6, client.getWhatsappNumber());
            pStatement.executeUpdate();
            pStatement.close();

        } catch (SQLException ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            DAOException daoEx = new DAOException();
            daoEx.setStackTrace(trace);
            throw daoEx;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                StackTraceElement[] trace = ex.getStackTrace();
                DAOException daoEx = new DAOException();
                daoEx.setStackTrace(trace);
                throw daoEx;
            }
        }

    }

    public void update_client(Client client) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(getUpdate_client_query());
            pStatement.setString(1, client.getName());
            pStatement.setString(2, client.getDeviceNumber());
            pStatement.setString(3, client.getDamageDescription());
            pStatement.setString(4, client.getReceiveDate());
            pStatement.setString(5, client.getPhoneNumber());
            pStatement.setString(6, client.getWhatsappNumber());
            pStatement.setInt(7, client.getId());
            pStatement.executeUpdate();
            pStatement.close();

        } catch (SQLException ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            DAOException daoEx = new DAOException();
            daoEx.setStackTrace(trace);
            throw daoEx;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                StackTraceElement[] trace = ex.getStackTrace();
                DAOException daoEx = new DAOException();
                daoEx.setStackTrace(trace);
                throw daoEx;
            }
        }
    }

    public Client get_client(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Client client = new Client();
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(getGet_client_query());
            pStatement.setInt(1, id);
            rs = pStatement.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setDamageDescription(rs.getString("damage_description"));
                client.setDeviceNumber(rs.getString("device_number"));
                client.setName(rs.getString("name"));
                client.setPhoneNumber(rs.getString("phone_number"));
                client.setWhatsappNumber(rs.getString("whatsapp_number"));
                rs.close();
                pStatement.close();
            }

        } catch (SQLException ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            DAOException daoEx = new DAOException();
            daoEx.setStackTrace(trace);
            throw daoEx;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                StackTraceElement[] trace = ex.getStackTrace();
                DAOException daoEx = new DAOException();
                daoEx.setStackTrace(trace);
                throw daoEx;
            }
        }

        return client;
    }

    public void delete_client(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(getDelete_client_query());
            pStatement.setInt(1, id);
            pStatement.executeUpdate();
            pStatement.close();

        } catch (SQLException ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            DAOException daoEx = new DAOException();
            daoEx.setStackTrace(trace);
            throw daoEx;
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                StackTraceElement[] trace = ex.getStackTrace();
                DAOException daoEx = new DAOException();
                daoEx.setStackTrace(trace);
                throw daoEx;
            }
        }
    }

    public List<Client> get_all_clients() throws DAOException {

        List<Client> all_clients = new ArrayList<>();
        Client client;
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pStatement = conn.prepareStatement(getGet_all_query());
            rs = pStatement.executeQuery();
            while (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setDamageDescription(rs.getString("damage_description"));
                client.setDeviceNumber(rs.getString("device_number"));
                client.setName(rs.getString("name"));
                client.setPhoneNumber(rs.getString("phone_number"));
                client.setWhatsappNumber(rs.getString("whatsapp_number"));
                all_clients.add(client);
            }
            rs.close();
            pStatement.close();

        } catch (SQLException ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            DAOException daoEx = new DAOException();
            daoEx.setStackTrace(trace);
            throw daoEx;
        } finally {
        }

        return all_clients;
    }

    public String getBatch_drop() {
        String batch_drop = "DROP TABLE IF EXISTS client_data;";
        return batch_drop;
    }

    public String getBatch_table_create() {
        String batch_table_create = "CREATE TABLE client_data" + "("
                + "name" + " varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,"
                + "id" + " int(11) NOT NULL AUTO_INCREMENT,"
                + "device_number" + " varchar(200)CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,"
                + "damage_description" + " varchar(5000)  CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,"
                + "receive_date" + " varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,"
                + "phone_number" + " varchar(45)CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,"
                + "whatsapp_number" + " varchar(45)CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,"
                + "PRIMARY KEY (" + "id" + ")"
                + ") ENGINE=InnoDB CHARSET=utf8 COLLATE utf8_general_ci;";
        return batch_table_create;
    }

    public String getBatch_lock() {
        String batch_lock = "LOCK TABLES " + "client_data" + " WRITE;";
        return batch_lock;
    }

    public String getBatch_insert() {
        String batch_insert = "INSERT INTO " + "client_data" + " VALUES ('mohamed',1,'553334343242120','screen damage','15/10/99','4411222',NULL),('Ali',2,'553334343242120','screen damage and destruction','15/10/99','45646',NULL)";
        return batch_insert;
    }

    public void makeTableInDatabase() throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.addBatch(getBatch_drop());
            stmt.addBatch(getBatch_table_create());
            stmt.addBatch(getBatch_lock());
            stmt.addBatch(getBatch_insert());
            stmt.executeBatch();
            stmt.close();
        } catch (SQLException ex) {
            StackTraceElement[] trace = ex.getStackTrace();
            DAOException daoEx = new DAOException();
            daoEx.setStackTrace(trace);
            throw daoEx;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                StackTraceElement[] trace = ex.getStackTrace();
                DAOException daoEx = new DAOException();
                daoEx.setStackTrace(trace);
                throw daoEx;
            }
        }
    }

}
