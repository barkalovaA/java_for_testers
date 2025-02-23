package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
                      .withName(firstname)
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
              .withName(randomString(i * 5))
              .withLastname(randomString(i * 5))
              .withAddress(randomString(i * 5))
              .withMobile(randomString(i * 5))
              .withEmail(randomString(i * 5)));
    }
    return result;
  }

  //@Test
  //public void canCreateContact() {
  //  app.contacts().createContact(new ContactData("test_1","test_1","test_1","test_1","test_1"));
  //}

  //@Test
  //public void canCreateContactEmpty() {
  //  app.contacts().createContact(new ContactData());
  //}

  @Test
  public void canCreateContactWithNameOnly() {
    app.contacts().createContact(new ContactData().withName("Some firstname"));
  }

  @Test
  public void canCreateContactWithAddressOnly() {
    app.contacts().createContact(new ContactData().withAddress("Some address"));
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContacts(ContactData contact) {
    //int n = 5;
    int contactCount = app.contacts().getContactCount();
    app.contacts().createContact(contact);
    int newContactCount = app.contacts().getContactCount();
    Assertions.assertEquals(contactCount + 1, newContactCount);
  }
}
