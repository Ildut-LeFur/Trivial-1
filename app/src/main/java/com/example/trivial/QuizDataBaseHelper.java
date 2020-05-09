package com.example.trivial;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.trivial.QuizContrac.*;

import androidx.annotation.Nullable;

public class QuizDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Trivial.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CRATE TABLE " +
                QuestionTable.TABLE_NAME +  " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXt, " +
                QuestionTable.COLUMN_OPTION1 + " TEXt, " +
                QuestionTable.COLUMN_OPTION2 + " TEXt, " +
                QuestionTable.COLUMN_OPTION3 + " TEXt, " +
                QuestionTable.COLUMN_ANSWER_NB + " INTERGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionTable() {
        Question q1 = new Question("coche reponse 1", "1","2","3",1);
        addQuestion(q1);
        Question q2 = new Question("coche reponse 2", "1","2","3",2);
        addQuestion(q2);
        Question q3 = new Question("coche reponse 3", "1","2","3",3);
        addQuestion(q3);
    }
    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NB, question.getAnswernb());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }
}
