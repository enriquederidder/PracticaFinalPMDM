package edr.practica.practicafinalpmdm.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RecogidaDatosViewModel {
    private val _datos: MutableLiveData<MutableList<DatosCliente>> = MutableLiveData()
    val datos: LiveData<MutableList<DatosCliente>> = _datos

    var datoselected: DatosCliente? = null

    init {
        _datos.value = mutableListOf(
            DatosCliente(
                "John",
                "tesemail@email.com",
                "123456798"
            )
        )
    }

    fun addViaje(DatosCliente: DatosCliente) {
        val currentList = _datos.value ?: mutableListOf()
        currentList.add(DatosCliente)
        _datos.value = currentList
    }
}