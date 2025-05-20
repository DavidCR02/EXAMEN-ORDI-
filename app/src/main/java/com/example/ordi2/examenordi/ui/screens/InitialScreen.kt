package com.example.ordi2.examenordi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ordi2.examenordi.navigation.Screen

@Composable
fun InitialScreen(navController: NavController) {
    var option by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Opción a probar (1 o 2)?",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = option,
                    onValueChange = {
                        option = it
                        showError = false
                    },
                    singleLine = true,
                    modifier = Modifier.width(200.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 170.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (option == "1") {
                        showError = false
                        navController.navigate(Screen.GameOver.route)
                    } else if (option == "2") {
                        showError = false
                        navController.navigate(Screen.PokemonList.route)
                    } else {
                        showError = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = "Validar",
                    color = Color.White
                )
            }
            if (showError) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Por favor ingrese alguno de estos numeros: 1, 2",
                    color = Color.Red
                )
            }
        }

        Text(
            text = "David Castillo - 20374",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
} 