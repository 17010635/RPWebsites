package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCat,spnSubCat;
    Button btnGo;
    ArrayList<String> alNumbers;
    ArrayAdapter<String> aaNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCat = findViewById(R.id.spinnerCat);
        spnSubCat = findViewById(R.id.spinnerSubCat);
        btnGo = findViewById(R.id.buttonGo);

        alNumbers = new ArrayList<>();

        //Create an ArrayAdapter using the default Spinner layout and the ArrayList
        aaNumbers = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, alNumbers);

        //Bind the ArrayAdapter to the Spinner
        spnSubCat.setAdapter(aaNumbers);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),WebViewActivity.class);
                int pos = spnCat.getSelectedItemPosition();
                int pos1 = spnSubCat.getSelectedItemPosition();
                String[][] sites = {
                        {
                                "https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life"
                        },
                        {
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
                        }
                };
                String url = sites[pos][pos1];

//                if (pos == 0){
//                    if (pos1 == 0 ){
//                        url1 ="https://www.rp.edu.sg/";
//                    } else{
//                        url1 = "https://www.rp.edu.sg/student-life";
//                    }
//                } else {
//                    if (pos1 == 0){
//                        url1 = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
//                    } else {
//                        url1 = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
//                    }
//                }
                intent.putExtra("url",url);
                startActivity(intent);
            }

        });

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        alNumbers.clear();
                        String[] strNumbers = getResources().getStringArray(R.array.rpSubCat);
                        alNumbers.addAll(Arrays.asList(strNumbers));
                        break;
                    case 1:
                        alNumbers.clear();
                        String[] strNumber = getResources().getStringArray(R.array.soiSubCat);
                        alNumbers.addAll(Arrays.asList(strNumber));
                        break;
                }
                aaNumbers.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = sharedPref.edit();
        int pos = spnCat.getSelectedItemPosition();
        int pos1 = spnSubCat.getSelectedItemPosition();
        preEdit.putInt("spinnerSelection", pos);
        preEdit.putInt("spinnerSelection2", pos1);
        preEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = sharedPref.edit();
        spnCat.setSelection(sharedPref.getInt("spinnerSelection", 0));
        spnSubCat.setSelection(sharedPref.getInt("spinnerSelection2",0));
    }
}
