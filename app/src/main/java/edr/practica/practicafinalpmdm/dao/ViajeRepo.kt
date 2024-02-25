package edr.practica.practicafinalpmdm.dao

import android.content.Context

class ViajeRepo(var context: Context) {
    private var _viajeDao: ViajeDao

    init {
        val database = AppDatabase.getInstance(context)
        _viajeDao = database.viajeDao()
    }

    fun insert(viaje: Viaje) {
        _viajeDao.insertViajeS(viaje)
    }

    fun deleteByIdVieje(idViaje: String) {
        _viajeDao.deleteByIDViaje(idViaje)
    }


    fun getAllViajes(): MutableList<Viaje> {
        return _viajeDao.getAllViajes().toMutableList()
    }
}