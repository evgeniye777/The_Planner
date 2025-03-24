package com.example.the_planner_semen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                // Здесь вы можете вызвать вашу основную функцию Compose
                Greeting("Android")
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    // Ваш UI-код здесь
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        Greeting("Android")
    }
}
//https\://services.gradle.org/distributions/gradle-8.12.1-bin.zip
//file\:///D:/MyFiles/Trainings/0_Gradle/gradle-8.12.1-bin.zip