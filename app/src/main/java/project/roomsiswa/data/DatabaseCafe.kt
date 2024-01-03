package project.roomsiswa.data

import Menu
import Pesanan
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Menu::class, Pesanan::class], version = 1, exportSchema = false)
abstract class DatabaseCafe : RoomDatabase(){
    abstract fun menuDao() : MenuDao
    abstract fun pesananDao() : PesananDao

    companion object{
        @Volatile
        private var Instance:DatabaseCafe? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): DatabaseCafe {
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context, DatabaseCafe::class.java, "database_cafe_ody")
                    .build().also { Instance=it }
            })
        }
    }

}
