package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface ModelRepeatedTest {
    @BeforeEach
    default void testSetUp(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println("=== Running test " + testInfo.getDisplayName() + " ===");
    };
}
