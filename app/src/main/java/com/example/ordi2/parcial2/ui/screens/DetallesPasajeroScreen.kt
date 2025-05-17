package com.example.ordi2.parcial2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ordi2.R
import com.example.ordi2.parcial2.ui.components.AerolineaCard
import com.example.ordi2.parcial2.viewmodel.VuelosViewModel
import coil.compose.AsyncImage

data class Aerolinea(
    val nombre: String,
    val imagenResId: Int
)

@Composable
fun DetallesPasajeroScreen(
    navController: NavController,
    destinoId: String,
    viewModel: VuelosViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var nombrePasajero by remember { mutableStateOf("") }
    var aerolineaSeleccionada by remember { mutableStateOf<Aerolinea?>(null) }
    var mostrarError by remember { mutableStateOf(false) }
    var mostrarConfirmacion by remember { mutableStateOf(false) }
    
    val vuelosNacionales by viewModel.vuelosNacionales.collectAsState()
    val vuelosInternacionales by viewModel.vuelosInternacionales.collectAsState()
    val destino = vuelosNacionales.find { it.id == destinoId }
        ?: vuelosInternacionales.find { it.id == destinoId }
    
    val aerolineas = remember {
        listOf(
            Aerolinea(
                nombre = "Aeroméxico",
                imagenResId = R.drawable.aeromexico
            ),
            Aerolinea(
                nombre = "VivaAerobus",
                imagenResId = R.drawable.viva
            ),
            Aerolinea(
                nombre = "Volaris",
                imagenResId = R.drawable.volaris
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nombre del Pasajero",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nombrePasajero,
            onValueChange = { 
                nombrePasajero = it
                mostrarError = false
            },
            label = { Text("Ingrese su nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = mostrarError && nombrePasajero.isBlank()
        )

        if (mostrarError && nombrePasajero.isBlank()) {
            Text(
                text = "Por favor ingrese el nombre del pasajero",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
        }

        Text(
            text = "Selecciona tu aerolínea",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            aerolineas.forEach { aerolinea ->
                AerolineaCard(
                    aerolinea = aerolinea,
                    isSelected = aerolineaSeleccionada == aerolinea,
                    onClick = {
                        aerolineaSeleccionada = aerolinea
                        mostrarError = false
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
        }

        if (mostrarError && aerolineaSeleccionada == null) {
            Text(
                text = "Por favor seleccione una aerolínea",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { 
                    navController.popBackStack("vuelos", false)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Regresar")
            }
            
            Button(
                onClick = { 
                    if (nombrePasajero.isBlank() || aerolineaSeleccionada == null) {
                        mostrarError = true
                    } else {
                        mostrarConfirmacion = true
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Reservar")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        destino?.let { dest ->
            AsyncImage(
                model = dest.imagen,
                contentDescription = "Imagen de ${dest.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }

    if (mostrarConfirmacion) {
        AlertDialog(
            onDismissRequest = { mostrarConfirmacion = false },
            title = { Text("¡Reserva Exitosa!") },
            text = { 
                Text(
                    "${nombrePasajero}, tu vuelo ${destino?.tipoVuelo?.lowercase()} a ${destino?.nombre} en ${aerolineaSeleccionada?.nombre?.lowercase()} ha sido reservado"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { 
                        mostrarConfirmacion = false
                        navController.popBackStack("vuelos", false)
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
} 