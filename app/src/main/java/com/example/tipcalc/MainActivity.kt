package com.example.tipcalc


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalcLayout()
        }
    }
}

@Composable
fun TipCalcLayout() {
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var customTip by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val percent = tipInput.toDoubleOrNull() ?: 0.0

    val tip = if (customTip.isNotEmpty()) {
        customTip.toDoubleOrNull() ?: 0.0
    } else {
        amount * percent / 100
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Calculate Tip",
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Bill Amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = tipInput,
            onValueChange = { tipInput = it },
            label = { Text("Tip Percentage") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = customTip,
            onValueChange = { customTip = it },
            label = { Text("Custom Tip Amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Tip Amount: $%.2f".format(tip),
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalcPreview() {
    TipCalcLayout()
}
