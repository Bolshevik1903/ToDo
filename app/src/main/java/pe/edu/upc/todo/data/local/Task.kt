package pe.edu.upc.todo.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Task(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: Int
)
