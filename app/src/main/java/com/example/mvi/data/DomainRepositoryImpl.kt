package com.example.mvi.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvi.domain.model.NoteModel
import com.example.mvi.domain.repository.DomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.time.LocalDate
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(): DomainRepository {

    companion object{
        var count = 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllNotes(): List<NoteModel> {
        val list: List<NoteModel>
        withContext(Dispatchers.IO){
            when(count){
                0 ->{
                    count++
                    delay(1000)
                    list = emptyList()
                }
                1 ->{
                    count++
                    delay(10000)
                    throw ConnectException("Lost internet connection")

                }

                2 ->{
                    delay(3000)
                    list = listOf(
                        NoteModel(1, "Note 1", "Subtitle 1", data = LocalDate.now(), author = "Author 1"),
                        NoteModel(2, "Note 2", "Subtitle 2", data = LocalDate.now(), author = "Author 1"),
                        NoteModel(3, "Note 3", "Subtitle 3", data = LocalDate.now(), author = "Author 1"),
                        NoteModel(4, "Note 4", "Subtitle 4", data = LocalDate.now(), author = "Author 1"),
                    )
                }
                else -> list = emptyList()
            }
            return@withContext
        }
        return list
    }
}