package com.example.ordi2.practica_ordi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ordi2.practica_ordi.model.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    navController: NavController,
    students: List<Student>,
    onStudentClick: (String) -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Estudiantes") },
                actions = {
                    TextButton(onClick = onLogout) {
                        Text("Cerrar sesión")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(students) { student ->
                StudentCard(
                    student = student,
                    onClick = { onStudentClick(student.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentCard(
    student: Student,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = student.imageUrl,
                contentDescription = "Foto de ${student.fullName}",
                modifier = Modifier
                    .size(60.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = student.fullName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "ID Carrera: ${student.careerId}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = student.email,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun StudentListRoute(
    navController: NavController,
    viewModel: StudentViewModel = viewModel(),
    onLogout: () -> Unit
) {
    val students by viewModel.students.collectAsState()

    StudentListScreen(
        navController = navController,
        students = students,
        onStudentClick = { studentId ->
            // Aquí podrías navegar si tienes otra pantalla
        },
        onLogout = onLogout
    )
}
