package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Verify if correct view is returned")
    @Test
    void index() {
        Assertions.assertEquals("index", controller.index(), "Wrong view returned");
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
}