package com.test.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "navigate1") {
                        composable("navigate1") { Navigate1(navController) }
                        composable("navigate2") { Navigate2(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",
    modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 0.dp))
}

@Composable
fun Navigate1(navController: NavController) {
    Button(
        onClick = {
                    navController.navigate("navigate2")
                  },
        modifier = Modifier
            .padding(8.dp, 48.dp, 0.dp, 0.dp)
    ) {
        Text(text = "Navigate 1")
    }
}

@Composable
fun Navigate2(navController: NavController) {
    Button(
        onClick = { navController.navigate("navigate1")},
        modifier = Modifier
            .padding(8.dp, 56.dp, 0.dp, 0.dp)
    ) {
        Text(text = "Navigate 2")
    }

    Text(text = "Test",
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp, 24.dp, 0.dp, 0.dp))

    Greeting("Android")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Greeting("Android")
    }
}