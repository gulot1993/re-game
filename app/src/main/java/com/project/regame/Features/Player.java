package com.project.regame.Features;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import com.project.regame.R;

public class Player extends DialogFragment {

    private static Player fragment;
    private View view;




    public Player() {
        // Required empty public constructor
    }


    public static Player newInstance() {
        Bundle args = new Bundle();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_player, container, false);
        assert getArguments() != null;
        String link = getArguments().getString("link");
        String title = getArguments().getString("title");
        VideoView videoView = view.findViewById(R.id.videoview);
        TextView textView = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.exit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        textView.setText(title);
        MediaController mediaController= new MediaController(getContext());

        mediaController.setAnchorView(videoView);

        Uri uri= Uri.parse(link);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        return view;
    }


}