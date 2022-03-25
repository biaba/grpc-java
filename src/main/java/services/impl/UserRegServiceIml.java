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

    @Override
    public boolean userExistsInDb(User user) throws SQLException {
        boolean exists = false;
        if(repo.userExists(user.getUserId(), user.getUsername())){
            exists = true;
        }
        return exists;
    }

    @Override
    public long getNextId() throws SQLException {
        System.out.println("Next Id from DB");
        long nextID = repo.nextAvailableId();
        return nextID;
    }
}
