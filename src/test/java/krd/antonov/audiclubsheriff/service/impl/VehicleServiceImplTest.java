package krd.antonov.audiclubsheriff.service.impl;

import krd.antonov.audiclubsheriff.UnitTest;
import krd.antonov.audiclubsheriff.model.Vehicle;
import krd.antonov.audiclubsheriff.repository.VehicleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@UnitTest
public class VehicleServiceImplTest {

    @Spy
    private VehicleRepository vehicleRepository;

    @Test
    @DisplayName("existByLicensePlate returns OK")
    public void existByLicensePlate() {
        Vehicle vehicle = createMockEntity();
        Mockito.when(vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())).thenReturn(true);
        assertThat(vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())).isTrue();
    }

    private Vehicle createMockEntity() {
        return new Vehicle().setId(1L).setLicensePlate("Е050ВР123").setModel("AUDI A5 B9").setYear(2019);
    }
}