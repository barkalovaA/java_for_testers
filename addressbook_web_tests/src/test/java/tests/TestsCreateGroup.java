package tests;

import common.CommonFunctions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
/*  переделано на стрим
    public static List<GroupData> singleRandomGroup() throws IOException {
     return List.of(new GroupData()
             .withName(CommonFunctions.randomString(5))
             .withHeader(CommonFunctions.randomString(5))
             .withFooter(CommonFunctions.randomString(5)));
    }*/

public static Stream<GroupData> randomGroups() throws IOException {
    Supplier<GroupData> randomGroup = () -> new GroupData()
            .withName(CommonFunctions.randomString(5))
            .withHeader(CommonFunctions.randomString(6))
            .withFooter(CommonFunctions.randomString(7));
    return Stream.generate(randomGroup).limit(1);
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
   // @MethodSource("groupProvider")
    @MethodSource("randomGroups")
    //public void canCreateMultipleGroups(GroupData group)
    public void canCreateGroupFromBase(GroupData group) {
        //int groupCount = app.groups().getCount();
        // дальше заменено на работу с БД(jdbc) var oldGroups = app.groups().getList();
        //var oldGroups = app.jdbc().getGroupList();
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        //int newGroupCount = app.groups().getCount();
        //Assertions.assertEquals(groupCount + 1, newGroupCount);
        //var newGroups = app.jdbc().getGroupList();
        var newGroups = app.hbm().getGroupList();
/*        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);*/
//        var maxId = newGroups.get(newGroups.size() - 1).id();
        var extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
       // expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));

       // var newUIGroups = app.groups().getList();
        //TODO произвести сравнение newUIGroups с newGroups по Id и name (1й вариант дописать в эот тест, 2й - отдельный тест)
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
