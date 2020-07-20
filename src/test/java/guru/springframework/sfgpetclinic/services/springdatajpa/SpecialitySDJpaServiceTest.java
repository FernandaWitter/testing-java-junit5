package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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
    void findByIdBddTest(){
        // Given
        Speciality speciality = new Speciality();
        BDDMockito.given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
        // When
        Speciality foundSpecialty = specialitySDJpaService.findById(1L);
        // Then
        Assertions.assertNotNull(foundSpecialty);
//        BDDMockito.then(specialtyRepository).should().findById(ArgumentMatchers.anyLong());
        BDDMockito.then(specialtyRepository).should(Mockito.times(1)).findById(ArgumentMatchers.anyLong());
//        BDDMockito.then(specialtyRepository).shouldHaveNoInteractions();
    }

    @Test
    void deleteByObjectTest(){
        Speciality speciality = new Speciality();
        specialitySDJpaService.delete(speciality);
        Mockito.verify(specialtyRepository).delete(ArgumentMatchers.any(Speciality.class));
    }

    @Test
    void deleteByObjectBddTest(){
        // Given
        Speciality speciality = new Speciality();
        // When
        specialitySDJpaService.delete(speciality);
        // Then
        BDDMockito.then(specialtyRepository).should().delete(ArgumentMatchers.any(Speciality.class));
    }

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdBddTest() {
        // Given - none

        // When
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);
        // Then
        BDDMockito.then(specialtyRepository).should(Mockito.times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.atLeastOnce()).deleteById(1L);
        Mockito.verify(specialtyRepository, Mockito.atLeast(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeastBddTest() {
        // Given

        // When
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);
        // Then
        BDDMockito.then(specialtyRepository).should(Mockito.atLeastOnce()).deleteById(1L);
        BDDMockito.then(specialtyRepository).should(Mockito.atLeast(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdAtMostBddTest() {
        // Given

        // When
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);
        // Then
        BDDMockito.then(specialtyRepository).should(Mockito.atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        Mockito.verify(specialtyRepository, Mockito.atLeastOnce()).deleteById(1L);
        Mockito.verify(specialtyRepository, Mockito.never()).deleteById(5L);
    }

    @Test
    void deleteByIdNeverBddTest() {
        // Given

        // When
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);
        // Then
        BDDMockito.then(specialtyRepository).should(Mockito.atLeastOnce()).deleteById(1L);
        BDDMockito.then(specialtyRepository).should(Mockito.never()).deleteById(5L);
    }

    @Test
    void testDelete(){
        specialitySDJpaService.delete(new Speciality());
    }

    @Test
    void testDeleteBdd(){
        // When
        specialitySDJpaService.delete(new Speciality());
        // Then
        BDDMockito.then(specialtyRepository).should().delete(ArgumentMatchers.any(Speciality.class));
    }

}