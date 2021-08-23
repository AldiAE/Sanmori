package com.aldiavliar.sanmoriapps.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity

@Dao
interface PandegaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pandegaEntity: PandegaEntity)
    @Update
    fun update(pandegaEntity: PandegaEntity)
    @Delete
    fun delete(pandegaEntity: PandegaEntity)
    @Query("SELECT * from pandegaentity ORDER BY id ASC")
    fun getAllPandega(): LiveData<List<PandegaEntity>>
    @Query("SELECT * FROM pandegaentity where kategori = 'PANDEGA' OR kategori = 'Pandega' OR kategori = 'pandega'")
    fun getPandega(): LiveData<List<PandegaEntity>>
}