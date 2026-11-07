package wiki;

import com.google.gson.Gson;
import java.util.Collections;
import java.util.List;

public class WikiResponseParser {
    private final Gson gson = new Gson();

    public List<SearchResult> parse(String jsonResponse) {
        try {
            WikiResponse response = gson.fromJson(jsonResponse, WikiResponse.class);
            if (response != null && response.query != null && response.query.search != null) {
                return response.query.search;
            }
        } catch (Exception e) {
            System.err.println("Ошибка при разборе JSON ответа: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
