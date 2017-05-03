package amu.haimanot.censussystem4arbaminch;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.asynctask.*;

import java.util.HashMap;

public class AreaIdentificationActivity extends AppCompatActivity {

    private Spinner spinRegion;
    private AutoCompleteTextView spinZone;
    private AutoCompleteTextView spinWoreda;
    private AutoCompleteTextView spinTown;
    static String region;
    static String zone;
    static String woreda;
    static String town;
    static String kebele;
    static String subcity;
    static String stationArea;
    static String enumArea;
    private Button btnNext;
    private Button btnCancel;
    static String[] regions;
    static String[] zones;
    static String[] woredas;
    static String[] towns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        spinRegion=(Spinner)findViewById(R.id.spinRegion);
        spinZone=(AutoCompleteTextView)findViewById(R.id.spinZone);
        spinWoreda=(AutoCompleteTextView)findViewById(R.id.spinWereda);
        spinTown=(AutoCompleteTextView)findViewById(R.id.spinTown);
        Log.d("APP","starting hashmap");
        HashMap regData=new HashMap();
        regData.put("type","all");
        Log.d("APP","Hashmap created. Creating AsyncResponce");
        AsyncResponse regResponce=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s==null || s.isEmpty()){
                    Log.d("APP","Empty Responce");
                    Toast.makeText(AreaIdentificationActivity.this,"Some items not loaded, connection problem",Toast.LENGTH_LONG).show();
                }
                else if(s.contains("error")){
                    Log.d("APP","Error Responce");
                    Toast.makeText(AreaIdentificationActivity.this,s,Toast.LENGTH_LONG).show();
                }
                else if(s.contains(";")){
                    Log.d("APP","array found");
                    String[] scenes=s.split(";");
                    if(scenes[0]!=null && scenes[0].contains(","))
                    regions=scenes[0].split(",");
                    else regions=new String[]{"other"};
                    if(scenes[1]!=null && scenes[1].contains(","))
                    zones=scenes[1].split(",");
                    else zones=new String[]{"other"};
                    if(scenes[2]!=null && scenes[2].contains(","))
                    woredas=scenes[2].split(",");
                    else woredas=new String[]{"other"};
                    if(scenes[3]!=null && scenes[3].contains(","))
                    towns=scenes[3].split(",");
                    else towns=new String[]{"others"};
                    Log.d("APP","All Arayys Assigned");
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(AreaIdentificationActivity.this,R.layout.support_simple_spinner_dropdown_item,regions);

                    ArrayAdapter<String> adapterZone=new ArrayAdapter<String>(AreaIdentificationActivity.this,R.layout.support_simple_spinner_dropdown_item,zones);
                    ArrayAdapter<String> adapterWoreda=new ArrayAdapter<String>(AreaIdentificationActivity.this,R.layout.support_simple_spinner_dropdown_item,woredas);
                    ArrayAdapter<String> adapterTown=new ArrayAdapter<String>(AreaIdentificationActivity.this,R.layout.support_simple_spinner_dropdown_item,towns);
                    Log.d("APP","Adapters SET");
                    spinRegion.setAdapter(adapter);
                    spinZone.setAdapter(adapterZone);
                    spinWoreda.setAdapter(adapterWoreda);
                    spinTown.setAdapter(adapterTown);
                    Log.d("APP","Spinners connected");
                    Toast.makeText(AreaIdentificationActivity.this,"Success on Load",Toast.LENGTH_LONG);
                }
                else
                    Toast.makeText(AreaIdentificationActivity.this,s,Toast.LENGTH_LONG).show();
            }
        };

        PostResponseAsyncTask task=new PostResponseAsyncTask(this,regData,regResponce);
        Log.d("APP","Excecuting PosResponce");
        task.execute(Login.server+"/census/autocomplete.php");
        Log.d("APP","Responce success");
        btnNext=(Button)findViewById(R.id.btnNext);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        Log.d("APP","Buttons set");
        btnNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(store()) {
                            Intent intent = new Intent(AreaIdentificationActivity.this, ResidentialInfo.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                            Toast.makeText(AreaIdentificationActivity.this,"Please fill all required fields",Toast.LENGTH_LONG).show();
                    }
                }
        );
        spinZone.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        spinZone.showDropDown();
                    }
                }
        );
        spinWoreda.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        spinWoreda.showDropDown();
                    }
                }
        );
        spinTown.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        spinTown.showDropDown();
                    }
                }
        );
    }
    public boolean store(){
        region=((Spinner)findViewById(R.id.spinRegion)).getSelectedItem().toString();
        zone=spinZone.getText().toString();
        woreda=spinWoreda.getText().toString();
        town=spinTown.getText().toString();
        subcity=((EditText)findViewById(R.id.etSubcity)).getText().toString();
        kebele=((EditText)findViewById(R.id.etKebele)).getText().toString();
         enumArea=((EditText)findViewById(R.id.etEnumArea)).getText().toString();
         stationArea=((EditText)findViewById(R.id.etStationArea)).getText().toString();
        if(region.isEmpty() || zone.isEmpty() || woreda.isEmpty() || town.isEmpty() || subcity.isEmpty() || kebele.isEmpty()
                || enumArea.isEmpty() || stationArea.isEmpty())
            return false;

        return true;
    }
    public void populateZone(String region){
        HashMap data=new HashMap();
        data.put("type","zone");
        data.put("region",region);
        AsyncResponse responce=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s==null || s.isEmpty()){
                    Toast.makeText(AreaIdentificationActivity.this,"Some items not loaded, connection problem",Toast.LENGTH_LONG).show();
                }
                else if(s.contains("error")){
                    Toast.makeText(AreaIdentificationActivity.this,s,Toast.LENGTH_LONG).show();
                }
                else{
                    regions=s.split(",");
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(AreaIdentificationActivity.this,R.layout.support_simple_spinner_dropdown_item,regions);
                    spinZone.setAdapter(adapter);
                }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,data,responce);
        task.execute(Login.server+"/census/autocomplete.php");
    }
    public void populateWoreda(String zone){
        HashMap data=new HashMap();
        data.put("type","wereda");
        data.put("zone",zone);
        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
              if(s!=null && s.contains(",")){
                  String[] woredas=s.split(",");
                  ArrayAdapter<String> adapter=new ArrayAdapter<String>(AreaIdentificationActivity.this,R.layout.support_simple_spinner_dropdown_item,woredas);
                  spinWoreda.setAdapter(adapter);
              }
              else Toast.makeText(AreaIdentificationActivity.this,s,Toast.LENGTH_LONG).show();

            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,data,response);
        task.execute(Login.server+"/census/autocomplete.php");
    }
    public void populateTown(String woreda){
        HashMap data=new HashMap();

        data.put("type","town");
        data.put("zone",zone);
        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s!=null && s.contains(",")){
                    String[] towns=s.split(",");
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(AreaIdentificationActivity.this,R.layout.support_simple_spinner_dropdown_item,towns);
                    spinTown.setAdapter(adapter);
                }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,data,response);
        task.execute(Login.server+"/census/autocomplete.php");
    }

}
