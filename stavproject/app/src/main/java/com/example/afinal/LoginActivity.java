package com.example.afinal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView switchToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // קישור לקובץ ה-XML

        // קישור רכיבי ה-UI
        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        switchToSignUp = findViewById(R.id.switch_to_sign_up);

        // מאזין ללחיצה על כפתור ההתחברות
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(email, password)) {
                logInUser(email, password);
            }
        });

        // מעבר למסך ההרשמה
        switchToSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        // הסתרת מקלדת אוטומטית בעת לחיצה מחוץ לשדה טקסט
        setupKeyboardHiding(findViewById(R.id.login_button));
    }

    // פונקציה לבדיקת תקינות הקלט
    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }

        return true;
    }

    // פונקציה להתחברות (לדוגמה: חיבור ל-Firebase)
    private void logInUser(String email, String password) {
        // כאן ניתן להוסיף חיבור ל-Firebase או לשרת
        Toast.makeText(this, "Logging in with email: " + email, Toast.LENGTH_SHORT).show();
    }

    // פונקציה להסתרת מקלדת בעת לחיצה מחוץ לשדה
    private void setupKeyboardHiding(View rootView) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            boolean isKeyboardVisible = insets.isVisible(WindowInsetsCompat.Type.ime());
            if (!isKeyboardVisible) {
                rootView.clearFocus();
            }
            return insets;
        });
    }
}
