package edr.practica.practicafinalpmdm.models

data class DatosViaje(
    var direccion: String,
    var fechaSalida: String,
    var horaSalida: String,
    var destino: String,
    var fechaRegreso: String,
    var horaRegreso: String,
    var numerodePasajeros: Int,
    val toString: String,
) {
    companion object {
        // Static variable to keep track of the last assigned ID
        private var lastAssignedId = 0

        // Function to generate the next ID
        private fun generateNextId(): Int {
            lastAssignedId++
            return lastAssignedId
        }
    }

    // Property to hold the auto-increment ID
    val idViaje: Int = generateNextId()
}
