package com.example.convidadosapp.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidadosapp.constants.DatabaseConstants
import com.example.convidadosapp.model.GuestModel

class GuestRepository(context: Context) { //Construtor fechado, a classe não poderá ser instânciada

    private val guestDataBase = GuestDatabase.getDatabase(context).guestDAO() //Quando a classe GuestRepository for instânciada esta instância também será

    fun insert(guest: GuestModel): Boolean {
        return try {
            guestDataBase.insert(guest)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            guestDataBase.update(guest)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun delete(guestId: Int): Boolean {
        return try {
            val guest = get(guestId)
            guestDataBase.delete(guest)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getAll(): List<GuestModel> {
        return try {
            guestDataBase.getAll()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getPresent(): List<GuestModel> {
        return try {
            guestDataBase.getPresent()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getAbsent(): List<GuestModel> {
        return try {
            guestDataBase.getAbsent()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun get(id: Int): GuestModel {
        return guestDataBase.get(id)
    }

}