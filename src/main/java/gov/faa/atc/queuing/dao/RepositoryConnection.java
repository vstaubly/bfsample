package gov.faa.atc.queuing.dao;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

public class RepositoryConnection
{
    private static RepositoryConnection rp = null;

    private Properties cfg;
    private Connection conn;

    public RepositoryConnection()
    {
        conn = null;
        cfg = new Properties();
        try {
            cfg.load(RepositoryConnection.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean connect()
    {
        try {
            String driverClass = cfg.getProperty("db.class");
            String username = cfg.getProperty("db.user");
            String password = cfg.getProperty("db.pass");
            String url = cfg.getProperty("db.url");

            if ((driverClass != null) && (driverClass.length() > 0))
                Class.forName(driverClass); // pre-load may be needed in some cases
            if ((username != null) && (username.length() > 0))
                conn = DriverManager.getConnection(url, username, password);
            else
                conn = DriverManager.getConnection(url);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // should be logging this
        }
        return false;
    }

    public Connection getConnection()
    {
        return conn;
    }

    public static RepositoryConnection getRepository()
    {
        if (rp == null) {
            rp = new RepositoryConnection();
            if (! rp.connect())
                rp = null;
        }
        return rp;
    }
}
