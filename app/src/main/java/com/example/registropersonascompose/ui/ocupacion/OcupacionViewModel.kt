package com.example.registropersonascompose.ui.ocupacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registropersonascompose.data.Repository.OcupacionRepository
import com.example.registropersonascompose.model.Ocupacion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val ocupacionRepository: OcupacionRepository
) : ViewModel() {
    var nombre by mutableStateOf("")

    var ocupaciones = ocupacionRepository.getList()
    private set
    fun Guardar(){
        viewModelScope.launch {
            ocupacionRepository.insertar(
                Ocupacion(
                    nombres = nombre
                )
            )
        }
    }
}