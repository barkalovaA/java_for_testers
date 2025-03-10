package tests;

import Common.CommonFunctions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestsCreateGroup extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
/*        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        } */
    /*эта часть код заменина на данные из файла json, yaml или xml
            for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 5))
                    .withHeader(CommonFunctions.randomString(i * 5))
                    .withFooter(CommonFunctions.randomString(i * 5)));
        } */
        //код ниже - чтение файла построчно
/*        var json = "";
        try (var reader = new FileReader("groups.json");
            var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line !=null) {
                json = json + line;
                line = breader.readLine();
            }
        }*/
        //чтение файла целиком осуществляется с помощью readString
        //var json = Files.readString(Paths.get("groups.json"));
        //ObjectMapper mapper = new ObjectMapper();
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {});
        result.addAll(value);
        return result;
    }

    /*@ParameterizedTest
    @ValueSource(strings = {"group name", "group name'"})
    public void canCreateGroup(String name) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData(name, "group header", "group footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    } */

   /* @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

    @Test
    public void canCreateGroupWithHeaderOnly() {
        app.groups().createGroup(new GroupData().withHeader("some Header"));
    }*/

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        //int groupCount = app.groups().getCount();
        var oldGroups = app.groups().getList();
            app.groups().createGroup(group);
        //int newGroupCount = app.groups().getCount();
        //Assertions.assertEquals(groupCount + 1, newGroupCount);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData ("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        //int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        //int newGroupCount = app.groups().getCount();
        //Assertions.assertEquals(groupCount, newGroupCount);
        Assertions.assertEquals(newGroups, oldGroups);
    }
}
