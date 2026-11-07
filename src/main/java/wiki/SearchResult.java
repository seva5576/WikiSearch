package wiki;

public class SearchResult {
    public String title; // название статьи
    public String snippet; // короткий отрывок текста
    public int pageid; // уникальный номер статьи

    // Метод для очистки snippet от html-тегов, которые возвращает википедия
    public String getCleanSnippet() {
        return snippet.replaceAll("<.*?>", "");
    }
}