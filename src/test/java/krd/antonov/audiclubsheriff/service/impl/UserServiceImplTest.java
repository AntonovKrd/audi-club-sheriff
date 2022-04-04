package krd.antonov.audiclubsheriff.service.impl;

import krd.antonov.audiclubsheriff.UnitTest;
import krd.antonov.audiclubsheriff.exceptions.UserNotFoundException;
import krd.antonov.audiclubsheriff.model.User;
import krd.antonov.audiclubsheriff.service.UserService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.List;

@UnitTest
public class UserServiceImplTest {

    @Spy
    private UserService userService;

    @Test
    public void create() {
        Mockito.when(userService.create("test","Владимир",
                LocalDate.of(1997, 6, 3),
                "@antonov_krd",
                "89181144744", "Краснодар", true)).thenReturn(getMockEntity());
        AssertionsForClassTypes.assertThat(userService.create("test","Владимир",
                LocalDate.of(1997, 6, 3),
                "@antonov_krd",
                "89181144744", "Краснодар", true)).isNotNull();
    }

    @Test
    public void get() throws UserNotFoundException {
        Mockito.when(userService.get(1L)).thenReturn(getMockEntity());
        AssertionsForClassTypes.assertThat(getMockEntity()).isEqualTo(userService.get(1L));
    }

    @Test
    public void getByTgNickname() throws UserNotFoundException {
        Mockito.when(userService.getByTgNickname("@antonov_krd")).thenReturn(getMockEntity());
        AssertionsForClassTypes.assertThat(userService.getByTgNickname("@antonov_krd")).isEqualTo(getMockEntity());
    }

    @Test
    public void update() {
        Mockito.when(userService.update(getMockEntity())).thenReturn(getMockEntity());
        AssertionsForClassTypes.assertThat(userService.update(getMockEntity())).isEqualTo(getMockEntity());
    }

    @Test
    public void deleteUser() throws UserNotFoundException {
        Mockito.when(userService.getByTgNickname("@antonov_krd")).thenThrow(new UserNotFoundException("1"));
        userService.deleteUser(getMockEntity());
        AssertionsForClassTypes.assertThatThrownBy(() -> userService.getByTgNickname("@antonov_krd")).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void getAllUsers() {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(getMockEntity(), getMockEntity(), getMockEntity()));
        AssertionsForClassTypes.assertThat(userService.getAllUsers().size()).isEqualTo(3);
    }

    private User getMockEntity() {
        return new User()
                .setId(1L)
                .setChatId("test")
                .setName("Владимир")
                .setDateBirth(LocalDate.of(1997, 6, 3))
                .setTgNickname("@antonov_krd")
                .setPhone("89181144744")
                .setCity("Краснодар")
                .setActive(true);
    }
}