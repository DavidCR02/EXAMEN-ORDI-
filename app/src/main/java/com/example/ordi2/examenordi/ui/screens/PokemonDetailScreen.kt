package com.example.ordi2.examenordi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

@Composable
fun PokemonDetailScreen(navController: NavController, pokemonName: String) {
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var pokemonData by remember { mutableStateOf<PokemonDetail?>(null) }

    LaunchedEffect(pokemonName) {
        isLoading = true
        error = null
        try {
            val response = withContext(Dispatchers.IO) {
                URL("https://pokeapi.co/api/v2/pokemon/${pokemonName.lowercase()}").readText()
            }
            val json = JSONObject(response)
            val name = json.getString("name")
            val height = json.getInt("height")
            val weight = json.getInt("weight")
            val order = json.getInt("order")
            val sprite = json.getJSONObject("sprites").getString("front_default")
            pokemonData = PokemonDetail(name, height, weight, order, sprite)
        } catch (e: Exception) {
            error = "La información no se cargo"
        }
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (error != null) {
            Text(
                text = error ?: "Error quien sabe",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            pokemonData?.let { data ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Regresar"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = data.name.replaceFirstChar { it.uppercase() },
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data.sprite)
                            .crossfade(true)
                            .build(),
                        contentDescription = data.name,
                        modifier = Modifier.size(180.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text("Height: ${data.height}", fontSize = 20.sp)
                    Text("Weight: ${data.weight}", fontSize = 20.sp)
                    Text("Order: ${data.order}", fontSize = 20.sp)
                }
            }
        }
    }
}
