package com.example.registropersonascompose.ui.ocupacion

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun RegistroOcupacionesScreen(
    navController: NavHostController,
    viewModel: OcupacionViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = viewModel.nombre,
            label = { Text(text = "Ocupacion") },
            onValueChange = { viewModel.nombre = it },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            }
        )
        Row(Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { /*TODO*/ }) {
                Modifier.padding(8.dp)
                Text(text = "Nuevo")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                viewModel.Guardar()
                navController.navigate("ConsultaOcupacion")
            }) {
                Modifier.padding(8.dp)
                Text(text = "Guardar")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {/*TODO*/ }) {
                Modifier.padding(8.dp)
                Text(text = "Eliminar")
            }
        }

    }

}