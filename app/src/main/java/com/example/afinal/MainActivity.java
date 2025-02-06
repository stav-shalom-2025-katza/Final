package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // הגדרת סרגל הכלים (Toolbar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // אפשר כפתור פעולה בתור כפתור "החזור"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // מאפשר כפתור בחזרה בצד שמאל

        // קישור לכפתורי התחברות והרשמה
        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.sign_up_button);

        // כפתור להתחברות (Log In)
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // כפתור להרשמה (Sign In)
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    // יצירת סרגל כלים
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // הגדרת פעולות כפתורי סרגל הכלים
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.new_order) {
            // מעבר למסך של הזמנה חדשה
            Intent intentNewOrder = new Intent(MainActivity.this, NewOrderActivity.class);
            startActivity(intentNewOrder);
            return true;
        } else if (itemId == R.id.product_inventory) {
            // מעבר למסך של מלאי המוצרים
            Intent intentInventory = new Intent(MainActivity.this, ProductInventoryActivity.class);
            startActivity(intentInventory);
            return true;
        } else if (itemId == R.id.shopping_cart) {
            // מעבר למסך של סל הקניות
            Intent intentCart = new Intent(MainActivity.this, ShoppingCartActivity.class);
            startActivity(intentCart);
            return true;
        } else if (itemId == R.id.contact_us) {
            // מעבר למסך צור קשר
            Intent intentContact = new Intent(MainActivity.this, ContactUsActivity.class);
            startActivity(intentContact);
            return true;
        } else if (itemId == R.id.about_us) {
            // מעבר למסך אודות
            Intent intentAbout = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intentAbout);
            return true;
        } else if (itemId == android.R.id.home) {
            // פעולת כפתור החזרה
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
