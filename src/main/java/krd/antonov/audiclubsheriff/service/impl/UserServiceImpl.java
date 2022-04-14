package krd.antonov.audiclubsheriff.service.impl;

import krd.antonov.audiclubsheriff.exceptions.UserNotFoundException;
import krd.antonov.audiclubsheriff.model.User;
import krd.antonov.audiclubsheriff.repository.UserRepository;
import krd.antonov.audiclubsheriff.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(String chatId, String name, LocalDate dateBirth, String tgNickname,
                       String phone, String city, String comment, boolean active) {
        User user = new User().setChatId(chatId).setName(name).setDateBirth(dateBirth).setTgNickname(tgNickname)
                .setPhone(phone).setDateBirth(LocalDate.now()).setCity(city).setActive(active).setComment(comment);
        userRepository.save(user);
        return user;
    }

    @Override
    public User get(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }

    @Override
    public User getByTgNickname(String tgNickname) throws UserNotFoundException {
        return userRepository.findByTgNickname(tgNickname).orElseThrow(() -> new UserNotFoundException(tgNickname));
    }

    @Override
    public User getByChatId(String chatId) throws UserNotFoundException {
        return userRepository.findByChatId(chatId).orElseThrow(() -> new UserNotFoundException("chatId - " + chatId));
    }

    @Override
    public boolean existsByChatId(String chatId) {
        return userRepository.existsByChatId(chatId);
    }

    @Override
    @Transactional
    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
