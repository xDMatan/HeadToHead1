package com.example.headtohead;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {

    private final ArrayList<String> stringList;

    // Constructor to accept the list of strings
    public StringAdapter(ArrayList<String> stringList) {
        this.stringList = stringList;
    }

    // ViewHolder class to hold references to the item views
    public static class StringViewHolder extends RecyclerView.ViewHolder {
        TextView tvString;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            tvString = itemView.findViewById(R.id.tvString);
        }
    }

    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_string, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
        // Bind the string data to the TextView
        holder.tvString.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        // Return the size of the string list
        return stringList.size();
    }
}
