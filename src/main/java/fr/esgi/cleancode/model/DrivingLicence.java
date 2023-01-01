package fr.esgi.cleancode.model;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.service.DrivingLicenceCreateService;
import fr.esgi.cleancode.service.DrivingLicenceIdGenerationService;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;

import static java.lang.Double.parseDouble;

@Value
@Builder
public class DrivingLicence {
    UUID id;
    String driverSocialSecurityNumber;

    @With
    @Default
    int availablePoints = 12;



    @Override
    public String toString() {
        return "DrivingLicence{" +
                "id=" + id +
                ", driverSocialSecurityNumber='" + driverSocialSecurityNumber + '\'' +
                ", availablePoints=" + availablePoints +
                '}';
    }
}
