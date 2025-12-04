package com.hellotractor.notes.networok.api.notes

import com.hellotractor.notes.networok.api.notes.models.NoteResponse

fun mockNotesApi(): List<NoteResponse> = listOf(
    NoteResponse(
        id = 1L,
        title = "First Note",
        dateCreated = "2024-01-01T10:00:00Z",
        author = "Alice",
        content = "This is the content of the first note.",
        comments = listOf("Great note!", "Very informative."),
        tags = listOf("personal", "work"),
        type = com.hellotractor.notes.networok.api.notes.models.NoteTypeResponse.IMPORTANT
    ),
    NoteResponse(
        id = 2L,
        title = "Second Note",
        dateCreated = "2024-02-15T14:30:00Z",
        author = "Bob",
        content = "This is the content of the second note.",
        comments = listOf("Thanks for sharing."),
        tags = listOf("ideas"),
        type = com.hellotractor.notes.networok.api.notes.models.NoteTypeResponse.NORMAL
    ),
    NoteResponse(
        id = 3L,
        title = "Third Note",
        dateCreated = "2024-03-10T09:15:00Z",
        author = "Charlie",
        content = "This is the content of the third note.",
        comments = emptyList(),
        tags = listOf("private", "thoughts"),
        type = com.hellotractor.notes.networok.api.notes.models.NoteTypeResponse.PRIVATE
    )
)
