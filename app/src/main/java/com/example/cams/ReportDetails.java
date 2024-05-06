package com.example.cams;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportDetails extends AppCompatActivity {

    TextView detENam, detDEnc, detFNam, detCNum, detBar, detBir, detAge, detGen, detFLoc, detTAre, detCom, detTyp, detVar, detPAre, detEYie, detAYie, detPDam, detDPla, detEHar, detAHar, detRem, backbtn;
    String key = "";
    Button deletebtn, updatebtn;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repdet);

        detENam = findViewById(R.id.recENam);
        detDEnc = findViewById(R.id.recDEnc);
        detFNam = findViewById(R.id.recFNam);
        detCNum = findViewById(R.id.recCNum);
        detBar = findViewById(R.id.recBar);
        detBir = findViewById(R.id.recBir);
        detAge = findViewById(R.id.recAge);
        detGen = findViewById(R.id.recGen);
        detFLoc = findViewById(R.id.recFLoc);
        detTAre = findViewById(R.id.recTAre);
        detCom = findViewById(R.id.recCom);
        detTyp = findViewById(R.id.recTyp);
        detVar = findViewById(R.id.recVar);
        detPAre = findViewById(R.id.recPAre);
        detEYie = findViewById(R.id.recEYie);
        detAYie = findViewById(R.id.recAYie);
        detPDam = findViewById(R.id.recPDam);
        detDPla = findViewById(R.id.recDPla);
        detEHar = findViewById(R.id.recEHar);
        detAHar = findViewById(R.id.recAHar);
        detRem = findViewById(R.id.recRem);

        backbtn = findViewById(R.id.back);
        deletebtn = findViewById(R.id.deletebtn);
        updatebtn = findViewById(R.id.updatebtn);
        builder = new AlertDialog.Builder(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            detENam.setText(bundle.getString("encNam"));
            detDEnc.setText(bundle.getString("datEnc"));
            detFNam.setText(bundle.getString("farNam"));
            detCNum.setText(bundle.getString("conNum"));
            detBar.setText(bundle.getString("bara"));
            detBir.setText(bundle.getString("birDay"));
            detAge.setText(bundle.getString("edad"));
            detGen.setText(bundle.getString("genDer"));
            detFLoc.setText(bundle.getString("farLoc"));
            detTAre.setText(bundle.getString("totAre"));
            detCom.setText(bundle.getString("com"));
            detTyp.setText(bundle.getString("typ"));
            detVar.setText(bundle.getString("vari"));
            detPAre.setText(bundle.getString("plaAre"));
            detEYie.setText(bundle.getString("expYie"));
            detAYie.setText(bundle.getString("actYie"));
            detPDam.setText(bundle.getString("perDam"));
            detDPla.setText(bundle.getString("datPla"));
            detEHar.setText(bundle.getString("expHar"));
            detAHar.setText(bundle.getString("actHar"));
            detRem.setText(bundle.getString("rem"));
            key = bundle.getString("Key");
        }

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), homepage.class);
                startActivity(intent);
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Warning!").setMessage("Do you wish to proceed with the action?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Entries");

                                reference.child(key).removeValue();
                                startActivity(new Intent(getApplicationContext(), homepage.class));
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), updateEntry.class)
                        .putExtra("encNam", detENam.getText().toString())
                        .putExtra("datEnc", detDEnc.getText().toString())
                        .putExtra("farNam", detFNam.getText().toString())
                        .putExtra("conNum", detCNum.getText().toString())
                        .putExtra("bara", detBar.getText().toString())
                        .putExtra("birDay", detBir.getText().toString())
                        .putExtra("edad", detAge.getText().toString())
                        .putExtra("genDer", detGen.getText().toString())
                        .putExtra("farLoc", detFLoc.getText().toString())
                        .putExtra("totAre", detTAre.getText().toString())
                        .putExtra( "com", detCom.getText().toString())
                        .putExtra("typ", detTyp.getText().toString())
                        .putExtra("vari", detVar.getText().toString())
                        .putExtra("expYie", detEYie.getText().toString())
                        .putExtra("actYie", detAYie.getText().toString())
                        .putExtra("plaAre", detPAre.getText().toString())
                        .putExtra("perDam", detPDam.getText().toString())
                        .putExtra("datPla", detDPla.getText().toString())
                        .putExtra("expHar", detEHar.getText().toString())
                        .putExtra("actHar", detAHar.getText().toString())
                        .putExtra("rem", detRem.getText().toString());
                startActivity(intent);
            }
        });
    }
}