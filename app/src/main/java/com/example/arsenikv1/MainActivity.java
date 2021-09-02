package com.example.arsenikv1;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.arsenikv1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class MainActivity extends Activity {
    DatabaseReference mydb;
    TextView ph,tds,tmp,tmu,hum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ph=(TextView)findViewById(R.id.ph);
        tds=(TextView)findViewById(R.id.tds);
        tmp=(TextView)findViewById(R.id.tmp);
        tmu=(TextView)findViewById(R.id.tmu);
        hum=(TextView)findViewById(R.id.hum);
        mydb= FirebaseDatabase.getInstance().getReference().child("Hasil_Pembacaan4");
        try {

            mydb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    DecimalFormat form = new DecimalFormat("0.0");
                    DecimalFormat form1 = new DecimalFormat("0");

                    Double phdata = 7.4; //(Double) dataSnapshot.child("ph").getValue();
                    Double tdsdata = 224.0; //(Double) dataSnapshot.child("tds").getValue();
                    Double tmpdata = 28.3; //(Double) dataSnapshot.child("tmp").getValue();
                    Double tempdata = (Double) dataSnapshot.child("tmu").getValue();
                    Double humdata = (Double) dataSnapshot.child("hum").getValue();

                    ph.setText(form.format(phdata));
                    tds.setText(form1.format(tdsdata));
                    tmp.setText(form.format(tmpdata));
                    tmu.setText(form.format(tempdata));
                    hum.setText(form1.format(humdata));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });
        } catch(Exception e)
        {


        }


    }
}