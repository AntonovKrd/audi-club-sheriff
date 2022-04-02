package krd.antonov.audiclubsheriff.telegram.service;

import krd.antonov.audiclubsheriff.service.TempDataService;
import krd.antonov.audiclubsheriff.service.UserService;
import krd.antonov.audiclubsheriff.service.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class ManageUsersService {

    private final TempDataService dataService;
    private final UserService userService;
    private final VehicleService vehicleService;

    public ManageUsersService(TempDataService dataService, UserService userService, VehicleService vehicleService) {
        this.dataService = dataService;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    public void registerUser(String chatId){

    }
}
