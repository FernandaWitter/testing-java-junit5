package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        // Given
        Set<Visit> visits = Set.of(new Visit(), new Visit());
        BDDMockito.given(visitRepository.findAll()).willReturn(visits);
        // When
        Set<Visit> foundVisits = service.findAll();
        // Then
        assertEquals(2, foundVisits.size());
        BDDMockito.then(visitRepository).should().findAll();
    }

    @Test
    void findById() {
        // Given
        Visit v1 = new Visit();
        BDDMockito.given(visitRepository.findById(1L)).willReturn(Optional.of(v1));
        // When
        Visit foundVisit = service.findById(1L);
        // Then
        assertNotNull(foundVisit);
        BDDMockito.then(visitRepository).should(Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void save() {
        // Given
        Visit v1 = new Visit();
        BDDMockito.given(visitRepository.save(v1)).willReturn(v1);
        // When
        Visit savedVisit = service.save(v1);
        // Then
        assertNotNull(savedVisit);
        BDDMockito.then(visitRepository).should(Mockito.atLeastOnce()).save(ArgumentMatchers.any(Visit.class));
    }

    @Test
    void delete() {
        // When
        service.delete(new Visit());
        // Then
        BDDMockito.then(visitRepository).should(Mockito.atLeastOnce()).delete(ArgumentMatchers.any(Visit.class));
    }

    @Test
    void deleteById() {
        // When
        service.deleteById(1L);
        // Then
        BDDMockito.then(visitRepository).should(Mockito.atLeastOnce()).deleteById(ArgumentMatchers.anyLong());
    }
}