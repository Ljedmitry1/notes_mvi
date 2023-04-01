package com.example.mvi.presentation.items

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.domain.model.NoteModel
import com.example.mvi.presentation.ui.theme.MviTheme
import java.time.LocalDate
import java.util.Random

@Composable
fun NoteItem(note: NoteModel, modifier: Modifier) {
    val color = Color(
        Random().nextInt(256),
        Random().nextInt(256),
        Random().nextInt(256),
        alpha = 30
    )
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(color)
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Column {
                Text(text = note.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = note.subtitle,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 24.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = note.author, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteItemPrev() {
    MviTheme {
        val note = NoteModel(
            id = 1,
            title = "Note 1",
            subtitle = "subtitle 1",
            data = LocalDate.now(),
            author = "Author 1"
        )
        NoteItem(note = note, modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
    }
}