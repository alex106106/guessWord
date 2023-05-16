package com.movie.words

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.movie.words.play.generateRandomWord

class PlayViewModel(): ViewModel() {
    private val _word = mutableStateOf(generateRandomWord())
    val word: State<String> get() = _word

    private val _textFieldStates = mutableStateOf(listOf(List(_word.value.length) { TextFieldValue("") }))
    val textFieldStates: State<List<List<TextFieldValue>>> get() = _textFieldStates

    private val _showNewRow = mutableStateOf(false)
    val showNewRow: State<Boolean> get() = _showNewRow

    private val _checked = mutableStateOf(false)
    val checked: State<Boolean> get() = _checked

    private val _lastValidIndex = mutableStateOf(0)
    val lastValidIndex: State<Int> get() = _lastValidIndex

    init {
        resetGame()
    }

    fun onTextFieldValueChanged(row: Int, column: Int, value: TextFieldValue) {
        val currentWord = word.value
        val currentRowIsValid = if (column < currentWord.length) {
            (row == lastValidIndex.value || row == lastValidIndex.value - 1) && value.text.lowercase() == currentWord[column].lowercase().toString()
        } else {
            false
        }

        if (!currentRowIsValid) {
            _checked.value = false
        }

        _textFieldStates.value = textFieldStates.value.toMutableList().apply {
            if (row in this.indices) {
                this[row] = this[row].toMutableList().apply {
                    if (column in this.indices) {
                        this[column] = value
                    }
                }
            }
        }

        if (currentRowIsValid && row == lastValidIndex.value - 1) {
            _lastValidIndex.value++
        }
    }

    fun onCheckButtonClicked(context: Context) {
        _checked.value = true
        val lastTextFieldValues = textFieldStates.value.last()
        val allMatched = lastTextFieldValues.map { it.text.lowercase() } == word.value.toList().map { it.lowercase().toString() }

        if (!allMatched) {
            val hasError = lastTextFieldValues.withIndex().any { (index, value) -> value.text.lowercase() != word.value[index].lowercase().toString() }
            if (hasError) {
                val newTextFieldValues = List(word.value.length) { TextFieldValue("") }
                _textFieldStates.value = textFieldStates.value + listOf(newTextFieldValues)
                _showNewRow.value = true
            }
        } else {
            Toast.makeText(context, "¡Felicidades! Has ganado. Empezando un nuevo juego...", Toast.LENGTH_SHORT).show()
            resetGame()
        }
    }

    private fun resetGame() {
        _word.value = generateRandomWord()
        _textFieldStates.value = listOf(List(word.value.length) { TextFieldValue("") })
        _showNewRow.value = false
        _checked.value = false
        _lastValidIndex.value = 0
    }
}
