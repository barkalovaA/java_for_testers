package model;

public record ContactData(String firstname, String lastname, String address, String mobile, String email) {
    public ContactData(){
        this("", "", "", "", "");
    }

    public ContactData withName(String name){
        return new ContactData(name, this.lastname, this.address, this.mobile, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstname, this.lastname, address, this.mobile, this.email);
    }
}
