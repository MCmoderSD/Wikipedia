package de.MCmoderSD.wikipedia.enums;

/**
 * Enum representing supported Wikipedia languages.
 * Each language has a code, a name, and optional aliases.
 */
@SuppressWarnings("ALL")
public enum Language {

    // Languages
    ENGLISH("en", "English", "en"),
    GERMAN("de", "German", "de");

    // Attributes
    private final String code;
    private final String name;
    private final String[] alias;

    /**
     * Constructs a {@code Language} enum instance with the specified code, name, and aliases.
     *
     * @param code The language code.
     * @param name The primary name of the language, followed by any aliases.
     */
    Language(String code, String... name) {
        this.code = code;
        this.name = name[0];
        this.alias = name;
    }

    /**
     * Returns the language code.
     *
     * @return The language code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the primary name of the language.
     *
     * @return The language name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the aliases associated with the language.
     *
     * @return An array of aliases.
     */
    public String[] getAlias() {
        return alias;
    }

    /**
     * Retrieves the {@code Language} instance that matches the specified string.
     * The match is case-insensitive and ignores spaces.
     *
     * @param language The string to match against language names, codes, or aliases.
     * @return The matching {@code Language} instance, or {@code null} if no match is found.
     */
    public Language getLanguage(String language) {
        if (language == null || language.isBlank()) return null;
        language = language.trim().toLowerCase().replaceAll(" ", "");
        for (Language lang : values()) {
            if (lang.name.equalsIgnoreCase(language) || lang.code.equalsIgnoreCase(language)) return lang;
            for (String alias : lang.alias) if (alias.equalsIgnoreCase(language)) return lang;
        }
        return null;
    }

    /**
     * Checks whether the specified string matches any language name, code, or alias.
     * The match is case-insensitive and ignores spaces.
     *
     * @param language The string to check.
     * @return {@code true} if the string matches a language; {@code false} otherwise.
     */
    public boolean contains(String language) {
        if (language == null || language.isBlank()) return false;
        language = language.trim().toLowerCase().replaceAll(" ", "");
        return getLanguage(language) != null;
    }
}