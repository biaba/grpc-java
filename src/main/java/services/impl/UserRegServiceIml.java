package services.impl;

import com.proto.user.User;
import services.UserRegService;

public class UserRegServiceIml implements UserRegService {

    @Override
    public boolean registerUser(User user) {
        System.out.println("Service called to register");
        // connects to db and registers user
        return true;
    }

    @Override
    public boolean userExistsInDb(User user) {
        System.out.println("User checked in DB");
        // db connection. Checking for username or id exists
        // imitating response
        long id = 4;
        String username = "ivo";
        boolean exists = false;

        if(user.getUserId() == id || user.getUsername().equals(username)){
            exists = true;
        }
        return exists;
    }

    @Override
    public long getNextId() {
        System.out.println("Next Id from DB");
        // connects to db and gets next available id
        return 5l;
    }
}
