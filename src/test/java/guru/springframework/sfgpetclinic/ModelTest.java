package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("Model")
public interface ModelTest {
    @BeforeEach
    default void testSetUp(TestInfo testInfo){
        System.out.println("=== Running test " + testInfo.getDisplayName() + " ===");
    };
}
