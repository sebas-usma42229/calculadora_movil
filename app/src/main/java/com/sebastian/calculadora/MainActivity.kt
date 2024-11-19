package com.sebastian.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview

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
    var displayValue by remember { mutableStateOf("0") }
    var firstOperand by remember { mutableStateOf(0.0) }
    var secondOperand by remember { mutableStateOf(0.0) }
    var operator by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        DisplayScreen(displayValue)
        Spacer(modifier = Modifier.height(16.dp))
        CalculatorButtons(
            onNumberClick = { number ->
                if (displayValue == "0" || displayValue == "0.0") {
                    displayValue = number
                } else {
                    displayValue += number
                }
            },
            onOperatorClick = { selectedOperator ->
                firstOperand = displayValue.toDouble()
                operator = selectedOperator
                displayValue = "0"
            },
            onEqualClick = {
                secondOperand = displayValue.toDouble()
                displayValue = calculateResult(firstOperand, secondOperand, operator).toString()
                operator = null
            },
            onClearClick = {
                displayValue = "0"
                firstOperand = 0.0
                secondOperand = 0.0
                operator = null
            }
        )
    }
}

fun calculateResult(firstOperand: Double, secondOperand: Double, operator: String?): Double {
    return when (operator) {
        "+" -> firstOperand + secondOperand
        "-" -> firstOperand - secondOperand
        "*" -> firstOperand * secondOperand
        "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
        else -> 0.0
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
fun CalculatorButtons(
    onNumberClick: (String) -> Unit,
    onOperatorClick: (String) -> Unit,
    onEqualClick: () -> Unit,
    onClearClick: () -> Unit
) {
    val buttonSpacing = 8.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("7", Modifier.weight(1f)) { onNumberClick("7") }
            CalculatorButton("8", Modifier.weight(1f)) { onNumberClick("8") }
            CalculatorButton("9", Modifier.weight(1f)) { onNumberClick("9") }
            CalculatorButton("/", Modifier.weight(1f)) { onOperatorClick("/") }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("4", Modifier.weight(1f)) { onNumberClick("4") }
            CalculatorButton("5", Modifier.weight(1f)) { onNumberClick("5") }
            CalculatorButton("6", Modifier.weight(1f)) { onNumberClick("6") }
            CalculatorButton("*", Modifier.weight(1f)) { onOperatorClick("*") }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("1", Modifier.weight(1f)) { onNumberClick("1") }
            CalculatorButton("2", Modifier.weight(1f)) { onNumberClick("2") }
            CalculatorButton("3", Modifier.weight(1f)) { onNumberClick("3") }
            CalculatorButton("-", Modifier.weight(1f)) { onOperatorClick("-") }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("0", Modifier.weight(1f)) { onNumberClick("0") }
            CalculatorButton(".", Modifier.weight(1f)) { onNumberClick(".") }
            CalculatorButton("C", Modifier.weight(1f)) { onClearClick() }
            CalculatorButton("+", Modifier.weight(1f)) { onOperatorClick("+") }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton("=", Modifier.fillMaxWidth()) { onEqualClick() }
        }
    }
}

@Composable
fun CalculatorButton(symbol: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val backgroundColor = when (symbol) {
        "C" -> Color.Red 
        "/", "*", "-", "+", "=" -> Color(0xFFADD8E6)
        else -> Color.White
    }
    Button(
        onClick = onClick,
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