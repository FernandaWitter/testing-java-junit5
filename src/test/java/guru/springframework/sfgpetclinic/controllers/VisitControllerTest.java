package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Spy
    PetMapService petService;

    @InjectMocks
    VisitController visitController;

    @Test
    void LoadPetWithVisit(){
        // Given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        Pet pet2 = new Pet(2L);
        petService.save(pet);
        petService.save(pet2);
        BDDMockito.given(petService.findById(ArgumentMatchers.anyLong())).willCallRealMethod();
        // When
        Visit visit = visitController.loadPetWithVisit(1L, model);
        // Then
        Assertions.assertThat(visit).isNotNull();
        Assertions.assertThat(visit.getPet()).isNotNull();
        Assertions.assertThat(visit.getPet().getId()).isEqualTo(1L);
        Mockito.verify(petService, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void LoadPetWithVisitStubbing(){
        // Given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        Pet pet2 = new Pet(2L);
        petService.save(pet);
        petService.save(pet2);
        BDDMockito.given(petService.findById(ArgumentMatchers.anyLong())).willReturn(pet2);
        // When
        Visit visit = visitController.loadPetWithVisit(1L, model);
        // Then
        Assertions.assertThat(visit).isNotNull();
        Assertions.assertThat(visit.getPet()).isNotNull();
        Assertions.assertThat(visit.getPet().getId()).isEqualTo(2L);
        Mockito.verify(petService, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
    }
}