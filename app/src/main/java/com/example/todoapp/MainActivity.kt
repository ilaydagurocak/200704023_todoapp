package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoApp()
        }
    }
}

@Composable
fun ToDoApp() {
    var text by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<Pair<String, Boolean>>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter a task") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            singleLine = true
        )
        Button(
            onClick = {
                if (text.isNotEmpty()) {
                    tasks = tasks + Pair(text, false)
                    text = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Add")
        }
        Spacer(modifier = Modifier.height(16.dp))
        tasks.forEachIndexed { index, task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.first,
                    modifier = Modifier.weight(1f),
                    color = if (task.second) Color.Gray else Color.Black,
                    textDecoration = if (task.second) TextDecoration.LineThrough else TextDecoration.None
                )
                Checkbox(
                    checked = task.second,
                    onCheckedChange = { isChecked ->
                        val updatedTasks = tasks.toMutableList()
                        updatedTasks[index] = task.copy(second = isChecked)
                        tasks = updatedTasks
                    }
                )
            }
        }
    }
}