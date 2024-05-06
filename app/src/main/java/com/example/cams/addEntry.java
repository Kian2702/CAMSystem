package com.example.cams;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addEntry extends AppCompatActivity {
    EditText encNam, datEnc, farNam, conNum, bara, birDay, edad, genDer, farLoc, totAre, com, typ, vari, plaAre, datPla, expHar, expYie, actHar, actYie, perDam, rem;
    Button create;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        create = findViewById(R.id.createbtn);

        encNam = findViewById(R.id.encoderName);
        datEnc = findViewById(R.id.dateEncoded);
        farNam = findViewById(R.id.farmerName);
        conNum = findViewById(R.id.contactNumber);
        bara = findViewById(R.id.barangay);
        birDay = findViewById(R.id.birthday);
        edad = findViewById(R.id.age);
        genDer = findViewById(R.id.gender);
        farLoc = findViewById(R.id.location);
        totAre = findViewById(R.id.area);
        com = findViewById(R.id.commodity);
        typ = findViewById(R.id.type);
        vari = findViewById(R.id.variety);
        plaAre = findViewById(R.id.plantedArea);
        datPla = findViewById(R.id.datePlanted);
        expHar = findViewById(R.id.exharvest);
        expYie = findViewById(R.id.exyield);
        actHar = findViewById(R.id.acharvest);
        actYie = findViewById(R.id.acyield);
        perDam = findViewById(R.id.damage);
        rem = findViewById(R.id.remarks);

        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        datEnc.setText(currentDate);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Entries");

                String en = encNam.getText().toString();
                String de = datEnc.getText().toString();
                String fa = farNam.getText().toString();
                String cn = conNum.getText().toString();
                String ba = bara.getText().toString();
                String bd = birDay.getText().toString();
                String ag = edad.getText().toString();
                String gd = genDer.getText().toString();
                String fl = farLoc.getText().toString();
                String ta = totAre.getText().toString();
                String cm = com.getText().toString();
                String tp = typ.getText().toString();
                String vr = vari.getText().toString();
                String pa = plaAre.getText().toString();
                String dp = datPla.getText().toString();
                String eh = expHar.getText().toString();
                String ey = expYie.getText().toString();
                String ah = actHar.getText().toString();
                String ay = actYie.getText().toString();
                String pd = perDam.getText().toString();
                String rm = rem.getText().toString();

                if (en.isEmpty() | de.isEmpty() | fa.isEmpty() | cn.isEmpty() | ba.isEmpty() | bd.isEmpty() | ag.isEmpty() | gd.isEmpty() | fl.isEmpty() | ta.isEmpty() | cm.isEmpty() | tp.isEmpty() | vr.isEmpty() | pa.isEmpty() | eh.isEmpty() | ey.isEmpty()) {
                    Toast.makeText(addEntry.this, "Please fill in all pre-harvest fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    EntryClass entryClass = new EntryClass(en, de, fa, cn, ba, bd, ag, gd, fl, ta, cm, tp, vr, pa, dp, eh, ey, ah, ay, pd, rm);

                    reference.child(de).setValue(entryClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(addEntry.this, "Successfully added.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
        });
    }
}