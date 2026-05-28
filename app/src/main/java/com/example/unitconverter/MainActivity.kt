package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverterDisplay()
                }
            }
        }
    }
}

@Composable
fun UnitConverterDisplay() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputDropdownExpanded by remember { mutableStateOf(false) }
    var outputDropdownExpanded by remember { mutableStateOf(false) }
    var iUnits by remember { mutableStateOf(1.0) }
    var oUnits by remember { mutableStateOf(1.0) }
    var iUnitName by remember { mutableStateOf("Meters") }
    var oUnitName by remember { mutableStateOf("Meters") }

    fun convertToUnits() {
        val iVal = inputValue.toDoubleOrNull() ?: 0.0
        val oVal = (iVal * iUnits * 1000.0 / oUnits).roundToInt() / 1000.0
        outputValue = oVal.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter")

        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            label = {
                Text("Enter a value")
            },
            value = inputValue,
            placeholder = {Text("Enter value to convert")},
            onValueChange = {
                inputValue = it
                convertToUnits()
            }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
                Button(
                    onClick = {
                        inputDropdownExpanded = !inputDropdownExpanded
                    }
                ) {
                    Text(iUnitName)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "",
                    )
                }

                DropdownMenu(
                    expanded = inputDropdownExpanded,
                    onDismissRequest = {
                        inputDropdownExpanded = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            inputDropdownExpanded = false
                            iUnits = 0.01
                            iUnitName = "Centimeters"
                            convertToUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            inputDropdownExpanded = false
                            iUnits = 1.0
                            iUnitName = "Meters"
                            convertToUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            inputDropdownExpanded = false
                            iUnits = 0.3048
                            iUnitName = "Feet"
                            convertToUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            inputDropdownExpanded = false
                            iUnits = 0.001
                            iUnitName = "Millimeters"
                            convertToUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Box {
                Button(
                    onClick = {
                        outputDropdownExpanded = !outputDropdownExpanded
                    }
                ) {
                    Text(oUnitName)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "",
                    )
                }

                DropdownMenu(
                    expanded = outputDropdownExpanded,
                    onDismissRequest = {
                        outputDropdownExpanded = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            outputDropdownExpanded = false
                            oUnits = 0.01
                            oUnitName = "Centimeters"
                            convertToUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            outputDropdownExpanded = false
                            oUnits = 1.0
                            oUnitName = "Meters"
                            convertToUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            outputDropdownExpanded = false
                            oUnits = 0.3048
                            oUnitName = "Feet"
                            convertToUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            outputDropdownExpanded = false
                            oUnits = 0.001
                            oUnitName = "Millimeters"
                            convertToUnits()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text("Converted Units: ${outputValue}")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUnitConverterDisplay() {
    UnitConverterDisplay()
}