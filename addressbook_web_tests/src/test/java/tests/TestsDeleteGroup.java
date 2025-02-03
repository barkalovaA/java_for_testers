package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class TestsDeleteGroup extends TestBase {

    @Test
    public void canDeleteGroup() {
        app.openGroupsPage();
        if(!app.isGroupPresent()) {
            app.createGroup(new GroupData("name", "group header", "group footer"));
        }
        app.deleteGroup();
    }
}
