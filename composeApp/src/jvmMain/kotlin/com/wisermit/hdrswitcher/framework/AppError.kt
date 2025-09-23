package com.wisermit.hdrswitcher.framework

sealed class AppError(
    message: String? = null,
    cause: Throwable? = null,
) : AppException(message, cause = cause) {

    class InvalidFile(val fileName: String, cause: Throwable) : AppError(cause = cause)
    class UnsupportedFile(val fileName: String) : AppError()
}

class ProcessException(
    message: String,
    commands: Array<out String>,
    causeMessage: String,
) : AppException(
    message,
    detailedMessage = message +
            "\nCommand:\n    ${commands.joinToString("\n    ", "+ ")}" +
            "\nError: $causeMessage"
)

open class AppException(
    message: String?,
    val detailedMessage: String? = message,
    cause: Throwable? = null,
) : Exception(message ?: cause?.message, cause)