package repos;

import java.sql.*;

public class DbConnection {

    private Connection connection;
    private String jdbcURL = "jdbc:h2:mem:test";

    public DbConnection() throws SQLException {
        connection = DriverManager.getConnection(jdbcURL);
        Statement statement = connection.createStatement();
        statement.executeUpdate("Create table if not exists user (ID int primary key, username varchar(50), password varchar(50))");
    }

    // for INSERT, UPDATE, DELETE or DDL
    public boolean executeUpdateStatement(String sql) throws SQLException {
        System.out.println("in db connection updates");
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);

        return rows>0;
    }

    public ResultSet executeStatement(String sql) throws SQLException {
        System.out.println("in db connection selects");
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
