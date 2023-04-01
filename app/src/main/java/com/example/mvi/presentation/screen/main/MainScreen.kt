package com.example.mvi.presentation.screen.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mvi.domain.model.NoteModel
import com.example.mvi.presentation.items.ErrorItem
import com.example.mvi.presentation.items.LoadingItem
import com.example.mvi.presentation.items.NoteItem

@Composable
fun MainScreen(navController: NavHostController) {

    val viewModel = hiltViewModel<MainViewModel>()

    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            Log.d("checkData", "Loading....")
            LoadingItem()
        }

        state.data.isNotEmpty() -> {
            Log.d("checkData", "Data Size: ${state.data.size}")
            MainScreenContent(state.data)
        }

        state.error != null -> {
            Log.d("checkData", "ErrorMessage: ${state.error}")
            ErrorItem(state.error) {
                viewModel.sendEvent(MainScreenEvent.LoadingData)
            }
        }
    }

}

@Composable
fun MainScreenContent(data: List<NoteModel>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Text(
                    text = "My Super Notes App",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 16.dp)
                )
            }
            items(data){ note ->

                NoteItem(note, modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))

            }
        }
    }
}

