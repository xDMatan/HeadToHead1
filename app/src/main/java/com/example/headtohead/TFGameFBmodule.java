package com.example.headtohead;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TFGameFBmodule {

    private Context context;
    private FirebaseDatabase firebaseDatabase;

    public TFGameFBmodule(Context context) {
        this.context = context;


        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference Players = firebaseDatabase.getReference("TFGameControl/playerswaiting");
        DatabaseReference AnswerStatusP1 = firebaseDatabase.getReference("TFGameControl/answerStatus/p1");
        DatabaseReference AnswerStatusP2 = firebaseDatabase.getReference("TFGameControl/answerStatus/p2");
        DatabaseReference CurrentQuestion = firebaseDatabase.getReference("TFGameControl/CurrentQuestion");
        CurrentQuestion.setValue("");
        AnswerStatusP1.setValue(null);
        AnswerStatusP2.setValue(null);


    }

    public void TFSetPlayers(GameActivityTF gameActivityTF) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TFGameControl/playerswaiting");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Boolean isWaiting = dataSnapshot.getValue(Boolean.class);
                if (isWaiting == false) {
                    reference.setValue(true);
                    gameActivityTF.StratingPoint(1);
                }
                    else if (isWaiting == true) {
                    reference.setValue(false);
                        gameActivityTF.StratingPoint(2);
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TFSetQuestion(GameActivityTF gameActivityTF){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TFGameControl/CurrentQuestion");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue(String.class);
                    gameActivityTF.StartGame(question);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void TFSetWhoWasCorrect(GameActivityTF gameActivityTF){
        DatabaseReference referencep1 = firebaseDatabase.getReference("TFGameControl/answerStatus/p1");
        DatabaseReference referencep2 = firebaseDatabase.getReference("TFGameControl/answerStatus/p2");
        referencep1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.getValue()!=null) {
                Boolean WasP1Correct = snapshot.getValue(Boolean.class);
                gameActivityTF.setWasP1Correct(WasP1Correct);
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referencep2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    Boolean WasP2Correct = snapshot.getValue(Boolean.class);
                    gameActivityTF.setWasP2Correct(WasP2Correct);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}