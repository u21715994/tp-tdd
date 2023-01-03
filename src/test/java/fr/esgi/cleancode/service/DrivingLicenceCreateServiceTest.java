package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreateServiceTest {
    @InjectMocks
    DrivingLicenceCreateService drivingLicenceCreateService;
    @Mock
    InMemoryDatabase inMemoryDatabase;

    @Test
    public void test_validate_security_number(){
        var drivingLicence = drivingLicenceCreateService.createDrivingLicence("123456789123456");
        assertTrue(drivingLicenceCreateService.validateSecurityNumber(drivingLicence.getDriverSocialSecurityNumber()));
    }
    @Test
    public void test_create_driving_licence(){
        var drivingLicence = drivingLicenceCreateService.createDrivingLicence("123456789123456");
        assertEquals(drivingLicence.getAvailablePoints(), 12);
        when(inMemoryDatabase.findById(drivingLicence.getId())).thenReturn(Optional.ofNullable(drivingLicence));
        var drivingLicenceFound = inMemoryDatabase.findById(drivingLicence.getId());
        verify(inMemoryDatabase).findById(drivingLicence.getId());
        assertThat(drivingLicenceFound.get()).usingRecursiveComparison().isEqualTo(drivingLicence);
    }
}
