package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WithDrawDrivingLicenceServiceTest {
    @InjectMocks
    private WithDrawDrivingLicenceService withDrawDrivingLicenceService;

    @Mock
    private InMemoryDatabase database;

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6, 13})
    void withdraw_point_driving_licence_test(int point_to_remove){
        UUID id = new DrivingLicenceIdGenerationService().generateNewDrivingLicenceId();
        final var given = DrivingLicence.builder().id(id).build();
        when(database.findById(id)).thenReturn(Optional.ofNullable(given));
        assertEquals(point_to_remove > 0, true);
        var drivingLicence = withDrawDrivingLicenceService.withdrawPoint(id, point_to_remove);
        when(database.findById(id)).thenReturn(Optional.ofNullable(drivingLicence));
        verify(database).findById(id);
        assertThat(database.findById(id).get()).usingRecursiveComparison().isEqualTo(drivingLicence);
    }
}
