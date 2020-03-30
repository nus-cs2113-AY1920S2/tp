package seedu.duke.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    void getPersonName() {
        Person person = new Person("Duke", 2018);
        String name = "Duke";
        assertEquals(name, Person.getName());
    }

    @Test
    void getPersonYear() {
        Person person = new Person("Duke", 2018);
        int matricYear = 2018;
        assertEquals(matricYear, Person.getMatricYear());
    }
}
