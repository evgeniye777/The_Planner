package com.example.the_planner_semen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.ui.text.style.TextAlign
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    LazyColumn(
        modifier = Modifier.padding(vertical = 10.dp) 
    ) {
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

@Preview(showBackground = true)
@Composable
fun PreviewOrderList() {
    Surface {
        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp) // Задаем отступы для предпросмотра
        ) {
            items(generateOrders()) { order ->
                OrderItem(order)
            }
        }
    }
}
//https\://services.gradle.org/distributions/gradle-8.12.1-bin.zip
//file\:///D:/MyFiles/Trainings/0_Gradle/gradle-8.12.1-bin.zip