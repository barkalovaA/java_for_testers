package generator;

import common.CommonFunctions;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
//набор параметров запуска
    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
                throw new IllegalArgumentException("Неизвестный тип данных " + type);
            }
        }

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
/*        var result = new ArrayList<Object>();
        for (int i = 0; i < count; i++) {
            result.add(dataSupplier.get());
        }
        return result;*/
    }

    private Object generateGroups() {
        return generateData(() -> new GroupData()
                .withName(CommonFunctions.randomString(5))
                .withHeader(CommonFunctions.randomString(5))
                .withFooter(CommonFunctions.randomString(5))); //нет возможность генерировать строки различной длины
/*        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 5))
                    .withHeader(CommonFunctions.randomString(i * 5))
                    .withFooter(CommonFunctions.randomString(i * 5)));
        }
        return result;*/
    }

    private Object generateContacts() {
        return generateData(() -> new ContactData()
                .withFirstname(CommonFunctions.randomString(5))
                .withLastname(CommonFunctions.randomString(5)));
/*        var result = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            result.add(new ContactData ()
                    .withFirstname(CommonFunctions.randomString(i * 5))
                    .withLastname(CommonFunctions.randomString(i * 5))
                    .withAddress(CommonFunctions.randomString(i * 5))
                    .withMobile(CommonFunctions.randomString(i * 5))
                    .withEmail(CommonFunctions.randomString(i * 5))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
        }
        return result;*/
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    //    mapper.writeValue(new File(output), data);
          var json = mapper.writeValueAsString(data);

          try (var writer = new FileWriter(output)) {
              writer.write(json);
          }
    } if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
         throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
