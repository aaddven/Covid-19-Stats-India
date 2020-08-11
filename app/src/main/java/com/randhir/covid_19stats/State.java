package com.randhir.covid_19stats;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;


public class State extends AppCompatActivity {

     ListView listView ;

     View statewiseLayout;

     ImageView back  ;

     TextView totalCases ;
     TextView totalRecovered ;
     TextView totalDeaths ;
     TextView updated ;

     TextView listofStates ;

    ArrayAdapter<String> arrayAdapter ;

     TextView state ;

     ArrayList<String> mState = new ArrayList<>();
     ArrayList<String> mCases = new ArrayList<>();
     ArrayList<String> mRecovered = new ArrayList<>();
     ArrayList<String> mDeaths = new ArrayList<>();
     ArrayList<String> mUpdateTime = new ArrayList<>();

     final ArrayList<String> states = new ArrayList<>();






    private void updateUI(View view){

        listView.setVisibility(View.GONE);

        statewiseLayout.setVisibility(View.VISIBLE);

        back.setVisibility(View.VISIBLE);

        listofStates.setVisibility(View.GONE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        listView = findViewById(R.id.swipeListView);

        statewiseLayout = findViewById(R.id.stateWiseDataLayout);

        state = findViewById(R.id.stateTextView);

        listofStates = findViewById(R.id.listofstates);

        totalCases = findViewById(R.id.noC);
        totalRecovered = findViewById(R.id.noR);
        totalDeaths = findViewById(R.id.noD);
        updated = findViewById(R.id.updatedTextVIew);

        back = findViewById(R.id.back);

        statewiseLayout.setVisibility(View.GONE);

        back.setVisibility(View.GONE);



        /*--------- Taking ArrayList Data from Main Activity ----------*/

        mState = (ArrayList<String>)  getIntent().getSerializableExtra("states") ;
        mCases = (ArrayList<String>) getIntent().getSerializableExtra("cases");
        mRecovered = (ArrayList<String>) getIntent().getSerializableExtra("recovered") ;
        mDeaths = (ArrayList<String>) getIntent().getSerializableExtra("deaths") ;
        mUpdateTime = (ArrayList<String>) getIntent().getSerializableExtra("updateTime") ;



        /* ---------------------------------------------------------------------------
                                         STATE LIST
          (Doing Manually Because Downloading Data Takes Time and ListView Shows Up Very Late)
         -----------------------------------------------------------------------------*/

        states.add("Maharashtra");
        states.add("Tamil Nadu");
        states.add("Delhi (UT)");
        states.add("Karnataka");
        states.add("Andhra Pradesh");
        states.add("Uttar Pradesh");
        states.add("Gujarat");
        states.add("West Bengal");
        states.add("Telangana");
        states.add("Rajasthan");
        states.add("Bihar");
        states.add("Haryana");
        states.add("Assam");
        states.add("Madhya Pradesh");
        states.add("Odisha");
        states.add("Jammu and Kashmir (UT)");
        states.add("Kerala");
        states.add("Punjab");
        states.add("Jharkhand");
        states.add("Chhattisgarh");
        states.add("Uttarakhand");
        states.add("Goa");
        states.add("Tripura");
        states.add("Puducherry (UT)");
        states.add("Manipur");
        states.add("Himachal Pradesh");
        states.add("Ladakh (UT)");
        states.add("Nagaland");
        states.add("Arunachal Pradesh");
        states.add("Chandigarh (UT)");
        states.add("Dadra and Nagar Haveli and Daman and Diu (UT)");
        states.add("Meghalaya");
        states.add("Sikkim");
        states.add("Mizoram");
        states.add("Andaman and Nicobar Islands (UT)");


        arrayAdapter = new ArrayAdapter<>(State.this, simple_list_item_1, states);

        if(listView != null) {
            listView.setAdapter(arrayAdapter);
        }

        /*------------ Search Bar --------*/

    /*    search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (State.this).arrayAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }); */

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);

                statewiseLayout.setVisibility(View.GONE);

                back.setVisibility(View.GONE);

                listofStates.setVisibility(View.VISIBLE);

            }
        });




         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    updateUI(view);


                    switch (parent.getPositionForView(view)){

                        /*-------------------------------------------------------------
                            Just to inform you I didn't wrote these switch cases.
                            C++ helped me for printing these cases :P
                       **************************************************************
                            #include <iostream>

                        using namespace std;

                      int main()
                        {

	                     for(int i = 0 ; i <= 34 ; i++){
		                     cout<<"case " << i << " :" <<endl<<
                            "state.setText(mState.get("<<i<<"));"<<endl<<
                            "totalCases.setText(mCases.get("<<i<<"));"<<endl<<
                            "totalRecovered.setText(mRecovered.get("<<i<<"));"<<endl<<
                            "totalDeaths.setText(mDeaths.get("<<i<<"));"<<endl<<
                            "updated.setText(mUpdateTime.get("<<i<<"));"<<endl<<
                            "break;"<<endl<<endl;
	                  }

	                   return 0;
                      }
                       *******************************************************************
                        ---------------------------------------------------------------*/

                        case 0 :
                            state.setText(mState.get(0));
                            totalCases.setText(mCases.get(0));
                            totalRecovered.setText(mRecovered.get(0));
                            totalDeaths.setText(mDeaths.get(0));
                            updated.setText(mUpdateTime.get(0));
                            break;

                        case 1 :
                            state.setText(mState.get(1));
                            totalCases.setText(mCases.get(1));
                            totalRecovered.setText(mRecovered.get(1));
                            totalDeaths.setText(mDeaths.get(1));
                            updated.setText(mUpdateTime.get(1));
                            break;

                        case 2 :
                            state.setText(mState.get(2));
                            totalCases.setText(mCases.get(2));
                            totalRecovered.setText(mRecovered.get(2));
                            totalDeaths.setText(mDeaths.get(2));
                            updated.setText(mUpdateTime.get(2));
                            break;

                        case 3 :
                            state.setText(mState.get(3));
                            totalCases.setText(mCases.get(3));
                            totalRecovered.setText(mRecovered.get(3));
                            totalDeaths.setText(mDeaths.get(3));
                            updated.setText(mUpdateTime.get(3));
                            break;

                        case 4 :
                            state.setText(mState.get(4));
                            totalCases.setText(mCases.get(4));
                            totalRecovered.setText(mRecovered.get(4));
                            totalDeaths.setText(mDeaths.get(4));
                            updated.setText(mUpdateTime.get(4));
                            break;

                        case 5 :
                            state.setText(mState.get(5));
                            totalCases.setText(mCases.get(5));
                            totalRecovered.setText(mRecovered.get(5));
                            totalDeaths.setText(mDeaths.get(5));
                            updated.setText(mUpdateTime.get(5));
                            break;

                        case 6 :
                            state.setText(mState.get(6));
                            totalCases.setText(mCases.get(6));
                            totalRecovered.setText(mRecovered.get(6));
                            totalDeaths.setText(mDeaths.get(6));
                            updated.setText(mUpdateTime.get(6));
                            break;

                        case 7 :
                            state.setText(mState.get(7));
                            totalCases.setText(mCases.get(7));
                            totalRecovered.setText(mRecovered.get(7));
                            totalDeaths.setText(mDeaths.get(7));
                            updated.setText(mUpdateTime.get(7));
                            break;

                        case 8 :
                            state.setText(mState.get(8));
                            totalCases.setText(mCases.get(8));
                            totalRecovered.setText(mRecovered.get(8));
                            totalDeaths.setText(mDeaths.get(8));
                            updated.setText(mUpdateTime.get(8));
                            break;

                        case 9 :
                            state.setText(mState.get(9));
                            totalCases.setText(mCases.get(9));
                            totalRecovered.setText(mRecovered.get(9));
                            totalDeaths.setText(mDeaths.get(9));
                            updated.setText(mUpdateTime.get(9));
                            break;

                        case 10 :
                            state.setText(mState.get(10));
                            totalCases.setText(mCases.get(10));
                            totalRecovered.setText(mRecovered.get(10));
                            totalDeaths.setText(mDeaths.get(10));
                            updated.setText(mUpdateTime.get(10));
                            break;

                        case 11 :
                            state.setText(mState.get(11));
                            totalCases.setText(mCases.get(11));
                            totalRecovered.setText(mRecovered.get(11));
                            totalDeaths.setText(mDeaths.get(11));
                            updated.setText(mUpdateTime.get(11));
                            break;

                        case 12 :
                            state.setText(mState.get(12));
                            totalCases.setText(mCases.get(12));
                            totalRecovered.setText(mRecovered.get(12));
                            totalDeaths.setText(mDeaths.get(12));
                            updated.setText(mUpdateTime.get(12));
                            break;

                        case 13 :
                            state.setText(mState.get(13));
                            totalCases.setText(mCases.get(13));
                            totalRecovered.setText(mRecovered.get(13));
                            totalDeaths.setText(mDeaths.get(13));
                            updated.setText(mUpdateTime.get(13));
                            break;

                        case 14 :
                            state.setText(mState.get(14));
                            totalCases.setText(mCases.get(14));
                            totalRecovered.setText(mRecovered.get(14));
                            totalDeaths.setText(mDeaths.get(14));
                            updated.setText(mUpdateTime.get(14));
                            break;

                        case 15 :
                            state.setText(mState.get(15));
                            totalCases.setText(mCases.get(15));
                            totalRecovered.setText(mRecovered.get(15));
                            totalDeaths.setText(mDeaths.get(15));
                            updated.setText(mUpdateTime.get(15));
                            break;

                        case 16 :
                            state.setText(mState.get(16));
                            totalCases.setText(mCases.get(16));
                            totalRecovered.setText(mRecovered.get(16));
                            totalDeaths.setText(mDeaths.get(16));
                            updated.setText(mUpdateTime.get(16));
                            break;

                        case 17 :
                            state.setText(mState.get(17));
                            totalCases.setText(mCases.get(17));
                            totalRecovered.setText(mRecovered.get(17));
                            totalDeaths.setText(mDeaths.get(17));
                            updated.setText(mUpdateTime.get(17));
                            break;

                        case 18 :
                            state.setText(mState.get(18));
                            totalCases.setText(mCases.get(18));
                            totalRecovered.setText(mRecovered.get(18));
                            totalDeaths.setText(mDeaths.get(18));
                            updated.setText(mUpdateTime.get(18));
                            break;

                        case 19 :
                            state.setText(mState.get(19));
                            totalCases.setText(mCases.get(19));
                            totalRecovered.setText(mRecovered.get(19));
                            totalDeaths.setText(mDeaths.get(19));
                            updated.setText(mUpdateTime.get(19));
                            break;

                        case 20 :
                            state.setText(mState.get(20));
                            totalCases.setText(mCases.get(20));
                            totalRecovered.setText(mRecovered.get(20));
                            totalDeaths.setText(mDeaths.get(20));
                            updated.setText(mUpdateTime.get(20));
                            break;

                        case 21 :
                            state.setText(mState.get(21));
                            totalCases.setText(mCases.get(21));
                            totalRecovered.setText(mRecovered.get(21));
                            totalDeaths.setText(mDeaths.get(21));
                            updated.setText(mUpdateTime.get(21));
                            break;

                        case 22 :
                            state.setText(mState.get(22));
                            totalCases.setText(mCases.get(22));
                            totalRecovered.setText(mRecovered.get(22));
                            totalDeaths.setText(mDeaths.get(22));
                            updated.setText(mUpdateTime.get(22));
                            break;

                        case 23 :
                            state.setText(mState.get(23));
                            totalCases.setText(mCases.get(23));
                            totalRecovered.setText(mRecovered.get(23));
                            totalDeaths.setText(mDeaths.get(23));
                            updated.setText(mUpdateTime.get(23));
                            break;

                        case 24 :
                            state.setText(mState.get(24));
                            totalCases.setText(mCases.get(24));
                            totalRecovered.setText(mRecovered.get(24));
                            totalDeaths.setText(mDeaths.get(24));
                            updated.setText(mUpdateTime.get(24));
                            break;

                        case 25 :
                            state.setText(mState.get(25));
                            totalCases.setText(mCases.get(25));
                            totalRecovered.setText(mRecovered.get(25));
                            totalDeaths.setText(mDeaths.get(25));
                            updated.setText(mUpdateTime.get(25));
                            break;

                        case 26 :
                            state.setText(mState.get(26));
                            totalCases.setText(mCases.get(26));
                            totalRecovered.setText(mRecovered.get(26));
                            totalDeaths.setText(mDeaths.get(26));
                            updated.setText(mUpdateTime.get(26));
                            break;

                        case 27 :
                            state.setText(mState.get(27));
                            totalCases.setText(mCases.get(27));
                            totalRecovered.setText(mRecovered.get(27));
                            totalDeaths.setText(mDeaths.get(27));
                            updated.setText(mUpdateTime.get(27));
                            break;

                        case 28 :
                            state.setText(mState.get(28));
                            totalCases.setText(mCases.get(28));
                            totalRecovered.setText(mRecovered.get(28));
                            totalDeaths.setText(mDeaths.get(28));
                            updated.setText(mUpdateTime.get(28));
                            break;

                        case 29 :
                            state.setText(mState.get(29));
                            totalCases.setText(mCases.get(29));
                            totalRecovered.setText(mRecovered.get(29));
                            totalDeaths.setText(mDeaths.get(29));
                            updated.setText(mUpdateTime.get(29));
                            break;

                        case 30 :
                            state.setText(mState.get(30));
                            totalCases.setText(mCases.get(30));
                            totalRecovered.setText(mRecovered.get(30));
                            totalDeaths.setText(mDeaths.get(30));
                            updated.setText(mUpdateTime.get(30));
                            break;

                        case 31 :
                            state.setText(mState.get(31));
                            totalCases.setText(mCases.get(31));
                            totalRecovered.setText(mRecovered.get(31));
                            totalDeaths.setText(mDeaths.get(31));
                            updated.setText(mUpdateTime.get(31));
                            break;

                        case 32 :
                            state.setText(mState.get(32));
                            totalCases.setText(mCases.get(32));
                            totalRecovered.setText(mRecovered.get(32));
                            totalDeaths.setText(mDeaths.get(32));
                            updated.setText(mUpdateTime.get(32));
                            break;

                        case 33 :
                            state.setText(mState.get(33));
                            totalCases.setText(mCases.get(33));
                            totalRecovered.setText(mRecovered.get(33));
                            totalDeaths.setText(mDeaths.get(33));
                            updated.setText(mUpdateTime.get(33));
                            break;

                        case 34 :
                            state.setText(mState.get(34));
                            totalCases.setText(mCases.get(34));
                            totalRecovered.setText(mRecovered.get(34));
                            totalDeaths.setText(mDeaths.get(34));
                            updated.setText(mUpdateTime.get(34));
                            break;

                    }

            }
        });



    }
}