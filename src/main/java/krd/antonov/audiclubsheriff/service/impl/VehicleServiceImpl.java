package krd.antonov.audiclubsheriff.service.impl;

import krd.antonov.audiclubsheriff.repository.VehicleRepository;
import krd.antonov.audiclubsheriff.service.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public boolean existByLicensePlate(String licensePlate) {
        return vehicleRepository.existsByLicensePlate(licensePlate);
    }
}
