package com.project.regame.Features.Leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.project.regame.Database.DatabaseQueryClass;
import com.project.regame.Features.CreateData.Leaderboard;
import com.project.regame.R;

import java.util.List;

public class GameListRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<Leaderboard> leaderboardList;
    private DatabaseQueryClass databaseQueryClass;

    public GameListRecyclerViewAdapter(Context context, List<Leaderboard> leaderboardList) {
        this.context = context;
        this.leaderboardList = leaderboardList;
        databaseQueryClass = new DatabaseQueryClass(context);

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final int itemPosition = position;
        final Leaderboard leaderboard = leaderboardList.get(position);
          holder.position.setText("#".concat(String.valueOf(position+1)));
        holder.nameTextView.setText(leaderboard.getName());
        holder.scoreNumTextView.setText(String.valueOf(leaderboard.getScoreNumber()));


    }


    @Override
    public int getItemCount() {
        return leaderboardList.size();
    }
}
