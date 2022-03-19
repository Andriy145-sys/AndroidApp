package com.example.coolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coolapp.model.Users;


public class MainActivity extends AppCompatActivity {
    Users users = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Sign up action
    public void GoToLogin(View view) {
        setContentView(R.layout.activity_login);
    }

    public void SignUp(View view) {

        EditText username = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        EditText confirmPassword = (EditText) findViewById(R.id.editTextTextConfirmPassword);

        //Сheck that all fields are filled
        if (username.getText().toString().equals("") ||
                email.getText().toString().equals("") ||
                password.getText().toString().equals("") ||
                confirmPassword.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Будь ласка заповніть всі поля", Toast.LENGTH_SHORT).show();
        } else {
            //Check to valid min symbols to password
            if (password.getText().toString().length() < 8) {
                Toast.makeText(MainActivity.this, "Пароль повинен містити мінімум 8 символів", Toast.LENGTH_SHORT).show();
            } else {
                //Create user
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    users.setEmail(email.getText().toString());
                    users.setUserName(username.getText().toString());
                    users.setPassword(password.getText().toString());

                    setContentView(R.layout.activity_login);
                    Toast.makeText(MainActivity.this, "Користвача успішно створено", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Паролі не збігаються", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    //Login action
    public void GoToSignUp(View view) {
        setContentView(R.layout.activity_main);
    }

    public void Login(View view) {
        EditText email = (EditText) findViewById(R.id.loginEmail);
        EditText password = (EditText) findViewById(R.id.loginPassword);
        String userEmail = users.getEmail();
        String userPassword = users.getPassword();
        if (userEmail.equals("")) {
            Toast.makeText(MainActivity.this, "Ви ще не створили аккаунт", Toast.LENGTH_SHORT).show();
        } else {
            if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
                Toast.makeText(MainActivity.this, "Будь ласка заповніть всі поля", Toast.LENGTH_SHORT).show();
            } else {
                if (email.getText().toString().equals(userEmail.toString()) && password.getText().toString().equals(userPassword.toString())) {
                    Toast.makeText(MainActivity.this, "Вхід успішно виконано", Toast.LENGTH_SHORT).show();
                    this.GoToProfile(view);
                } else {
                    Toast.makeText(MainActivity.this, "Невірно введено емайл або пароль", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    //Profile action
    public void GoToProfile(View view) {
        setContentView(R.layout.activity_profile);
        this.getUserData();
    }

    public void getUserData() {
        EditText emailEdit = (EditText) findViewById(R.id.userEmailEdit);
        EditText usernameEdit = (EditText) findViewById(R.id.userNameEdit);
        TextView emailView = (TextView) findViewById(R.id.userEmailView);
        TextView usernameView = (TextView) findViewById(R.id.userNameView);
        emailEdit.setText(users.getEmail());
        emailView.setText(users.getEmail());
        usernameEdit.setText(users.getUserName());
        usernameView.setText(users.getUserName());
    }

    public void updateUserData(View view) {
        EditText emailEdit = (EditText) findViewById(R.id.userEmailEdit);
        EditText usernameEdit = (EditText) findViewById(R.id.userNameEdit);

        //Update user data
        users.setUserName(usernameEdit.getText().toString());
        users.setEmail(emailEdit.getText().toString());

        this.getUserData();
    }

    public void updateUserPassword(View view) {
        String oldPassword = users.getPassword();
        EditText currentPassword = (EditText) findViewById(R.id.userCurrentPassword);
        EditText password = (EditText) findViewById(R.id.userNewPassword);
        EditText confirmPassword = (EditText) findViewById(R.id.userConfirmPassword);

        //Check to valid old password
        if (oldPassword.equals(currentPassword.getText().toString())) {
            //Check to valid min symbols to password
            if (password.getText().toString().length() >= 8) {
                ////Check to valid match password
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    //Update password
                    users.setPassword(password.getText().toString());
                    Toast.makeText(MainActivity.this, "Пароль успішно змінено", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Паролі не збігаються", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Пароль повинен містити мінімум 8 символів", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Старий пароль введено невірно", Toast.LENGTH_SHORT).show();
        }
    }

    public void logout(View view) {
        setContentView(R.layout.activity_login);
    }
}