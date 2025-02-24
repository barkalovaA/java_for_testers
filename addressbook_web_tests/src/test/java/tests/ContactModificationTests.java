package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class ContactModificationTests extends TestBase{
    @Test
    void canModifyContact(){
        app.contacts().openHomePage();
        if (app.contacts().getContactCount() == 0){
            app.contacts().createContact(new ContactData());
        }
        var oldContacts = app.contacts().getListContact();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstname("modified firstname");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getListContact();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).idContact()));
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.idContact()), Integer.parseInt(o2.idContact()));
        };
        newContacts.sort(compareByIdContact);
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
