# Wikipedia
[![](https://jitpack.io/v/MCmoderSD/Wikipedia.svg)](https://jitpack.io/#MCmoderSD/Wikipedia)

## Description
A simple Java library to get a summary of a Wikipedia page.

## Usage

### Maven
Make sure you have the JitPack repository added to your `pom.xml` file:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add the dependency to your `pom.xml` file:
```xml
<dependency>
    <groupId>com.github.MCmoderSD</groupId>
    <artifactId>Wikipedia</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Usage Example
```java
import de.MCmoderSD.wikipedia.core.WikipediaAPI;
import de.MCmoderSD.wikipedia.data.Page;
import de.MCmoderSD.wikipedia.enums.Language;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize
        Scanner input = new Scanner(System.in);
        WikipediaAPI wiki = new WikipediaAPI(); // Create a new WikipediaAPI instance
        Page page = null;
        Language language;
        String topic;

        // Get Language
        System.out.println("Enter a Language (en/de): ");
        language = Language.ENGLISH.getLanguage(input.nextLine()); // Get the Language enum instance

        // Get Topic
        System.out.println("Enter a Wikipedia Topic: ");
        topic = input.nextLine(); // Get the topic from the user

        // Query Wikipedia
        try {
            page = wiki.query(language, topic); // Query Wikipedia for the specified topic
        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        if (page == null) {
            System.err.println("No results found for the specified topic.");
            return;
        }

        // Display Results
        System.out.println("Page ID: " + page.getPageId());
        System.out.println("Title: " + page.getTitle());
        System.out.println("Extract: " + page.getExtract());
    }
}
```