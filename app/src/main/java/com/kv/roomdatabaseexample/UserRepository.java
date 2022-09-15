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

    public void insert(User user)
    {
        new Thread(()-> userDao.insert(user)).start();
        // Добавить уведомление о том что записано
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
