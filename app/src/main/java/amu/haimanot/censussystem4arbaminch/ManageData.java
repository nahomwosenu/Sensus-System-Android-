package amu.haimanot.censussystem4arbaminch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class ManageData extends AppCompatActivity {

    private LinearLayout tabData;
    private LinearLayout tabEnum;
    private LinearLayout tabReport;
    private Button btnDelete;
    private Button btnEdit;
    private TableLayout dataGrid;
    private EditText etSearch;
    private EditText[] fnames;
    private EditText[] lnames;
    private EditText[] mnames;
    private EditText[] ids;
    private CheckBox[] chkBox;
    private LinearLayout[] rowLayout;
    GridLayout tempLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        etSearch=(EditText)findViewById(R.id.etSearchInput);
        tabData=(LinearLayout)findViewById(R.id.Data);
        tabEnum=(LinearLayout)findViewById(R.id.Enumerator);
        tabReport=(LinearLayout)findViewById(R.id.Report);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        dataGrid=(TableLayout) findViewById(R.id.dataGrid);

        etSearch.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_NULL
                                && event.getAction() == KeyEvent.ACTION_DOWN) {
                            search(etSearch.getText().toString());
                            return true;
                        }
                        return false;
                    }
                }
        );
        btnDelete.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        delete();
                    }
                }
        );
    }
    static boolean exec=false;
    public void delete(){
        String id="";
        int i=0;
        for(CheckBox b:chkBox){
            if(b.isChecked()){
                id+=b.getText().toString()+",";
                i++;
            }
        }
        HashMap map=new HashMap();
        map.put("request","delete");
        map.put("data",id);
        map.put("length",i);
        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s==null || s.isEmpty())
                    //Toast.makeText(ManageData.this,"Connection failed",Toast.LENGTH_LONG).show();
                    Log.d("APP","Connection failed");
                else if(s.contains("error") || s.contains("Error") || s.contains("false")){
                    Log.d("APP","Response: "+s);
                    //Toast.makeText(ManageData.this,"Error occured, please try again letter",Toast.LENGTH_LONG).show();
                }
                else if(s.contains("true")){
                    Toast.makeText(ManageData.this,"Items deleted successfully",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ManageData.this,ManageData.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        final PostResponseAsyncTask task=new PostResponseAsyncTask(this,map,response);
        if(i<1){
            Toast.makeText(this,"No items selected",Toast.LENGTH_LONG).show();
            return;
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure to delete "+i+" items?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        exec=true;

                    }
                }
        );
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(ManageData.this,"Canceled",Toast.LENGTH_SHORT).show();
                        exec=false;
                    }
                }
        );
        builder.show();
        if(exec) {

            Toast.makeText(this,"Excec is true & task executed",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"Deletion Canceled",Toast.LENGTH_LONG).show();
        task.execute(Login.server + "/census/get.php");
    }
    public void search(String term){
        HashMap map=new HashMap();
        //Pre process the input term
        if(term.contains(" ")){
            String[] temp=term.split(" ");
            if(temp.length==2){
                map.put("mode","2");
                map.put("firstname",temp[0]);
                map.put("lastname",temp[1]);
            }
            else if(temp.length==3){
                map.put("mode","3");
                map.put("firstname",temp[0]);
                map.put("lastname",temp[1]);
                map.put("middlename",temp[2]);
            }
        }
        else if(term.startsWith("p")){
            map.put("mode",4);
            map.put("id",term);
        }
        else{
            map.put("mode","1");
            map.put("term",term);
        }
        map.put("request","search");

        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s==null || s.isEmpty()){
                    Valid.showDialog(ManageData.this,"Connection Failed","Conection to server failed at the momment");
                }
                else if(s.contains("error") || s.contains("Error")){
                    Log.d("APP","Response: "+s);
                    Valid.showDialog(ManageData.this,"Error","Sorry, An Error occured, we'll try to fix it soon.");
                }
                else{
                    Log.d("APP","Response: "+s);
                    String[] row=s.split(";");
                    int total=row.length;
                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    TableRow tableRow=new TableRow(ManageData.this);
                    dataGrid.removeAllViews();
                    fnames=new EditText[total];
                    lnames=new EditText[total];
                    mnames=new EditText[total];
                    ids=new EditText[total];
                    rowLayout=new LinearLayout[total];
                    chkBox=new CheckBox[total];
                    LinearLayout layout=new LinearLayout(ManageData.this);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView view1=new TextView(ManageData.this);
                    view1.setText("ID");
                    TextView view2=new TextView(ManageData.this);
                    view2.setText("Firstname");
                    TextView view3=new TextView(ManageData.this);
                    view3.setText("Lastname");
                    TextView view4=new TextView(ManageData.this);
                    view4.setText("MiddleName");
                    layout.addView(view1);
                    layout.addView(view2);
                    layout.addView(view3);
                    tableRow.addView(layout);

                    EditText.OnEditorActionListener listener=new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            return onEditComplete(v,actionId,event);
                        }
                    };

                    int counter=0;
                    for(String d:row){
                        String[] data=d.split(",");
                        String fname=data[0];
                        String lname=data[1];
                        String mname=data[2];
                        String id=data[3];
                        chkBox[counter]=new CheckBox(ManageData.this);
                        chkBox[counter].setText(id);
                        //ids[counter]=new EditText(ManageData.this);
                        fnames[counter]=new EditText(ManageData.this);
                        fnames[counter].setText(fname);
                        fnames[counter].setHint("firstname for "+id);
                        fnames[counter].setOnEditorActionListener(listener);
                        lnames[counter]=new EditText(ManageData.this);
                        lnames[counter].setText(lname);
                        lnames[counter].setHint("lastname for "+id);
                        lnames[counter].setOnEditorActionListener(listener);
                        mnames[counter]=new EditText(ManageData.this);
                        mnames[counter].setText(mname);
                        mnames[counter].setHint("middlename for "+id);
                        mnames[counter].setOnEditorActionListener(listener);
                        rowLayout[counter]=new LinearLayout(ManageData.this);
                        rowLayout[counter].setOrientation(LinearLayout.HORIZONTAL);
                        rowLayout[counter].addView(chkBox[counter]);
                        rowLayout[counter].addView(fnames[counter]);
                        rowLayout[counter].addView(lnames[counter]);
                        rowLayout[counter].addView(mnames[counter]);
                        tableRow=new TableRow(ManageData.this);
                        tableRow.addView(rowLayout[counter]);
                        dataGrid.addView(tableRow);
                        counter++;
                    }
                }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,map,response);
        task.execute(Login.server+"/census/get.php");
    
    }
    public boolean onEditComplete(final TextView etText, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_NULL
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d("APP","Hint: "+etText.getHint().toString());
            String[] hint=etText.getHint().toString().split(" ");
            final String id=hint[2];
            final String column=hint[0];
            String value=etText.getText().toString();
            String key="id";
            String keyValue=id;
            if(id==null || id.isEmpty())
                return false;
            final HashMap map=new HashMap();
            map.put("request","update");
            map.put("table","person");
            map.put("column",column);
            map.put("value",value);
            map.put("key",key);
            map.put("keyValue",keyValue);
            final AsyncResponse response=new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    if(s==null || s.isEmpty()) {
                        Toast.makeText(ManageData.this, "Connection failed, name not updated", Toast.LENGTH_LONG).show();
                    }
                    else if(s.contains("error") || s.contains("Error") || s.contains("false")){
                        Toast.makeText(ManageData.this,"An error occured, please try again letter",Toast.LENGTH_LONG).show();
                    }
                    else if(s.contains("true")){
                        Toast.makeText(ManageData.this,"Data updated successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            };
            PostResponseAsyncTask task=new PostResponseAsyncTask(this,map,response);
            task.execute(Login.server+"/census/get.php");
        }
        return true;
    }
    public int getIndexOf(String id){
        for(int i=0;i<ids.length;i++){
            if(ids[i].equals(id))
                return i;
        }
        return -1;
    }
    @Override
    public void onBackPressed(){

        Intent intent=null;
        if(Valid.userType.equals("supervisor"))
            intent=new Intent(ManageData.this,SupervisorActivity.class);
        else intent=new Intent(ManageData.this,EnumeratorActivity.class);
        startActivity(intent);
        finish();
    }
}
