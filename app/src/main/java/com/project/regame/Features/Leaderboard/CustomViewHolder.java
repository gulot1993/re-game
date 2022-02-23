package com.project.regame.Features.Leaderboard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.regame.R;


public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    TextView position;
    TextView scoreNumTextView;

    public CustomViewHolder(View itemView) {
        super(itemView);
        position = itemView.findViewById(R.id.position);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        scoreNumTextView = itemView.findViewById(R.id.scoreNumTextView);

    }
}
