package seedu.techtoday.api.apiviewjobs;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Job;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JobsCreatorTest {

    @Test
    void rawToProcessedJob() {
        String inputJsonText = "{ \"by\" : \"pjmlp\",\"descendants\""
                + " : 6, \"id\" : 22582276, \"kids\" : [ 22582600 ], "
                + "\"score\" : 76, \"time\" : 1584262684, \"title\" : "
                + "\"Augmented Reality Developer\","
                + " \"category\" : \"default\", \"text\" : \"Its a job of the future\"}";

        JSONObject input = new JSONObject(inputJsonText);

        String title = "Augmented Reality Developer";
        String text = "Its a job of the future";

        Job output = JobsCreator.convertToProcessedJob(input);

        String textOutput = output.getText();
        String titleOutput = output.getTitle();

        assertEquals(text, textOutput);
        assertEquals(title, titleOutput);
    }
}