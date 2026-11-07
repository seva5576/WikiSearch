package wiki;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WikiApiClient {
    public String search(String query) throws Exception {
        // Кодируем запрос
        String encoded = URLEncoder.encode(query, "UTF-8");
        String url = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" + encoded;

        // Открываем соединение
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "WikiSearchApp/1.0");

        // Читаем ответ
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        reader.close();
        conn.disconnect();
        return result.toString();
    }
}
