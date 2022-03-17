package krd.antonov.audiclubsheriff.service;

import krd.antonov.audiclubsheriff.exceptions.UserNotFoundException;
import krd.antonov.audiclubsheriff.model.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    User create(String name, Date dateBirth, String tgNickname, String phone);

    User get(Long id) throws UserNotFoundException;

    User getByTgNickname(String tgNickname) throws UserNotFoundException;

    User update(User user);

    void deleteUser(User user);

    List<User> getAllUsers();
}
