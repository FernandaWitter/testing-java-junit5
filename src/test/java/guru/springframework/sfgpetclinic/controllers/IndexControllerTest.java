package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void oupsHandler() {
        Assertions.assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "This is some expensive" +
                " message to build" +
                " for my test");
    }
}