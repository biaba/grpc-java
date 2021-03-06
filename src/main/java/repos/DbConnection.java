package repos;

import java.sql.*;

public class DbConnection {

    public Connection connection;
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
        int rows = 0;
        try{
            rows = statement.executeUpdate(sql);
        } catch (SQLException e){
            System.out.println("SQL exception: "+ e.getLocalizedMessage());
        }
        return rows>0;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
