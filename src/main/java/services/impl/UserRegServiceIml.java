package services.impl;

import com.proto.user.User;
import repos.UserRepository;
import services.UserRegService;

import java.sql.SQLException;

public class UserRegServiceIml implements UserRegService {

    UserRepository repo = new UserRepository();

    @Override
    public boolean registerUser(User user) throws SQLException {
        return repo.createUser(user);
    }
}
