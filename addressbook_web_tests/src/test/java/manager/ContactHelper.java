package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper {
    protected final ApplicationManager manager;
    public ContactHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void openHomePage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    public boolean isContactPresent() {
      return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        manager.driver.findElement(By.linkText("add new")).click();
        fillFirstLastNameOfContactForm(contact);
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
        manager.driver.findElement(By.cssSelector("input:nth-child(75)")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }

    private void fillFirstLastNameOfContactForm(ContactData contact) {
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).clear();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).clear();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
    }

    //private void attach (String file) {
    //    manager.driver.findElement(By.name("photo")).sendKeys(Paths.get(file).toAbsolutePath().toString());
    //}

    public void deleteContact(ContactData contact) {
        selectContact(contact);
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.idContact())));
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    public int getContactCount() {
        openHomePage();
        return  manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllContacts() {
        manager.driver.findElement(By.id("MassCB")).click();
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }
    public void deleteContacts() {
        manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
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
            var firstname = tr.getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstname(firstname));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification();
        fillFirstLastNameOfContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactModification() {
        click(By.name("update"));
        //manager.driver.findElement(By.name("update")).click();
    }

    private void initContactModification() {
    //    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
//   click(By.cssSelector(String.format("input[value='%s']", contact.idContact())));
    }
}
