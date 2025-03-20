package tests;

import common.CommonFunctions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestsCreateContact extends TestBase {

  public static List<ContactData> contactProvider() throws IOException {
    var result = new ArrayList<ContactData>();
/*    for (var firstname : List.of("", "contact firstname")) {
      for (var lastname : List.of("", "contact lastname")) {
        for (var address : List.of("", "contact address")) {
          for (var mobile : List.of("", "contact mobile")) {
            for (var email : List.of("", "contact email")) {
              result.add(new ContactData()
                      .withFirstname(firstname)
                      .withLastname(lastname)
                      .withAddress(address)
                      .withMobile(mobile)
                      .withEmail(email));
            }
          }
        }
      }
    }*/
 /*   for (int i = 0; i < 3; i++) {
      result.add(new ContactData ()
              .withFirstname(CommonFunctions.randomString(i * 5))
              .withLastname(CommonFunctions.randomString(i * 5))
              .withAddress(CommonFunctions.randomString(i * 5))
              .withMobile(CommonFunctions.randomString(i * 5))
              .withEmail(CommonFunctions.randomString(i * 5)));
    }*/
    var mapper = new XmlMapper();
    var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
    result.addAll(value);
    return result;
  }

  @Test
  public void canCreateContactWithPhoto() {
    var contact = new ContactData()
            .withFirstname(CommonFunctions.randomString(10))
            .withLastname(CommonFunctions.randomString(10))
//            .withPhoto("src/test/resources/images/avatar.png"); строго заданное значение файла
            .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
    app.contacts().createContact(contact);
  }

  @Test
  public void canCreateContactInGroup() {
    var contact = new ContactData()
            .withFirstname(CommonFunctions.randomString(10))
            .withLastname(CommonFunctions.randomString(10))
            .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
    if(app.hbm().getGroupCount() == 0) {
      app.hbm().createGroup(new GroupData("", "name", "group header", "group footer"));
    }
    var group = app.hbm().getGroupList().get(0);
    var oldRelated = app.hbm().getContactsGroup(group);
    app.contacts().createContact(contact, group);
    var newRelated = app.hbm().getContactsGroup(group);
    Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    //TODO сравнить содержимае списков
  }

  @Test
  public void canCreateContactEmpty() {
    var contact = new ContactData();
    app.contacts().createContact(contact);
  }

  @Test
  public void canCreateContactWithNameOnly() {
    var contact = new ContactData().withFirstname("Some firstname");
    app.contacts().createContact(contact);
  }

  @Test
  public void canCreateContactWithAddressAndEmail() {
    var contact = new ContactData().withAddress("Some address").withEmail("test@test.ru");
    app.contacts().createContact(contact);
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContacts(ContactData contact) {
    //int n = 5;
    //int contactCount = app.contacts().getContactCount();
    var oldContacts = app.contacts().getListContact();
    app.contacts().createContact(contact);
    //int newContactCount = app.contacts().getContactCount();
    //Assertions.assertEquals(contactCount + 1, newContactCount);
    var newContacts = app.contacts().getListContact();
    Comparator<ContactData> compareByIdContact = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
    };
    newContacts.sort(compareByIdContact);
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).idContact()).withAddress("").withMobile("").withEmail(""));
    expectedList.sort(compareByIdContact);
    Assertions.assertEquals(newContacts, expectedList);
  }
}
