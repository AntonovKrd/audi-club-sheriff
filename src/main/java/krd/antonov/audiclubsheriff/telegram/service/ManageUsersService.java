package krd.antonov.audiclubsheriff.telegram.service;

import krd.antonov.audiclubsheriff.exceptions.TempDataNotFoundException;
import krd.antonov.audiclubsheriff.model.TempData;
import krd.antonov.audiclubsheriff.model.User;
import krd.antonov.audiclubsheriff.model.Vehicle;
import krd.antonov.audiclubsheriff.service.TempDataService;
import krd.antonov.audiclubsheriff.service.UserService;
import krd.antonov.audiclubsheriff.util.DataConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManageUsersService {

    private final TempDataService dataService;
    private final UserService userService;

    public ManageUsersService(TempDataService dataService, UserService userService) {
        this.dataService = dataService;
        this.userService = userService;
    }

    public void registerUserWithVehicle(String chatId) throws TempDataNotFoundException {
        List<TempData> tempData = dataService.getListTempDataByChatId(chatId);
        User user = new User().setDateRegistration(LocalDateTime.now());
        Vehicle vehicle = new Vehicle();
        for (TempData data : tempData) {
            switch (data.getStage()) {
                case 0 -> user.setTgNickname(data.getValue());
                case 1 -> user.setPhone(data.getValue());
                case 2 -> {
                    user.setName(data.getValue());
                    user.setChatId(data.getChatId());
                }
                case 3 -> user.setDateBirth(DataConverter.convertStringToLocalDate(data.getValue()));
                case 4 -> user.setCity(data.getValue());
                case 5 -> vehicle.setModel(data.getValue());
                case 6 -> vehicle.setYear(Integer.valueOf(data.getValue()));
                case 7 -> vehicle.setLicensePlate(data.getValue());
            }
        }
        user.addVehicle(vehicle);
        userService.update(user);
        dataService.deleteTempDataByChatId(chatId);
    }
}
