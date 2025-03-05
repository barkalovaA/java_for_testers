package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestsCreateContact extends TestBase {

  public static List<ContactData> contactProvider() {
    var result = new ArrayList<ContactData>();
    for (var firstname : List.of("", "contact firstname")) {
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
    }
    for (int i = 0; i < 3; i++) {
      result.add(new ContactData ()
              .withFirstname(randomString(i * 5))
              .withLastname(randomString(i * 5))
              .withAddress(randomString(i * 5))
              .withMobile(randomString(i * 5))
              .withEmail(randomString(i * 5)));
    }
    return result;
  }

  @Test
  public void canCreateContact() {
    app.contacts().createContact(new ContactData()
            .withFirstname(randomString(10))
            .withLastname(randomString(10)));
 //           .withPhoto("src/test/resources/images/avatar.png")));
  }

  //@Test
  //public void canCreateContactEmpty() {
  //  app.contacts().createContact(new ContactData());
  //}

  @Test
  public void canCreateContactWithNameOnly() {
    app.contacts().createContact(new ContactData().withFirstname("Some firstname"));
  }

  @Test
  public void canCreateContactWithAddressAndEmail() {
    app.contacts().createContact(new ContactData().withAddress("Some address").withEmail("test@test.ru"));
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
