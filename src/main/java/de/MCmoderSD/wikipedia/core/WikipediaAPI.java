package de.MCmoderSD.wikipedia.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import de.MCmoderSD.wikipedia.data.Page;
import de.MCmoderSD.wikipedia.enums.Language;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A utility class for interacting with the Wikipedia API.
 * This class provides methods to query Wikipedia pages and retrieve summary data.
 */
@SuppressWarnings("ALL")
public class WikipediaAPI {

    // Endpoints
    private final static String SUMMARY_API_URL = "https://%s.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=%s";

    // Attributes
    private final HttpClient httpClient;
    private final JsonMapper jsonMapper;

    /**
     * Constructs a new {@code WikipediaAPI} instance with default configurations.
     */
    public WikipediaAPI() {
        this.httpClient = HttpClient.newHttpClient();
        this.jsonMapper = new JsonMapper();
    }

    /**
     * Queries the Wikipedia API for a page summary using the default language (English).
     *
     * @param title The title of the Wikipedia page to query.
     * @return A {@link Page} object containing the summary data.
     * @throws IOException              If an I/O error occurs while querying the API.
     * @throws InterruptedException     If the request is interrupted.
     * @throws IllegalArgumentException If the title is null or empty.
     */
    public Page query(String title) throws IOException, InterruptedException {
        return query(Language.ENGLISH, title);
    }

    /**
     * Queries the Wikipedia API for a page summary in the specified language.
     *
     * @param language The {@link Language} to query in (e.g., English, French).
     * @param title    The title of the Wikipedia page to query.
     * @return A {@link Page} object containing the summary data.
     * @throws IOException              If an I/O error occurs while querying the API.
     * @throws InterruptedException     If the request is interrupted.
     * @throws IllegalArgumentException If the language is null, or if the title is null or empty.
     */
    public Page query(Language language, String title) throws IOException, InterruptedException {

        // Check Parameters
        if (language == null) throw new IllegalArgumentException("Language cannot be null");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be null or empty");

        // Format title
        title = title.trim().replace(" ", "%20");

        // Construct the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(SUMMARY_API_URL, language.getCode(), title)))
                .GET()
                .build();

        return new Page(sendRequest(request));
    }

    /**
     * Sends an HTTP request to the Wikipedia API and parses the response as JSON.
     *
     * @param request The {@link HttpRequest} to send.
     * @return A {@link JsonNode} representing the parsed JSON response.
     * @throws IOException              If an I/O error occurs while sending the request or parsing the response.
     * @throws InterruptedException     If the request is interrupted.
     * @throws IllegalArgumentException If the HTTP response status is not 200 (OK).
     */
    private JsonNode sendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200)
            throw new IOException("HTTP " + response.statusCode() + ": " + response.body());
        return jsonMapper.readTree(response.body()).get("query").get("pages").elements().next();
    }
}