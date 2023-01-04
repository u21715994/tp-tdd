package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static java.lang.Double.parseDouble;

@RequiredArgsConstructor
public class DrivingLicenceCreateService {
    private final InMemoryDatabase database;

    public DrivingLicence createDrivingLicence(String driverSocialSecurityNumber){
        if(validateSecurityNumber(driverSocialSecurityNumber) == false)
            throw new InvalidDriverSocialSecurityNumberException("Le numero de securite social n'est pas valide");
        UUID uuid = new DrivingLicenceIdGenerationService().generateNewDrivingLicenceId();
        DrivingLicence drivingLicence = DrivingLicence.builder().id(uuid)
                .driverSocialSecurityNumber(driverSocialSecurityNumber).build();
        saveInDatabase(uuid, drivingLicence);
        return drivingLicence;
    }

    public boolean validateSecurityNumber(String driverSocialSecurityNumber){
        if(driverSocialSecurityNumber == null)
            return false;
        if(driverSocialSecurityNumber.length() != 15)
            return false;
        if(Double.isNaN(parseDouble(driverSocialSecurityNumber)))
            return false;
        return true;
    }

    public void saveInDatabase(UUID uuid, DrivingLicence drivingLicence){
        database.save(uuid, drivingLicence);
    }
}
