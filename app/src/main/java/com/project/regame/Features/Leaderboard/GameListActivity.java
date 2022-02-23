package com.project.regame.Features.Leaderboard;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.regame.Database.DatabaseQueryClass;
import com.project.regame.Features.CreateData.GameCreateListener;
import com.project.regame.Features.CreateData.Leaderboard;
import com.project.regame.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameListActivity extends AppCompatActivity implements GameCreateListener {

    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);

    private List<Leaderboard> leaderboardList = new ArrayList<>();

    private TextView gameListEmptyTextView;
    private RecyclerView recyclerView;
    private GameListRecyclerViewAdapter gameListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);
        gameListEmptyTextView = (TextView) findViewById(R.id.gameListEmptyTextView);

        leaderboardList.addAll(databaseQueryClass.getAllData());
        Collections.sort(leaderboardList, new Comparator<Leaderboard>() {
            public int compare(Leaderboard s, Leaderboard s2) {
                return s2.getScoreNumber() - s.getScoreNumber();
            }
        });
        gameListRecyclerViewAdapter = new GameListRecyclerViewAdapter(this, leaderboardList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(gameListRecyclerViewAdapter);
        TextView textView = (TextView) findViewById(R.id.button);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewVisibility();


    }




    public void viewVisibility() {
        if(leaderboardList.isEmpty())
            gameListEmptyTextView.setVisibility(View.VISIBLE);
        else
            gameListEmptyTextView.setVisibility(View.GONE);
    }



    @Override
    public void onUserCreated(Leaderboard leaderboard) {
        leaderboardList.add(leaderboard);
        gameListRecyclerViewAdapter.notifyDataSetChanged();
        viewVisibility();
    }

}
