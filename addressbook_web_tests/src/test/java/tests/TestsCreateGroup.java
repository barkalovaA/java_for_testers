package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class TestsCreateGroup extends TestBase {

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withName(randomString(i * 5))
                    .withHeader(randomString(i * 5))
                    .withFooter(randomString(i * 5)));
        }
        return result;
    }

    //@ParameterizedTest
    //@ValueSource(strings = {"group name", "group name'"})
    //public void canCreateGroup(String name) {
    //    int groupCount = app.groups().getCount();
    //    app.groups().createGroup(new GroupData(name, "group header", "group footer"));
    //    int newGroupCount = app.groups().getCount();
    //    Assertions.assertEquals(groupCount + 1, newGroupCount);
    //}

   // @Test
   // public void canCreateGroupWithEmptyName() {
   //     app.groups().createGroup(new GroupData());
   // }

   // @Test
   // public void canCreateGroupWithNameOnly() {
   //     app.groups().createGroup(new GroupData().withName("some name"));
   // }

   // @Test
   // public void canCreateGroupWithHeaderOnly() {
   //     app.groups().createGroup(new GroupData().withHeader("some Header"));
   // }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        int groupCount = app.groups().getCount();
            app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData ("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }
}
