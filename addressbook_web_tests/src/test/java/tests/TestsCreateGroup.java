package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class TestsCreateGroup extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

    @Test
    public void canCreateGroupWithHeaderOnly() {
        var emptyGroup = new GroupData();
        var groupWithHeader = emptyGroup.withHeader("some Header");
        app.groups().createGroup(groupWithHeader);
    }
}
