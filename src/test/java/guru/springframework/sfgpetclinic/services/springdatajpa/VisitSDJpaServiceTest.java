package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Set<Visit> visits = Set.of(new Visit(), new Visit());
        Mockito.when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();
        assertEquals(2, foundVisits.size());
        Mockito.verify(visitRepository, Mockito.atLeastOnce()).findAll();
    }

    @Test
    void findById() {
        Visit v1 = new Visit();
        Mockito.when(visitRepository.findById(1L)).thenReturn(Optional.of(v1));
        Visit foundVisit = service.findById(1L);
        assertNotNull(foundVisit);
        Mockito.verify(visitRepository, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void save() {
        Visit v1 = new Visit();
        Mockito.when(visitRepository.save(v1)).thenReturn(v1);
        Visit savedVisit = service.save(v1);
        assertNotNull(savedVisit);
        Mockito.verify(visitRepository, Mockito.atLeastOnce()).save(ArgumentMatchers.any(Visit.class));
    }

    @Test
    void delete() {
        service.delete(new Visit());
        Mockito.verify(visitRepository, Mockito.atLeastOnce()).delete(ArgumentMatchers.any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        Mockito.verify(visitRepository, Mockito.atLeastOnce()).deleteById(ArgumentMatchers.anyLong());
    }
}