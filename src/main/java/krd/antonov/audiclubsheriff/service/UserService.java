package krd.antonov.audiclubsheriff.service;

import krd.antonov.audiclubsheriff.exceptions.UserNotFoundException;
import krd.antonov.audiclubsheriff.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User create(String chatId, String name, LocalDate dateBirth, String tgNickname,
                String phone, String city, String comment, boolean active);

    User get(Long id) throws UserNotFoundException;

    User getByTgNickname(String tgNickname) throws UserNotFoundException;

    boolean existsByChatId(String chatId);

    User update(User user);

    void deleteUser(User user);

    List<User> getAllUsers();
}
