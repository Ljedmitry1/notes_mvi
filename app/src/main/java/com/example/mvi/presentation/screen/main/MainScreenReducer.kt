package com.example.mvi.presentation.screen.main

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvi.domain.usecase.LoadNotesUseCase
import com.example.mvi.presentation.screen.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainScreenReducer(
    initial: MainScreenState,
    val useCase: LoadNotesUseCase,
    val viewModelScope: CoroutineScope
) : Reducer<MainScreenState, MainScreenEvent>(initial) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun reduce(oldState: MainScreenState, event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.ShowData -> {
                setState(oldState.copy(isLoading = false, data = event.data))
            }

            is MainScreenEvent.LoadingData -> {
                viewModelScope.launch {
                    setState(oldState.copy(isLoading = true, data = emptyList()))

                    try {
                        useCase.invoke().let { data ->
                            if (data.isNotEmpty()) {
                                sentEvent(MainScreenEvent.ShowData(data = data))
                            } else {
                                sentEvent(MainScreenEvent.ShowError(errorMessage = "Data is empty"))
                            }
                        }
                    } catch (e: Exception) {
                        sentEvent(
                            MainScreenEvent.ShowError(
                                errorMessage = e.message ?: "Exception"
                            )
                        )
                    }
                }
            }

            is MainScreenEvent.ShowError -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        data = emptyList(),
                        error = event.errorMessage
                    )
                )
            }
        }
    }
}