package com.example.convidadosapp.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.convidadosapp.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest : GuestModel)

    @Update
    fun update(guest : GuestModel)

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int) : GuestModel

    @Query("SELECT * FROM Guest")
    fun getAll() : List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent() : List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent() : List<GuestModel>
}