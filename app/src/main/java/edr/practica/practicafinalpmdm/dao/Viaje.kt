package edr.practica.practicafinalpmdm.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viajes")
data class Viaje(
    @PrimaryKey(autoGenerate = true)
    val idViaje: Int = 0,
    val direccion: String,
    val fechaSalida: String,
    val horaSalida: String,
    val direccionRegreso: String,
    val fechaRegreso: String,
    val horaRegreso: String,
    val cantidadPasajeros: String
)