package edr.practica.practicafinalpmdm.dao

import android.content.Context

class ClienteRepo(var context: Context) {
    private var _clienteDao: ClienteDao

    init {
        val database = AppDatabase.getInstance(context)
        _clienteDao = database.clienteDao()
    }

    fun insert(cliente: Cliente) {
        _clienteDao.insertCliente(cliente)
    }

    fun deleteByPhoneNumber(phoneNumber: String) {
        _clienteDao.deleteClienteByPhoneNumber(phoneNumber)
    }


    fun getAllClientes(): MutableList<Cliente> {
        return _clienteDao.getAllClientes().toMutableList()
    }
}
