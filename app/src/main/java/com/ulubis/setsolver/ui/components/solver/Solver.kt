package com.ulubis.setsolver.ui.components.solver

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SetTheorySolver() {
    var setA by remember { mutableStateOf("") }
    var setB by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = setA,
            onValueChange = { setA = it },
            label = { Text("Set A") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = setB,
            onValueChange = { setB = it },
            label = { Text("Set B") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { result = handleIntersection(setA, setB) }) {
            Text("Intersection")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { result = handleUnion(setA, setB) }) {
            Text("Union")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { result = handleSubset(setA, setB) }) {
            Text("Subset")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result: $result")
    }
}

private fun handleIntersection(setA: String, setB: String): String {
    val intersection = setA.split(",").intersect(setB.split(",").toSet()).joinToString(", ")
    return intersection.ifBlank { "No intersection" }
}

private fun handleUnion(setA: String, setB: String): String {
    val union = (setA.split(",") + setB.split(",")).distinct().joinToString(", ")
    return union.ifBlank { "No union" }
}

private fun handleSubset(setA: String, setB: String): String {
    val isSubset = setA.split(",").all { setB.split(",").contains(it) }
    return if (isSubset) "A is a subset of B" else "A is not a subset of B"
}

@Preview(showBackground = true)
@Composable
fun PreviewSetTheoryApp() {
    SetTheorySolver()
}