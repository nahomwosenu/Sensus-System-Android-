package amu.haimanot.censussystem4arbaminch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.*;

import java.util.HashMap;

public class EnumurationForm extends AppCompatActivity {

    private Button btnRegister;
    private Button btnCancel;
    private ScrollView verticalScroll;
    private EditText etId;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etMiddleName;
    private EditText etAge;
    private RadioButton radMale;
    private RadioButton radFemale;
    private EditText sPOB;
    private EditText etDurResidence;
    private Spinner sMartialStatus;
    private EditText etOrphanHood;
    private EditText etPreviosResidence;
    private RelativeLayout rLayout;
    private LinearLayout lLayout;
    private GridLayout gridLayout;
    private Spinner sReligion;

    //static String[] regions={"SNNPR","Amhara","Oromia","Tigray","Afar","Gambela","Harari","Diredewa","Addis Ababa","Benshangul Gumz"};
    static String[] religions={"protestant","orthodox","muslim","christian-other","other"};
    static String selectedRegion="SNNPR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etDurResidence=new EditText(this);
        etDurResidence.setHint("Duration of Residence(Yrs)");
        etOrphanHood=new EditText(this);
        etOrphanHood.setHint("Orphan Hood");
        etPreviosResidence=new EditText(this);
        etPreviosResidence.setHint("Previos Residence");
        etId=new EditText(this);
        etId.setHint("ID");
        etFirstName=new EditText(this);
        etLastName=new EditText(this);
        etMiddleName=new EditText(this);
        etAge=new EditText(this);
        etAge.setHint("Age");
        etDurResidence=new EditText(this);
        etDurResidence.setHint("Duration of Residence");
        etOrphanHood=new EditText(this);
        etOrphanHood.setHint("Orphan Hood");
        etPreviosResidence=new EditText(this);
        etPreviosResidence.setHint("Previous Residence");
        radMale=new RadioButton(this);
        radFemale=new RadioButton(this);
        radMale.setText("Male");
        radFemale.setText("Female");
        RadioGroup radSex=new RadioGroup(this);
        radSex.addView(radMale);
        radSex.addView(radFemale);

        sMartialStatus=new Spinner(this);
        sPOB=new EditText(this);
        sPOB.setHint("Place of Birth");
        etFirstName.setHint("First Name");
        etLastName.setHint("Last Name");
        etMiddleName.setHint("Middle Name");
        sReligion=new Spinner(this);
        //Inner layout definitions



        LinearLayout religionLayout=new LinearLayout(this);
        TextView religionView=new TextView(this);
        religionView.setText("Religion: ");
        religionLayout.addView(religionView);
        religionLayout.addView(sReligion);
        LinearLayout martialLayout=new LinearLayout(this);
        TextView martialView=new TextView(this);
        martialView.setText("Martial Status: ");
        martialLayout.addView(martialView);
        martialLayout.addView(sMartialStatus);

        gridLayout=new GridLayout(this);
        gridLayout.setColumnCount(1);
        gridLayout.setRowCount(18);
        gridLayout.addView(etId);
        gridLayout.addView(etFirstName);
        gridLayout.addView(etLastName);
        gridLayout.addView(etMiddleName);
        gridLayout.addView(radSex);
        gridLayout.addView(etAge);
        //gridLayout.addView(regionLayout);

        gridLayout.addView(sPOB);
        //gridLayout.addView(zoneLayout);
        gridLayout.addView(martialLayout);
        gridLayout.addView(religionLayout);
        gridLayout.addView(etDurResidence);
        gridLayout.addView(etOrphanHood);
        gridLayout.addView(etPreviosResidence);
        GridLayout tempLayout=new GridLayout(this);
        btnRegister=new Button(this);
        btnCancel=new Button(this);
        btnRegister.setText("Register");
        btnCancel.setText("Back");
        //handle button events right here
        tempLayout.addView(btnCancel);
        tempLayout.addView(btnRegister);
        gridLayout.addView(tempLayout);
        ScrollView scrollView=new ScrollView(this);
        scrollView.addView(gridLayout);
        setContentView(scrollView);
        ArrayAdapter<String> religionArray=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,religions);
        sReligion.setAdapter(religionArray);
        ArrayAdapter<String> martialArray=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,new String[]{"Married","Single","Taken","other"});
        sMartialStatus.setAdapter(martialArray);
