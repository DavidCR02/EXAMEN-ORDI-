package com.example.ordi2.practica_ordi.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordi2.practica_ordi.data.DogRepository
import com.example.ordi2.practica_ordi.model.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val repository = DogRepository()
    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    private val localStudents = listOf(
        Student(
            id = "19327",
            careerId = "19327",
            fullName = "GERARDO GOMEZ SCHEKAIBAN",
            email = "gerardo.gomez01@lesti.edu.mx",
            imageUrl = "",
            biography = "Licenciatura en Ingeniería en Sistemas y Negocios Digitales (Plan 2020)"
        ),
        Student(
            id = "19584",
            careerId = "19584",
            fullName = "KARIME YUANEL RUIZ CERON",
            email = "karime.ruiz@lesti.edu.mx",
            imageUrl = "",
            biography = "Licenciatura en Ingeniería en Sistemas y Negocios Digitales (Plan 2020)"
        ),
        Student(
            id = "20059",
            careerId = "20059",
            fullName = "STEFANOS ALAIN NICHOLS RAMIRO",
            email = "stefanos.nichols@lesti.edu.mx",
            imageUrl = "",
            biography = "Licenciatura en Ingeniería en Sistemas y Negocios Digitales (Plan 2020)"
        ),
        Student(
            id = "20104",
            careerId = "20104",
            fullName = "LUIS ALBERTO FLORES CAVAZOS",
            email = "alberto.flores@lesti.edu.mx",
            imageUrl = "",
            biography = "Licenciatura en Ingeniería en Sistemas y Negocios Digitales (Plan 2020)"
        ),
        Student(
            id = "20291",
            careerId = "20291",
            fullName = "JULIA MAYDELI CASTAN PACHECO",
            email = "maydeli.castan@lesti.edu.mx",
            imageUrl = "",
            biography = "Licenciatura en Ingeniería en Sistemas y Negocios Digitales (Plan 2020)"
        ),
        Student(
            id = "20374",
            careerId = "20374",
            fullName = "DAVID CASTILLO RUIZ",
            email = "david.castillo01@lesti.edu.mx",
            imageUrl = "",
            biography = "Licenciatura en Ingeniería en Sistemas y Negocios Digitales (Plan 2020)"
        )
    )

    private val backupImage = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png"

    init {
        _students.value = localStudents.map { it.copy(imageUrl = backupImage) }
        assignRandomDogImagesToStudents()
    }

    private fun assignRandomDogImagesToStudents() {
        viewModelScope.launch {
            Log.d("StudentViewModel", "Entrando a la función de obtener imágenes de perros")

            val images = try {
                val result = repository.getDogImages()
                Log.d("StudentViewModel", "Respuesta de la API: $result")
                result
            } catch (e: Exception) {
                Log.e("StudentViewModel", "Error al obtener imágenes: ${e.message}", e)
                emptyList()
            }

            if (images.isNotEmpty()) {
                val imagesFinal = images.shuffled()
                val studentsWithImages = localStudents.mapIndexed { index, student ->
                    val image = imagesFinal.getOrNull(index) ?: backupImage
                    student.copy(imageUrl = image)
                }

                studentsWithImages.forEach {
                    Log.d("StudentViewModel", "${it.fullName} → ${it.imageUrl}")
                }

                _students.value = studentsWithImages
            } else {
                Log.w("StudentViewModel", "No se obtuvieron imágenes, usando respaldo.")
            }
        }
    }
}
