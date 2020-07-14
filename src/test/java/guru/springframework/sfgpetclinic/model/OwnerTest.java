package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTest {

    @DisplayName("Test dependent assertions for Owner")
    @Test
    public void dependentAssertions(){
        Owner owner = new Owner(1L, "John", "Smith");
        owner.setCity("Cardiff");
        owner.setTelephone("123-TARDIS");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        ()-> assertEquals("John", owner.getFirstName(), "First name did not match"),
                        ()-> assertEquals("Smith", owner.getLastName(), "Last name did not match")),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Cardiff", owner.getCity(), "City did not match"),
                        () -> assertEquals("123-TARDIS", owner.getTelephone(), "Phone did not match")));
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring", "Testing", "Guru"})
    void testValueSource(String val){
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV Input Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({"FL,1,1", "OH,2,2", "MI,3,3"})
    void csvInputTest(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }
}