package com.example.test.login;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    DatabaseAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        helper = new DatabaseAdapter(this);


    }

    public void addUser(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        long id = helper.insertdata(user, pass);
        if (id < 0) {
            Message.message(this, "Unsuccessful Signup");
        } else {
            Message.message(this, "successful");
        }


    }

    public void viewData(View v) {
        String data = helper.getAllData();
        Message.message(this,data);
    }
}
