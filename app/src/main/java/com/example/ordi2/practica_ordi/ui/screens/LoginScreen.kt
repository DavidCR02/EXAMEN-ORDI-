package com.example.ordi2.practica_ordi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.ordi2.R

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp)
                    .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                    .size(130.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(min = 300.dp, max = 350.dp)
                    .height(340.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(Color(0xFFFFE0A3))
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(Color(0xFF3B5998))
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(min = 300.dp, max = 350.dp)
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFFE0A96D), shape = RoundedCornerShape(8.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text("Usuario", fontSize = 18.sp, modifier = Modifier.align(Alignment.Start))
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isError = false
                        errorMessage = ""
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                Text("Contraseña", fontSize = 18.sp, modifier = Modifier.align(Alignment.Start))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isError = false
                        errorMessage = ""
                    },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                Button(
                    onClick = {
                        if (username.isBlank() || password.isBlank()) {
                            isError = true
                            errorMessage = "Por favor complete todos los campos"
                        } else if (username == "david.castillo01" && password == "xrce5328") {
                            onLoginSuccess()
                        } else {
                            isError = true
                            errorMessage = "Usuario o contraseña incorrectos"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Entrar")
                }

                if (isError) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
} 