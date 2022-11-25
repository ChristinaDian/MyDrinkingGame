package com.example.mydrinkinggame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "drinkingGameDB.db";
    public static final int VERSION = 1;

    public static  final String TABLE_CARDS= "cards";
    public static  final String TABLE_USERS= "users";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TO DO: ADD FOREIGN KEY

        db.execSQL("CREATE TABLE " + TABLE_CARDS + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Content TEXT)");
        db.execSQL("CREATE TABLE "+TABLE_USERS+"(Username TEXT primary key, Password TEXT NOT NULL," +
                " Name TEXT NOT NULL, Bottle varchar(15) DEFAULT 'bombay')");
        db.execSQL("INSERT INTO " +TABLE_CARDS+ " (Content) VALUES('Say something that will make the person to the left blush')," +
                "('French kiss with the person whose name begins with the first letter of the alphabet')," +
                "('Ten seconds massage to the person who first raises his/her hand')," +
                "('Underage drink')," +"('Kiss for 10 sec with the person to the right'),"+
                "('Kiss on the neck the youngest')," +"('Prank call someone (with a hidden number)'),"+
                "('Call ex or mom')," +"('Text someone U hate')," + "('Body shot from the person who took a bath recently')," +
                "('Lap dance')," + "('2 truths, 1 lie')," + "('Neighbours shot (both neighbours drink)')," +
                "('Tallest & shortest take a shot')," +"('Youngest & Oldest take a shot')," +"('Never have I ever |||')," +
                "('Animal owners (drink)')," + "('Twerk for 30 secs')," + "('Cutest person in the room (shot)')," +
                "('Take off shirt/pants')," +"('Remove part of clothing')," +"('Switch clothes with the person to the right')," +
                "('Take so’s t-shirt with your teeth')," +"('Fresh meat (virgins take a shot)')," +
                "('Secrets don’t make friends (tell a secret)')," +"('Nose goes (who touch his nose last drinks)')," +
                "('Spill the tea about someone in the room')," + "('Ask me anything')," + "('Group shot')," +
                "('Truth or dare')," + "('Partners (choose so and he has to drink with you whenever you drink)')," +
                "('Dicks (guys drink)')," + "('Whores (girls drink)')," + "('Rock, Paper, Scissors with the person in front of you')," +
                "('Mustache (someone has to draw a mustache on you)')," + "('CHUG  (water)! U need it!')," +
                "('Flip-A-Coin (heads-2 shots; tails-1 shot)')," + "('Send Selfie to your crush')," +
                "('Group selfie')," +"('Post selfie on Story')," + "('Take a picture DOGGY style')," +
                "('Last hookup?')," + "('Blondes drink')," + "('Brunets drink')," + "('Most likely to get arrested (shot)')," +
                "('Most likely to get a ring first')," +"('Worst driver (shot)')," + "('Cuddle with the person <- for 5 turns')," +
                "('Give your best-drunk story')," + "('Finish your drink')," + "('Pick a partner, cross arms, and CHUG')," +
                "('Make TikTok')," + "('Dare or Dare')," + "('Me + U in a room=?')," + "('Booty slap someone')," +
                "('Biggest turn on')," + "('Wet Willy (mokar predmet v na nqkoi uhoto)')," +
                "('Get a hickey from ????? (group choice)')," + "('Give a hickey to  ????? (group choice)')," +
                "('Yell first thing that comes in your head')," + "('Tell a sex story')," + "('Go outside and summon the rain')," +
                "('Would you rather (WYR)')," + "('Blindfold and guess')," + "('Walk in a line')," + "('Kiss or Slap')," +
                "('Stoners drink')," + "('Host drinks')," + "('Karaoke time')," +
                "('What are the odds?');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        onCreate(db);
    }

    public void addUsers(String username, String password, String name) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Password", password);
        values.put("Name", name);

        //inserting new row
        sqLiteDatabase.insert(TABLE_USERS, null , values);
        //close database connection
        sqLiteDatabase.close();
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLE_USERS+ " where Username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }
    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLE_USERS+ " where Username = ? and Password = ?", new String[] {username,password});
        return cursor.getCount() > 0;
    }
    public void setBottle(String username, String bottle) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLE_USERS+ " where Username = ?", new String[]{username});
        if (cursor!=null){
            cursor.moveToFirst();
        }
        ContentValues values =  new ContentValues();
        values.put("Password",cursor.getString(1));
        values.put("Name",cursor.getString(2));
        values.put("Bottle", bottle);
        //updating row
        MyDB.update(TABLE_USERS, values, "Username = ?", new String[]{username});
        cursor.close();
        MyDB.close();
    }
    public String getBottle(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLE_USERS+ " where Username = ?", new String[]{username});
        if (cursor!=null){
            cursor.moveToFirst();
        }
        String bottle = cursor.getString(3);
        cursor.close();
        return bottle;
    }

    //TO DO: Make User with multiple cards
    public void insertCard(String card) {
        //Отваряме конекция
        SQLiteDatabase db = getWritableDatabase();

        //извършване на операции
        ContentValues cv = new ContentValues();
        cv.put("Content", card);

        db.insert(TABLE_CARDS, null, cv);
        Cursor cursor = db.rawQuery("Select * from "+TABLE_CARDS+ " where Content = ?", new String[]{card});
        if (cursor!=null){
            cursor.moveToFirst();
        }
        cv = new ContentValues();
        cv.put("CardId", cursor.getInt(0));
        db.insert(TABLE_USERS, null, cv);
        cursor.close();
        //затваряне
        db.close();
    }
    public void updateCard(String card, String oldCard) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("Content", card);
        //updating row
        sqLiteDatabase.update(TABLE_CARDS, values, "Content='" + oldCard+"'", null);
        sqLiteDatabase.close();
    }
    public ArrayList<String> getCards() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        // select all query
        String select_query= "SELECT *FROM " + TABLE_CARDS;


        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String card;
                card=(cursor.getString(1));

                arrayList.add(card);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<String> getMyCards() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        // select all query
        String select_query= "SELECT cards.Content FROM " + TABLE_USERS +" JOIN cards ON cards.ID=users.CardId";


        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String card;
                card=(cursor.getString(0));

                arrayList.add(card);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public void deleteCard(String pos) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_CARDS, "Content= '" + pos + "'", null);
        sqLiteDatabase.close();
    }
}
