package com.example.root.vma;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

import static android.widget.CompoundButton.*;

public class VetFragment extends Fragment {

    public static final String ARG_VISIT_ID = "visit_id";
    public static final String DIALOG_DATE = "DialogDate";


    private Visit mVisit;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;


    public static VetFragment newInstance(UUID visitID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_VISIT_ID, visitID);

        VetFragment fragment = new VetFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       UUID visitID = (UUID) getArguments().getSerializable(ARG_VISIT_ID);
        mVisit = VisitLab.get(getActivity()).getVisit(visitID);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vet, container,false);

        mTitleField = (EditText) v.findViewById(R.id.vet_title);
        mTitleField.setText(mVisit.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVisit.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDateButton = (Button) v.findViewById(R.id.vet_date);
        mDateButton.setText(mVisit.getDate().toString());
       mDateButton.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
               FragmentManager fragmentManager = getFragmentManager();
               DatePickerFragment dialog = DatePickerFragment
                       .newInstance(mVisit.getDate());
               dialog.show(fragmentManager,DIALOG_DATE);

           }
       });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.vet_solved);
        mSolvedCheckBox.setChecked(mVisit.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mVisit.setSolved(isChecked);
            }
        });
        return v;
    }
}
