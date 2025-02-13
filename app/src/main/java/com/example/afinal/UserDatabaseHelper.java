package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    // שם מסד הנתונים וגרסתו
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 2; // עדכון גרסה

    // שם הטבלה ושדותיה
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_IS_ADMIN = "is_admin"; // שדה חדש למנהל

    // שאילתת יצירת טבלה (כולל שדה is_admin)
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT UNIQUE, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_IS_ADMIN + " INTEGER DEFAULT 0);"; // ברירת המחדל היא 0 (לא מנהל)

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // אם גרסת מסד הנתונים ישנה יותר, נוסיף את עמודת is_admin
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_IS_ADMIN + " INTEGER DEFAULT 0");
        }
    }

    // פונקציה להוספת משתמש חדש
    public boolean addUser(String username, String password, boolean isAdmin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        // אם הסיסמה היא "idan2709", נעדכן את ה-`is_admin` ל-1
        if (password.equals("idan2709")) {
            values.put(COLUMN_IS_ADMIN, 1); // המנהל יקבל את הערך 1
        } else {
            values.put(COLUMN_IS_ADMIN, 0); // משתמש רגיל יקבל את הערך 0
        }

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1; // מחזיר true אם התווסף בהצלחה
    }

    // פונקציה לבדוק אם המשתמש קיים
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }

    // פונקציה לבדוק אם המשתמש הוא מנהל
    public boolean isAdmin(String username, String password) {
        if (password.equals("idan2709")) {
            return true; // אם הסיסמה היא "idan2709", המשתמש הוא מנהל
        }

        // אחרת, נבדוק את הערך של is_admin במסד הנתונים
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_IS_ADMIN + " FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean isAdmin = false;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_IS_ADMIN);
            if (columnIndex != -1) {
                // אם העמודה קיימת, נבדוק את הערך שלה
                isAdmin = cursor.getInt(columnIndex) == 1;
            }
        }
        cursor.close();
        db.close();

        return isAdmin;
    }
}
