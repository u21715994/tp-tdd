package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class WithDrawDrivingLicenceService {
    private final InMemoryDatabase database;
    public DrivingLicence withdrawPoint(UUID drivingLicenceID, int point_to_remove){
        return null;
    }
}
