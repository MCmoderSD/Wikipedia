package de.MCmoderSD.wikipedia.data;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a Wikipedia page with basic information such as page ID, title, and summary extract.
 */
@SuppressWarnings("ALL")
public class Page {

    // Attributes
    private final int pageId;
    private final String title;
    private final String extract;

    /**
     * Constructs a new {@code Page} instance with the specified page ID, title, and summary extract.
     *
     * @param pageId  The unique identifier of the page.
     * @param title   The title of the page.
     * @param extract The summary extract of the page.
     */
    public Page(int pageId, String title, String extract) {
        this.pageId = pageId;
        this.title = title;
        this.extract = extract;
    }

    /**
     * Constructs a new {@code Page} instance from a {@link JsonNode} containing page data.
     *
     * @param json The {@link JsonNode} containing the page data.
     *             It is expected to have fields "pageid", "title", and "extract".
     */
    public Page(JsonNode json) {
        pageId = json.get("pageid").asInt();
        title = json.get("title").asText();
        extract = json.get("extract").asText();
    }

    /**
     * Returns the unique identifier of the Wikipedia page.
     *
     * @return The page ID.
     */
    public int getPageId() {
        return pageId;
    }

    /**
     * Returns the title of the Wikipedia page.
     *
     * @return The page title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the summary extract of the Wikipedia page.
     *
     * @return The page extract.
     */
    public String getExtract() {
        return extract;
    }
}