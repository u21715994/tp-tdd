package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_find() {
        UUID id = new DrivingLicenceIdGenerationService().generateNewDrivingLicenceId();
        final var given = DrivingLicence.builder().id(id).build();
        when(database.findById(id)).thenReturn(Optional.ofNullable(given));
        var given_service = service.findById(id);
        verify(database).findById(id);
        verifyNoMoreInteractions(database);
        assertEquals(database.findById(id), given_service);
        assertThat(given_service.get()).usingRecursiveComparison().isEqualTo(given);
    }

    @Test
    void should_not_find() {
        UUID id = new DrivingLicenceIdGenerationService().generateNewDrivingLicenceId();
        final var given = DrivingLicence.builder().id(id).build();
        when(database.findById(id)).thenReturn(Optional.empty());
        var given_service = service.findById(id);
        verify(database).findById(id);
        verifyNoMoreInteractions(database);
        assertNotEquals(Optional.of(given), given_service);
        assertThat(given_service).usingRecursiveComparison().isEqualTo(Optional.empty());
    }
}