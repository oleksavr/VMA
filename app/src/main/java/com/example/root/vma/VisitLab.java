package com.example.root.vma;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    }

    public List<Visit> getVisits(){
        return new ArrayList<>();
    }

    public Visit getVisit(UUID id){

        return null;
    }
}
