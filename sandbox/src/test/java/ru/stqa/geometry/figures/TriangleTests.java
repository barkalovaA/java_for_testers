package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateArea() {
        Assertions.assertEquals(6.0, Triangle.triangleArea(3.0, 4.0, 5.0));
    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(12.0, Triangle.trianglePerimeter(3.0, 4.0, 5.0));
    }
}
