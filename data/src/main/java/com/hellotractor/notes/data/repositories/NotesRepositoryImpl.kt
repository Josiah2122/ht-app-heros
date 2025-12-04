package com.hellotractor.notes.data.repositories

import com.hellotractor.notes.domain.models.Note
import com.hellotractor.notes.domain.repositories.NotesRepository
import com.hellotractor.notes.networok.api.notes.commands.FetchNotesCommand
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val fetchNotesCommand: FetchNotesCommand
): NotesRepository {

    override suspend fun fetchNotes(): List<Note> {
        return fetchNotesCommand.run()
    }
}
