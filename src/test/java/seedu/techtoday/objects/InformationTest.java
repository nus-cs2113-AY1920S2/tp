package seedu.techtoday.objects;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InformationTest {

    @Test
    void setExtract_newExtract_success() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        info.setExtract("newExtract");
        assertEquals(info.extract, "newExtract");
    }

    @Test
    void setExtract_withInfo_success() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        info.setExtract(info, "newExtract");
        assertEquals(info.extract, "newExtract");
    }

    @Test
    void getTitle_testTitle_success() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        String actual = info.getTitle();
        assertEquals(actual, "testTitle");
    }

    @Test
    void getExtract() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        String actual = info.getExtract();
        assertEquals(actual, "testExtract");
    }

    @Test
    void getTimeStamp() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        String actual = info.getTimeStamp();
        assertEquals(actual, "testTimeStamp");
    }

    @Test
    void getCategory_Null_success() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        String actual = info.getCategory();
        assertEquals(actual, null);
    }

    @Test
    void getCategory_newCategory_success() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        info.category = "newCategory";
        assertEquals(info.category, "newCategory");
    }

    @Test
    void setTime_NewTime_success() {
        Information info = new Information("testTitle", "testExtract", "testTimeStamp");
        info.setTime("newTime");
        assertEquals(info.timeStamp, "newTime");
    }
}