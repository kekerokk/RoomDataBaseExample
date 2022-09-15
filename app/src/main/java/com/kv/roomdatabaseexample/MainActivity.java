package com.kv.roomdatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kv.roomdatabaseexample.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding __binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        __binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(__binding.getRoot());

        UserRepository repository = new UserRepository(LocalDataBase.getInstance(getApplicationContext()).dao());

        __binding.button.setOnClickListener(view ->
        {
            String login = __binding.editTextTextEmailAddress.getText().toString();
            String password = __binding.editTextTextPassword.getText().toString();
            repository.insert(new User(login, password), new UserRepository.DataBaseListener() {
                @Override
                public <T> void notify(T data) {
                    __binding.getRoot().post(() -> Toast.makeText(getApplicationContext(), ""+data, Toast.LENGTH_SHORT).show());
                }
            });


        });

        __binding.button2.setOnClickListener(view -> {
            repository.getAll(new UserRepository.DataBaseListener() {
                @Override
                public <T> void notify(T data) {
                List<User> users = (List<User>) data;

                __binding.getRoot().post(() -> {
                    users.forEach(u-> {
                        String old = __binding.textView2.getText().toString();
                        __binding.textView2.setText("" + old + u.toString());
                    });
                });

                }
            });
        });


    }
}