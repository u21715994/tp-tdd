package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class WithDrawDrivingLicenceService {
    private final InMemoryDatabase database;
    public DrivingLicence withdrawPoint(UUID drivingLicenceID, int point_to_remove){
        DrivingLicence drivingLicence = database.findById(drivingLicenceID).get();
        if(drivingLicence == null)
            throw new ResourceNotFoundException("Permis de conduire introuvable");
        drivingLicence = drivingLicence.withAvailablePoints(drivingLicence.getAvailablePoints() - point_to_remove);
        database.save(drivingLicenceID, drivingLicence);
        return drivingLicence;
    }
}
