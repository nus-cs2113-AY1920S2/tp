package modulelogic;

import org.junit.jupiter.api.Test;

import static common.Messages.MESSAGE_MODULECODE_IN_BLACKLIST;
import static common.Messages.MESSAGE_EMPTY_MODULE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleHandlerTest {
    ModuleHandler myModuleHandler;

    @Test
    public void generateModule_UnformattedModuleCode() {
        myModuleHandler = new ModuleHandler("EG3301R");
        String illformatMessage = myModuleHandler.generateModule();
        assertEquals(illformatMessage, MESSAGE_MODULECODE_IN_BLACKLIST);
    }

    @Test
    public void generateModule_FormattedAndCorrectModuleCode() {
       myModuleHandler = new ModuleHandler("CG2023");
       String expected = myModuleHandler.generateModule();
       String actual = "Success";
       assertEquals(expected, actual);
    }

    @Test
    public void generateModule_IncorrectModuleCode() {
        myModuleHandler = new ModuleHandler("ABCD");
        String expected = myModuleHandler.generateModule();
        assertEquals(expected, MESSAGE_EMPTY_MODULE);
    }
}


