package com.merive.securepass.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface PasswordDao {

    @Insert(onConflict = IGNORE)
    void insertItem(Password item);

    @Query("UPDATE password SET " +
            "name = :name, " +
            "login = :login, " +
            "password = :password, " +
            "description = :description " +
            "WHERE name = :nameBefore")
    void updateItem(String nameBefore, String name, String login, String password, String description);

    @Query("SELECT login FROM password WHERE name = :name")
    String getLoginByName(String name);

    @Query("SELECT password FROM password WHERE name = :name")
    String getPasswordByName(String name);

    @Query("SELECT description FROM password WHERE name = :name")
    String getDescriptionByName(String name);

    @Query("DELETE FROM password WHERE name = :name")
    void deleteByName(String name);

    @Query("SELECT * FROM password")
    List<Password> getAll();

    @Query("DELETE FROM Password")
    void deleteAll();
}