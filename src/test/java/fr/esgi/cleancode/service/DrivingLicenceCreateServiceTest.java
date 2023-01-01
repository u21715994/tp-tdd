package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreateServiceTest {
    @InjectMocks
    DrivingLicenceCreateService drivingLicenceCreateService;
    @Mock
    InMemoryDatabase inMemoryDatabase;

    //@Test
    //public void test_security_number_null() {
    //    when(drivingLicenceCreateService.driverSocialSecurityNumberIsNull("123456789123456")).thenReturn(true);
    //    verify(drivingLicenceCreateService).driverSocialSecurityNumberIsNull("123456789123456");
    //    verifyNoInteractions(drivingLicenceCreateService);
    //}

    @Test
    public void test_create_driving_licence(){
        assertEquals("1234567891234567" == null, false);
        assertEquals("123456789123456".length(), 15);
        assertEquals("1234567891234567".isEmpty(), false);
        DrivingLicence drivingLicence = drivingLicenceCreateService.createDrivingLicence("1234567891234567");
        assertEquals(drivingLicence.getAvailablePoints(), 12);
    }

    @Test
    public void test_available_points() {
        final var id = UUID.randomUUID();
        final var drivingLicence = DrivingLicence.builder().id(id)
                .driverSocialSecurityNumber("123456789123456").build();
        assertEquals(drivingLicence.getAvailablePoints(), 12);
    }

    @Test
    public void test_save_in_database(){
        final var id = UUID.randomUUID();
        final var drivingLicence = DrivingLicence.builder().id(id).build();
        when(inMemoryDatabase.save(drivingLicence.getId(), drivingLicence)).thenReturn(drivingLicence);
        //verify(inMemoryDatabase).save(drivingLicence.getId(), drivingLicence);
        //verifyNoMoreInteractions(inMemoryDatabase);
        assertEquals(inMemoryDatabase.save(drivingLicence.getId(), drivingLicence), drivingLicence);
    }
}
