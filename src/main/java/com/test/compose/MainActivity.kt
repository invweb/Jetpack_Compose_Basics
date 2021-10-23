package com.test.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.compose.list.row.ItemTypeOne
import com.test.compose.list.row.ItemTypeTwo
import com.test.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "navigate2") {
                        composable("navigate1") {
                            Navigate1(navController)
                        }
                        composable("navigate2") {
                            Navigate2(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemOne(itemTypeOne: ItemTypeOne) {
    Text(text = itemTypeOne.text)
    Divider(color = Color.Blue, thickness = 1.dp)
}

@Composable
fun ItemTwo(itemTypeTwo: ItemTypeTwo) {
    Column {
        Text(text = itemTypeTwo.text)
        Text(text = itemTypeTwo.description)
    }
    Divider(color = Color.Blue, thickness = 1.dp)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",
    modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 0.dp))
}

@Composable
fun Navigate1(navController: NavController, modifier: Modifier = Modifier) {
    val materialBlue700= Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val dataItems = listOf(
        ItemTypeOne("1"),
        ItemTypeTwo("text", "description")
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = {Text("TopAppBar")},backgroundColor = materialBlue700)  },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Text("X")
        } },
        drawerContent = { Text(text = "drawerContent") },
        content = {
            Greeting("Android: content 1")
            Button(
                onClick = {
                    navController.navigate("navigate2")
                },
                modifier = Modifier
                    .padding(48.dp, 48.dp, 0.dp, 0.dp)
            ) {
                Text(text = "Navigate 2")
            }

            LazyColumn(
                modifier = Modifier
                    .padding(0.dp, 108.dp, 0.dp, 0.dp)
            ) {
                items(items = dataItems, itemContent = { item ->
                    when(item){
                        is ItemTypeOne -> {
                            val itT1: ItemTypeOne = item as ItemTypeOne
                            ItemOne(itemTypeOne = itT1)
                        }
                        is ItemTypeTwo -> {
                            val itT2: ItemTypeTwo = item as ItemTypeTwo
                            ItemTwo(itemTypeTwo = itT2)
                        }
                    }
                })
            }
                  },
        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
    )
}

@Composable
fun Navigate2(navController: NavController) {
    val materialBlue700= Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = {Text("TopAppBar")},backgroundColor = materialBlue700)  },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Text("X")
        } },
        drawerContent = { Text(text = "drawerContent") },
        content = {
            Greeting("Android: content 2")
            Button(
                onClick = {
                    navController.navigate("navigate1")
                },
                modifier = Modifier
                    .padding(48.dp, 48.dp, 0.dp, 0.dp))
            {
                Text(text = "Navigate 1")
            }
                  },
        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
    )
}