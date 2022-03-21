package krd.antonov.audiclubsheriff.service.impl;

import krd.antonov.audiclubsheriff.UnitTest;
import krd.antonov.audiclubsheriff.model.Vehicle;
import krd.antonov.audiclubsheriff.service.VehicleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@UnitTest
public class VehicleServiceImplTest {

    @Spy
    private VehicleService vehicleService;

    @Test
    @DisplayName("existByLicensePlate returns OK")
    public void existByLicensePlate() {
        Vehicle vehicle = createMockEntity();
        Mockito.when(vehicleService.existByLicensePlate(vehicle.getLicensePlate())).thenReturn(true);
        assertThat(vehicleService.existByLicensePlate(vehicle.getLicensePlate())).isTrue();
    }

    private Vehicle createMockEntity() {
        return new Vehicle().setId(1L).setLicensePlate("Е050ВР123").setModel("AUDI A5 B9").setYear(2019);
    }
}