package com.yaroslavzghoba.model.util

/**
 * Language of the application
 *
 * @param iso369 two-letter ISO 369-1 code of language. See more about
 * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
 */
// TODO: Add more languages
enum class Language(val iso369: String) {
    ENGLISH(iso369 = "en"),
    RUSSIAN(iso369 = "ru"),
    UKRAINIAN(iso369 = "uk"),
}

/**
 * Cast the language ISO 369-1 code to [Language]. See more about
 * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
 *
 * @receiver language ISO 369-1 code
 * @return [Language] object
 * @throws UnknownLanguageException if language with received ISO 369-1 code doesn't exist
 */
// TODO: Add usage sample to the documentation
internal fun String.toLanguage(): Language {
    return try {
        Language.valueOf(this)
    } catch (exception: IllegalArgumentException) {
        throw UnknownLanguageException(
            message = "Cannot cast \"$this\" ISO 369-1 code to Language",
        )
    }
}

/**
 * Throw it you cannot cast something to [Language]
 */
internal class UnknownLanguageException(
    message: String = "Unknown Language identifier",
) : IllegalArgumentException(message)