package com.yaroslavzghoba.model

/**
 * Language of the application
 *
 * @param iso369Code1 two-letter ISO 369-1 code of language. See more about
 * [ISO-369 language codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
 */
// TODO: Add more languages
enum class Language(val iso369Code1: String) {
    ENGLISH(iso369Code1 = "en"),
    RUSSIAN(iso369Code1 = "ru"),
    UKRAINIAN(iso369Code1 = "uk"),
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
internal fun String.toLanguage(): Language = when (this) {
    Language.ENGLISH.iso369Code1 -> Language.ENGLISH
    Language.RUSSIAN.iso369Code1 -> Language.RUSSIAN
    Language.UKRAINIAN.iso369Code1 -> Language.UKRAINIAN
    else -> throw UnknownLanguageException(
        message = "Cannot cast \"$this\" ISO 369-1 code to Language",
    )
}

/**
 * Throw it you cannot cast something to [Language]
 */
internal class UnknownLanguageException(
    message: String = "Unknown Language identifier",
) : Exception(message)