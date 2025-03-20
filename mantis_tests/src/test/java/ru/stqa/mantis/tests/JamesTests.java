package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import static ru.stqa.mantis.tests.TestBase.app;

public class JamesTests extends TestBase {

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(
                String.format("%s@localhost", CommonFunctions.randomString(8)),
                "password");
    }
}
