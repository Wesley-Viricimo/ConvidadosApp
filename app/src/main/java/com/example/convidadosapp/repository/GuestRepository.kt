package com.example.convidadosapp.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidadosapp.constants.DatabaseConstants
import com.example.convidadosapp.model.GuestModel

class GuestRepository private constructor(context: Context) { //Construtor fechado, a classe não poderá ser instânciada

    private val guestDataBase = GuestDatabase(context) //Quando a classe GuestRepository for instânciada esta instância também será

    companion object {

        // Singleton - Conceito de singleton é basicamente controlar o número de instâncias de uma classe
        private lateinit var repository : GuestRepository

        fun getInstance(context: Context) : GuestRepository {
            if (!Companion::repository.isInitialized) { //verifica se o repositório está inicializado, caso não estiver irá inicializar -> Singleton para controlar intância da classe
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest : GuestModel) : Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert("Guest", null, values) //Primeiro parâmetro é  nome da tabela, segundo é a coluna que permitirá nulos e o terceiro são os valores da tabela
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun update(guest : GuestModel) : Boolean {

        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?" //Serão atualizados os registros onde o id for igual ao id informado por parâmetro
            val args = arrayOf(guest.id.toString()) //Através dos argumentos que serão atualizados os registros, ou seja, será atualizado todos os registros onde o id for igual ao id informado pelo usuário

            db.update(DatabaseConstants.GUEST.TABLE_NAME, values, selection, args) //Para executar um update é necessário informar como argumento o nome da tabela, os valores que serão atualizados, a seleção e os arumentos
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }

    fun delete(guestId : Int) : Boolean {

        return try {
            val db = guestDataBase.writableDatabase //writableDatabase serve para escrever no banco de dados, ou seja, operações de insert, update e delete

            val selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?" //Serão atualizados os registros onde o id for igual ao id informado por parâmetro
            val args = arrayOf(guestId.toString()) //Através dos argumentos que serão atualizados os registros, ou seja, será atualizado todos os registros onde o id for igual ao id informado pelo usuário

            db.delete(DatabaseConstants.GUEST.TABLE_NAME, selection, args) //Para executar um update é necessário informar como argumento o nome da tabela, os valores que serão atualizados, a seleção e os arumentos
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getAll() : List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase //readableDatabase serve para operações de leitura do banco de dados, ou seja, select
            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.ID,
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME, //Nome da tabela
                projection,                          //Quais as colunas que serão selecionadas
                null,                       //Selection nulo
                null,                    //Argumentos de seleção nulos
                null,                       //Group by nulo
                null,                        //Cláusulá having nula
                null)                       //Order by nulo

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun getPresent() : List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase //readableDatabase serve para operações de leitura do banco de dados, ou seja, select
            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.ID,
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            //Da pra fazer um select puro também
            //val cursor2 = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            val selection = DatabaseConstants.GUEST.COLUMNS.PRESENCE + " = ?" //Selection = where -> Selecionar os convidados onde a presença
            val args = arrayOf("1")                                           // = 1

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME, //Nome da tabela
                projection,                         //Quais as colunas que serão selecionadas
                selection,
                args,
                null,                       //Group by nulo
                null,                        //Cláusulá having nula
                null)                       //Order by nulo

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun getAbsent() : List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase //readableDatabase serve para operações de leitura do banco de dados, ou seja, select
            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.ID,
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            //Da pra fazer um select puro também
            //val cursor2 = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            val selection = DatabaseConstants.GUEST.COLUMNS.PRESENCE + " = ?" //Selection = where -> Selecionar os convidados onde a presença
            val args = arrayOf("0")                                           // = 0

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME, //Nome da tabela
                projection,                         //Quais as colunas que serão selecionadas
                selection,
                args,                               //Argumentos de seleção nulos
                null,                       //Group by nulo
                null,                        //Cláusulá having nula
                null)                       //Order by nulo

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun get(id: Int) : GuestModel? {
        var guest : GuestModel? = null

        try {
            val db = guestDataBase.readableDatabase //readableDatabase serve para operações de leitura do banco de dados, ou seja, select
            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.ID,
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?" //Serão atualizados os registros onde o id for igual ao id informado por parâmetro
            val args = arrayOf(id.toString()) //Através dos argumentos que serão atualizados os registros, ou seja, será atualizado todos os registros onde o id for igual ao id informado pelo usuário

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME, //Nome da tabela
                projection,                         //Quais as colunas que serão selecionadas
                selection,
                args,
                null,                       //Group by nulo
                null,                        //Cláusulá having nula
                null)                       //Order by nulo

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE))

                    guest = (GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return guest
        }
        return guest
    }

}