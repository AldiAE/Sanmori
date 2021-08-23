package com.aldiavliar.sanmoriapps.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aldiavliar.sanmoriapps.data.Entity.ATBEntity

@Dao
interface ATBDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(atbEntity: ATBEntity)
    @Update
    fun update(atbEntity: ATBEntity)
    @Delete
    fun delete(atbEntity: ATBEntity)
    @Query("SELECT * from atbentity ORDER BY id ASC")
    fun getAllAtb(): LiveData<List<ATBEntity>>
}