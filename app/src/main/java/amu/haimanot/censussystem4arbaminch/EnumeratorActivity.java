package amu.haimanot.censussystem4arbaminch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnumeratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enumerator);
        findViewById(R.id.btnFillform).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(EnumeratorActivity.this, AreaIdentificationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        findViewById(R.id.btnSearch).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(EnumeratorActivity.this,SearchActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

}
