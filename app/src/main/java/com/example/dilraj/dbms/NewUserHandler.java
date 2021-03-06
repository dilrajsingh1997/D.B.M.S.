package com.example.dilraj.dbms;

/**
 * Created by DilrajSingh on 24-Feb-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class NewUserHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 27;
    private static final String DATABASE_NAME = "libDB.db";
    private static final String COLUMN_ID = "_id";

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BRANCH = "branch";
    private static final String COLUMN_ROLL = "roll";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PSSWD = "passwd";

    private static final String TABLE_ADMINS = "admins";
    private static final String COLUMN_ADMIN_UID = "admin_uid";
    private static final String COLUMN_ADMIN_PSSWD = "admin_psswd";

    private static final String TABLE_BOOKS = "books";
    private static final String COLUMN_BOOK_ID = "book_id";
    private static final String COLUMN_BOOK_NAME = "book_name";
    private static final String COLUMN_BOOK_AUTHORNAME = "book_authorname";
    private static final String COLUMN_BOOK_STATUS = "book_status";

    private static final String TABLE_RENT = "rents";
    private static final String COLUMN_USERID = "id_rent";
    private static final String COLUMN_BOOKID = "book_id_rent";
    private static final String COLUMN_DATE = "date";

    private static final String TABLE_HISTORY = "history_rent";
    private static final String COLUMN_RETURN_DATE = "date_return";

    private static final String TRIGGER = "own_trigger";

    public NewUserHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER AUTO INCREMENT, " +
                COLUMN_NAME + " VARCHAR(200), " +
                COLUMN_BRANCH + " VARCHAR(20), " +
                COLUMN_ROLL + " VARCHAR(20) PRIMARY KEY, " +
                COLUMN_PHONE + " INTEGER, " +
                COLUMN_ADDRESS + " VARCHAR(200), " +
                COLUMN_PSSWD + " VARCHAR(20)" +
                ");";
        db.execSQL(q);
        q = "CREATE TABLE IF NOT EXISTS " + TABLE_ADMINS + "(" +
                //COLUMN_ID + " INTEGER AUTO INCREMENT, " +
                COLUMN_ADMIN_UID + " VARCHAR(20) PRIMARY KEY, " +
                COLUMN_ADMIN_PSSWD + " VARCHAR(200)" +
                ");";
        db.execSQL(q);
        q = "INSERT INTO " + TABLE_ADMINS + " VALUES (" +
                "\"78912\", \"admin\" )";
        db.execSQL(q);
        q = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + "(" +
                COLUMN_ID + " INTEGER AUTO INCREMENT, " +
                COLUMN_BOOK_ID + " VARCHAR(200) PRIMARY KEY, " +
                COLUMN_BOOK_NAME + " VARCHAR(200), " +
                COLUMN_BOOK_AUTHORNAME + " VARCHAR(200), " +
                COLUMN_BOOK_STATUS + " INTEGER" +
                ");";
        db.execSQL(q);
        q = "CREATE TABLE IF NOT EXISTS " + TABLE_RENT + "(" +
                COLUMN_ID + " INTEGER AUTO INCREMENT, " +
                COLUMN_USERID + " VARCHAR(200), " +
                COLUMN_BOOKID + " VARCHAR(200), " +
                COLUMN_DATE + " VARCHAR(200), " +
                "FOREIGN KEY (" +
                COLUMN_USERID + ") REFERENCES " +
                TABLE_USERS + "(" +
                COLUMN_ROLL + "), " +
                "FOREIGN KEY (" +
                COLUMN_BOOKID + ") REFERENCES " +
                TABLE_BOOKS + "(" +
                COLUMN_BOOK_ID + ")" +
                ");";
        db.execSQL(q);
        q = "CREATE TABLE " + TABLE_HISTORY + "(" +
                COLUMN_USERID + " VARCHAR(200), " +
                COLUMN_BOOKID + " VARCHAR(200), " +
                COLUMN_DATE + " VARCHAR(200), " +
                COLUMN_RETURN_DATE + " VARCHAR(200))";
        db.execSQL(q);
        q = "CREATE TRIGGER " + TRIGGER +
                " BEFORE DELETE ON " + TABLE_RENT +
                " FOR EACH ROW " +
                " BEGIN " +
                " INSERT INTO " + TABLE_HISTORY + " VALUES (" +
                " old." + COLUMN_USERID + ", " +
                " old." + COLUMN_BOOKID + ", " +
                " old." + COLUMN_DATE + ", \"" +
                "date('now')" + "\" );" +
                " END;";
//        try{
        db.execSQL(q);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }

    public void addUserDB(USers u) throws SQLiteConstraintException, SQLiteAbortException, SQLIntegrityConstraintViolationException {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, u.getName());
            values.put(COLUMN_BRANCH, u.getBranch());
            values.put(COLUMN_ROLL, u.getRoll());
            values.put(COLUMN_PHONE, u.getPhone());
            values.put(COLUMN_ADDRESS, u.getAddress());
            values.put(COLUMN_PSSWD, u.getPsswd());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_USERS, null, values);
            db.close();
        } catch (SQLiteAbortException e) {
            throw new SQLiteAbortException(e.toString());
        } catch (SQLiteConstraintException e) {
            throw new SQLiteConstraintException(e.toString());
        }
    }

    public void addBook(Books b) throws SQLIntegrityConstraintViolationException, SQLiteAbortException, SQLiteConstraintException {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_BOOK_ID, b.getId());
            values.put(COLUMN_BOOK_NAME, b.getName());
            values.put(COLUMN_BOOK_AUTHORNAME, b.getAuthor());
            values.put(COLUMN_BOOK_STATUS, 0);
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_BOOKS, null, values);
            db.close();
        } catch (SQLiteAbortException e) {
            throw new SQLiteAbortException(e.toString());
        } catch (SQLiteConstraintException e) {
            throw new SQLiteConstraintException(e.toString());
        }
    }

    public void addRent(Rent r) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERID, r.getUserid());
            values.put(COLUMN_BOOKID, r.getBookid());
            values.put(COLUMN_DATE, r.getDate());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_RENT, null, values);
            db.close();
        } catch (SQLiteAbortException e) {
            throw new SQLiteAbortException(e.toString());
        } catch (SQLiteConstraintException e) {
            throw new SQLiteConstraintException(e.toString());
        }
    }

    public boolean onRent(String id) {
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_RENT + " WHERE " + COLUMN_BOOKID + " = \"" + id + "\"";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    public ArrayList<USers> getUsers() {
        ArrayList<USers> ar = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_USERS + " WHERE 1";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            USers u = new USers(c.getString(c.getColumnIndex(COLUMN_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_BRANCH)),
                    c.getString(c.getColumnIndex(COLUMN_ROLL)),
                    c.getString(c.getColumnIndex(COLUMN_PHONE)),
                    c.getString(c.getColumnIndex(COLUMN_ADDRESS)),
                    c.getString(c.getColumnIndex(COLUMN_PSSWD)));
            ar.add(u);
            u = null;
            System.gc();
            c.moveToNext();
        }
        db.close();
        c.close();
        return ar;
    }


    public boolean authAdmin(String id, String pass) {
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_ADMIN_UID + " = \"" + id + "\" AND " + COLUMN_ADMIN_PSSWD + " = \"" + pass + "\"";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public boolean authUser(String roll, String pass) {
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ROLL + " = \"" + roll + "\" AND " + COLUMN_PSSWD + " = \"" + pass + "\"";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            c.close();
            return true;
        }
        c.close();
        return false;
    }


    public boolean checkUser(String roll) {
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ROLL + " = \"" + roll + "\"";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public ArrayList<Books> getBooks() {
        ArrayList<Books> ar = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_BOOKS + " WHERE 1";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Books u = new Books(c.getString(c.getColumnIndex(COLUMN_BOOK_ID)), c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_BOOK_AUTHORNAME)), c.getInt(c.getColumnIndex(COLUMN_BOOK_STATUS)));
            ar.add(u);
            u = null;
            System.gc();
            c.moveToNext();
        }
        db.close();
        c.close();
        return ar;
    }

    public ArrayList<Rent> getRent() {
        ArrayList<Rent> ar = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_RENT + " WHERE 1";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Rent u = new Rent(c.getString(c.getColumnIndex(COLUMN_USERID)), c.getString(c.getColumnIndex(COLUMN_BOOKID)),
                    c.getString(c.getColumnIndex(COLUMN_DATE)));
            ar.add(u);
            u = null;
            System.gc();
            c.moveToNext();
        }
        db.close();
        c.close();
        return ar;
    }


    public void delete_book(String adapterPosition) throws SQLIntegrityConstraintViolationException, SQLiteConstraintException {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String q = "DELETE FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_ID + "= \"" + adapterPosition + "\"";
            db.execSQL(q);
            db.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Books> getBooksUnIssued() {
        ArrayList<Books> ar = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_STATUS + " =0";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Books u = new Books(c.getString(c.getColumnIndex(COLUMN_BOOK_ID)), c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_BOOK_AUTHORNAME)), c.getInt(c.getColumnIndex(COLUMN_BOOK_STATUS)));
            ar.add(u);
            u = null;
            System.gc();
            c.moveToNext();
        }
        db.close();
        c.close();
        return ar;
    }

    public void updateIssuedBooks(String bookid) {
        SQLiteDatabase db = getWritableDatabase();
        String q = "UPDATE " + TABLE_BOOKS + " SET " + COLUMN_BOOK_STATUS + " = 1 WHERE " + COLUMN_BOOK_ID + " = " + bookid;
        db.execSQL(q);
        db.close();

    }

    public ArrayList<Rent> getRentsOfUser(String userid) {
        ArrayList<Rent> ar = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q;
        try {
            q = "SELECT " + COLUMN_BOOK_NAME + "," + COLUMN_BOOKID + "," + COLUMN_DATE + " FROM " + TABLE_RENT + "," + TABLE_BOOKS + " WHERE " + TABLE_RENT + "." + COLUMN_BOOKID + "=" + TABLE_BOOKS + "." + COLUMN_BOOK_ID + " AND " + COLUMN_USERID + "=\"" + userid + "\";";
        } catch (NullPointerException e) {
            throw e;
        }
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Rent u = new Rent(c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)), c.getString(c.getColumnIndex(COLUMN_BOOKID)),
                    c.getString(c.getColumnIndex(COLUMN_DATE)));
            ar.add(u);
            u = null;
            System.gc();
            c.moveToNext();
        }
        db.close();
        c.close();
        return ar;
    }

    public void updateBooks(String bookid) {
        SQLiteDatabase db = getWritableDatabase();
        String q = "UPDATE " + TABLE_BOOKS + " SET " + COLUMN_BOOK_STATUS + " = 0 WHERE " + COLUMN_BOOK_ID + " = " + bookid;
        db.execSQL(q);
        db.close();

    }

    public void delete_rent(String bookid) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String q = "DELETE FROM " + TABLE_RENT + " WHERE " + COLUMN_BOOKID + "= \"" + bookid + "\"";
            db.execSQL(q);
            db.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void setHistoryReturnDate(String date, String returndate) {
        SQLiteDatabase db = getWritableDatabase();
        String q = "UPDATE " + TABLE_HISTORY + " SET " + COLUMN_RETURN_DATE + "=" + " '" + returndate + "'" + "  WHERE " + COLUMN_DATE + " = " + " '" + date + "'";
        db.execSQL(q);
        db.close();
    }

    public ArrayList<Rent> getHistoryrent(String id) {
        ArrayList<Rent> ar = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String q = "SELECT * FROM " + TABLE_HISTORY + " WHERE " + COLUMN_USERID + "= " + "'" + id + "'";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Rent u = new Rent(c.getString(c.getColumnIndex(COLUMN_BOOKID)), c.getString(c.getColumnIndex(COLUMN_DATE)),
                    c.getString(c.getColumnIndex(COLUMN_RETURN_DATE)));
            ar.add(u);
            u = null;
            System.gc();
            c.moveToNext();
        }
        db.close();
        c.close();
        return ar;

    }
}

