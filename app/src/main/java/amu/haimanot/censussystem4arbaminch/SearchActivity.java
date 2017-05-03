package amu.haimanot.censussystem4arbaminch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    private Button btnSearch;
    private Button btnCancel;
    private AutoCompleteTextView etSearch;
    private TextView lblResult;
    private static Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etSearch=(AutoCompleteTextView)findViewById(R.id.etSearch);
        btnSearch=(Button)findViewById(R.id.btnSearch);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        lblResult=(TextView)findViewById(R.id.labelResult);
        etSearch.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if(!etSearch.getText().toString().isEmpty() && event.getAction()==KeyEvent.KEYCODE_SPACE){
                            String term=etSearch.getText().toString();
                            autocomplete(term);
                        }
                        return true;
                    }
                }
        );
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String term=etSearch.getText().toString();
                        if(term.contains(" ")) {
                            String[] names =term.split(" ");
                            Log.d("APP","length="+names.length);
                            if(names.length==3){
                                String fname=names[0];
                                String lname=names[1];
                                String mname=names[2];
                                person=null;
                                searchPerson(fname,lname,mname);
                                Log.d("APP","name searched");
                                if(person!=null){
                                    String result="Name: "+person.getFirstName()+" "+person.getLastName()+" "+person.getMiddleName();
                                    result=result+"\nAge: "+person.getAge()+" Sex: \n"+person.getSex();
                                    result=result+"\nHome Town: "+person.getTown();
                                    result=result+"\nMartial Status: "+person.getMartialStatus();
                                    lblResult.setText(result);
                                    Log.d("APP","Result: "+person.getFirstName());
                                }
                            }
                            else if(names.length==2){
                                String fname=names[0];
                                String lname=names[1];
                                searchPerson(fname,lname);

                            }
                        }
                        else
                        autocomplete(term);
                    }
                }
        );
    }
    public void searchPerson(String fname,String lname){
       HashMap map=new HashMap();
        map.put("firstname",fname);
        map.put("lastname",lname);
        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s==null || s.contains("error")){
                    Toast.makeText(SearchActivity.this,"no results found, check your internet connection",Toast.LENGTH_LONG).show();
                }
                else if(s!=null && s.contains(",") && s.contains(";")){
                    String[] persons=s.split(";");
                    String[][] names=new String[persons.length][3];
                    String[] realNames=new String[persons.length];
                    int i=0;
                    for(String p:persons){
                        String[] temp=p.split(",");
                        names[i][0]=temp[0];
                        names[i][1]=temp[1];
                        names[i][2]=temp[2];
                        realNames[i]=names[i][0]+" "+names[i][1]+" "+names[i][2];
                        i++;
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchActivity.this,R.layout.support_simple_spinner_dropdown_item,realNames);
                    etSearch.setAdapter(adapter);
                    etSearch.showDropDown();
                }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,map,response);
        task.execute(Login.server+"/census/search.php");
    }
    public void searchPerson(String fname,String lname,String mname){
        HashMap map=new HashMap();
        map.put("firstname",fname);
        map.put("lastname",lname);
        map.put("middlename",mname);
        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s==null || s.isEmpty()){
                    Toast.makeText(SearchActivity.this,"No person found, check your internet connection",Toast.LENGTH_LONG).show();
                }
                else if(s!=null && s.contains(",") && !s.contains("error")){
                    String[] data=s.split(",");
                    Person p=new Person(data[7],data[0],data[1],data[2],data[4],Integer.parseInt(data[3]));
                    p.setMartialStatus(data[8]);
                    p.setTown(data[9]);
                    person=p;
                    Log.d("APP","name searched");
                    if(p!=null){
                        String result="Name: "+person.getFirstName()+" "+person.getLastName()+" "+person.getMiddleName();
                        result=result+"\nAge: "+person.getAge()+" Sex: \n"+person.getSex();
                        result=result+"\nHome Town: "+person.getTown();
                        result=result+"\nMartial Status: "+person.getMartialStatus();

                        AlertDialog.Builder dialog=new AlertDialog.Builder(SearchActivity.this);
                        dialog.setTitle("Search Result");
                        dialog.setMessage(result);
                        Log.d("APP","Result: "+person.getFirstName());
                    }
                }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,map,response);
        task.execute(Login.server+"/census/search.php");

    }
    public void autocomplete(String term){
        if(term==null || term.isEmpty())
            return;
        HashMap data=new HashMap();
        data.put("term",term);
        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                    if(s==null || s.isEmpty())
                        Toast.makeText(SearchActivity.this,"Connection Problem",Toast.LENGTH_LONG).show();
                     else if(s.contains(",") && !s.contains("<")){
                        String[] persons=s.split(";");
                        String[][] names=new String[persons.length][3];
                        String[] realNames=new String[persons.length];
                        int i=0;
                        for(String p:persons){
                            String[] temp=p.split(",");
                            names[i][0]=temp[0];
                            names[i][1]=temp[1];
                            names[i][2]=temp[2];
                            realNames[i]=names[i][0]+" "+names[i][1]+" "+names[i][2];
                            i++;
                        }
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchActivity.this,R.layout.support_simple_spinner_dropdown_item,realNames);
                        etSearch.setAdapter(adapter);
                        etSearch.showDropDown();
                    }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,data,response);
        task.execute(Login.server+"/census/search.php");
    }

}
