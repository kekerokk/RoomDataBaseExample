package com.kv.roomdatabaseexample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Optional;

@Dao
public interface UserDao {

    @Query("Select * from User")
    List<User> getAll();

    @Query("Select * from User where id = :id")
    Optional<User> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User entity);

    @Delete
    void delete(User entity);
}