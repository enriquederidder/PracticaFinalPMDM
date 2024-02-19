package edr.practica.practicafinalpmdm.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edr.practica.practicafinalpmdm.models.DatosViaje

class ReservaViewModel: ViewModel() {

    private val _viajes: MutableLiveData<MutableList<DatosViaje>> = MutableLiveData()
    val viajes: LiveData<MutableList<DatosViaje>> = _viajes

    var viajeSelected: DatosViaje? = null

    init {
        _viajes.value = mutableListOf(
            DatosViaje(
                "Salida 1",
                "2024-02-20",
                "08:00 AM",
                "Destino 1",
                "2024-02-25",
                "06:00 PM",
                2
            )
        )
    }

    fun addViaje(datosViaje: DatosViaje) {
        val currentList = _viajes.value ?: mutableListOf()
        currentList.add(datosViaje)
        _viajes.value = currentList
    }

    val getCaractera: LiveData<MutableList<DatosViaje>>
        get() = viajes

    var getAndSetselected: DatosViaje?
        get() = viajeSelected
        set(item) {
            viajeSelected = item
        }

}