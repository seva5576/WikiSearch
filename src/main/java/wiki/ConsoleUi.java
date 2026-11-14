package wiki;

import java.util.List;
import java.util.Scanner;

public class ConsoleUi {
    private final Scanner scanner = new Scanner(System.in);
    // 1. Считываем запрос пользователя
    public String getSearchQuery() {
        System.out.print("\nВведите поисковый запрос (или 'exit' для выхода): ");
        return scanner.nextLine().trim();
    }
    // 2. Выводим результаты поиска
    public void printResults(List<SearchResult> results) {
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено. Попробуйте изменить запрос.");
            return;
        }
        System.out.println("\n--- Результаты поиска ---");
        for (int i = 0; i < results.size(); i++) {
            SearchResult res = results.get(i);
            System.out.printf("%d. %s\n   %s\n", i + 1, res.title, res.getCleanSnippet());
        }
        System.out.println("-------------------------");
    }
    // 3. Получаем выбор пользователя (номер статьи)
    public int getUserChoice(int maxChoices) {
        while (true) {
            System.out.printf("Выберите номер статьи (1-%d) или 0 для нового поиска: ", maxChoices);
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice <= maxChoices) {
                    return choice;
                }
                System.out.println("Нет статьи с таким номером. Попробуйте снова.");
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число.");
            }
        }
    }
}