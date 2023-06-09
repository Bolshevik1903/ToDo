package pe.edu.upc.todo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.upc.todo.data.entities.Task
import pe.edu.upc.todo.data.local.AppDatabase
import pe.edu.upc.todo.ui.theme.ToDoTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val tasks = remember {
        mutableStateOf(emptyList<Task>())
    }
    val newTask = remember {
        mutableStateOf(TextFieldValue())
    }
    val isEditing = remember {
        mutableStateOf(false)
    }
    val enabled = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    val taskDao = AppDatabase.getInstance(context).taskDao()



    var temp = 0
    Scaffold(
        topBar = {

        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(text = "Todo App")
            },
            actions = {
                IconButton(onClick = {
                    if(!isEditing.value) {
                        //tasks.add(newTask.value.text)
                        taskDao.insert(Task(newTask.value.text, 0))
                        newTask.value = TextFieldValue()
                        enabled.value = false
                    }
                    else {
                        //tasks.set(temp, newTask.value.text)
                        isEditing.value = false
                        newTask.value = TextFieldValue()
                    }

                }, enabled = enabled.value) {
                    if(isEditing.value) {
                        Icon(Icons.Filled.Done, null)
                    } else {
                        Icon(Icons.Filled.Add, null)
                    }

                }
            }
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            TextField(
                label = { Text( text = "New task" ) },
                value = newTask.value,
                onValueChange = {
                    enabled.value = newTask.value.text.isNotEmpty()
                    newTask.value = it },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { newTask.value = TextFieldValue() }) {
                        Icon(Icons.Default.Close, null)
                    }

                }
            )
            /*Tasks(tasks) {value ->
                newTask.value = TextFieldValue(value)
                isEditing.value = true
                temp = tasks.indexOf(value)
            }*/
        }
    }
}

@Composable
fun Tasks(tasks: MutableList<String>, selectTask: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {

        items(tasks) { task ->
            TaskItem(
                     task = task,
                     deleteTask = {
                         tasks.remove(it)
                     },
                    selectTask = {
                        selectTask(it);
                    }
                )
        }
    }
}

//Changes here
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    task: String,
    deleteTask: (String) -> Unit,
    selectTask: (String) -> Unit
) {
    Card (
        onClick = { selectTask(task) }
            ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val checkedState = remember { mutableStateOf(false) }
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
            Text(text = task)
            IconButton(
                onClick = {
                    deleteTask(task)
                }
            ){
                Icon(Icons.Filled.Delete, null)
            }
        }
    }
}

/*
@Composable
fun Contacts() {
    var names = ArrayList<String>()
    names.add("Enrique")
    names.add("Francisco")
    names.add("Erika")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(names) { name ->
            Text(text = "My name is $name")
        }
    }
}
*/

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ToDoTheme {
        MainScreen()
    }
}