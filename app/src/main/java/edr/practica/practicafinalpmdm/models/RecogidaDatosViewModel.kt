package edr.practica.practicafinalpmdm.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecogidaDatosViewModel : ViewModel() {

    private var new_item: Boolean = false
    private var _datos: MutableLiveData<MutableList<DatosCliente>> = MutableLiveData(mutableListOf())
    val datos: LiveData<MutableList<DatosCliente>>
        get() = _datos

    private var _clienteSeleccionado = MutableLiveData<DatosCliente?>(DatosCliente("","",""))
    var clienteSeleccionado = MutableLiveData<DatosCliente>(DatosCliente("","",""))

    init {
        this._datos.value?.add(DatosCliente("Enrique", "enriqu@e", "1234124"))
        this._datos.value?.add(DatosCliente("Miguel", "Miguel@e", "5765675"))


    }

    fun addCliente(datosCliente: DatosCliente) {
        val currentList = _datos.value ?: mutableListOf()
        currentList.add(datosCliente)
        _datos.value = currentList
        setClienteSeleccionado(datosCliente) // Set the clienteSeleccionado after adding the client
    }
    fun removeCliente(datosCliente: DatosCliente) {
        val currentList = _datos.value ?: mutableListOf()
        currentList.remove(datosCliente)
        _datos.value = currentList
    }

    fun setClienteSeleccionado(datosCliente: DatosCliente?) {
        _clienteSeleccionado.value = datosCliente
    }

    fun updatelist() {
        var values = this._datos.value
        this._datos.value = values
    }

    private fun updateItem() {
        this._clienteSeleccionado.value = this._clienteSeleccionado.value?.copy()
    }

    fun settSelected(item: DatosCliente) {
        this._clienteSeleccionado.value = item
        this.clienteSeleccionado.value = item.copy()
        this.new_item = false
    }

    fun settSelected(index: Int) {
        this._datos.value?.let {
            this._clienteSeleccionado.value = it.get(index)
            this.clienteSeleccionado.value = it.get(index).copy()
            this.new_item = false
        }

    }

    fun create_new() {
        this._clienteSeleccionado.value = DatosCliente("", "", "")
        this.clienteSeleccionado.value = this._clienteSeleccionado.value
        this.new_item = true
    }

    fun update() {
        if (new_item) {
            this.new_item = false
            this.clienteSeleccionado.value?.let {
                this._datos.value?.add(it)
                this.updatelist()
            }

        } else {
            this._clienteSeleccionado.value?.let {
                it.nombre = clienteSeleccionado.value?.let { it.nombre } ?: it.nombre
                it.email = clienteSeleccionado.value?.let { it.email } ?: it.email
                it.numeroTelefone = clienteSeleccionado.value?.let { it.numeroTelefone } ?: it.numeroTelefone


                this.updatelist()
                this.updateItem()
            }
        }

    }
}
