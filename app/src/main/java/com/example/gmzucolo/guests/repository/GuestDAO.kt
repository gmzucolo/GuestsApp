package com.example.gmzucolo.guests.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gmzucolo.guests.model.GuestModel

@Dao
interface GuestDAO {
    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun getOne(id: Int): GuestModel

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getAllPresents(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAllAbsents(): List<GuestModel>
}