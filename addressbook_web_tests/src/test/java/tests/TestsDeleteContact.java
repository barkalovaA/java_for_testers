package tests;

import org.junit.jupiter.api.Test;

public class TestsDeleteContact extends TestBase{

  @Test
  public void canDeleteContact() {
    openHomePage();
    if (!isContactPresent()) {
      createContact();
    }
    deleteContact();
  }

}
