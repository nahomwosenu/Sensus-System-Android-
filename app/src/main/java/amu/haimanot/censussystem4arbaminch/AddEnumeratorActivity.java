package amu.haimanot.censussystem4arbaminch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kosalgeek.asynctask.*;

import java.util.HashMap;

public class AddEnumeratorActivity extends AppCompatActivity implements AsyncResponse {

    Button btnRegister;
    Button btnCancel;
    EditText txtFirstName;
    EditText txtLastName;
    EditText txtMiddleName;
    EditText txtUsername;
    EditText txtAge;
    EditText txtID;
    RadioButton radMale;
    RadioButton radFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enumerator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        txtFirstName=(EditText)findViewById(R.id.txtFirstname);
        txtLastName=(EditText)findViewById(R.id.txtLastname);
        txtMiddleName=(EditText)findViewById(R.id.txtMiddleName);
        txtAge=(EditText)findViewById(R.id.txtAge);
        txtUsername=(EditText)findViewById(R.id.txtUsername);
        radMale=(RadioButton)findViewById(R.id.radMale);
        radFemale=(RadioButton)findViewById(R.id.radFemale);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        txtID=(EditText)findViewById(R.id.txtID);
        btnRegister.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        String fn=txtFirstName.getText().toString();
                        String ln=txtLastName.getText().toString();
                        String mn=txtMiddleName.getText().toString();
                        String user=txtUsername.getText().toString();
                        String age=txtAge.getText().toString();
                        String sex="male";

                        String id=txtID.getText().toString();
                        String pass="12345";//default password for all new accounts
                        register(fn,ln,mn,user,age,sex,pass,id);
                    }
                }
        );
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtFirstName.setText("");
                        txtLastName.setText("");
                        txtAge.setText("");
                        txtMiddleName.setText("");
                        txtUsername.setText("");
                    }
                }
        );
    }
    public void register(String fn,String ln,String mn,String user,String age,String sex,String pass,String id){
        HashMap data=new HashMap();
        data.put("firstname",fn);
        data.put("lastname",ln);
        data.put("middlename",mn);
        data.put("username",user);
        data.put("age",age);
        data.put("sex",sex);
        data.put("password",pass);
        data.put("id",id);
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,data);
        task.execute(Login.server+"/census/register_enum.php");
    }

    @Override
    public void processFinish(String s) {
      if(s.isEmpty()){
          AlertDialog.Builder dialog=new AlertDialog.Builder(this);
          dialog.setTitle("Error");
          dialog.setMessage("Couldn\'t connect to Census server at the moment");

          dialog.show();
      }
      else if(s.contains("true")){
          AlertDialog.Builder dialog=new AlertDialog.Builder(this);
          dialog.setTitle("Success");
          dialog.setMessage("Enumerator is succesfully registered");
          dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  Intent intent=new Intent(AddEnumeratorActivity.this,SupervisorActivity.class);
                  startActivity(intent);
                  finish();
              }
          });
          dialog.show();
      }
      else if (s.contains("error")){
          AlertDialog.Builder dialog=new AlertDialog.Builder(this);
          dialog.setTitle("Error");
          dialog.setMessage("There are some errors in the form please fix and try again!");
          dialog.show();

      }
       Toast.makeText(this,s,Toast.LENGTH_LONG).show();

    }
}
