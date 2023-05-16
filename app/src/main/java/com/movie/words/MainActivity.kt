package com.movie.words

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.activity.viewModels

import com.movie.words.play.generateRandomWord
import com.movie.words.ui.theme.WordsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<PlayViewModel>()
        setContent {
            WordsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    play(context = this, viewModel)
                }
            }
        }
    }
}
@Composable
fun play(context: Context, viewModel: PlayViewModel) {
    val word by viewModel.word
    val textFieldStates by viewModel.textFieldStates
    val checked by viewModel.checked

    Column {
        for (i in textFieldStates.indices) {
            Row {
                for (j in word.indices) {
                    val textFieldValue = textFieldStates[i][j]
                    val isMatched = textFieldValue.text.lowercase() == word[j].lowercase().toString()
                    var visible by remember { mutableStateOf(true) }

                    OutlinedTextField(
                        value = textFieldValue,
                        onValueChange = { value ->
                            viewModel.onTextFieldValueChanged(i, j, value)
                        },
                        textStyle = TextStyle(
                            color = if (checked && isMatched) Color.Green
                            else if (checked && word.contains(textFieldValue.text.lowercase()) && textFieldValue.text.isNotBlank() && !isMatched) Color.Blue
                            else Color.Unspecified
                        ),
                        modifier = Modifier.weight(1f)
                            .padding(top = 5.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                viewModel.onCheckButtonClicked(context)
            },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            enabled = textFieldStates.last().any { it.text.isNotBlank() }
        ) {
            Text(text = "Comprobar")
        }
        Text(text = "Palabra generada: $word")
        Text(text = "Longitud de palabra: ${word.length}")
    }
}














