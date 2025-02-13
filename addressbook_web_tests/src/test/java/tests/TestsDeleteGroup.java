package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class TestsDeleteGroup extends TestBase {

    @Test
    public void canDeleteGroup() {
        if(!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("name", "group header", "group footer"));
        }
        app.groups().deleteGroup();
    }
}
