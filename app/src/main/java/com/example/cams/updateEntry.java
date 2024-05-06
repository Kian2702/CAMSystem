package com.example.cams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.HashMap;

public class updateEntry extends AppCompatActivity {
    EditText encNam, datEnc, farNam, conNum, bara, birDay, edad, genDer, farLoc, totAre, com, typ, vari, plaAre, datPla, expHar, expYie, actHar, actYie, perDam, rem;
    String en, de, fn, cn, bg, bd, ed, gd, fl, ta, cm, ty, vr, pa, dp, eh, ey, ah, ay, pd, rm;
    Button update;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update_entry);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Entries");

        update = findViewById(R.id.updatebtn);

        encNam = findViewById(R.id.encoderName);
        datEnc = findViewById(R.id.dateEncoded);
        farNam = findViewById(R.id.farmerName);
        conNum = findViewById(R.id.contactNumber);
        bara = findViewById(R.id.barangay);
        birDay  = findViewById(R.id.birthday);
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

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            encNam.setText(bundle.getString("encNam"));
            datEnc.setText(bundle.getString("datEnc"));
            farNam.setText(bundle.getString("farNam"));
            conNum.setText(bundle.getString("conNum"));
            bara.setText(bundle.getString("bara"));
            birDay.setText(bundle.getString("birDay"));
            edad.setText(bundle.getString("edad"));
            genDer.setText(bundle.getString("genDer"));
            farLoc.setText(bundle.getString("farLoc"));
            totAre.setText(bundle.getString("totAre"));
            com.setText(bundle.getString("com"));
            typ.setText(bundle.getString("typ"));
            vari.setText(bundle.getString("vari"));
            plaAre.setText(bundle.getString("plaAre"));
            expYie.setText(bundle.getString("expYie"));
            actYie.setText(bundle.getString("actYie"));
            perDam.setText(bundle.getString("perDam"));
            datPla.setText(bundle.getString("datPla"));
            expHar.setText(bundle.getString("expHar"));
            actHar.setText(bundle.getString("actHar"));
            rem.setText(bundle.getString("rem"));
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                Intent intent = new Intent(getApplicationContext(), homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void updateData(){
        en = encNam.getText().toString();
        de = datEnc.getText().toString();
        fn = farNam.getText().toString();
        cn = conNum.getText().toString();
        bg = bara.getText().toString();
        bd = birDay.getText().toString();
        ed = edad.getText().toString();
        gd = genDer.getText().toString();
        fl = farLoc.getText().toString();
        ta = totAre.getText().toString();
        cm = com.getText().toString();
        ty = typ.getText().toString();
        vr = vari.getText().toString();
        pa = plaAre.getText().toString();
        dp = datPla.getText().toString();
        eh = expHar.getText().toString();
        ey = expYie.getText().toString();
        ah = actHar.getText().toString();
        ay = actYie.getText().toString();
        pd = perDam.getText().toString();
        rm = rem.getText().toString();

        HashMap entryClass = new HashMap();
        entryClass.put("encNam", en);
        entryClass.put("datEnc", de);
        entryClass.put("farNam", fn);
        entryClass.put("conNum", cn);
        entryClass.put("bara", bg);
        entryClass.put("birDay", bd);
        entryClass.put("edad", ed);
        entryClass.put("genDer", gd);
        entryClass.put("farLoc", fl);
        entryClass.put("totAre", ta);
        entryClass.put("com", cm);
        entryClass.put("typ", ty);
        entryClass.put("vari", vr);
        entryClass.put("plaAre", pa);
        entryClass.put("datPla", dp);
        entryClass.put("expHar", eh);
        entryClass.put("expYie", ey);
        entryClass.put("actHar", ah);
        entryClass.put("actYie", ay);
        entryClass.put("perDam", pd);
        entryClass.put("rem", rm);


        reference.child(de).updateChildren(entryClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    finish();
                }
            }
        });
    }
}