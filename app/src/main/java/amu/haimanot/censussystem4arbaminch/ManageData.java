package amu.haimanot.censussystem4arbaminch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ManageData extends AppCompatActivity {

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
        TableRow row=new TableRow(this);
        TableLayout layout=(TableLayout)findViewById(R.id.tblFormData);
        TextView title1=new TextView(this);
        title1.setText("Date");
        TextView title2=new TextView(this);
        title2.setText("Area");
        TextView title3=new TextView(this);
        title3.setText("No. Emurated");
        row.addView(title1);
        row.addView(title2);
        row.addView(title3);
        layout.addView(row);



    }

}
