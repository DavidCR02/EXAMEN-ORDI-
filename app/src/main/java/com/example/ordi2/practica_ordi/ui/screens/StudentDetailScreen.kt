package com.example.ordi2.practica_ordi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ordi2.practica_ordi.model.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(
    navController: NavController,
    student: Student
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Estudiante") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = student.imageUrl,
                contentDescription = "Foto de ${student.fullName}",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = student.fullName,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "ID Carrera: ${student.careerId}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = student.email,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Biografía",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = student.biography,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
} 