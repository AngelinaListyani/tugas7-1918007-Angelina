package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainCreate extends AppCompatActivity {
    private com.example.sqlite.MyDatabase db;
    private EditText Enama, Ealamat ;
    private String Snama, Salamat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        db = new com.example.sqlite.MyDatabase(this);
        Enama = (EditText) findViewById(R.id.create_nama);
        Ealamat = (EditText) findViewById(R.id.create_alamat);
        Button btnCreate = (Button)
                findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Salamat = String.valueOf(Ealamat.getText());if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi nama pasien",
                            Toast.LENGTH_SHORT).show();
                }
                else if (Salamat.equals("")) {
                    Ealamat.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi alamat pasien",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Enama.setText("");
                    Ealamat.setText("");
                    Toast.makeText(MainCreate.this, "Data telah ditambah",
                            Toast.LENGTH_SHORT).show();
                    db.CreatePasien(new Pasien(null, Snama, Salamat ));
                    Intent a = new Intent(MainCreate.this,
                            MainActivity.class);
                    startActivity(a);
                }
            }
        });
    }
}
