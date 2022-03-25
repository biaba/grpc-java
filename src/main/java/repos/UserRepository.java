package repos;

import com.proto.user.User;

import java.sql.SQLException;

public class UserRepository {

    public boolean createUser(User user) throws SQLException {
        DbConnection connection = new DbConnection();

        String sql="Insert into user (ID, username, password) values ";
        sql += String.format("(%s, '%s', '%s')", user.getUserId(),user.getUsername(),user.getPassword());
        boolean created = connection.executeUpdateStatement(sql);
        connection.closeConnection();
        return created;
    }
}
