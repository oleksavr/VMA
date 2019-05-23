package com.example.root.vma;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VetListFragment extends Fragment {

    private RecyclerView mVisitRecyclerView;
    private VisitAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_vet_list, container,false);
       mVisitRecyclerView = (RecyclerView) view
               .findViewById(R.id.visit_recycler_view);
       mVisitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       updateUI();

        return view;
    }


    private void updateUI(){
        VisitLab visitLab = VisitLab.get(getActivity());
        List<Visit> visits = visitLab.getVisits();

        mAdapter = new VisitAdapter(visits);
        mVisitRecyclerView.setAdapter(mAdapter);
    }



    private class VisitHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Visit mVisit;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        public VisitHolder (LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_visit,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.visit_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.visit_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.visit_solved);
        }

        public void bind(Visit visit){
            mVisit = visit;
            mTitleTextView.setText(mVisit.getTitle());
            mDateTextView.setText(mVisit.getDate().toString());
            mSolvedImageView.setVisibility(visit.isSolved() ? View.VISIBLE : View.GONE);

        }


        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    mVisit.getTitle() + " clicked!", Toast.LENGTH_LONG)
                    .show();
        }
    }




    private class VisitAdapter extends RecyclerView.Adapter<VisitHolder>{
        private List<Visit> mVisits;
        public VisitAdapter(List<Visit> visits){
            mVisits = visits;
        }

        @NonNull
        @Override
        public VisitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new VisitHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull VisitHolder holder, int position) {
            Visit visit = mVisits.get(position);
            holder.bind(visit);
        }

        @Override
        public int getItemCount() {
            return mVisits.size();
        }


    }


}
