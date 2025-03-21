package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestsDeleteGroup extends TestBase {

    @Test  //тест через БД
    public void canDeleteGroup() {
        if(app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "group header", "group footer"));
        }
        //int groupCount = app.groups().getCount();
        //List<GroupData> oldGroups =
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        //List<GroupData> newGroups = app.groups().getList();
        //int newGroupCount = app.groups().getCount();
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        //Assertions.assertEquals(newGroups.size(), oldGroups.size() - 1);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test  //тест через БД
    void canDeleteAllGroupsAtOnce() {
        if(app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "group header", "group footer"));
        }
        app.groups().deleteAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }

/*    @Test  для фронта
    public void canDeleteGroup() {
        if(app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "name", "group header", "group footer"));
        }
        //int groupCount = app.groups().getCount();
        //List<GroupData> oldGroups =
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        //List<GroupData> newGroups = app.groups().getList();
        //int newGroupCount = app.groups().getCount();
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        //Assertions.assertEquals(newGroups.size(), oldGroups.size() - 1);
        Assertions.assertEquals(newGroups, expectedList);
    }*/

    /*@Test для фронта
    void canDeleteAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "name", "group header", "group footer"));
        }
        app.groups().deleteAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }*/
}
