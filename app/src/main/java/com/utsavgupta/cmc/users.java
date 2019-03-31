package com.utsavgupta.cmc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class users extends AppCompatActivity {
Button patient,pathologist,attendant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        patient=(Button)findViewById(R.id.button2);
        pathologist=(Button)findViewById(R.id.button4);
        attendant=(Button)findViewById(R.id.button3);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(users.this,RecyclerPatientRecord.class));
            }
        });
        pathologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users.this,Pathologist.class));
            }
        });
        attendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(users.this,BlockTabbedteacher.class));
            }
        });
    }
}
