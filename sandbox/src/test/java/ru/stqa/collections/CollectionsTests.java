package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionsTests {

    @Test
    void arrayTests() {
       //массив
       // var array = new String[]{"a", "b", "c"};
        var array = new String[3];
        Assertions.assertEquals(3, array.length);
        array[0] = "a";
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTest(){
       //список
        //var list = List.of("a", "b", "c"); если список создан через List.of, то его нельзя модифицировать
        //var list = new ArrayList<String>(List.of("a", "b", "c")); ArrayList можно скомбинировать с List.of и удалить String, смотри стороку ниже
        var list = new ArrayList<>(List.of("a", "b", "c"));
        //Assertions.assertEquals(0, list.size());
        //list.add("a");
        //list.add("b");
        //list.add("c");
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }
}
