package com.example.mvi.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvi.data.DomainRepositoryImpl
import com.example.mvi.domain.model.NoteModel
import javax.inject.Inject

class LoadNotesUseCase @Inject constructor(
    private val domainRepository: DomainRepositoryImpl
) : BaseUseCase<List<NoteModel>>() {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun invoke(): List<NoteModel> = domainRepository.getAllNotes()
}