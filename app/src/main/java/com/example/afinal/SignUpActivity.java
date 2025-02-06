package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;  // Import this
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends BaseActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;
    private TextView switchToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up); // קישור לקובץ ה-XML

        // קישור רכיבי ה-UI
        emailEditText = findViewById(R.id.signup_email);
        passwordEditText = findViewById(R.id.signup_password);
        confirmPasswordEditText = findViewById(R.id.signup_confirm_password);
        signUpButton = findViewById(R.id.signup_button);
        switchToLogin = findViewById(R.id.switch_to_login);

        // מאזין ללחיצה על כפתור ההרשמה
        signUpButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            // כאן ניתן להוסיף חיבור ל-Firebase להרשמה
            signUpUser(email, password);
        });

        // מעבר למסך ההתחברות
        switchToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // הסתרת מקלדת אוטומטית בעת לחיצה מחוץ לשדה טקסט
        setupKeyboardHiding(findViewById(R.id.signup_button));
    }

    // פונקציה להסרת מקלדת כאשר היא לא נדרשת
    private void setupKeyboardHiding(View rootView) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            boolean isKeyboardVisible = insets.isVisible(WindowInsetsCompat.Type.ime());
            if (!isKeyboardVisible) {
                rootView.clearFocus();
            }
            return insets;
        });
    }

    // פונקציה להרשמה (לדוגמה: חיבור ל-Firebase)
    private void signUpUser(String email, String password) {
        // כאן ניתן להוסיף חיבור ל-Firebase או לשרת
        Toast.makeText(this, "Signing up with email: " + email, Toast.LENGTH_SHORT).show();
    }
}
