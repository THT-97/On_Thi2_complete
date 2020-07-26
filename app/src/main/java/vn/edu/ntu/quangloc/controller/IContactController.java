package vn.edu.ntu.quangloc.controller;

import java.util.List;

import vn.edu.ntu.quangloc.model.Contact;

public interface IContactController {
    public List<Contact> getAllContact();
    public void addContact(Contact contact);
    public int layId();
    public int setCurrent(int pos);
    public int getCurrent();
}
