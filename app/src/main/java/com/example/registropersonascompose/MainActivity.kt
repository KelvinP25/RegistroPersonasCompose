package com.example.registropersonascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registropersonascompose.ui.theme.RegistroPersonasComposeTheme

var selectedOcupacion: String? = null

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroPersonasComposeTheme {
                MyApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "ConsultaPersona") {
            composable("ConsultaPersona") {
                ConsultaPersonasScreen(navController = navController)
            }
            composable("RegistroPersona") {
                RegistroPersonasScreen(navController = navController)
            }
            composable("ConsultaOcupacion") {
                ConsultaOcupacionesScreen(navController = navController)
            }
            composable("RegistroOcupacion") {
                RegistroOcupacionesScreen(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun DefaultPreview() {
    RegistroPersonasComposeTheme {
        Greeting("Android")
    }
}

@Composable
fun RowPersonas(nombre: String) {
    Row() {
        Text(text = nombre)
    }
}

@Composable
fun ConsultaPersonasScreen(navController: NavHostController) {
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

            val listNombres = listOf("Nachely", "Alvaro", "Vismar", "Felix")

            Button(onClick = { navController.navigate("ConsultaOcupacion") }) {
                Text(text = "Ocupaciones")
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listNombres) { nombre ->
                    RowPersonas(nombre = nombre)
                }
            }


        }
    }


}

@Composable
fun RegistroPersonasScreen(navController: NavHostController) {
    val ListaOcupaciones = listOf("Estudiante", "Profesor", "Administrativo", "Ingeniero")

    var person by rememberSaveable() {
        mutableStateOf("")
    }
    var nombre by rememberSaveable() {
        mutableStateOf("")
    }
    var ite by rememberSaveable() {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Registro de Personas") }
            )
        }
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = person,
                label = { Text(text = "Nombres") },
                onValueChange = { person = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }
            )
            OutlinedTextField(
                value = nombre,
                label = { Text(text = "Email") },
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                }
            )
            OcupacionesSpinner(ocupacion = ListaOcupaciones)
            OutlinedTextField(
                value = ite,
                label = { Text(text = "Salario") },
                onValueChange = {ite = it},
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
                Button(onClick = { navController.navigate("ConsultaPersona") }) {
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

@Composable
fun OcupacionesSpinner(ocupacion: List<String>) {

    var ocupacionText by remember {
        mutableStateOf("")
    }
    var expended by remember {
        mutableStateOf(false)
    }
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(Modifier
            .fillMaxWidth()
            .clickable {
                expended = !expended
            }
            .padding(6.dp)
        ) {
            Text(text = ocupacionText, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            DropdownMenu(expanded = expended, onDismissRequest = { expended = false }) {
                ocupacion.forEach { ocupacion ->
                    DropdownMenuItem(onClick = {

                        expended = false
                        ocupacionText = ocupacion.toString()
                        selectedOcupacion = ocupacion
                    }) {
                        Text(text = ocupacion.toString())
                    }
                }

            }
        }
    }
}

@Composable
fun ConsultaOcupacionesScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Consulta de Ocupaciones") }
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

            val listNombres = listOf("Estudiante", "Profesor", "Administrativo", "Ingeniero")

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listNombres) { nombre ->
                    RowPersonas(nombre = nombre)
                }
            }

        }
    }

}

@Composable
fun RegistroOcupacionesScreen(navController: NavHostController) {

    var person by rememberSaveable() {
        mutableStateOf("")
    }
    var nombre by rememberSaveable() {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Registro de Ocupaciones") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = person,
                label = { Text(text = "Id Ocupacion") },
                onValueChange = { person = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }
            )
            OutlinedTextField(
                value = nombre,
                label = { Text(text = "Nombre") },
                onValueChange = { nombre = it },
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
                Button(onClick = { navController.navigate("ConsultaOcupacion") }) {
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
