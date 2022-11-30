package fr.esgi.cleancode.model;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.service.DrivingLicenceIdGenerationService;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

import static java.lang.Double.parseDouble;

@Value
//@Builder
public class DrivingLicence {
    UUID id;
    String driverSocialSecurityNumber;

    @With
    @Default
    int availablePoints = 12;

    public DrivingLicence(String driverSocialSecurityNumber){
        this.id = new DrivingLicenceIdGenerationService().generateNewDrivingLicenceId();
        if(driverSocialSecurityNumberIsNull(driverSocialSecurityNumber))
            throw new InvalidDriverSocialSecurityNumberException("Numero securite sociale null");
        this.driverSocialSecurityNumber = driverSocialSecurityNumber;
        InMemoryDatabase.getInstance().save(this.id, this);
    }

    public boolean driverSocialSecurityNumberIsNull(String driverSocialSecurityNumber){
        return driverSocialSecurityNumber == null;
    }

    public boolean driverSocialSecurityNumberNotFormat(String driverSocialSecurityNumber){
        return Double.isNaN(parseDouble(driverSocialSecurityNumber));
    }

    public boolean driverSocialSecurityNumberLenghtIs15(String driverSocialSecurityNumber){
        return driverSocialSecurityNumber.length() == 15;
    }

    @Override
    public String toString() {
        return "DrivingLicence{" +
                "id=" + id +
                ", driverSocialSecurityNumber='" + driverSocialSecurityNumber + '\'' +
                ", availablePoints=" + availablePoints +
                '}';
    }
}
