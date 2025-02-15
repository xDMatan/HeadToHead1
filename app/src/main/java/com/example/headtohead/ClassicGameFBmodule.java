package com.example.headtohead;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClassicGameFBmodule {

    private Context context;
    private FirebaseDatabase firebaseDatabase;

    public ClassicGameFBmodule(Context context) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference Chat = FirebaseDatabase.getInstance().getReference("ClassicGameControl/Chat");
        DatabaseReference Players = firebaseDatabase.getReference("ClassicGameControl/playerswaiting");
        DatabaseReference CurrentPlayer = firebaseDatabase.getReference("ClassicGameControl/CurrentPlayer");
        DatabaseReference CurrentQuestion = firebaseDatabase.getReference("ClassicGameControl/CurrentQuestion");
        DatabaseReference ShowCustomDialog = firebaseDatabase.getReference("ClassicGameControl/ShowCustomDialog");
        CurrentPlayer.setValue(null);
        Chat.setValue(null);
        CurrentQuestion.setValue("");
        ShowCustomDialog.setValue(false);


    }

    public void ClassicSetPlayers(GameActivityClassic gameActivityClassic) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ClassicGameControl/playerswaiting");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Boolean isWaiting = dataSnapshot.getValue(Boolean.class);
                if (isWaiting == false) {
                    reference.setValue(true);
                    gameActivityClassic.StratingPoint(1);
                } else if (isWaiting == true) {
                    reference.setValue(false);
                    gameActivityClassic.StratingPoint(2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void Chat(GameActivityClassic gameActivityClassic){
        DatabaseReference Chat = FirebaseDatabase.getInstance().getReference("ClassicGameControl/Chat");
        Chat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null) {
                    String LastMassage = snapshot.getValue(String.class);
                    gameActivityClassic.SetChat(LastMassage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void SetQuestion(GameActivityClassic gameActivityClassic) {
        DatabaseReference CurrentQuestion = FirebaseDatabase.getInstance().getReference("ClassicGameControl/CurrentQuestion");
        CurrentQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.getValue(String.class).equals(""))
                    gameActivityClassic.SetQuestionForPlayersAndStartTheGame(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void WhoNeedsToPlay(GameActivityClassic gameActivityClassic){
        DatabaseReference CurrentPlayer = FirebaseDatabase.getInstance().getReference("ClassicGameControl/CurrentPlayer");
        CurrentPlayer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    int currentPlayer = snapshot.getValue(int.class);
                    gameActivityClassic.setWhichPLayerisPlaying(currentPlayer);
                    gameActivityClassic.GameTime();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void ShowCustomDialog(GameActivityClassic gameActivityClassic){
        DatabaseReference ShowCustomDialog = FirebaseDatabase.getInstance().getReference("ClassicGameControl/ShowCustomDialog");
        ShowCustomDialog.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(boolean.class)==true){
                    gameActivityClassic.CustomDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}