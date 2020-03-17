package seedu.nuke.data;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

public class ScreenShot {
    private String moduleListInJsonStr;

    public ScreenShot(String moduleListInJsonStr) {
        this.moduleListInJsonStr = moduleListInJsonStr;
    }

    public String getModuleListInJsonStr() {
        return moduleListInJsonStr;
    }

    public void setModuleListInJsonStr(String moduleListInJsonStr) {
        this.moduleListInJsonStr = moduleListInJsonStr;
    }
}

