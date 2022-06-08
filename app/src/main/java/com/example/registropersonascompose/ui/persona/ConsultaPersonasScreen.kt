package com.example.registropersonascompose.ui.persona

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.registropersonascompose.RowPersonas

@Composable
fun ConsultaPersonasScreen(
    navController: NavHostController,
    viewModel: PersonaViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Consulta de Personas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("RegistroPersona") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            val list = viewModel.persona.collectAsState(initial = emptyList())

            Button(onClick = { navController.navigate("ConsultaOcupacion") }) {
                Text(text = "Ocupaciones")
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(list.value) { persona ->
                    RowPersonas(
                        nombre = persona.nombres,
                        email = persona.email,
                        ocupacionId = persona.ocupacionId,
                        salario = persona.salario
                    )
                }
            }


        }
    }


}