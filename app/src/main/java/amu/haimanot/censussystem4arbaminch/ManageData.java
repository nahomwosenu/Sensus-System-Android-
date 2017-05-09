package amu.haimanot.censussystem4arbaminch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

public class ManageData extends AppCompatActivity {

    private LinearLayout tabData;
    private LinearLayout tabEnum;
    private LinearLayout tabReport;
    private Button btnDelete;
    private Button btnEdit;
    private GridLayout dataGrid;
    private EditText etSearch;
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
        etSearch.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        search(etSearch.getText().toString());
                        return true;
                    }
                }
        );
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
        //preprocessing complete
    
    }

}
