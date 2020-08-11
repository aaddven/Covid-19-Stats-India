package com.randhir.covid_19stats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    Button allStates ;

    ImageView refresh ;

    TextView totalCases ;
    TextView totalRecovered ;
    TextView totalDeaths ;
    TextView newCases ;
    TextView newRecovered;
    TextView newDeaths ;
    TextView date ;

    ArrayList<String> mState = new ArrayList<>();
    ArrayList<String> mCases = new ArrayList<>();
    ArrayList<String> mRecovered = new ArrayList<>();
    ArrayList<String> mDeaths = new ArrayList<>();
    ArrayList<String> mUpdateTime = new ArrayList<>();

    TextView gitHubLink ;
    TextView dataLink;

    String tC ,tR ,tD ; // total numbers as a string

    String nC ,nR , nD ; // new numbers as a string

    String d ; // date as a string

    DownloadTask task;

    /*---------------------------------------------------------------------------
                              Downloads Data
     ---------------------------------------------------------------------------*/


    public class DownloadTask extends AsyncTask<String , Void , String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try{

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                BufferedReader inB = new BufferedReader(reader);

                String inputLine;

                while ((inputLine = inB.readLine()) != null)
                    result+=inputLine;

                in.close();

                int data;

                //while (data = reader.read()!= -1){

                 //   char current = (char) data ;
                 //   result += current;
                 //   data = reader.read();
                //}

                return result;

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        /* Get results here */

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                String stateInfo = jsonObject.getString("statewise");

                String caseTime = jsonObject.getString("cases_time_series");

                Log.i("Information", stateInfo);

                Log.i("Information", caseTime);

                JSONArray arr = new JSONArray(stateInfo);

                JSONArray arr2 = new JSONArray(caseTime);

                //arr.length()

                JSONObject jsonPart1 = arr.getJSONObject(0); // Index 0 has Total cases data in "statewise" array

                    Log.i("Total Cases",jsonPart1.getString("confirmed"));
                    Log.i("Total Recovered",jsonPart1.getString("recovered"));
                    Log.i("Total Deaths",jsonPart1.getString("deaths"));

                    // Converting json object to string

                    tC = jsonPart1.getString("confirmed");
                    tR = jsonPart1.getString("recovered");
                    tD = jsonPart1.getString("deaths") ;


                JSONObject jsonPart2 = arr2.getJSONObject(arr2.length()-1); // This is the last index of "case_time_series" array and last index has data of latest day .

                    Log.i("New Cases",jsonPart2.getString("dailyconfirmed"));
                    Log.i("New Recovered",jsonPart2.getString("dailyrecovered"));
                    Log.i("New Deaths",jsonPart2.getString("dailydeceased"));
                    Log.i("Date",jsonPart2.getString("date"));

                    // Converting json object to string

                    nC = jsonPart2.getString("dailyconfirmed");
                    nR = jsonPart2.getString("dailyrecovered");
                    nD = jsonPart2.getString("dailydeceased") ;
                    d = jsonPart2.getString("date");

                /*------- Storing Statewise Data in Arrays ----------------*/

                for(int i = 1 ; i < arr.length() ; i++){

                    JSONObject jsonPart3 = arr.getJSONObject(i);

                    Log.i("State",jsonPart3.getString("state"));
                    Log.i("Confirmed",jsonPart3.getString("confirmed"));
                    Log.i("Recovered",jsonPart3.getString("recovered"));
                    Log.i("Deaths",jsonPart3.getString("deaths"));
                    Log.i("Updated",jsonPart3.getString("lastupdatedtime"));

                    mState.add(jsonPart3.getString("state"));
                    mCases.add(jsonPart3.getString("confirmed"));
                    mRecovered.add(jsonPart3.getString("recovered"));
                    mDeaths.add(jsonPart3.getString("deaths"));
                    mUpdateTime.add(jsonPart3.getString("lastupdatedtime"));
                }

                // Setting up total data text view
                totalCases.setText(tC);
                totalRecovered.setText(tR);
                totalDeaths.setText(tD);

                // Setting up new data text view
                newCases.setText(nC);
                newRecovered.setText(nR);
                newDeaths.setText(nD);

                // Setting up date of new data
                date.setText(d + ", 2020");

                Toast.makeText(getApplicationContext(),"Done !",Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        allStates = findViewById(R.id.button_allStates);

        refresh = findViewById(R.id.ref);

        totalCases = findViewById(R.id.noTC);
        totalRecovered = findViewById(R.id.noTR);
        totalDeaths = findViewById(R.id.noTD);
        newCases = findViewById(R.id.noNC);
        newRecovered = findViewById(R.id.noNR);
        newDeaths = findViewById(R.id.noND);
        date = findViewById(R.id.dateTextView);
        gitHubLink = findViewById(R.id.gitHubLink);
        dataLink = findViewById(R.id.dataLink);



        /*----------------- Refresh the data -------------------------------*/

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Clicked : Please Wait ! Accessing Data", Toast.LENGTH_LONG).show();
                task =  new DownloadTask();

                totalCases.setText("Updating...");
                totalRecovered.setText("Updating...");
                totalDeaths.setText("Updating...");
                newCases.setText("Updating...");
                newRecovered.setText("Updating...");
                newDeaths.setText("Updating...");
                date.setText("Updating...");

                // Downloads Data

                task.execute("https://api.covid19india.org/data.json");

                //threadDownloadTask.start(); // Use thread.start() if u will use thread.run() that will run in same main thread

            }
        });

        // Developer's Link

        gitHubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/GITrandhir")));
            }
        });

        // Json Data Link

        dataLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://api.covid19india.org/data.json")));
            }
        });

        // Opens StateWise Data Activity

        allStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(task.getStatus().equals(DownloadTask.Status.FINISHED)){ // Checking if data is downloaded or not
                    Intent intent = new Intent(MainActivity.this , State.class);

                    // Passing ArrayLists which have data for State Activity

                    intent.putExtra("states",mState)
                            .putExtra("cases",mCases)
                            .putExtra("recovered",mRecovered)
                            .putExtra("deaths",mDeaths)
                            .putExtra("updateTime",mUpdateTime);
                    startActivity(intent);
                }else if(task.getStatus().equals(DownloadTask.Status.RUNNING)){ // Checking if download task is running
                    Toast.makeText(getApplicationContext(),"Data is Loading , Please Wait ",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Click on Refresh to Load Data",Toast.LENGTH_LONG).show();
                }

            }
        });

    }





}
