package com.example.headtohead;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.headtohead.question.ClassicQuestion;
import com.example.headtohead.question.GameCollections;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class GameActivityClassic extends AppCompatActivity implements View.OnClickListener {
    private GameCollections collections;
    private EditText etAnswer;
    private ImageButton ibSend;
    private ClassicGameFBmodule fBmodule;
    private RecyclerView RvChat;
    private ClassicQuestion CurrentQuestion;
    private int player;
    private TextView tvQuestion, Current;
    private Handler handler;
    private CountDownTimer countDownTimer;
    private MediaPlayer mediaPlayer;
    private ArrayList<String> stringArrayList;
    private int WhichPLayerisPlaying = 0;
    private String WaitingString;
    private CustomDialog customDialog;
    private ArrayList<ClassicQuestion> classicCollection, Temp;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_classic);
        intent = getIntent(); //נקבל את השיר שהשחקן הזין
        if (intent.getStringExtra("PlaySong").equals("true")) { //נפעיל את השיר
            String Song = intent.getStringExtra("SongName");
            int resourceId = getResources().getIdentifier(Song, "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(this, resourceId);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        collections = new GameCollections();
        classicCollection = collections.getClassisCollection();
        etAnswer = findViewById(R.id.etAnwser);
        ibSend = findViewById(R.id.ibSendAnswer);
        ibSend.setOnClickListener(this);
        tvQuestion = findViewById(R.id.TvQuestion);
        fBmodule = new ClassicGameFBmodule(this);
        fBmodule.ClassicSetPlayers(this);//נפעיל את הפיירבייס כדי לדעת מתי שחקן חדש נכנס ונדע איזה מספר הוא
        CurrentQuestion = new ClassicQuestion("", "", new HashMap<>(), false);
        RvChat = findViewById(R.id.rvChat);
        stringArrayList = new ArrayList<String>();
        Temp = collections.getClassisCollection();
        // Set up RecyclerView
        RvChat.setLayoutManager(new LinearLayoutManager(this));
        RvChat.setAdapter(new StringAdapter(stringArrayList));
        etAnswer.setFocusable(false);
        handler = new Handler();
        Current = findViewById(R.id.Tvcurrent);
        WaitingString = "Strating game in a few Seconds";
        customDialog = new CustomDialog(this, this);
        fBmodule.Chat(this);//נפעיל את הפיירבייס כדי לדעת מתי יש שינוי בכתיבה של השחקן
        fBmodule.SetQuestion(this);//נפעיל את הפיירבייס כד לדעת מתי הועלה שאלה חדשה
        fBmodule.ShowCustomDialog(this);//נפעיל את הפיירבייס כדי לדעת מתי להפעיל את קאסטום דייאלוג
        fBmodule.WhoNeedsToPlay(this);//נפעיל את הפיירבייס כדי לדעת מתי יש שינוי במי אמור לשחק הבא בתור
    }

    public void StratingPoint(int player) {
        //לאחר שאנחנו יודעים מי נכנסו נגדיר אותם
        this.player = player;

        if (this.player == 1) {
            //אם הפלייר ראשון אז הוא מחכה ליריב לכן
            tvQuestion.setText("Waiting for Players");
        }
        if (this.player == 2) {
            //השחקן השני יעלה את שאלה לפיירבייס וכך יעשה טריגר לקביעת השאלה
            tvQuestion.setText("Waiting...");
            GetANewRandomQuestion();//מעלה שאלה חדשה
            DatabaseReference CurrentQuestion = FirebaseDatabase.getInstance().getReference("ClassicGameControl/CurrentQuestion");
            CurrentQuestion.setValue(Temp.get(0).getQuestion());

        }
    }

    public void SetQuestionForPlayersAndStartTheGame(String Question) {
        //  ראונד שני ומעלה תיתכן בעיה בה קאסטום דיאלוג יישאר לשחקן אחד עקב דיליי ולא יהיה דיסמיס עקב הרצון לעשות את הפקודה הזו מכך כאשר תעלה שאלה חדשה נעשה דיסמיס לשחקן הראשון ונוריד לו את ההנדלר בלי קשר לזמן
        if(this.player==1&&customDialog.isShowing())
        {
            if (customDialog.getHandler() != null) {
                customDialog.getHandler().removeCallbacksAndMessages(null);

            }
            customDialog.dismiss();
        }
        //אם הראונד הוא שני ומעלה נצטרך לעשות קליר לצאט
        stringArrayList.clear();
        //נגדיר מחדש את הצאט
        RvChat.setAdapter(new StringAdapter(stringArrayList));
        tvQuestion.setText(WaitingString);
        WaitingString = "strating next round in a few seconds";
        // נקבע את השאלה לכל שחקן ושחקן 2 יעלה מי האדם הראנדומלי שצריך לשחק בראונד הבא מה שיפעיל את הפקודה הבאה
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < classicCollection.size(); i++) {
                    if (Question.equals(classicCollection.get(i).getQuestion()))
                        CurrentQuestion = classicCollection.get(i);
                }
                //נשים דיליי נוסף על פלייר 2 בהעלאת האדם הראנדומלי כדי לוודא ששני השחקנים קבעו את השאלה שלהם
                if (getPlayer() == 2) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Random rand = new Random();
                            int WhoplaysFirst = rand.nextInt(2) + 1;//יחליט מי ראשון
                            DatabaseReference CurrentPLayer = FirebaseDatabase.getInstance().getReference("ClassicGameControl/CurrentPlayer");
                            CurrentPLayer.setValue(WhoplaysFirst);
                        }
                    }, 1000);
                }
            }
        }, 2000);
    }

    public void GameTime() {
        //לאחר שיודעים מי אמור להתחיל אפשר להתחיל את המשחק
        tvQuestion.setText(CurrentQuestion.getQuestion());
        //אם הוא משחק נאפשר לו לכתוב וכדומה
        if (WhichPLayerisPlaying == this.player) {
            etAnswer.setFocusable(true);
            etAnswer.setEnabled(true);
            etAnswer.setFocusableInTouchMode(true);
            etAnswer.setInputType(InputType.TYPE_CLASS_TEXT);

            countDownTimer = new CountDownTimer(20000, 50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    long milliseconds = millisUntilFinished % 1000;
                    Current.setText(String.format("Time remaining: %d.%03d seconds", seconds, milliseconds));

                }

                @Override
                public void onFinish() {
                    etAnswer.setFocusable(false);
                    etAnswer.setEnabled(false);
                    etAnswer.setFocusableInTouchMode(false);
                    etAnswer.setInputType(InputType.TYPE_NULL);
                    etAnswer.setText("");
                    Current.setText("Time's up!");
                    DatabaseReference ShowCustomDialog = FirebaseDatabase.getInstance().getReference("ClassicGameControl/ShowCustomDialog");
                    ShowCustomDialog.setValue(true);

                }
            }.start();
        } else {
            //אם לא משחק נסגור לו את האופציה לכתוב ואם היה לו countdowntimer מהראונד הקודם נסגור אותו
            if(countDownTimer!=null)
                countDownTimer.cancel();
            Current.setText("opponnent's turn...");
            etAnswer.setFocusable(false);
            etAnswer.setEnabled(false);
            etAnswer.setFocusableInTouchMode(false);
            etAnswer.setInputType(InputType.TYPE_NULL);

        }
    }


    @Override
    public void onClick(View v) {
        if (v == ibSend) {
            //כאשר נשלח משהו לאדיט טקסט נעלה אותו לפיירבייס כדי ששניהם יוכלו לראות מה האחר כתב
            if (!etAnswer.getText().toString().equals("")) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ClassicGameControl/Chat");
                reference.setValue(etAnswer.getText().toString());//מעלה את השאלה לfb
            }
        }
    }

    public void GetANewRandomQuestion() {
        Boolean valid = false;
        while (!valid) {
            java.util.Collections.shuffle(Temp);
            if (!Temp.get(0).getWaseverused()&&CurrentQuestion.getTypeofquestion()!=Temp.get(0).getTypeofquestion()) {
                Temp.get(0).setWaseverused(true);
                valid = true;
            }

        }


    }

    public void SetChat(String LastMassage) {
        //כאשר יש שינוי בצאט של הפיירבייס נדע מי שלח לפי קארנט פלייר
        String Result = "";
        String Name = "";
        if (this.player == this.WhichPLayerisPlaying)
            Name += "אתה";
        else
            Name += "יריב";
        //נבדוק האם התשובה נכונה
        if (CurrentQuestion.getAnswers().containsKey(LastMassage)) {
            if (CurrentQuestion.getAnswers().get(LastMassage)) {
                Result += "נכון";

            } else
                Result += "היה כבר בשימוש";
        } else
            Result += "לא נכון";
        stringArrayList.add(Name + " : " + LastMassage + " - " + Result);
        RvChat.setAdapter(new StringAdapter(stringArrayList));
        RvChat.scrollToPosition(stringArrayList.size() - 1);
        etAnswer.setText("");
        if (CurrentQuestion.getAnswers().containsKey(LastMassage)) {//אם התשובה הייתה נכונה תעביא את התור לשחקו הבא
            if (CurrentQuestion.getAnswers().get(LastMassage)) {
                CurrentQuestion.getAnswers().put(LastMassage, false);

                if (this.player == getWhichPLayerisPlaying()) {
                    etAnswer.setFocusable(false);
                    etAnswer.setEnabled(false);
                    etAnswer.setFocusableInTouchMode(false);
                    etAnswer.setInputType(InputType.TYPE_NULL);
                    countDownTimer.cancel();
//נעלה מי אמור לשחק בתור הבא
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ClassicGameControl/CurrentPlayer");
                    if (getWhichPLayerisPlaying() == 1)
                        reference.setValue(2);
                    else
                        reference.setValue(1);
                }
            }
        }
    }


    public int getWhichPLayerisPlaying() {
        return WhichPLayerisPlaying;
    }

    public void setWhichPLayerisPlaying(int whichPLayerisPlaying) {
        WhichPLayerisPlaying = whichPLayerisPlaying;
    }

    public int getPlayer() {
        return player;
    }

    public ArrayList<ClassicQuestion> getTemp() {
        return Temp;
    }

    public void CustomDialog() {
        if (!isFinishing() && !isDestroyed()) {
            if (customDialog != null && !customDialog.isShowing()) {
                customDialog.show();
                customDialog.Change();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if(countDownTimer!=null)
            countDownTimer.cancel();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ClassicGameControl/playerswaiting");
        reference.setValue(false);
    }

}