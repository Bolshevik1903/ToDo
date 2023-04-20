package pe.edu.upc.todo.data.local

import androidx.room.*
import pe.edu.upc.todo.data.entities.Task

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("select * from Task where name=:name")
    fun fetchByName(name: String) : List<Task>

    //@Query("select * from Task where status=:status")
    //fun fetchByStatus(name: Int): List<Task>

    @Delete
    fun delete(task: Task)
}