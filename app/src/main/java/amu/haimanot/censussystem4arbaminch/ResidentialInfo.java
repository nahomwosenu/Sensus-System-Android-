package amu.haimanot.censussystem4arbaminch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class ResidentialInfo extends AppCompatActivity {

    static String[] types={};
    static String type;
    static String houseSN;
    static String houseHoldSN;
    private Spinner spinType;
    private EditText etHouseSN;
    private EditText etHouseHoldSN;
    private Button btnNext;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residential_info);

        spinType=(Spinner)findViewById(R.id.spinType);
        etHouseSN=(EditText)findViewById(R.id.etHouseSN);
        etHouseHoldSN=(EditText)findViewById(R.id.etHouseHoldSN);
        HashMap map=new HashMap();
        map.put("type","type");
        AsyncResponse responce=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s!=null && !s.isEmpty() && s.contains(",")){
                    types=s.split(",");
                    ArrayAdapter<String> array=new ArrayAdapter<String>(ResidentialInfo.this,R.layout.support_simple_spinner_dropdown_item,types);
                    spinType.setAdapter(array);
                }
                else Toast.makeText(ResidentialInfo.this,s,Toast.LENGTH_LONG).show();
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,map,responce);
        task.execute(Login.server+"/census/autocomplete.php");
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,types);
        spinType.setAdapter(adapter);
        spinType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        type=spinType.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        type="";
                    }
                }
        );
        btnBack=(Button)findViewById(R.id.btnBack);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ResidentialInfo.this,AreaIdentificationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        btnNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        houseSN=etHouseSN.getText().toString();
                        houseHoldSN=etHouseHoldSN.getText().toString();
                        Intent intent=new Intent(ResidentialInfo.this,EnumurationForm.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

}
