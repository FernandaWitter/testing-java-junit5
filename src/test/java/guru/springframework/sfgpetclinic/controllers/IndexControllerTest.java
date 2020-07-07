package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest {

    IndexController controller;
    private org.assertj.core.api.Assertions assertJ;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Verify if correct view is returned")
    @Test
    void index() {
        assertEquals("index", controller.index(), "Wrong view returned");
        org.assertj.core.api.Assertions.assertThat(controller.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test exception")
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class, () -> controller.oopsHandler());
    }

    @Disabled("Timeout demo")
    @Test
    void testTimeOut(){
        assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(2000));
        System.out.println("I got this far");
    }

    @Disabled("Timeout demo")
    @Test
    void testTimeOutPreempt(){
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> Thread.sleep(2000));
        System.out.println("I never got here!");
    }

    @Test
    void testAssumptionTrue(){
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue(){
        assumeTrue("GURU".equalsIgnoreCase("guru"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMac(){
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows(){
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8(){
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava11(){
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "ioxua")
    @Test
    void testIfUserIoxua(){
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "ocean")
    @Test
    void testIfUserOcean(){
    }
}