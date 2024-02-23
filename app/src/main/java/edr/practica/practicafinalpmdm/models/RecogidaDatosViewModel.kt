package edr.practica.practicafinalpmdm.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecogidaDatosViewModel: ViewModel() {
    private val _datos: MutableLiveData<MutableList<DatosCliente>> = MutableLiveData()
    val datos: LiveData<MutableList<DatosCliente>> = _datos

    private val _clienteSeleccionado: MutableLiveData<DatosCliente?> = MutableLiveData()
    val clienteSeleccionado: LiveData<DatosCliente?> = _clienteSeleccionado

    init {
        _datos.value = mutableListOf(
            DatosCliente(
                "John",
                "tesemail@email.com",
                "345345345"
            )
        )
    }

    fun addCliente(datosCliente: DatosCliente) {
        val currentList = _datos.value ?: mutableListOf()
        currentList.add(datosCliente)
        _datos.value = currentList
        setClienteSeleccionado(datosCliente) // Set the clienteSeleccionado after adding the client
    }

    fun setClienteSeleccionado(datosCliente: DatosCliente?) {
        _clienteSeleccionado.value = datosCliente
    }
}
