package com.example.mvi.presentation.screen.main

import com.example.mvi.domain.model.NoteModel
import com.example.mvi.presentation.screen.base.UiEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class MainScreenEvent: UiEvent {
    object LoadingData: MainScreenEvent()
    data class ShowData(val data: List<NoteModel>): MainScreenEvent()
    data class ShowError(val errorMessage: String): MainScreenEvent()
}