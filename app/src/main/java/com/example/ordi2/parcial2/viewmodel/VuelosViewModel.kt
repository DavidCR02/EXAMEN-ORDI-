package com.example.ordi2.parcial2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ordi2.R
import com.example.ordi2.parcial2.model.Destino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VuelosViewModel : ViewModel() {
    private val _vuelosNacionales = MutableStateFlow<List<Destino>>(emptyList())
    val vuelosNacionales: StateFlow<List<Destino>> = _vuelosNacionales.asStateFlow()

    private val _vuelosInternacionales = MutableStateFlow<List<Destino>>(emptyList())
    val vuelosInternacionales: StateFlow<List<Destino>> = _vuelosInternacionales.asStateFlow()

    init {
        cargarVuelosNacionales()
        cargarVuelosInternacionales()
    }

    private fun cargarVuelosNacionales() {
        val vuelos = listOf(
            Destino(
                id = "nacional_1",
                nombre = "Cancún",
                tipoVuelo = "Nacional",
                extras = "Ida y Vuelta",
                imagen = R.drawable.cancun
            ),
            Destino(
                id = "nacional_2",
                nombre = "Ciudad de México",
                tipoVuelo = "Nacional",
                extras = "Incluye Alojamiento",
                imagen = R.drawable.mexico
            ),
            Destino(
                id = "nacional_3",
                nombre = "Monterrey",
                tipoVuelo = "Nacional",
                extras = "Incluye Alojamiento y Desayunos",
                imagen = R.drawable.mty
            )
        )
        _vuelosNacionales.value = vuelos
    }

    private fun cargarVuelosInternacionales() {
        val vuelos = listOf(
            Destino(
                id = "internacional_1",
                nombre = "Seúl",
                tipoVuelo = "Internacional",
                extras = "Incluye Alojamiento",
                imagen = R.drawable.seul
            ),
            Destino(
                id = "internacional_2",
                nombre = "Nueva York",
                tipoVuelo = "Internacional",
                extras = "Incluye Alojamiento y Desayunos",
                imagen = R.drawable.usa
            ),
            Destino(
                id = "internacional_3",
                nombre = "París",
                tipoVuelo = "Internacional",
                extras = "Sencillo",
                imagen = R.drawable.paris
            )
        )
        _vuelosInternacionales.value = vuelos
    }
} 