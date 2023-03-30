package pe.edu.upc.todo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.upc.todo.ui.theme.ToDoTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    var tasks = remember {
        mutableStateOf(ArrayList<String>())
    }
    var newTask = remember {
        mutableStateOf(TextFieldValue())
    }
    Scaffold( topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text(text = "Todo App")
                },
                actions = {
                    IconButton(onClick = { tasks.value.add(newTask.value.text) }) {
                        Icon(Icons.Filled.Add,null)
                    }
                }
            )
        }) {
        Column {
            TextField(value = newTask.value, onValueChange = { newTask.value = it }, label = { Text(
                text = "New task"
            )})
            Tasks(tasks.value)
        }
    }
}

@Composable
fun Tasks(tasks: ArrayList<String>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tasks) { task ->
            Text(text = "$task")
        }
    }
}

@Composable
fun Contacts() {
    var names = ArrayList<String>()
    names.add("Enrique")
    names.add("Francisco")
    names.add("Erika")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(names) {name ->
            Text(text = "My name is $name")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoTheme() {
        MainScreen()
    }
}


