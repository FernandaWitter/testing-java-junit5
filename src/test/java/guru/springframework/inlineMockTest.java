package guru.springframework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

public class inlineMockTest {

    @Test
    void inlineMockTest(){
        Map mockMap = Mockito.mock(Map.class);
        Assertions.assertEquals(0, mockMap.size());
    }
}
