package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase{

    @Test
    void canLogin() {
//TODO предусловие, что логИн не выполнен или надо предварительно сделатьлогАут
        app.http().login("administrator", "root");
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
