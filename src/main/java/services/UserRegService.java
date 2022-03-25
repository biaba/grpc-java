package services;

import com.proto.user.User;

import java.sql.SQLException;

public interface UserRegService {

    boolean registerUser(User user) throws SQLException;
}
