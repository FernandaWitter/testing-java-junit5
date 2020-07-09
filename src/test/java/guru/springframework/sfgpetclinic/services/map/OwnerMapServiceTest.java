package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Map Service Test")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp(){
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("First before each");
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero(){
        int ownerCount = ownerMapService.findAll().size();
        Assertions.assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type")
    @Nested
    class TestCreatePetTypes{

        @BeforeEach
        void setUp(){
            PetType petType1 =  new PetType(1L, "Dog");
            PetType petType2 =  new PetType(2L, "Cat");
            petTypeService.save(petType1);
            petTypeService.save(petType2);

            System.out.println("Nested before each");
        }

        @Test
        void testPetCount(){
            int petCount = petTypeService.findAll().size();
            Assertions.assertThat(petCount).isEqualTo(2);
        }

        @DisplayName("Save Owners Test")
        @Nested
        class saveOwnersTest{

            @BeforeEach
            void setUp(){
                ownerMapService.save(new Owner(1L, "John", "Smith"));
            }

            @Test
            void saveOwner(){
                Owner owner = new Owner(2L, "Jack", "Harkness");
                Owner savedOwner = ownerMapService.save(owner);
                Assertions.assertThat(savedOwner).isNotNull();
            }

            @DisplayName("Save Owners Test")
            @Nested
            class findOwnersTest{

                @DisplayName("Find Owner")
                @Test
                void findOwner(){
                    Owner ownerFound = ownerMapService.findById(1L);
                    Assertions.assertThat(ownerFound).isNotNull();
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound(){
                    Owner ownerFound = ownerMapService.findById(2L);
                    Assertions.assertThat(ownerFound).isNull();
                }
            }
        }
    }

    @DisplayName("Verify Still   Zero Owners")
    @Test
    void ownersAreStillZero(){
        int ownerCount = ownerMapService.findAll().size();
        Assertions.assertThat(ownerCount).isZero();
    }
}