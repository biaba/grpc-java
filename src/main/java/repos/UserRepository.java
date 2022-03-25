package repos;

import com.proto.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public boolean createUser(User user) throws SQLException {
        DbConnection connection = new DbConnection();

        String sql="Insert into user (ID, username, password) values ";
        sql += String.format("(%s, '%s', '%s')", user.getUserId(),user.getUsername(),user.getPassword());
        return connection.executeUpdateStatement(sql);
    }

    public boolean userExists(long id, String username) throws SQLException {
        DbConnection connection = new DbConnection();
        String sql = String.format("SELECT * FROM user WHERE ID=%s OR username='%s'", id, username);
        System.out.println(connection.executeStatement(sql));
        System.out.println("in REPO user exists "+ (connection.executeStatement(sql).first()));
        return connection.executeStatement(sql).first();
    }

    public long nextAvailableId() throws SQLException {
        DbConnection connection = new DbConnection();
        String sql = String.format("SELECT ID FROM user ORDER BY ID DESC LIMIT 1");
        ResultSet resultSet = connection.executeStatement(sql);
        long nextValue = 1l;
        while(resultSet.next()){
            nextValue += resultSet.getLong("ID");
        }
        return nextValue;
    }
}
