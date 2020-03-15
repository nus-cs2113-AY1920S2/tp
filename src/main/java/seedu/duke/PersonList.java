package seedu.duke;

import java.util.ArrayList;

public class PersonList {
    private static ArrayList<Person> list;

    public PersonList() {
        list = new ArrayList<Person>();
    }

    public void append(Person person) {
        list.add(person);
    }

    public int getLength() {
        return list.size();
    }

    /**
     * get one Person object from personList with the name.
     * @param name
     * @return
     */
    public Person getOnePerson(String name) {
        boolean personExists = false;
        Person person = null;

        for (Person p:list) {
            if (p.isName(name)) {
                person = p;
                personExists = true;
                break;
            }
        }
        if (!personExists) {
            System.out.println("Person not found in the list,check your spelling first!!!");
            return null;
        }
        return person;
    }

    public Person getOnePerson(int index) {
        return list.get(index);
    }

}
