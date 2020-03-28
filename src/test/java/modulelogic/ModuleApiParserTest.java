package modulelogic;

import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleApiParserTest {
    ModuleApiParser myModuleApiParser;

    @Test
    public void myModuleApiParser_wrongModuleCode() {
        myModuleApiParser = new ModuleApiParser("ABCD");
        JsonArray nullJsonArray = new JsonArray();
        assertEquals(myModuleApiParser.parse(), nullJsonArray);
    }

    @Test
    public void setMyModuleApiParser_correctModuleCode() throws IOException {
        myModuleApiParser = new ModuleApiParser(("CG2023"));
        assertEquals(myModuleApiParser.parse().toString(), "[{\"semester\":2,\"timetable\":[{\""
                + "classNo\":\"04\",\"startTime\":\"0900\",\"endTime\":\"1200\",\"weeks\":[6,8,10,13],\"venue"
                + "\":\"E4-03-07\",\"day\":\"Friday\",\"lessonType\":\"Laboratory\",\"size\":40},{\"classNo\":\""
                + "05\",\"startTime\":\"1400\",\"endTime\":\"1700\",\"weeks\":[5,7,9,11],\"venue\":\"E4-03-07\""
                + ",\"day\":\"Friday\",\"lessonType\":\"Laboratory\",\"size\":40},{\"classNo\":\"02\",\"startTime\""
                + ":\"1600\",\"endTime\":\"1800\",\"weeks\":[1,2,3,4,5,6,7,8,9,10,11,12,13],\"venue\":\"E3-06-08\",\""
                + "day\":\"Thursday\",\"lessonType\":\"Packaged Lecture\",\"size\":70},{\"classNo\":\"02\",\"startTime\""
                + ":\"1600\",\"endTime\":\"1800\",\"weeks\":[1,2,3,4,5,6,7,8,9,10,11,12,13],\"venue\":\"E3-06-08\",\"day\""
                + ":\"Tuesday\",\"lessonType\":\"Packaged Tutorial\",\"size\":70},{\"classNo\":\"03\",\"startTime\":\""
                + "1600\",\"endTime\":\"1800\",\"weeks\":[1,2,3,4,5,6,7,8,9,10,11,12,13],\"venue\":\"E1-06-01\",\"day\""
                + ":\"Thursday\",\"lessonType\":\"Packaged Lecture\",\"size\":70},{\"classNo\":\"01\",\""
                + "startTime\":\"1600\",\"endTime\":\"1800\",\"weeks\""
                + ":[1,2,3,4,5,6,7,8,9,10,11,12,13],\"venue\":\"E5-03-20\",\"day\":\"Tuesday\",\"lessonType\":\""
                + "Packaged Tutorial\",\"size\":70},{\"classNo\":\"01\",\"startTime\":\"1600\",\"endTime\":\"1800\",\""
                + "weeks\":[1,2,3,4,5,6,7,8,9,10,11,12,13],\"venue\":\"E5-03-20\",\"day\":\"Thursday\",\"lessonType\":"
                + "\"Packaged Lecture\",\"size\":70},{\"classNo\":\"01\",\"startTime\":\"0900\",\"endTime\":\"1200\","
                + "\"weeks\":[5,7,9,11],\"venue\":\"E4-03-07\",\"day\":\"Tuesday\",\"lessonType\":\"Laboratory\","
                + "\"size\":40},{\"classNo\":\"06\",\"startTime\":\"1400\",\"endTime\":\"1700\",\"weeks\":[6,8,10,13],"
                + "\"venue\":\"E4-03-07\",\"day\":\"Friday\",\"lessonType\":\"Laboratory\",\"size\":40},{\"classNo\":"
                + "\"03\",\"startTime\":\"1600\",\"endTime\":\"1800\",\"weeks\":[1,2,3,4,5,6,7,8,9,10,11,12,13],\"venue"
                + "\":\"E1-06-01\",\"day\":\"Tuesday\",\"lessonType\":\"Packaged Tutorial\",\"size\":70},{\"classNo\""
                + ":\"03\",\"startTime\":\"0900\",\"endTime\":\"1200\",\"weeks\":[5,7,9,11],\"venue\":\"E4-03-07\",\"day"
                + "\":\"Friday\",\"lessonType\":\"Laboratory\",\"size\":40},{\"classNo\":\"02\",\"startTime\":\"0900\","
                + "\"endTime\":\"1200\",\"weeks\":[6,8,10,13],\"venue\":\"E4-03-07\",\"day\":\"Tuesday\",\"lessonType\":"
                + "\"Laboratory\",\"size\":40}],\"examDate\":\"2020-04-29T05:00:00.000Z\",\"examDuration\":150}]");

    }
}
