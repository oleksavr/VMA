package com.example.root.vma;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class VisitLab {

    private static VisitLab sVisitLab;
    private List<Visit> mVisits;

    public static VisitLab get(Context context){
        if(sVisitLab == null){
            sVisitLab = new VisitLab(context);
        }
        return sVisitLab;
    }

    private VisitLab(Context context){
        mVisits = new ArrayList<>();

        for(int i=0; i<100;i++){
            Visit visit = new Visit();
            visit.setTitle("Visit # " + i);
            visit.setSolved(i%2==0);
            mVisits.add(visit);
        }
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
