package com.example.the_planner_semen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface {
                OrderList(generateOrders())
            }
        }
    }
}



data class Order(val title: String, val creationDate: String)
fun generateOrders(): List<Order> {
    val orders = mutableListOf<Order>()
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    for (i in 1..50) {
        val title = "Order #$i"
        val creationDate = dateFormat.format(Date())
        orders.add(Order(title, creationDate))
    }

    return orders
}

@Composable
fun OrderList(orders: List<Order>) {
    LazyColumn {
        items(orders) { order ->
            OrderItem(order)
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicText(
            text = order.title,
            modifier = Modifier
                .weight(0.8f)
                .padding(end = 8.dp),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        BasicText(
            text = order.creationDate,
            modifier = Modifier
                .weight(0.2f),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp, textAlign = TextAlign.End)
        )
    }
}

//https\://services.gradle.org/distributions/gradle-8.12.1-bin.zip
//file\:///D:/MyFiles/Trainings/0_Gradle/gradle-8.12.1-bin.zip