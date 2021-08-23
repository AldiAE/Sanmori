package com.aldiavliar.sanmoriapps.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity

@Dao
interface PenegakDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(penegakEntity: PenegakEntity)
    @Update
    fun update(penegakEntity: PenegakEntity)
    @Delete
    fun delete(penegakEntity: PenegakEntity)
    @Query("SELECT * from penegakentity ORDER BY id ASC")
    fun getAllPenegak(): LiveData<List<PenegakEntity>>
    @Query("SELECT * FROM penegakentity where kategori = 'BANTARA' OR kategori = 'Bantara' OR kategori = 'bantara'")
    fun getPenegakBantara(): LiveData<List<PenegakEntity>>
    @Query("SELECT * FROM penegakentity where kategori = 'LAKSANA' OR kategori = 'Laksana' OR kategori = 'laksana'")
    fun getPenegakLaksana(): LiveData<List<PenegakEntity>>
}