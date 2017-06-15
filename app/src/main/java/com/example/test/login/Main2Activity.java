package com.example.test.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    EditText name;
    TextView tv;
    DatabaseAdapter helper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = (EditText) findViewById(R.id.editText3);
        tv = (TextView) findViewById(R.id.textView3);
        helper2 = new DatabaseAdapter(this);
    }

    public void showPassword(View view) {
        String s1 = name.getText().toString();
        String s2 = helper2.getData(s1);
        tv.setText("The password is "+s2);

    }

}
