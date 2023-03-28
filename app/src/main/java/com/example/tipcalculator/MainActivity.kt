package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import java.text.NumberFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipCalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun TipCalculatorScreen() {
    var serviceCostAmountInput by remember { mutableStateOf("") }
    val amount = serviceCostAmountInput.toDoubleOrNull() ?: 0.0


    var serviceTipAmountInput by remember { mutableStateOf("") }
    val tipAmount = serviceTipAmountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipAmount)

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Text(
        text = stringResource(R.string.tip_calculator_heading),
        fontSize = 24.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                // generate a random number between 1 and 100
                val randomNumber = String.format("%.2f", (1..100).random() + (0..99).random() / 100.0)
                // set the random number to serviceTipAmountInput variable
                serviceCostAmountInput = randomNumber.toString()

//                EditServiceCostField(
//                    value = serviceCostAmountInput,
//                    onValueChange = { serviceCostAmountInput = it }
//                )

            },
            modifier = Modifier.fillMaxWidth() // set the button width to match the parent container
        ) {
            Text(
                text = stringResource(R.string.generate_random_bill)
            )
        }

        Spacer(Modifier.height(16.dp))


        EditServiceCostField(
            value = serviceCostAmountInput,
            onValueChange = { serviceCostAmountInput = it }
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = {
                //your onclick code here
                serviceTipAmountInput = "10"
            }) {
                Text(
                    text = stringResource(R.string.tip1)
                )
            }
            Spacer(Modifier.width(10.dp))

            Button(onClick = {
                //your onclick code here
                serviceTipAmountInput = "15"

            }) {
                Text(
                    text = stringResource(R.string.tip2)
                )
            }
        }

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Button(onClick = {
                //your onclick code here
                serviceTipAmountInput = "18"

            },
            ) {
                Text(
                    text = stringResource(R.string.tip3)
                )
            }
            Spacer(Modifier.width(10.dp))

            Button(onClick = {
                //your onclick code here
                serviceTipAmountInput = "20"

            }) {
                Text(
                    text = stringResource(R.string.tip4)
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.bill_amount,  String.format("%.2f", amount)),
            modifier = Modifier.align(Alignment.End),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(R.string.tip_amount,  String.format("%.2f", tip)),
            modifier = Modifier.align(Alignment.End),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            color = Color.Gray,
            thickness = 3.dp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Text(
            text = stringResource(R.string.total, String.format("%.2f", (tip + amount))),
            modifier = Modifier.align(Alignment.End),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun EditServiceCostField(
    value: String,
    onValueChange: (String) -> Unit
) {
//    var serviceCostAmountInput by remember { mutableStateOf("") }
//    val amount = serviceCostAmountInput.toDoubleOrNull() ?: 0.0
//    val tip = calculateTip(amount)
    TextField(
        value = value,
        onValueChange = onValueChange,

//        value = serviceCostAmountInput,
//        onValueChange = { serviceCostAmountInput = it},
        label = { Text(stringResource(R.string.service_cost)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


private fun calculateTip(
    amount: Double,
    tipPercent: Double
): Double {
    return tipPercent / 100 * amount
//    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        TipCalculatorScreen()
    }
}