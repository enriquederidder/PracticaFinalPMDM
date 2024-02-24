package edr.practica.practicafinalpmdm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClienteDao {
    @Query("SELECT * FROM clientes")
    fun getAllClientes(): List<Cliente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCliente(cliente: Cliente)

    @Query("DELETE FROM clientes WHERE numeroTelefono = :phoneNumber")
    fun deleteClienteByPhoneNumber(phoneNumber: String)
}
