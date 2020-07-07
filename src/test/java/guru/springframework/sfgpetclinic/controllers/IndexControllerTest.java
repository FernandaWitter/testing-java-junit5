package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void index() {
        Assertions.assertEquals("index", controller.index(), "Wrong view returned");
    }

    @Test
    void oupsHandler() {
        Assertions.assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "This is some expensive" +
                " message to build" +
                " for my test");
    }
}