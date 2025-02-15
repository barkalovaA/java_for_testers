package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestsDeleteGroup extends TestBase {

    @Test
    public void canDeleteGroup() {
        if(app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().deleteGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    void canDeleteAllGroupsAtOnce() {
        if(app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("name", "group header", "group footer"));
        }
        app.groups().deleteAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
