package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;

import java.util.UUID;

import static java.lang.Double.parseDouble;

public class DrivingLicenceCreateService {
    public DrivingLicence createDrivingLicence(String driverSocialSecurityNumber){
        UUID uuid = new DrivingLicenceIdGenerationService().generateNewDrivingLicenceId();
        DrivingLicence drivingLicence = DrivingLicence.builder().id(uuid)
                .driverSocialSecurityNumber(driverSocialSecurityNumber).build();

        return drivingLicence;
    }
}
