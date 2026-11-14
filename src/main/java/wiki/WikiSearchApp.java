package wiki;

import java.util.List;

public class WikiSearchApp {
    private final ConsoleUi ui = new ConsoleUi();
    private final WikiApiClient apiClient = new WikiApiClient();
    private final WikiResponseParser parser = new WikiResponseParser();
    private final BrowserService browserService = new BrowserService();

    public void run() {
        System.out.println("Добро пожаловать в консольный поиск по Википедии!");
        while (true) {
            String query = ui.getSearchQuery();
            if (query.equalsIgnoreCase("exit")) {
                System.out.println("До свидания!");
                break;
            }
            if (query.isEmpty()) {
                System.out.println("Запрос не может быть пустым.");
                continue;
            }
            try {
                // 1. Запрос к серверу
                String jsonResponse = apiClient.search(query);
                // 2. Парсинг ответа
                List<SearchResult> results = parser.parse(jsonResponse);
                // 3. Вывод результатов
                ui.printResults(results);
                if (!results.isEmpty()) {
                    // 4. Выбор пользователя
                    int choice = ui.getUserChoice(results.size());
                    if (choice > 0) {
                        // 5. Открытие в браузере
                        SearchResult selected = results.get(choice - 1);
                        System.out.println("Открываю статью: " + selected.title);
                        browserService.openArticle(selected.pageid);
                    }
                }
            } catch (Exception e) {
                System.err.println("Произошла ошибка при выполнении запроса: " + e.getMessage());
                System.out.println("Проверьте подключение к интернету и попробуйте снова.");
            }
        }
    }
}
