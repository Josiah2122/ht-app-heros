package com.hellotractor.notes.networok.api.notes

import com.hellotractor.notes.networok.api.notes.commands.FetchNotesCommand
import com.hellotractor.notes.networok.api.notes.converters.NoteResponseListToNoteListConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotesModule {

    @Provides
    @Singleton
    fun provideNotesConverter(): NoteResponseListToNoteListConverter =
        NoteResponseListToNoteListConverter()

    @Provides
    @Singleton
    fun provideFetchNotesCommand(converter: NoteResponseListToNoteListConverter): FetchNotesCommand =
        FetchNotesCommand(converter)
}
