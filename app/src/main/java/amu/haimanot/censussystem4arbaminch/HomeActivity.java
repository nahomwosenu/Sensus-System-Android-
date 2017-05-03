package amu.haimanot.censussystem4arbaminch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btnToLogin).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(HomeActivity.this,Login.class));
                        finish();
                    }
                }
        );
        findViewById(R.id.btnExit).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        System.exit(0);
                    }
                }
        );
        findViewById(R.id.btnAbout).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder dialog=new AlertDialog.Builder(HomeActivity.this);
                        dialog.setTitle("About Us");
                        dialog.setMessage("This project is developed as a final year project for Information Technology Department of " +
                                "Arbaminch University \n by 4th year IT students" +
                                "\nHaimanot Bitew \nTsion Kassu\nEden Bedilu\nMelat Mulugeta\nZayed Kaleb\nSubagadis Alemayehu");
                        dialog.setPositiveButton("Back to Home", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        dialog.setCancelable(true);
                        dialog.show();
                    }
                }
        );
        findViewById(R.id.btnContacts).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder dialog=new AlertDialog.Builder(HomeActivity.this);
                        dialog.setTitle("Contact us");
                        dialog.setMessage("You can find as at\n" +
                                "Email: mail@amu.edu.et\nTel: +25111452290");
                        dialog.show();
                    }
                }
        );

    }
}
