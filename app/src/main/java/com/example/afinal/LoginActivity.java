package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView switchToSignUp;
    private UserDatabaseHelper dbHelper; // מחלקת מסד הנתונים

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // קישור רכיבי ה-UI
        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        switchToSignUp = findViewById(R.id.switch_to_sign_up);

        dbHelper = new UserDatabaseHelper(this); // אתחול מסד הנתונים

        // התחברות משתמש
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(email, password)) {
                // בדיקת אם המשתמש הוא מנהל
                if (dbHelper.isAdmin(email, password)) {
                    Toast.makeText(LoginActivity.this, "התחברת כמנהל!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class); // מסך ניהול
                    startActivity(intent);
                    finish(); // מבטיח שאין חזרה למסך הלוגין לאחר ההתחברות
                } else if (dbHelper.checkUser(email, password)) { // בדיקת משתמש רגיל
                    Toast.makeText(LoginActivity.this, "התחברת בהצלחה!", Toast.LENGTH_SHORT).show();
                    // מעבר למסך About Us
                    Intent intent = new Intent(LoginActivity.this, AboutUsActivity.class);
                    startActivity(intent);
                    finish(); // מבטיח שאין חזרה למסך הלוגין לאחר ההתחברות
                } else {
                    Toast.makeText(LoginActivity.this, "שם משתמש או סיסמה שגויים!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // מעבר למסך ההרשמה
        switchToSignUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
    }

    // בדיקת תקינות הקלט
    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            emailEditText.setError("יש להזין אימייל");
            emailEditText.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("יש להזין סיסמה");
            passwordEditText.requestFocus();
            return false;
        }

        return true;
    }
}
