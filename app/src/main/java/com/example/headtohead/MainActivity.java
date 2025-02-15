package com.example.headtohead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnclassic, btnsettings, btnrules, btntf;
    private String SongName;
    private String PlaySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnclassic = findViewById(R.id.btnclassic);
        btnclassic.setOnClickListener(this);
        btnsettings = findViewById(R.id.btnsettings);
        btnsettings.setOnClickListener(this);
        btnrules = findViewById(R.id.btnrules);
        btnrules.setOnClickListener(this);
        btntf = findViewById(R.id.btntf);
        btntf.setOnClickListener(this);
        Intent intent = getIntent();
        if(SongName==null&&PlaySong==null){
            SongName = "lobby_classic_game";
            PlaySong = "true";
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnclassic) {
        Intent i = new Intent(this,GameActivityClassic.class);
                i.putExtra("SongName",SongName);
                i.putExtra("PlaySong",PlaySong);
                startActivity(i);
        }
        if (v == btntf) {
            Intent i = new Intent(this,GameActivityTF.class);
            i.putExtra("SongName",SongName);
            i.putExtra("PlaySong",PlaySong);
            startActivity(i);
        }
        if (v == btnrules) {
            Intent i = new Intent(this,Rules.class);
            startActivity(i);
        }
        if (v == btnsettings) {
            Intent i = new Intent(this,Settings.class);
            startActivityForResult(i,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK) {
                if(data.getStringExtra("SongName")!=null) {
                    SongName = data.getStringExtra("SongName");
                }

                if(data.getStringExtra("PlaySong")!=null) {
                    PlaySong = data.getStringExtra("PlaySong");
                }
            }
        }
    }
}