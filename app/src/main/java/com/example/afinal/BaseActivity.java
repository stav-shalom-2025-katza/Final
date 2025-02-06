package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // כל קוד משותף לכל הפעילויות שלך יכול להיות כאן.
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // קובץ ה-menu שלך
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // כפתור "הזמנה חדשה"
        if (id == R.id.new_order) {
            // פעולה כשנלחץ כפתור "הזמנה חדשה"
            Intent intent = new Intent(this, NewOrderActivity.class);
            startActivity(intent);
            return true;
        }

        // כפתור "מלאי המוצרים"
        if (id == R.id.product_inventory) {
            // פעולה כשנלחץ כפתור "מלאי המוצרים"
            Intent intent = new Intent(this, ProductInventoryActivity.class);
            startActivity(intent);
            return true;
        }

        // כפתור "סל הקניות שלי"
        if (id == R.id.shopping_cart) {
            // פעולה כשנלחץ כפתור "סל הקניות שלי"
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
            return true;
        }

        // כפתור "צור קשר"
        if (id == R.id.contact_us) {
            // פעולה כשנלחץ כפתור "צור קשר"
            Intent intent = new Intent(this, ContactUsActivity.class);
            startActivity(intent);
            return true;
        }

        // כפתור "אודות"
        if (id == R.id.about_us) {
            // פעולה כשנלחץ כפתור "אודות"
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
            return true;
        }

        // אם לא בוצעה פעולה עבור כפתור כלשהו
        return super.onOptionsItemSelected(item);
    }

}
