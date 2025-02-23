package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestsDeleteContact extends TestBase{

  @Test
  public void canDeleteContact() {
    app.contacts().openHomePage();
   // if (!app.contacts().isContactPresent())
    if (app.contacts().getContactCount() == 0){
      app.contacts().createContact(new ContactData());
    }
//    int contactCount = app.contacts().getContactCount();
    var oldContacts = app.contacts().getListContact();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().deleteContact(oldContacts.get(index));
//    int newContactCount = app.contacts().getContactCount();
    var newContacts = app.contacts().getListContact();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @Test
  public void canDeleteAllContactsViaCheck() {
    app.contacts().openHomePage();
    if (app.contacts().getContactCount() == 0){
      app.contacts().createContact(new ContactData());
    }
    app.contacts().deleteAllContactsViaCheck();
    Assertions.assertEquals(0, app.contacts().getContactCount());
  }

  @Test
  public void canDeleteAllContacts() {
    app.contacts().openHomePage();
    if (app.contacts().getContactCount() == 0) {
      app.contacts().createContact(new ContactData());
    }
    app.contacts().deleteAllContacts();
    Assertions.assertEquals(0, app.contacts().getContactCount());
  }
}
