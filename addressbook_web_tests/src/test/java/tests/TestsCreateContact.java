package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class TestsCreateContact extends TestBase {

  @Test
  public void canCreateContact() {
    app.contacts().createContact(new ContactData("test_1","test_1","test_1","test_1","test_1"));
  }

  @Test
  public void canCreateContactEmpty() {
    app.contacts().createContact(new ContactData());
  }

  @Test
  public void canCreateContactWithNameOnly() {
    app.contacts().createContact(new ContactData().withName("Some firstname"));
  }

  @Test
  public void canCreateContactWithAddressOnly() {
    app.contacts().createContact(new ContactData().withAddress("Some address"));
  }
}
