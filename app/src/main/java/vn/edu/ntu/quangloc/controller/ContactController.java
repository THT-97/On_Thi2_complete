package vn.edu.ntu.quangloc.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.quangloc.model.Contact;

public class ContactController extends Application implements IContactController {

    List<Contact> listContacts = new ArrayList<>();
    int curentContact;
    public ContactController() {
        listContacts.add(new Contact(1,"Nguyễn Hoàng Nam1","08/12/1999","09827294725","05 Hòn Chồng"));
        listContacts.add(new Contact(2,"Nguyễn Hoàng Nam2","08/12/1999","09827294725","05 Hòn Chồng"));
        listContacts.add(new Contact(3,"Nguyễn Hoàng Nam3","08/12/1999","09827294725","05 Hòn Chồng"));
        listContacts.add(new Contact(4,"Nguyễn Hoàng Nam4","08/12/1999","09827294725","05 Hòn Chồng"));
        listContacts.add(new Contact(5,"Nguyễn Hoàng Nam5","08/12/1999","09827294725","05 Hòn Chồng"));
    }

    @Override
    public List<Contact> getAllContact() {
        return listContacts;
    }

    @Override
    public void addContact(Contact contact) {
        listContacts.add(contact);
    }

    @Override
    public int layId() {
        return listContacts.get(listContacts.size() -1).getId() +1;
    }

    @Override
    public int setCurrent(int pos) {
        curentContact = pos;
        return curentContact;
    }

    @Override
    public int getCurrent() {
        return curentContact;
    }

}
