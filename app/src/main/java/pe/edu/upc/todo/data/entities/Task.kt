package pe.edu.upc.todo.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "status")
    val status: Int
)
