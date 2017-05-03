package amu.haimanot.censussystem4arbaminch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.*;

import java.util.HashMap;

public class Login extends AppCompatActivity implements AsyncResponse {

    Button btnLogin;
    EditText etUsername;
    EditText etPassword;
    static final String server="http://10.0.3.2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String username=etUsername.getText().toString();
                        String password=etPassword.getText().toString();
                        login(username,password);
                        findViewById(R.id.progressLogin).setVisibility(View.VISIBLE);
                    }
                }
        );
    }
  public boolean login(String username,String password){
      HashMap data=new HashMap();
      data.put("username",username);
      data.put("password",password);
      PostResponseAsyncTask task=new PostResponseAsyncTask(this,data);
      task.execute(server+"/census/login.php");
      return true;
  }

    @Override
    public void processFinish(String s) {
     findViewById(R.id.progressLogin).setVisibility(View.INVISIBLE);
        if(!s.isEmpty())
        Toast.makeText(this,"Result: "+s,Toast.LENGTH_LONG).show();
        if(s.isEmpty())
            Toast.makeText(this,"Can not connect to server at the moment",Toast.LENGTH_LONG).show();
        else if(s.contains("-1") || s.equalsIgnoreCase("false")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Login Failed");
            dialog.setMessage("Incorrect Username or Password");
            dialog.show();
        }
        else if(s!=null) {
            if (s.contains("supervisor")) {
                Intent intent = new Intent(this, SupervisorActivity.class);
                startActivity(intent);
                finish();
            } else if (s.contains("enu")) {
                Intent intent = new Intent(this, EnumeratorActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
