package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @DisplayName("Test grouped person properties")
    @Test
    public void groupedAssertions(){
        //given
        Person person = new Person(1L, "John", "Smith");
        //then
        assertAll("Test Props Set",
                () -> assertEquals("John", person.getFirstName()),
                () -> assertEquals("Smith", person.getLastName()));
    }

    @Disabled("Error message demo")
    @DisplayName("Test exceptions in grouped assertions")
    @Test
    public void groupedAssertionsMessages(){
        //given
        Person person = new Person(1L, "John", "Smith");
        //then
        assertAll("Test Props Set",
                () -> assertEquals("Jack", person.getFirstName(), "First name failed"),
                () -> assertEquals("Harkness", person.getLastName(), "Last name failed"));
    }
}