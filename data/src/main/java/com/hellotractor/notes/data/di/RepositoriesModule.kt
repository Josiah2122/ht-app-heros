package com.hellotractor.notes.data.di

import com.hellotractor.notes.data.repositories.NotesRepositoryImpl
import com.hellotractor.notes.domain.repositories.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindNotesRepository(
        impl: NotesRepositoryImpl
    ): NotesRepository
}
