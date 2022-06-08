package com.example.registropersonascompose.ui.ocupacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.registropersonascompose.RowOcupaciones
import com.example.registropersonascompose.RowPersonas
import com.example.registropersonascompose.model.Ocupacion
import com.example.registropersonascompose.selectedOcupacion
import kotlinx.coroutines.selects.select


@Composable
fun ConsultaOcupacionesScreen(
    navController: NavHostController,
    viewModel: OcupacionViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Consulta Ocupaciones") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("RegistroOcupacion") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            val lista = viewModel.ocupaciones.collectAsState(initial = emptyList())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {

                items(lista.value) { ocupacion ->
                    RowOcupaciones(ocupacion = ocupacion)
                }
            }

        }
    }

}

