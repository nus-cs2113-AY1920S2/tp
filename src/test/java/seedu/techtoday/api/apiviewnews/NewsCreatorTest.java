package seedu.techtoday.api.apiviewnews;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Article;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewsCreatorTest {

    @Test
    void testNewsCreation() {
        String inputJsonText = "{ \"by\" : \"pjmlp\",\"descendants\""
                + " : 6, \"id\" : 22582276, \"kids\" : [ 22582600 ], "
                + "\"score\" : 76, \"time\" : 1584262684, \"title\" : "
                + "\"WebAssembly brings extensibility to network proxies\","
                + " \"type\" : \"story\", \"url\" : \"https://opensource.googl"
                + "eblog.com/2020/03/webassembly-brings-extensibility-to.html\"}";

        JSONObject input = new JSONObject(inputJsonText);

        String title = "WebAssembly brings extensibility to network proxies";
        String url = "https://opensource.googleblog.com/2020/03/webassembly-brings-extensibility-to.html";
        String category = "default";

        Article output = NewsCreator.convertToProcessedNews(input);

        String urlOutput = output.getUrl();
        String categoryOutput = output.getCategory();
        String titleOutput = output.getTitle();

        assertEquals(url, urlOutput);
        assertEquals(category, categoryOutput);
        assertEquals(title, titleOutput);
    }
}
