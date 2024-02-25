package edr.practica.practicafinalpmdm.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edr.practica.practicafinalpmdm.dao.Viaje
import edr.practica.practicafinalpmdm.dao.ViajeRepo

class ReservaViewModel : ViewModel() {

    private var new_item: Boolean = false
    private var _viajes: MutableLiveData<MutableList<DatosViaje>> = MutableLiveData(mutableListOf())
    val viajes: LiveData<MutableList<DatosViaje>>
        get() = _viajes
    private lateinit var _context: Context
    lateinit var viajeRepo: ViajeRepo
    private var idCounter: Int = 1


    private var _viajeSeleccionado = MutableLiveData<DatosViaje?>(
        DatosViaje("", "", "", "", "", "", 0, "")
    )
    var viajeSeleccionado = MutableLiveData<DatosViaje>(
        DatosViaje("", "", "", "", "", "", 0, "")
    )

    fun initialize(c: Context) {
        this._context = c
        this.viajeRepo = ViajeRepo(c)
        _viajes = MutableLiveData()
        val viajes = this.viajeRepo.getAllViajes()
        val datosViaje =
            viajes.map { viaje ->
                DatosViaje(
                    viaje.direccion,
                    viaje.fechaSalida,
                    viaje.horaSalida,
                    viaje.direccionRegreso,
                    viaje.fechaRegreso,
                    viaje.horaRegreso,
                    viaje.cantidadPasajeros.toInt(),
                    viaje.idViaje,
                )
            }.toMutableList()
        this._viajes.value = datosViaje
    }


    fun addViaje(datosViaje: DatosViaje) {
        val currentListViaje = _viajes.value ?: mutableListOf()
        datosViaje.idViaje = idCounter.toString()
        idCounter++

        currentListViaje.add(datosViaje)
        _viajes.value = currentListViaje

        viajeRepo.insert(
            Viaje(
                direccion = datosViaje.direccion,
                fechaSalida = datosViaje.fechaSalida,
                horaSalida = datosViaje.horaSalida,
                direccionRegreso = datosViaje.destino,
                fechaRegreso = datosViaje.fechaRegreso,
                horaRegreso = datosViaje.horaRegreso,
                cantidadPasajeros = datosViaje.numerodePasajeros.toString(),
                idViaje = datosViaje.idViaje
            )
        )
        setVaiajeSeleccionado(datosViaje)
    }

    fun removeViaje(datosViaje: DatosViaje) {
        val currentList = _viajes.value ?: mutableListOf()
        currentList.remove(datosViaje)
        _viajes.value = currentList

        viajeRepo.deleteByIdVieje(datosViaje.idViaje)
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
        this._viajeSeleccionado.value = DatosViaje("", "", "", "", "", "", 0, "")
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
                it.fechaRegreso =
                    viajeSeleccionado.value?.let { it.fechaRegreso } ?: it.fechaRegreso
                it.horaRegreso = viajeSeleccionado.value?.let { it.horaRegreso } ?: it.horaRegreso
                it.numerodePasajeros =
                    viajeSeleccionado.value?.let { it.numerodePasajeros } ?: it.numerodePasajeros

                this.updatelist()
                this.updateItem()
            }
        }

    }
}