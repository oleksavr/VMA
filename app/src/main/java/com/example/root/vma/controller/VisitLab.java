package com.example.root.vma.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.root.vma.database.VisitCursorWrapper;
import com.example.root.vma.database.VisitsDbSchema.VisitTable;
import com.example.root.vma.model.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class VisitLab {

    private static VisitLab sVisitLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;



    private VisitLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new VisitBaseHelper(mContext)
                .getWritableDatabase();

    }

    public static VisitLab get(Context context){

        if(sVisitLab == null){
            sVisitLab = new VisitLab(context);
        }
        return sVisitLab;
    }


    public void addVisit(Visit v){

        ContentValues values = getContentValues(v);
        mDatabase.insert(VisitTable.NAME, null,values);


    }

    public List<Visit> getVisits(){
        return new ArrayList<>();
    }

    public Visit getVisit(UUID id){

        return null;
    }

    public void  updateVisit(Visit visit){
        String uuidString = visit.getId().toString();
        ContentValues values = getContentValues(visit);

        mDatabase.update(VisitTable.NAME,values,
                VisitTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private VisitCursorWrapper queryVisits(String whereCluse, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                VisitTable.NAME,
                null, //null select all columns
                whereCluse,
                whereArgs,
                null,
                null,
                null
        );

        return new VisitCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Visit visit){
        ContentValues values = new ContentValues();
        values.put(VisitTable.Cols.UUID, visit.getId().toString());
        values.put(VisitTable.Cols.TITLE,visit.getTitle());
        values.put(VisitTable.Cols.DESCRIPTION,visit.getDetails());
        values.put(VisitTable.Cols.DATE,visit.getDate().getTime());
        values.put(VisitTable.Cols.SOLVED,visit.isSolved() ? 1 : 0);

        return values;

    }
}
