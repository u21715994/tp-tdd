package fr.esgi.cleancode.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ValidateSecurityNumberServiceTest {
    @Mock
    ValidateSecurityNumberService validateSecurityNumberService;
    @Test
    public void test_security_number_null(){
        lenient().when(validateSecurityNumberService.driverSocialSecurityNumberIsNull("123456789123456")).thenReturn(false);
        assertEquals(validateSecurityNumberService.driverSocialSecurityNumberIsNull("123456789123456"), false);
    }

    @Test
    public void test_security_number_contains_number(){
        lenient().when(validateSecurityNumberService.driverSocialSecurityNumberNotFormat("123456789123456")).thenReturn(true);
        assertEquals(validateSecurityNumberService.driverSocialSecurityNumberNotFormat("123456789123456"), true);
        //when(drivingLicenceCreateService.driverSocialSecurityNumberNotFormat("123456789123456")).thenReturn(true);
        //verify(drivingLicenceCreateService).driverSocialSecurityNumberNotFormat("123456789123456");
        //verifyNoInteractions(drivingLicenceCreateService);
    }

    @Test
    public void test_security_number_length(){
        lenient().when(validateSecurityNumberService.driverSocialSecurityNumberLenghtIs15("123456789123456")).thenReturn(true);
        assertEquals(validateSecurityNumberService.driverSocialSecurityNumberLenghtIs15("123456789123456"), true);
        //when(drivingLicenceCreateService.driverSocialSecurityNumberLenghtIs15("123456789123456")).thenReturn(true);
        //verify(drivingLicenceCreateService).driverSocialSecurityNumberLenghtIs15("123456789123456");
        //verifyNoInteractions(drivingLicenceCreateService);
    }
}
