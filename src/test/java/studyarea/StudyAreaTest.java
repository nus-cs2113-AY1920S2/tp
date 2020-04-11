package studyarea;

import org.junit.jupiter.api.Test;
import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


//@@author terrytay
class StudyAreaTest {

    StudyArea location1 = new StudyArea("mac commons", "utown", "nil", true, true,
            5);

    @Test
    void getAddress() {
        assertEquals("utown", location1.getAddress());
    }

    @Test
    void getFaculty() {
        assertEquals("nil", location1.getFaculty());
    }

    @Test
    void hasPort() {
        assertEquals(true, location1.getHasPort());
    }

    @Test
    void isIndoor() {
        assertEquals(true, location1.getIsIndoor());
    }

    @Test
    void getMaxPax() {
        assertEquals(5, location1.getMaxPax());
    }

    @Test
    void getName() {
        assertEquals("mac commons", location1.getName());
    }


    //@@author NizarMohd
    /**
     * Checks for accuracy of toString() format.
     */
    @Test
    void testToString() {
        String out = "\t __________________________________________________________" + lineSeparator()
               + "\t Name: mac commons" + lineSeparator()
               + "\t Address: utown" + lineSeparator()
               + "\t Faculty: nil" + lineSeparator()
               + "\t Port: true" + lineSeparator()
               + "\t Indoor: true" + lineSeparator()
               + "\t Maximum number of Pax: 5" + lineSeparator()
               + "\t __________________________________________________________";
        assertEquals(out, location1.toString());
    }

    /**
     * Checks for accuracy of method.
     */
    @Test
    void containsSearchKey_assertTrue() {
        assertTrue(location1.containsSearchKey("utown"));
    }

    /**
     * Checks for accuracy of method.
     */
    @Test
    void containsSearchKey_assertFalse() {
        assertFalse(location1.containsSearchKey("u-town"));
    }

    /**
     * Checks for accuracy of method.
     */
    @Test
    void isSizeCapable_assertTrue() {
        assertTrue(location1.isSizeCapable("3"));
    }

    /**
     * Checks for accuracy of method.
     */
    @Test
    void isSizeCapable_assertFalse() {
        assertFalse(location1.isSizeCapable("10"));
    }
}