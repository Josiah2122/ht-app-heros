package com.hellotractor.notes.networok.api.notes.models

data class NoteResponse(
    val id: Long,
    val title: String,
    val dateCreated: String,
    val author: String,
    val content: String,
    val comments: List<String>,
    val tags: List<String>,
    val type: NoteTypeResponse
)
