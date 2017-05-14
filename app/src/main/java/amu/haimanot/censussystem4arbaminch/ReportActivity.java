package amu.haimanot.censussystem4arbaminch;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class ReportActivity extends AppCompatActivity {

    private TextView txtReport;
    private Button btnPrint;
    private Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        txtReport=(TextView)findViewById(R.id.txtReport);
        btnPrint=(Button)findViewById(R.id.btnPrint);
        btnRefresh=(Button)findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        loadReport();
                    }
                }
        );
        loadReport();
    }
    public void loadReport(){
        HashMap map=new HashMap();
        map.put("request","report");
        map.put("type","general");
        AsyncResponse response=new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s==null || s.isEmpty()){
                    Toast.makeText(ReportActivity.this,"What a shame? No connection could be made to census server at the moment",Toast.LENGTH_LONG)
                    .show();
                }
                else if(s.contains("error") || s.contains("Error")){
                    Log.d("APP","Resp1: "+s);
                    Toast.makeText(ReportActivity.this,"An Internal Error occured, we'll try to fix it soon",Toast.LENGTH_LONG).show();
                }
                else{
                    Log.d("APP","Resp2: "+s);
                    //start filtering result
                    String[] temp=s.split("-");
                    String data="";
                    for(String t:temp)
                        data+=t+"\n";
                    txtReport.setText(data);
                }
            }
        };
        PostResponseAsyncTask task=new PostResponseAsyncTask(this,map,response);
        task.execute(Login.server+"/census/report.php");
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(ReportActivity.this,SupervisorActivity.class));
        finish();
    }
}
