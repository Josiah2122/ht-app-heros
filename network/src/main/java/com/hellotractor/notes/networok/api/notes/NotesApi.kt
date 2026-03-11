package com.hellotractor.notes.networok.api.notes

import com.hellotractor.notes.networok.api.notes.models.NoteResponse
import retrofit2.http.GET

interface NotesApi {

    @GET("notes")
    suspend fun fetchNotes(): List<NoteResponse>
}
