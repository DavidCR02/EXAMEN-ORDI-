package com.example.ordi2.parcial2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ordi2.parcial2.ui.screens.Aerolinea

@Composable
fun AerolineaCard(
    aerolinea: Aerolinea,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = aerolinea.imagenResId,
                contentDescription = "Logo de ${aerolinea.nombre}",
                modifier = Modifier
                    .size(60.dp),
                contentScale = ContentScale.Fit
            )
            
            Text(
                text = aerolinea.nombre,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
} 