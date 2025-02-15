package com.example.headtohead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity implements View.OnClickListener {
private Button btngoBackToMain;
private ImageButton imageButton;
private boolean onoff;
private RadioButton radioButton;
private String selectedSong;
private RadioButton kahoot,lofi,rickroll,intense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
     btngoBackToMain = findViewById(R.id.btnGoToMain);
     btngoBackToMain.setOnClickListener(this);
     imageButton = findViewById(R.id.ibonoff);
     imageButton.setOnClickListener(this);
     onoff=true;
        kahoot = findViewById(R.id.btnKahoot);
        lofi = findViewById(R.id.btnlofi);
        rickroll = findViewById(R.id.btnrickroll);
        intense = findViewById(R.id.btnintense);

        kahoot.setOnClickListener(this);
        lofi.setOnClickListener(this);
        rickroll.setOnClickListener(this);
        intense.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
if(v==btngoBackToMain){
    Intent resultIntent = new Intent();
    resultIntent.putExtra("SongName", selectedSong);
    resultIntent.putExtra("PlaySong", String.valueOf(onoff));
    setResult(RESULT_OK,resultIntent);
    finish();
}
if(v==imageButton){
    if(onoff==true){
        imageButton.setImageResource(R.drawable.off);
        onoff=false;
    }
    else{
        imageButton.setImageResource(R.drawable.on);
        onoff=true;
    }
}
if(v== kahoot)
    selectedSong = "lobby_classic_game";
if(v==lofi)
    selectedSong = "lofibeats";
if(v==rickroll)
    selectedSong= "rickroll";
if(v==intense)
    selectedSong = "intensemusic";
    }
}