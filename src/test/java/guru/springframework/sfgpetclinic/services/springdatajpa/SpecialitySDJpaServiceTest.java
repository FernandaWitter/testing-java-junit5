package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void findByIdTest(){
        Speciality speciality = new Speciality();
        Mockito.when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality foundSpecialty = specialitySDJpaService.findById(1L);
        Assertions.assertNotNull(foundSpecialty);
        Mockito.verify(specialtyRepository).findById(1L);
    }

    @Test
    void deleteByObjectTest(){
        Speciality speciality = new Speciality();
        specialitySDJpaService.delete(speciality);
        Mockito.verify(specialtyRepository).delete(ArgumentMatchers.any(Speciality.class));
    }

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