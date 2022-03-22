package services;

import com.proto.user.User;

public interface UserRegService {

    boolean registerUser(User user);

    boolean userExistsInDb(User user);

    long getNextId();
}
