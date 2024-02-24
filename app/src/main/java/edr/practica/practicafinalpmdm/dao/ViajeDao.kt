package edr.practica.practicafinalpmdm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ViajeDao {
    @Query("SELECT * FROM viajes")
    fun getAllViajes(): List<Viaje>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertViajeS(viaje: Viaje)

    @Query("DELETE FROM viajes WHERE idViaje = :idViewViaje")
    fun deleteByidViaje(idViewViaje: String)
}