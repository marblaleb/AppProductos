package com.example.examen_marcobl.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.examen1.data.DataSource.productos
import com.example.examen_marcobl.Datasource.ProductoState
import com.example.examen_marcobl.Model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class viewmodelproducto: ViewModel() {

    private val stateUi= MutableStateFlow(ProductoState())
    val publicStateUi: StateFlow<ProductoState> = stateUi.asStateFlow()



    fun OnClick(nombre:String, precio:String){

        val precioint=precio.toInt();



        var nuevo:Producto= Producto(nombre, precioint)








        stateUi.update { producto ->

            var productos=producto.lista;

            var i=0;

            var modificado=false
            var textoin="";

            var precioA=0;




            for(valor in productos){


                if(valor.nombre.equals(nombre)){
                    modificado=true;
                    var indice=productos.indexOf(valor)
                    precioA=valor.precio;
                    productos.get(indice).nombre = nombre;
                    productos.get(indice).precio = precioint;


                }
            }

            if(!modificado) {

                productos.add(nuevo)
                textoin="Se ha añadido ${nombre} con precio ${precio}"

            }else{
                if(precioA==precioint){
                    textoin="No se ha modificado nada de ${nombre}"
                }else {
                    textoin =
                        "Se ha modificado ${nombre} con precio ${precio} cuando antes tenía ${precioA}"
                }
            }



            producto.copy(
                lista=productos,

                texto=textoin


            )
        }

    }



}