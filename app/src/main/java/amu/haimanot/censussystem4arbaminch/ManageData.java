package amu.haimanot.censussystem4arbaminch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
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
        row.setBackgroundColor(Color.GRAY);
        TableRow.LayoutParams params=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        row.setLayoutParams(params);

        TableLayout layout=(TableLayout)findViewById(R.id.tblFormData);
        layout.setBackgroundColor(Color.GREEN);
        TextView title1=new TextView(this);
        title1.setText("Date");
        TextView title2=new TextView(this);
        title2.setText("Area");
        TextView title3=new TextView(this);
        title3.setText("No. Emurated");

        GridLayout tempLayout=new GridLayout(this);
        tempLayout.setColumnCount(3);
        tempLayout.setRowCount(1);
        tempLayout.addView(title1);
        tempLayout.addView(title2);
        tempLayout.addView(title3);

        row.addView(tempLayout);
        layout.addView(row);



    }

}
