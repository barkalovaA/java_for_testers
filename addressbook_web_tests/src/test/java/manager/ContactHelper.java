package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
    //protected final ApplicationManager manager;
    public ContactHelper(ApplicationManager manager) {
    //    this.manager = manager;
        super(manager);
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    public boolean isContactPresent() {
      return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        click(By.linkText("add new"));
        fillFirstLastNameOfContactForm(contact);
        click(By.name("address"));
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        click(By.name("mobile"));
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
        click(By.name("email"));
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
        click(By.cssSelector("input:nth-child(75)"));
        click(By.linkText("home page"));
    }

    private void fillFirstLastNameOfContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        attach(By.name("photo"), contact.photo());
    }

    public void createContact(ContactData contact, GroupData group) {
        click(By.linkText("add new"));
        fillFirstLastNameOfContactForm(contact);
        click(By.name("address"));
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        click(By.name("mobile"));
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
        click(By.name("email"));
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
        selectGroup(group);
        click(By.cssSelector("input:nth-child(75)"));
        click(By.linkText("home page"));
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void deleteContact(ContactData contact) {
        selectContact(contact);
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.idContact())));
    }

    public int getContactCount() {
        openHomePage();
        return  manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllContacts() {
        click(By.id("MassCB"));
        deleteContacts();
    }
    public void deleteContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }
    public void deleteAllContactsViaCheck() {
        openHomePage();
        selectAllContacts();
        deleteContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes){
            checkbox.click();
        }
    }

    public List<ContactData> getListContact() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            //var firstname = tr.getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
//            contacts.add(new ContactData().withId(id).withFirstname(firstname));
            contacts.add(new ContactData().withId(id).withFirstname("").withLastname("").withAddress("").withEmail("").withMobile(""));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
//        selectContact(contact);
        initContactModification(contact);
        fillFirstLastNameOfContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
    //    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
   //click(By.cssSelector(String.format("input[value='%s']", contact.idContact())));
   click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", contact.idContact())));
    }
}
