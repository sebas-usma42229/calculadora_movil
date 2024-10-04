package com.sebastian.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.shadow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        DisplayScreen("0")
        Spacer(modifier = Modifier.height(16.dp))
        CalculatorButtons()
    }
}

@Composable
fun DisplayScreen(value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = value,
            fontSize = 36.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun CalculatorButtons() {
    val buttonSpacing = 8.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("7", Modifier.weight(1f))
            CalculatorButton("8", Modifier.weight(1f))
            CalculatorButton("9", Modifier.weight(1f))
            CalculatorButton("/", Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("4", Modifier.weight(1f))
            CalculatorButton("5", Modifier.weight(1f))
            CalculatorButton("6", Modifier.weight(1f))
            CalculatorButton("*", Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("1", Modifier.weight(1f))
            CalculatorButton("2", Modifier.weight(1f))
            CalculatorButton("3", Modifier.weight(1f))
            CalculatorButton("-", Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("0", Modifier.weight(1f))
            CalculatorButton(".", Modifier.weight(1f))
            CalculatorButton("C", Modifier.weight(1f))
            CalculatorButton("+", Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("=", Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun CalculatorButton(symbol: String, modifier: Modifier = Modifier) {
    //  color  fondo  botones de operaciones =)
    val backgroundColor = if (symbol in listOf("/", "*", "-", "+", "=")) Color(0xFFADD8E6) else Color.White

    Button(
        onClick = { },
        modifier = modifier
            .aspectRatio(if (symbol == "=") 4f else 1f)
            .shadow(8.dp, if (symbol == "=") RoundedCornerShape(8.dp) else CircleShape),
        shape = if (symbol == "=") RoundedCornerShape(8.dp) else CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalculatorApp()
}
