package com.kv.roomdatabaseexample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {User.class}, version = 1)
public abstract class LocalDataBase extends RoomDatabase {
    public abstract UserDao dao();

    private static volatile LocalDataBase INSTANCE;

    public static LocalDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDataBase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDataBase.class, "DB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}