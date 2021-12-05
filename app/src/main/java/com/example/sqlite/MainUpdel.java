package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainUpdel extends AppCompatActivity {
    private com.example.sqlite.MyDatabase db;
    private String Sid, Snama, Salamat;
    private EditText Enama, Ealamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_main_updel);
        db = new com.example.sqlite.MyDatabase(this);
        Intent i = this.getIntent();
        Sid = i.getStringExtra("Iid");
        Snama = i.getStringExtra("Inama");
        Salamat = i.getStringExtra("Ialamat");
        Enama = (EditText) findViewById(R.id.updel_nama);
        Ealamat = (EditText) findViewById(R.id.updel_alamat);
        Enama.setText(Snama);
        Ealamat.setText(Salamat);
        Button btnUpdate = (Button) findViewById(R.id.btn_up);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Salamat = String.valueOf(Ealamat.getText());
                if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi nama pasien",
                            Toast.LENGTH_SHORT).show();
                } else if (Salamat.equals("")){
                    Ealamat.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi alamat pasien ",
                            Toast.LENGTH_SHORT).show();
                }
                 else {
                    db.UpdatePasien(new Pasien(Sid, Snama, Salamat ));
                    Toast.makeText(MainUpdel.this, "Data telah diupdate",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        Button btnDelete = (Button) findViewById(R.id.btn_del);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.DeletePasien(new Pasien (Sid, Snama, Salamat ));
                Toast.makeText(MainUpdel.this, "Data telah dihapus",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
