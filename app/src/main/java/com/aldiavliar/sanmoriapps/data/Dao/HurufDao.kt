package com.aldiavliar.sanmoriapps.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aldiavliar.sanmoriapps.data.Entity.HurufEntity

@Dao
interface HurufDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hurufEntity: HurufEntity)
    @Update
    fun update(hurufEntity: HurufEntity)
    @Delete
    fun delete(hurufEntity: HurufEntity)
    @Query("SELECT * from hurufentity ORDER BY id ASC")
    fun getAllHuruf(): LiveData<List<HurufEntity>>
}