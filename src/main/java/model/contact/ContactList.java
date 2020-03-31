package model.contact;

import java.util.ArrayList;

/**
 * Stores team members in a team member list.
 * Contains an ArrayList object as the team member list.
 * Has constructor and getter methods for the team member list.
 *
 * @see Contact
 */

public class ContactList {

    private ArrayList<Contact> contactList;

    public ContactList(ArrayList<Contact> tl) {
        this.contactList = tl;
    }

    public void add(Contact t) {
        this.contactList.add(t);
    }

    public void set(int index, Contact member) {
        this.contactList.set(index, member);
    }

    public ArrayList<Contact> getContactList() {

        return this.contactList;
    }

    public int getSize() {
        return this.getContactList().size();
    }
}