package com.aldiavliar.sanmoriapps.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity

@Dao
interface SiagaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(siagaEntity: SiagaEntity)
    @Update
    fun update(siagaEntity: SiagaEntity)
    @Delete
    fun delete(siagaEntity: SiagaEntity)
    @Query("SELECT * from siagaentity ORDER BY id ASC")
    fun getAllSiaga(): LiveData<List<SiagaEntity>>
    @Query("SELECT * FROM siagaentity where kategori = 'MULA' OR kategori = 'Mula' OR kategori = 'mula'")
    fun getSiagaMula(): LiveData<List<SiagaEntity>>
    @Query("SELECT * FROM siagaentity where kategori = 'BANTU' OR kategori = 'Bantu' OR kategori = 'bantu'")
    fun getSiagaBantu(): LiveData<List<SiagaEntity>>
    @Query("SELECT * FROM siagaentity where kategori = 'TATA' OR kategori = 'Tata' OR kategori = 'tata'")
    fun getSiagaTata(): LiveData<List<SiagaEntity>>
}