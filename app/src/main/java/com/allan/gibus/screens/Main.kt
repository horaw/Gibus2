package com.allan.gibus.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.allan.gibus.MainViewModel
import com.allan.gibus.MainViewModelFactory
import com.allan.gibus.model.Note
import com.allan.gibus.navigation.NavRoute
import com.allan.gibus.ui.theme.GibusTheme

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val allNotes = viewModel.readAllNotes().observeAsState(listOf()).value
    var searchQuery by remember { mutableStateOf("") }

    val filteredNotes = allNotes.filter { note ->
        note.title.contains(searchQuery, ignoreCase = true) ||
                note.subtitle.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Icons", tint = Color.White)
            }
        }
    ) {
        Column {
            SearchBar(searchQuery) { query -> searchQuery = query }

            LazyColumn {
                items(filteredNotes) { note ->
                    NoteItem(note = note, navController = navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Поиск") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = {
                    onQueryChange("")
                    keyboardController?.hide()
                }) {
                    Icon(Icons.Default.Close, contentDescription = "Clear")
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}


@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${note.id}")
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = note.subtitle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    GibusTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        MainScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}
