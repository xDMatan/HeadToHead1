package com.example.headtohead;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView score1,score2,currentscore;
    private Button GoBackHome;
    private int scorea,scoreb;
    private Handler handler;
    private GameActivityClassic gameActivityClassic;
    public CustomDialog(@NonNull Context context , GameActivityClassic gameActivityClassic) {
        super(context);
        this.gameActivityClassic = gameActivityClassic;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        scorea=0;
        scoreb=0;
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        currentscore = findViewById(R.id.currentscore);
        GoBackHome = findViewById(R.id.btnHome);
        GoBackHome.setOnClickListener(this);
        handler = new Handler();

    }
    public void Change() {
        if (gameActivityClassic.getPlayer() == gameActivityClassic.getWhichPLayerisPlaying()) {
            scoreb++;
            score1.setText(Integer.toString(scorea));
            score2.setText(Integer.toString(scoreb));
        } else {
            scorea++;
            score1.setText(Integer.toString(scorea));
            score2.setText(Integer.toString(scoreb));
        }
        if(scorea==2||scoreb==2){
            if(scoreb==2)
                currentscore.setText("Lost");
            if(scorea==2)
                currentscore.setText("WINNER");
            GoBackHome.setClickable(true);
            GoBackHome.setVisibility(View.VISIBLE);
        }
        else
        {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                },4000);

        }
    }

    @Override
    public void onClick(View view) {
        if(view == GoBackHome) {
            dismiss();
            gameActivityClassic.finish();
        }

    }
    @Override
    public void dismiss() {
        super.dismiss();
        if (scorea != 2 && scoreb != 2) {
            if (gameActivityClassic.getPlayer() == 2) {
                DatabaseReference ShowCustomDialog = FirebaseDatabase.getInstance().getReference("ClassicGameControl/ShowCustomDialog");
                ShowCustomDialog.setValue(false);
                gameActivityClassic.GetANewRandomQuestion();
                DatabaseReference CurrentPlayer = FirebaseDatabase.getInstance().getReference("ClassicGameControl/CurrentPlayer");
                CurrentPlayer.setValue(null);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ClassicGameControl/CurrentQuestion");
                reference.setValue(gameActivityClassic.getTemp().get(0).getQuestion());//מעלה את השאלה לfb
            }
        }

    }

    public Handler getHandler() {
        return handler;
    }
}