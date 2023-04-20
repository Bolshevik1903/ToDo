package pe.edu.upc.todo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.upc.todo.data.entities.Task

@Database (entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: AppDatabase?= null

        fun getInstance(context: Context): AppDatabase {
            INSTANCE = Room.databaseBuilder(context,
                AppDatabase::class.java,
                "todo.db")
                .allowMainThreadQueries().build()
            return INSTANCE as AppDatabase
        }
    }
}