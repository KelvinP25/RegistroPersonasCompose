package com.example.registropersonascompose.ui.persona

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.registropersonascompose.OcupacionesSpinner

@Composable
fun RegistroPersonasScreen(
    navController: NavHostController,
    viewModel: PersonaViewModel = hiltViewModel()
) {
    val ListaOcupaciones = listOf("Estudiante", "Profesor", "Administrativo", "Ingeniero")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Registro de Personas") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = viewModel.nombre,
                label = { Text(text = "Nombres") },
                onValueChange = { viewModel.nombre = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }
            )
            OutlinedTextField(
                value = viewModel.email,
                label = { Text(text = "Email") },
                onValueChange = { viewModel.email = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                }
            )

            OcupacionesSpinner()

            OutlinedTextField(
                value = viewModel.salario.toString(),
                label = { Text(text = "Salario") },
                onValueChange = {viewModel.salario = it.toFloat()},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
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
                    navController.navigate("ConsultaPersona")
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


}