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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registropersonascompose.model.Ocupacion
import com.example.registropersonascompose.ui.ocupacion.ConsultaOcupacionesScreen
import com.example.registropersonascompose.ui.ocupacion.OcupacionViewModel
import com.example.registropersonascompose.ui.ocupacion.RegistroOcupacionesScreen
import com.example.registropersonascompose.ui.persona.ConsultaPersonasScreen
import com.example.registropersonascompose.ui.persona.PersonaViewModel
import com.example.registropersonascompose.ui.persona.RegistroPersonasScreen
import com.example.registropersonascompose.ui.theme.RegistroPersonasComposeTheme
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint

var selectedOcupacion: String? = null

@AndroidEntryPoint
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
fun RowPersonas(nombre: String, email: String, ocupacionId: Int, salario : Float) {
    Row() {
        Column{
            Text(text = nombre)
            Text(text = email)
            Text(text = ocupacionId.toString())
            Text(text = salario.toString())
        }

    }
}

@Composable
fun RowOcupaciones(ocupacion: Ocupacion) {
    Row() {
        Text(text = ocupacion.nombres)
    }
}


@Composable
fun OcupacionesSpinner(
    viewModel: OcupacionViewModel = hiltViewModel(),
    viewModelP: PersonaViewModel = hiltViewModel()
) {

    val lista = viewModel.ocupaciones.collectAsState(initial = emptyList())
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
                lista.value.forEach { ocupacion ->
                    DropdownMenuItem(onClick = {
                        viewModelP.ocupacionId = ocupacion.ocupacionId
                        expended = false
                        ocupacionText = ocupacion.nombres
                        selectedOcupacion = ocupacion.nombres
                    }) {
                        Text(text = ocupacion.nombres)
                    }
                }

            }
        }
    }
}



