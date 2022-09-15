package com.kv.roomdatabaseexample;

import java.util.List;

public class UserRepository {

    private UserDao userDao;


    @FunctionalInterface
    public interface DataBaseListener {
        <T> void notify(T data);
    }

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void insert(User user, DataBaseListener DBL)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
                DBL.notify("Adding Complete");
            }
        }).start();
        // Добавить уведомление о том что записанo
    }

    public void delete(User user)
    {
        // добавить удаление и уведомление о удалении
    }

    public void getAll(DataBaseListener listener)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> result = userDao.getAll();
                listener.notify(result);
            }
        }).start();
    }
}
