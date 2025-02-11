package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class TestsDeleteContact extends TestBase{

  @Test
  public void canDeleteContact() {
    app.contacts().openHomePage();
    if (!app.contacts().isContactPresent()) {
      app.contacts().createContact(new ContactData());
    }
    app.contacts().deleteContact();
  }

}
