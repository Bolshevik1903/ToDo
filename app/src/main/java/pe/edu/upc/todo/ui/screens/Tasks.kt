package pe.edu.upc.todo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.upc.todo.ui.theme.ToDoTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold( topBar = {
            TopAppBar(
                elevation = 4.dp,
            ) {

            }
        })
    {
        Tasks()
    }
}

@Composable
fun Tasks(){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(text = "First item")
        }
        items(100){index ->
            Text(text = "Item: $index")
        }
        item {
            Text(text = "Last item")
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


