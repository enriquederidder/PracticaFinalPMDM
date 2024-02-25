package edr.practica.practicafinalpmdm.models

data class DatosViaje(
    var direccion: String,
    var fechaSalida: String,
    var horaSalida: String,
    var destino: String,
    var fechaRegreso: String,
    var horaRegreso: String,
    var numerodePasajeros: Int,
    var idViaje: String
) {
}
