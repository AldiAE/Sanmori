package com.aldiavliar.sanmoriapps.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity

@Dao
interface PenggalangDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(penggalangEntity: PenggalangEntity)
    @Update
    fun update(penggalangEntity: PenggalangEntity)
    @Delete
    fun delete(penggalangEntity: PenggalangEntity)
    @Query("SELECT * from penggalangentity ORDER BY id ASC")
    fun getAllPenggalang(): LiveData<List<PenggalangEntity>>
    @Query("SELECT * FROM penggalangentity where kategori = 'RAMU' OR kategori = 'Ramu' OR kategori = 'ramu'")
    fun getPenggalangRamu(): LiveData<List<PenggalangEntity>>
    @Query("SELECT * FROM penggalangentity where kategori = 'RAKIT' OR kategori = 'Rakit' OR kategori = 'rakit'")
    fun getPenggalangRakit(): LiveData<List<PenggalangEntity>>
    @Query("SELECT * FROM penggalangentity where kategori = 'TERAP' OR kategori = 'Terap' OR kategori = 'terap'")
    fun getPengglangTerap(): LiveData<List<PenggalangEntity>>
}