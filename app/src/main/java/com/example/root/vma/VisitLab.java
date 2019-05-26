package com.example.root.vma;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class VisitLab {

    private static VisitLab sVisitLab;
    private List<Visit> mVisits;
    private Context mContext;
    private SQLiteDatabase mDatabase;



    private VisitLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new VisitBaseHelper(mContext)
                .getWritableDatabase();
        mVisits = new ArrayList<>();
    }

    public static VisitLab get(Context context){

        if(sVisitLab == null){
            sVisitLab = new VisitLab(context);
        }
        return sVisitLab;
    }


    public void addVisit(Visit v){
        mVisits.add(v);
    }

    public List<Visit> getVisits(){
        return mVisits;
    }

    public Visit getVisit(UUID id){
        for(Visit visit:mVisits){
            if(visit.getId().equals(id)){
                return visit;
            }
        }
        return null;
    }
}
