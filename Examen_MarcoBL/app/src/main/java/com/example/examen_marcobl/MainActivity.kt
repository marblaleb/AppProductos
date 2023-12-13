package com.example.examen_marcobl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.examen1.data.DataSource.productos
import com.example.examen_marcobl.Datasource.ProductoState
import com.example.examen_marcobl.ViewModel.viewmodelproducto
import com.example.examen_marcobl.ui.theme.Examen_MarcoBLTheme
import kotlin.reflect.KFunction2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen_MarcoBLTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductosApp(viewmodelproducto())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosApp( viewmodel: viewmodelproducto, modifier: Modifier = Modifier) {

    val state by viewmodel.publicStateUi.collectAsState()





    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier .fillMaxSize()){

        Text(
            text = "Hola, soy alumno Marco Blanco Lebrón",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(20.dp, 50.dp)
        )

        ScreenApp(state=state, click = viewmodel::OnClick);

        Text(text = state.texto,textAlign= TextAlign.Center, modifier= Modifier .fillMaxWidth().height(130.dp) .background(Color.LightGray))





    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenApp(state: ProductoState, click: KFunction2<String, String, Unit>) {

    var nombre:String by rememberSaveable { mutableStateOf("")   }
    var precio by rememberSaveable { mutableStateOf("0")   }

    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
        .fillMaxWidth()
        .height(550.dp)
        .padding(15.dp)){

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier= Modifier
            .width(170.dp)
            .padding(15.dp)){

            OutlinedTextField(value = nombre, onValueChange = { nombre=it }, label={ Text(text = "Nombre")})

            OutlinedTextField(value = precio, onValueChange = { precio=it }, label={ Text(text = "Precio")})





            Button(onClick = { click(nombre, precio) }, modifier=Modifier .padding(0.dp, 20.dp)) {

                Text(text = "Add/Update Producto")

            }



        }

        LazyColumn(){

            items(state.lista){ producto ->

                CardProduct(nombre = producto.nombre, precio = producto.precio )




            }

        }
    }


}

@Composable
fun CardProduct(nombre: String, precio: Int){
    Card(modifier= Modifier .padding(8.dp)){

            Text(text = "Nombre: ${nombre}", textAlign = TextAlign.Center, modifier=Modifier .fillMaxSize() .background(
                Color.Cyan) .padding(8.dp))
            Text(text = "Precio: ${precio}", textAlign = TextAlign.Center, modifier=Modifier .fillMaxSize() .background(
                Color.Yellow) .padding(8.dp) )


    }
}


