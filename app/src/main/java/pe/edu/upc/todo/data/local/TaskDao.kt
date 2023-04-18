package pe.edu.upc.todo.data.local

import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("select * from Task where name=:name")
    fun fetchByName(name: String)

    @Query("select * from Task where status=:status")
    fun fetchByStatus(name: Int)

    @Delete
    fun delete(task: Task)
}