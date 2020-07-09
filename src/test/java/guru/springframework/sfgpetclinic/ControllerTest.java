package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("Controller")
public interface ControllerTest {
    @BeforeAll
    default void beforeAll(){
        System.out.println("Lets do something here");
    }
}
