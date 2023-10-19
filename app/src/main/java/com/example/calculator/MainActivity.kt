package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.dp
import androidx.compose.ui.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {
    val firstNumber = remember { mutableStateOf("") }
    val secondNumber = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }
    val operation = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = firstNumber.value,
            onValueChange = { firstNumber.value = it },
            label = { Text("First Number") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = secondNumber.value,
            onValueChange = { secondNumber.value = it },
            label = { Text("Second Number") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        DropdownMenu(
            expanded = operation.value.isNotEmpty(),
            onDismissRequest = { operation.value = "" }
        ) {
            DropdownMenuItem(onClick = { operation.value = "+" }) {
                Text("Add")
            }
            DropdownMenuItem(onClick = { operation.value = "-" }) {
                Text("Subtract")
            }
            DropdownMenuItem(onClick = { operation.value = "*" }) {
                Text("Multiply")
            }
            DropdownMenuItem(onClick = { operation.value = "/" }) {
                Text("Divide")
            }
        }

        Text(
            text = result.value || '0',
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(onClick + {
            try {
                val num1 = firstNumber.value.toFloat()
                val num2 = secondNumber.value.toFloat()
                result.value = performOperation(operation.value, num1, num2).toString()
            } catch (e: NumberFormatException) {
                result.value = "Invalid input"
            } catch (e: IllegalArgumentException) {
                result.value = "Invalid operation"
            }
        }) {
            Text("Calculate")
        }
    }
}

fun performOperation(operation: String, num1: Float, num2: Float): Float {
    return when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        else -> throw IllegalArgumentException("Invalid operation")
    }
}

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.filled.calculator

@Composable
fun navigation(modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            label = { Text("Calculator") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            label = { Text("Number Generator") },
            selected = true,
            onClick = {}
        )
    }
}