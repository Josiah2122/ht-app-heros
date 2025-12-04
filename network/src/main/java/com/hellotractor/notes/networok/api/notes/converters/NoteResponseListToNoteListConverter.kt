package com.hellotractor.notes.networok.api.notes.converters

import com.hellotractor.notes.domain.models.Note
import com.hellotractor.notes.networok.OneDirectionalEntityConverter
import com.hellotractor.notes.networok.api.notes.models.NoteResponse
import com.hellotractor.notes.networok.api.notes.models.NoteTypeResponse

class NoteResponseListToNoteListConverter: OneDirectionalEntityConverter<List<NoteResponse>, List<Note>> {
    override fun from(entity: List<NoteResponse>): List<Note> {
        return entity.map { noteResponse ->
            Note(
                id = noteResponse.id,
                title = noteResponse.title,
                content = noteResponse.content,
                author = noteResponse.author,
                dateCreated = noteResponse.dateCreated,
                comments = noteResponse.comments,
                tags = noteResponse.tags,
                type = when (noteResponse.type) {
                    NoteTypeResponse.IMPORTANT -> Note.NoteType.IMPORTANT
                    NoteTypeResponse.NORMAL -> Note.NoteType.NORMAL
                    NoteTypeResponse.PRIVATE -> Note.NoteType.PRIVATE
                }
            )
        }
    }
}