//        HashMap data=new HashMap();
//        data.put("data","town");
//        data.put("region","SNNPR");
//        AsyncResponse response=new AsyncResponse() {
//            @Override
//            public void processFinish(String s) {
//                if(s!=null && s.contains(","))
//                    towns=s.split(",");
//                else Toast.makeText(EnumurationForm.this,"Unable to load some items, slow network connection?",Toast.LENGTH_LONG).show();
//            }
//        };
//        PostResponseAsyncTask townTask=new PostResponseAsyncTask(this,data,response);
//        townTask.execute(Login.server+"/census/data.php");
//        sRegion.setOnItemSelectedListener(
//                new Spinner.OnItemSelectedListener(){
//                    public void onItemSelected(AdapterView v,View view,int x,long b){
//                        selectedRegion=sRegion.getSelectedItem().toString();
//                        HashMap map=new HashMap();
//                        map.put("data","town");
//                        map.put("region",selectedRegion);
//                        AsyncResponse response1=new AsyncResponse() {
//                            @Override
//                            public void processFinish(String s) {
//                                towns=s.split(",");
//                                ArrayAdapter<String> adapter=new ArrayAdapter<>(EnumurationForm.this,R.layout.support_simple_spinner_dropdown_item,towns);
//                                sTown.setAdapter(adapter);
//                            }
//                        };
//                        PostResponseAsyncTask task=new PostResponseAsyncTask(EnumurationForm.this,map,response1);
//                        task.execute(Login.server+"/census/data.php");
//                    }
//                    public void onNothingSelected(AdapterView v){
//                        selectedRegion="";
//                    }
//                }
//        );
        btnRegister.setOnClickListener(
            new Button.OnClickListener(){
                public void onClick(View v){
                    register();
                }
            }
        );
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(EnumurationForm.this,ResidentialInfo.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

    }
    public void register(){
        String id=etId.getText().toString();
        String fn=etFirstName.getText().toString();
        String ln=etLastName.getText().toString();
        String mn=etMiddleName.getText().toString();
        String sex=radMale.isSelected() ? "male":"female";
        String age=etAge.getText().toString();
        String pob=sPOB.getText().toString();
        String region=AreaIdentificationActivity.region;
        String zone=AreaIdentificationActivity.zone;
        String town=AreaIdentificationActivity.town;
        String wereda= AreaIdentificationActivity.woreda;
        String kebele= AreaIdentificationActivity.kebele;
        String subcity=AreaIdentificationActivity.subcity;
        String SA=AreaIdentificationActivity.stationArea;
        String EA=AreaIdentificationActivity.enumArea;
        String religion=sReligion.getSelectedItem().toString();
        String mstatus=sMartialStatus.getSelectedItem().toString();
        String dur=etDurResidence.getText().toString();
        String orphan=etOrphanHood.getText().toString();
        String prev=etPreviosResidence.getText().toString();
        String houseSN=ResidentialInfo.houseSN;
        String houseHoldSN=ResidentialInfo.houseHoldSN;
        String houseType=ResidentialInfo.type;

        HashMap post=new HashMap();
        post.put("firstname",fn);
        post.put("lastname",ln);
        post.put("id",id);
        post.put("middlename",mn);
        post.put("sex",sex);
        post.put("age",age);
        post.put("pob",pob);
        post.put("region",region);
        post.put("zone",zone);
        post.put("town",town);
        post.put("religion",religion);
        post.put("mstatus",mstatus);
        post.put("duration_of_residence",dur);
        post.put("orphood",orphan);
        post.put("prevres",prev);
        post.put("housesn",houseSN);
        post.put("householdsn",houseHoldSN);
        post.put("housetype",houseType);
        post.put("kebele",kebele);
        post.put("wereda",wereda);
        post.put("subcity",subcity);
        post.put("sa",SA);
        post.put("ea",EA);
        AsyncResponse responce=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s!=null && s.contains("true")){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(EnumurationForm.this);
                    dialog.setTitle("Success");
                    dialog.setMessage("Person now waits for supervisor confirmation");
                    dialog.setPositiveButton("Finish",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(EnumurationForm.this,EnumeratorActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                    );
                    dialog.show();
                }
                if(s!=null && s.contains("Duplicate")){
                    String[] mixed=s.split("'");
                    String error=mixed[1];
                    Toast.makeText(EnumurationForm.this,error+" already exists",Toast.LENGTH_LONG).show();
                }
                else {

                    AlertDialog.Builder dialog=new AlertDialog.Builder(EnumurationForm.this);
                    dialog.setTitle("Error");
                    dialog.setMessage("Connection to server failed at the moment, check your connection and try again");
                    dialog.show();
                    Toast.makeText(EnumurationForm.this,s,Toast.LENGTH_LONG).show();
                }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,post,responce);
        task.execute(Login.server+"/census/form.php");
    }
}
