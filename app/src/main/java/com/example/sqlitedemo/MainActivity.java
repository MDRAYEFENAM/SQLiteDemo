package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlitedemo.adapter.DBAdapter;
import com.example.sqlitedemo.adapter.DisplayAdapter;
import com.example.sqlitedemo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        EditText nameEditText, phoneEditText, emailEditText, cgpaEditText;
        String image= "https://avatars3.githubusercontent.com/u/41743850?s=400&u=2e15c471955787533eda17f7cacf91083221582a&v=4";

        DBAdapter adapter;
        ListView listView;
        DisplayAdapter displayAdapter;
        List<Student> studentList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            nameEditText = findViewById(R.id.nameET);
            phoneEditText = findViewById(R.id.phoneET);
            emailEditText = findViewById(R.id.emailET);
            cgpaEditText = findViewById(R.id.cgpaET);

            adapter =  new DBAdapter(this);
            listView = findViewById(R.id.list);
            studentList = adapter.getAllData();

            displayAdapter = new DisplayAdapter(this, studentList);
            listView.setAdapter(displayAdapter);

        }

        public void saveData(View view) {
            boolean error = false;

            String name = nameEditText.getText().toString();
            if(name.isEmpty()){
                nameEditText.setError("Name can't be empty");
                error = true;
            }else if (name.length()<6) {
                nameEditText.setError("User name should be more than 6 character");
                error = true;
            }else {
                //save
                error = false;
            }

            String phoneNo = phoneEditText.getText().toString();
            if (phoneNo.isEmpty()) {
                phoneEditText.setError("Phone no can't be empty");
                error = true;
            }else if (phoneNo.length()==11) {
                if (phoneNo.startsWith("013") || phoneNo.startsWith("014") || phoneNo.startsWith("015") || phoneNo.startsWith("016") || phoneNo.startsWith("017") || phoneNo.startsWith("018") || phoneNo.startsWith("019")){
                    //save
                    error = false;
                } else {
                    phoneEditText.setError("Phone no is not valid");
                    error = true;
                }
            }else {
                phoneEditText.setError("Please enter valid number with 11 digited");
                error = true;
            }

            String email = emailEditText.getText().toString();
            if(email.isEmpty()){
                emailEditText.setError("Error can't be empty");
                error = true;
            }else {
                //save
                error = false;
            }

            String cgpaStr = cgpaEditText.getText().toString();
            Float cgpa = null;
            if(cgpaStr.isEmpty()){
                cgpaEditText.setError("CGPA can't be empty");
                error = true;
            }else {
                cgpa = Float.valueOf(cgpaStr.toString());
                if(cgpa<=4){
                    error = false;
                }else {
                    cgpaEditText.setError("CGPA should be within 4.00");
                    error = true;
                }
            }

            if(error) {
                Toast.makeText(getApplicationContext(), "Data is not correct", Toast.LENGTH_SHORT).show();
            }else {
                //save
                clearData();
                Student aStudent = new Student(name,image,phoneNo,email,cgpa);
                adapter.insertIntoDB(aStudent);
                studentList.clear();
                Toast.makeText(getApplicationContext(), "Data is save successfully", Toast.LENGTH_SHORT).show();

            }
        }

    private void clearData() {
            emailEditText.setText(null);  // field clear method after save
            nameEditText.setText(null);
            phoneEditText.setText(null);
            cgpaEditText.setText(null);
    }

    public void showData(View view) {
        }
}
