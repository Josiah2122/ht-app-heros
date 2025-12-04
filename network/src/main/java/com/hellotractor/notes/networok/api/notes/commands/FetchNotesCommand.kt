package com.hellotractor.notes.networok.api.notes.commands

import com.hellotractor.notes.domain.models.Note
import com.hellotractor.notes.networok.NetworkExceptions
import com.hellotractor.notes.networok.api.notes.converters.NoteResponseListToNoteListConverter
import com.hellotractor.notes.networok.api.notes.mockNotesApi
import javax.inject.Inject

class FetchNotesCommand @Inject constructor(
    private val converter: NoteResponseListToNoteListConverter) {

    fun run(): List<Note>  =
        runCatching {
            val response = mockNotesApi()
            converter.from(response)
        }.onFailure { cause ->
            throwClientErrorWithCauseIfCauseNotRequestError(cause)
        }.getOrThrow()

    private fun throwClientErrorWithCauseIfCauseNotRequestError(cause: Throwable) {
        if (cause !is NetworkExceptions.RequestError) {
            throw NetworkExceptions.ClientError().apply { initCause(cause) }
        }
    }
}
