package com.example.examen_marcobl.Datasource

import com.example.examen_marcobl.Model.Producto
import com.example.examen1.data.DataSource.productos

data class ProductoState(

    val lista:ArrayList<Producto> = productos ,
    val nombre:String="",
    val precio:Int=0,
    val texto:String= "Todavía no se ha añadido ningún producto"


)
