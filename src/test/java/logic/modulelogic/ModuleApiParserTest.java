package logic.modulelogic;

import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(myModuleApiParser.parse().toString().contains("[{\"semester\":2,\"timetable\":[{\"classNo\""));
    }
}
