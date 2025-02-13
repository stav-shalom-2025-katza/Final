package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;
    private UserDatabaseHelper dbHelper; // מחלקת מסד הנתונים

    // סיסמת המנהל הקבועה
    private static final String ADMIN_PASSWORD = "idan2709";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEditText = findViewById(R.id.signup_email);
        passwordEditText = findViewById(R.id.signup_password);
        confirmPasswordEditText = findViewById(R.id.signup_confirm_password);
        signUpButton = findViewById(R.id.signup_button);

        dbHelper = new UserDatabaseHelper(this); // אתחול מסד הנתונים

        signUpButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (validateInputs(email, password, confirmPassword)) {
                boolean isAdmin = password.equals(ADMIN_PASSWORD); // בודק אם הסיסמה של המנהל

                if (dbHelper.addUser(email, password, isAdmin)) { // הוספת משתמש למסד הנתונים
                    Toast.makeText(SignUpActivity.this, "נרשמת בהצלחה!", Toast.LENGTH_SHORT).show();

                    // אם זה המנהל, נעביר אותו למסך הניהול
                    if (isAdmin) {
                        Intent intent = new Intent(SignUpActivity.this, AdminDashboardActivity.class);
                        startActivity(intent);
                    } else {
                        // אם זה לא המנהל, נעביר למסך About Us
                        Intent intent = new Intent(SignUpActivity.this, AboutUsActivity.class);
                        startActivity(intent);
                    }
                    finish(); // סגירת מסך ההרשמה
                } else {
                    Toast.makeText(SignUpActivity.this, "האימייל כבר קיים במערכת!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // פונקציה לבדיקת תקינות הקלט
    private boolean validateInputs(String email, String password, String confirmPassword) {
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
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("הסיסמאות אינן תואמות");
            confirmPasswordEditText.requestFocus();
            return false;
        }
        return true;
    }
}
