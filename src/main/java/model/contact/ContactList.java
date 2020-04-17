package model.contact;

import org.w3c.dom.Text;
import ui.TextUI;

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

    public void remove(String name) {
        for (Contact contact : contactList) {
            if (contact.getName().equals(name) && !contact.isMainUser()) {
                TextUI.displayRemovedPerson(name);
                contactList.remove(contact);
                return;
            } else if (contact.getName().equals(name) && contact.isMainUser()) {
                TextUI.displayMainUserDeleteError(name);
                return;
            }
        }
        TextUI.displayNoMemberFound(name);
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