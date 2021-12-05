package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private com.example.sqlite.MyDatabase db;
    private List<Pasien> Listpasien = new
            ArrayList<Pasien>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new com.example.sqlite.MyDatabase(this);
        adapter_off = new CustomListAdapter(this, Listpasien);
        mListView = (ListView) findViewById(R.id.list_pasien);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        Listpasien.clear();
        List<Pasien> pasien = db.ReadPasien();
        for (Pasien p : pasien) {
            Pasien daftar = new Pasien();
            daftar.set_id(p.get_id());
            daftar.set_nama(p.get_nama());
            daftar.set_alamat(p.get_alamat());
            Listpasien.add(daftar);
            if ((Listpasien.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int
            i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Pasien detailpas = (Pasien) o;
        String Sid = detailpas.get_id();
        String Snama = detailpas.get_nama();
        String Salamat = detailpas.get_alamat();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Ialamat", Salamat);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Listpasien.clear();
        mListView.setAdapter(adapter_off);
        List<Pasien> pasien = db.ReadPasien();
        for (Pasien p : pasien) {
            Pasien daftar = new Pasien();
            daftar.set_id(p.get_id());
            daftar.set_nama(p.get_nama());
            daftar.set_alamat(p.get_alamat());
            Listpasien.add(daftar);
            if ((Listpasien.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
