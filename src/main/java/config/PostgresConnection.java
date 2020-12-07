package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

    public Connection connect() throws SQLException {
        String url = "jdbc:postgresql://devweb2020.cis.strath.ac.uk:5432/cs253";
        String user = "qwb19204";
        String password = "Sunshine-3014";
        return DriverManager.getConnection(url, user, password);
    }
}
