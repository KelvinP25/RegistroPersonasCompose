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
        //ConsultaPersonasScreen()
        RegistroPersonasScreen()
        //RegistroOcupacionesScreen()
        //ConsultaOcupacionesScreen()
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
fun ConsultaPersonasScreen() {
    Column(modifier = Modifier.padding(16.dp)) {

        val listNombres = listOf("Nachely", "Alvaro", "Vismar", "Felix")

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(listNombres) { nombre ->
                RowPersonas(nombre = nombre)
            }
        }
        Button(onClick = {/*TODO*/ }) {
            Text(text = "Ocupaciones")
        }


    }

}

@Composable
fun RegistroPersonasScreen() {
    val ListaOcupaciones = listOf("Estudiante", "Profesor", "Administrativo", "Ingeniero")
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = "",
            label = { Text(text = "Nombres") },
            onValueChange = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            }
        )
        OutlinedTextField(
            value = "",
            label = { Text(text = "Email") },
            onValueChange = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            }
        )
        OcupacionesSpinner(ocupacion = ListaOcupaciones)
        OutlinedTextField(
            value = "",
            label = { Text(text = "Salario") },
            onValueChange = { /*TODO*/ },
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
            Button(onClick = {/*TODO*/ }) {
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
fun ConsultaOcupacionesScreen() {
    Column(modifier = Modifier.padding(16.dp)) {

        val listNombres = listOf("Nachely", "Alvaro", "Vismar", "Felix")

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(listNombres) { nombre ->
                RowPersonas(nombre = nombre)
            }
        }
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {/*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {

        }

    }
}

@Composable
fun RegistroOcupacionesScreen() {

    var person by rememberSaveable(){
        mutableStateOf("")
    }

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
            value = person,
            label = { Text(text = "Nombre") },
            onValueChange = { person = it },
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
            Button(onClick = {/*TODO*/ }) {
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