package wiki;

import java.awt.Desktop;
import java.net.URI;

public class BrowserService {
    public void openArticle(int pageId) {
        String url = "https://ru.wikipedia.org/w/index.php?curid=" + pageId;
        try {
            // Проверяем, поддерживает ли система открытие браузера
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("Статья открыта в браузере.");
            } else {
                System.out.println("Ваша система не поддерживает автоматическое открытие браузера.");
                System.out.println("Скопируйте ссылку вручную: " + url);
            }
        } catch (Exception e) {
            System.err.println("Не удалось открыть браузер: " + e.getMessage());
            System.out.println("Ссылка для ручного открытия: " + url);
        }
    }
}