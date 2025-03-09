package model;

public record ContactData(String idContact, String firstname, String lastname, String address, String mobile, String email, String photo) {
    public ContactData(){
        this("", "", "", "", "", "", "");
    }

    public ContactData withId(String idContact){
        return new ContactData(idContact, this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo);
    }

    public ContactData withFirstname(String firstname){
        return new ContactData(this.idContact, firstname, this.lastname, this.address, this.mobile, this.email, this.photo);
    }

    public ContactData withLastname(String lastname){
        return new ContactData(this.idContact, this.firstname, lastname, this.address, this.mobile, this.email, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.idContact, this.firstname, this.lastname, address, this.mobile, this.email, this.photo);
    }

    public ContactData withMobile(String mobile){
        return new ContactData(this.idContact, this.firstname, this.lastname, this.address, mobile, this.email, this.photo);
    }

    public ContactData withEmail(String email){
        return new ContactData(this.idContact, this.firstname, this.lastname, this.address, this.mobile, email, this.photo);
    }

    public ContactData withPhoto(String photo){
        return new ContactData(this.idContact, this.firstname, this.lastname, this.address, this.mobile, this.email, photo);
    }
}
