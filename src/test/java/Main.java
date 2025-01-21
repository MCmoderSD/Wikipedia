import de.MCmoderSD.wikipedia.core.WikipediaAPI;
import de.MCmoderSD.wikipedia.data.Page;
import de.MCmoderSD.wikipedia.enums.Language;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize
        Scanner input = new Scanner(System.in);
        WikipediaAPI wiki = new WikipediaAPI();
        Page page = null;
        Language language;
        String topic;

        // Get Language
        System.out.println("Enter a Language (en/de): ");
        language = Language.ENGLISH.getLanguage(input.nextLine());

        // Get Topic
        System.out.println("Enter a Wikipedia Topic: ");
        topic = input.nextLine();

        // Query Wikipedia
        try {
            page = wiki.query(language, topic);
        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        if (page == null) {
            System.err.println("No results found for the specified topic.");
            return;
        }

        System.out.println("Page ID: " + page.getPageId());
        System.out.println("Title: " + page.getTitle());
        System.out.println("Extract: " + page.getExtract());
    }
}