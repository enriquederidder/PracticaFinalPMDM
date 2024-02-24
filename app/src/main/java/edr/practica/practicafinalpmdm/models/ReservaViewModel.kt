package edr.practica.practicafinalpmdm.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReservaViewModel : ViewModel() {

    private var new_item: Boolean = false
    private var _viajes: MutableLiveData<MutableList<DatosViaje>> = MutableLiveData(mutableListOf())
    val viajes: LiveData<MutableList<DatosViaje>>
        get()= _viajes

    private var _viajeSeleccionado = MutableLiveData<DatosViaje?>(
        DatosViaje("","","","","","",0))
    var viajeSeleccionado = MutableLiveData<DatosViaje>(
        DatosViaje("","","","","","",0))
    init {
        this._viajes.value?.add(DatosViaje(
            "Madrid",
            "7-5-2024",
            "10AM",
            "Valencia",
            "7-6-2024",
            "1AM",
            4))
        this._viajes.value?.add(DatosViaje(
            "Valencia",
            "4-7-2024",
            "10AM",
            "Madrid",
            "5-6-2024",
            "1AM",
            34))
    }

    fun addViaje(datosViaje: DatosViaje) {
        val currentListViaje = _viajes.value ?: mutableListOf()
        currentListViaje.add(datosViaje)
        _viajes.value = currentListViaje
        setVaiajeSeleccionado(datosViaje)
    }
    fun removeViaje(datosViaje: DatosViaje) {
        val currentList = _viajes.value ?: mutableListOf()
        currentList.remove(datosViaje)
        _viajes.value = currentList
    }

    fun setVaiajeSeleccionado(datosViaje: DatosViaje?) {
        _viajeSeleccionado.value = datosViaje
    }

    fun updatelist() {
        var values = this._viajes.value
        this._viajes.value = values
    }

    private fun updateItem() {
        this._viajeSeleccionado.value = this._viajeSeleccionado.value?.copy()
    }

    fun settSelected(item: DatosViaje) {
        this._viajeSeleccionado.value = item
        this.viajeSeleccionado.value = item.copy()
        this.new_item = false
    }

    fun settSelected(index: Int) {
        this._viajes.value?.let {
            this._viajeSeleccionado.value = it.get(index)
            this.viajeSeleccionado.value = it.get(index).copy()
            this.new_item = false
        }

    }

    fun create_new() {
        this._viajeSeleccionado.value = DatosViaje("","","","","","",0)
        this.viajeSeleccionado.value = this._viajeSeleccionado.value
        this.new_item = true
    }

    fun update() {
        if (new_item) {
            this.new_item = false
            this.viajeSeleccionado.value?.let {
                this._viajes.value?.add(it)
                this.updatelist()
            }

        } else {
            this._viajeSeleccionado.value?.let {
                it.direccion = viajeSeleccionado.value?.let { it.direccion } ?: it.direccion
                it.fechaSalida = viajeSeleccionado.value?.let { it.fechaSalida } ?: it.fechaSalida
                it.horaSalida = viajeSeleccionado.value?.let { it.horaSalida } ?: it.horaSalida
                it.destino = viajeSeleccionado.value?.let { it.destino } ?: it.destino
                it.fechaRegreso = viajeSeleccionado.value?.let { it.fechaRegreso } ?: it.fechaRegreso
                it.horaRegreso = viajeSeleccionado.value?.let { it.horaRegreso } ?: it.horaRegreso
                it.numerodePasajeros = viajeSeleccionado.value?.let { it.numerodePasajeros } ?: it.numerodePasajeros

                this.updatelist()
                this.updateItem()
            }
        }

    }
}