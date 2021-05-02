package dao;

/**
 *
 * @author Mohamed Sayed
 */
public class ConnectionSettings {
    private String user;
    private String password;
    private String url;
    private String port;
    private String ip;
    private String dbname;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
    
   
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
   
    public String getUrl() {
        this.url ="jdbc:mysql://"+ getIp() +":"+getPort()+"/"+getDbname();
        return url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
