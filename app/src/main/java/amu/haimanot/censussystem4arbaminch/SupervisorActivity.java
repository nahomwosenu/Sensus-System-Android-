package amu.haimanot.censussystem4arbaminch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SupervisorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((Button)findViewById(R.id.btnAddEnumerator)).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent launch=new Intent(SupervisorActivity.this,AddEnumeratorActivity.class);
                        startActivity(launch);
                        finish();
                    }
                }

        );
        ((Button)findViewById(R.id.btnManage)).setOnClickListener(
          new Button.OnClickListener(){
              public void onClick(View v){
                  Intent launch=new Intent(SupervisorActivity.this,ManageData.class);
                  startActivity(launch);
                  finish();
              }
          }
        );
        ((Button)findViewById(R.id.btnSearch)).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent launch=new Intent(SupervisorActivity.this,SearchActivity.class);
                        startActivity(launch);
                        finish();
                    }
                }
        );
        findViewById(R.id.btnReport).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent=new Intent(SupervisorActivity.this,ReportActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

    }

}
