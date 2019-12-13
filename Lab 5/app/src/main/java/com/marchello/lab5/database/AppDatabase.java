package com.marchello.lab5.database;

import com.marchello.lab5.dao.PersonDao;
import com.marchello.lab5.model.Person;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao getPersonDao();
}