package com.example.headtohead;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.headtohead.question.GameCollections;
import com.example.headtohead.question.TFquestion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GameActivityTF extends AppCompatActivity implements View.OnClickListener {
    private int turn,player;
    private TFGameFBmodule fBmodule;

    private GameCollections collections;
    private ArrayList<TFquestion> tFCollection,Temp;
    private TextView TvQuestion, QuestionNumber;
    private TFquestion CurrentQuestion;
    private Button btnTrue, btnFalse, btnGoBackToMain;
    private Handler handler;
    private boolean WasP1Correct, WasP2Correct;
    private boolean CanGetNextQuestion = true;
private MediaPlayer mediaPlayer;
private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_tf);
        intent = getIntent();
        if(intent.getStringExtra("PlaySong").equals("true")) {
            String Song = intent.getStringExtra("SongName");
            int resourceId = getResources().getIdentifier(Song, "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this,resourceId );
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        fBmodule = new TFGameFBmodule(this);
        TvQuestion = findViewById(R.id.TvQuestion);
        fBmodule.TFSetPlayers(this);
        collections = new GameCollections();
        tFCollection = collections.getTFCollection();
        Temp = collections.getTFCollection();
        btnTrue = findViewById(R.id.btnTRUE);
        btnTrue.setOnClickListener(this);
        btnFalse = findViewById(R.id.btnFALSE);
        btnFalse.setOnClickListener(this);
        btnGoBackToMain = findViewById(R.id.btnGoBackToMain);
        btnGoBackToMain.setOnClickListener(this);
        handler = new Handler();
        turn = 1;
        player = 0;
        WasP2Correct = false;
        WasP1Correct = false;
        btnFalse.setEnabled(false);
        btnTrue.setEnabled(false);
        QuestionNumber = findViewById(R.id.TvQuestionNumber);
        CurrentQuestion = new TFquestion("", "", false, false);

    }

    public void StratingPoint(int player) {
        this.player = player;
             fBmodule.TFSetQuestion(this);
             fBmodule.TFSetWhoWasCorrect(this);
            if (this.player == 2) {
                GetANewRandomQuestion();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TFGameControl/CurrentQuestion");
                reference.setValue(Temp.get(0).getQuestion());//מעלה את השאלה לfb
                TvQuestion.setText("Waiting...");



            }
            if (this.player == 1) {
                    TvQuestion.setText("Waiting for players...");

            }

        }


    public void StartGame(String Question) {
        if (!CurrentQuestion.getQuestion().equals(Question)) {
            for (int i = 0; i < tFCollection.size(); i++) {
                if (Question.equals(tFCollection.get(i).getQuestion()))
                    CurrentQuestion = tFCollection.get(i);
            }

            if (CanGetNextQuestion) {
                if (turn == 1) {
                    TvQuestion.setText("Strating game in 5 seconds");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            QuestionNumber.setVisibility(View.VISIBLE);
                            TvQuestion.setText(CurrentQuestion.getQuestion());
                            btnFalse.setEnabled(true);
                            btnTrue.setEnabled(true);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    btnFalse.setEnabled(false);
                                    btnTrue.setEnabled(false);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            checkresults();


                                        }

                                    }, 1000);
                                }
                            }, 4000);
                        }
                    }, 5000);
                }
                else if (turn > 1) {

                    QuestionNumber.setText(Integer.toString(turn));
                    TvQuestion.setText(CurrentQuestion.getQuestion());
                    btnFalse.setEnabled(true);
                    btnTrue.setEnabled(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnFalse.setEnabled(false);
                            btnTrue.setEnabled(false);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    checkresults();


                                }
                            }, 1000);
                        }
                    }, 4000);
                }
            }
        }
    }
    public void HandleEndOfTurnIfBothWereRight() {
        turn++;
        if (this.player == 2) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestartAnswers();
                    GetANewRandomQuestion();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TFGameControl/CurrentQuestion");
                    reference.setValue(Temp.get(0).getQuestion());//מעלה את השאלה לfb
                }
            }, 250);
        }
    }
    public void GetANewRandomQuestion() {
        if(turn<=Temp.size()) {
            Boolean valid = false;
            while (!valid) {
                java.util.Collections.shuffle(this.Temp);
                if (!Temp.get(0).getWaseverused() ) {
                    Temp.get(0).setWaseverused(true);
                    valid = true;
                }

            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnFalse) {
            if (this.CurrentQuestion.getAnswer() == false) {
                SendIfAnswerWasRightToFb(true);
                TvQuestion.setText("CORRECT!!!");
            } else {
                SendIfAnswerWasRightToFb(false);
                TvQuestion.setText("Incorrect!!!");
            }
            btnFalse.setEnabled(false);
            btnTrue.setEnabled(false);
        }
        if (v == btnTrue) {
            if (this.CurrentQuestion.getAnswer() == true) {
                SendIfAnswerWasRightToFb(true);
                TvQuestion.setText("CORRECT!!!");
            } else {
                SendIfAnswerWasRightToFb(false);
                TvQuestion.setText("INCORRECT!!!");
            }
            btnTrue.setEnabled(false);
            btnFalse.setEnabled(false);
        }
        if (v == btnGoBackToMain) {
           finish();
        }
    }

    public void SendIfAnswerWasRightToFb(Boolean WasCorrect) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (this.player == 1) {
            DatabaseReference AnswerStatusp1 = firebaseDatabase.getReference("TFGameControl/answerStatus/p1");
            AnswerStatusp1.setValue(WasCorrect);
        }
        if (this.player == 2) {
            DatabaseReference AnswerStatusp2 = firebaseDatabase.getReference("TFGameControl/answerStatus/p2");
            AnswerStatusp2.setValue(WasCorrect);
        }


    }

    public int CheckIfSomeoneWon() {
        //1 - player 1 won
        //2 - player 2 won
        //0 - both were right, game keep going
        //-1 - both lost
        if (WasP1Correct == true && WasP2Correct == false) {
            return 1;
        }
        if (WasP1Correct == false && WasP2Correct == true)
            return 2;
        if (WasP1Correct == false && WasP2Correct == false)
            return -1;
        return 0;

    }

    public void setWasP2Correct(boolean wasP2Correct) {
        WasP2Correct = wasP2Correct;
    }

    public void setWasP1Correct(boolean wasP1Correct) {
        WasP1Correct = wasP1Correct;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void MakeGoBackApear() {
        if(mediaPlayer!=null)
            mediaPlayer.release();
        CanGetNextQuestion=false;
        btnGoBackToMain.setEnabled(true);
        btnGoBackToMain.setVisibility(View.VISIBLE);
        btnTrue.setVisibility(View.INVISIBLE);
        btnFalse.setVisibility(View.INVISIBLE);
    }
    public void RestartAnswers() {
        if(this.player ==2) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference AnswerStatusp1 = firebaseDatabase.getReference("TFGameControl/answerStatus/p1");
            AnswerStatusp1.setValue(false);
            DatabaseReference AnswerStatusp2 = firebaseDatabase.getReference("TFGameControl/answerStatus/p2");
            AnswerStatusp2.setValue(false);
        }
    }

    public int getPlayer() {
        return player;
    }
    public void checkresults(){
        if (CheckIfSomeoneWon() == 1) {
            if(getPlayer()==2) {
                TvQuestion.setText("LOSER");
            }
            if(getPlayer()==1) {
                TvQuestion.setText("WINNER");
            }
            MakeGoBackApear();

        } else if (CheckIfSomeoneWon() == 2) {
            if(getPlayer()==2) {
                TvQuestion.setText("WINNER");
            }
            if(getPlayer()==1) {
                TvQuestion.setText("LOSER");
            }
            MakeGoBackApear();
        }
        else if (CheckIfSomeoneWon() == -1) {
            TvQuestion.setText("BOTH OF YOU SHOULD BE ASHAMED");
            MakeGoBackApear();

        } else if (turn == tFCollection.size()) {
            TvQuestion.setText("Both of you got every Question Right");
            MakeGoBackApear();
        }
        else if(CheckIfSomeoneWon()==0) {
            HandleEndOfTurnIfBothWereRight();
        }

    }
}


