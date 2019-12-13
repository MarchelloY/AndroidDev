package com.marchello.lab5.dao;

import com.marchello.lab5.model.Person;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PersonDao {
    @Insert
    void insert(Person person);

    @Query("SELECT * FROM persons WHERE name LIKE :searchParam OR surname LIKE :searchParam")
    List<Person> getAllPersonWithSearchParam(String searchParam);
}
