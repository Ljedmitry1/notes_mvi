package com.example.mvi.domain.repository

import com.example.mvi.domain.model.NoteModel

interface DomainRepository {

    suspend fun getAllNotes(): List<NoteModel>

}