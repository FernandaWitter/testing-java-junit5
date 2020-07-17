package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.atLeastOnce()).deleteById(1L);
        Mockito.verify(specialtyRepository, Mockito.atLeast(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.never()).deleteById(5L);
    }

    @Test
    void testDelete(){
        specialitySDJpaService.delete(new Speciality());
    }

}