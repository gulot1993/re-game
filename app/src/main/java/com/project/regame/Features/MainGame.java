package com.project.regame.Features;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.regame.Database.DatabaseQueryClass;
import com.project.regame.Features.CreateData.Leaderboard;
import com.project.regame.Features.CreateData.GameCreateListener;
import com.project.regame.Features.Leaderboard.GameListActivity;
import com.project.regame.R;
import com.project.regame.Util.Session;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Logger;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechDelegate;
import net.gotev.speech.SpeechRecognitionNotAvailable;
import net.gotev.speech.SpeechUtil;
import net.gotev.speech.TextToSpeechCallback;
import net.gotev.speech.ui.SpeechProgressView;

import java.util.ArrayList;
import java.util.List;

public class MainGame extends AppCompatActivity implements SpeechDelegate {
    private static GameCreateListener gameCreateListener;
    private final int PERMISSIONS_REQUEST = 1;
    private static final String LOG_TAG = MainGame.class.getSimpleName();
    private ImageButton button;
    private Button speak;
    private AppCompatButton skip;
    private CardView button1;
    private TextView text,score;
    private TextView textToSpeech;
    private SpeechProgressView progress;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private List<String> names;
    private Session session;
    private ImageView heart1, heart2, heart3, heart4, heart5;
    List<Drawable> drawables;
    Integer i,c=0, error;
    Integer scorevalue;
    private TextView playername;
    DatabaseQueryClass databaseQueryClass;

    private TextToSpeech.OnInitListener mTttsInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(final int status) {
            switch (status) {
                case TextToSpeech.SUCCESS:
                    Logger.info(LOG_TAG, "TextToSpeech engine successfully started");
                    break;

                case TextToSpeech.ERROR:
                    Logger.error(LOG_TAG, "Error while initializing TextToSpeech engine!");
                    break;

                default:
                    Logger.error(LOG_TAG, "Unknown TextToSpeech status: " + status);
                    break;
            }
        }
    };

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Speech.init(this, getPackageName(), mTttsInitListener);
        session = new Session(getApplicationContext());
         databaseQueryClass = new DatabaseQueryClass(getApplicationContext());
        imageView = findViewById(R.id.imageview);
        linearLayout = findViewById(R.id.linearLayout);
        button1 = findViewById(R.id.button1);
        button = findViewById(R.id.button);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        heart4 = findViewById(R.id.heart4);
        heart5 = findViewById(R.id.heart5);
        skip = findViewById(R.id.skip);
        score = findViewById(R.id.score);
        playername = findViewById(R.id.playername);

        speak = findViewById(R.id.speak);
        button.setOnClickListener(view -> onButtonClick());
        speak.setOnClickListener(view -> onSpeakClick());

        text = findViewById(R.id.text);
        textToSpeech = findViewById(R.id.textToSpeech);
        progress = findViewById(R.id.progress);

        i = 0;
        error = 0;
         drawables = new ArrayList<>();
        drawables.add(getDrawable(R.drawable.apple));
        drawables.add(getDrawable(R.drawable.banana));
        drawables.add(getDrawable(R.drawable.cat));
        drawables.add(getDrawable(R.drawable.dog));
        drawables.add(getDrawable(R.drawable.earth));
        drawables.add(getDrawable(R.drawable.frog));
        drawables.add(getDrawable(R.drawable.goat));
        drawables.add(getDrawable(R.drawable.horse));
        drawables.add(getDrawable(R.drawable.ice));
        drawables.add(getDrawable(R.drawable.juice));
        drawables.add(getDrawable(R.drawable.kangaroo));
        drawables.add(getDrawable(R.drawable.lemon));
        drawables.add(getDrawable(R.drawable.monkey));
        drawables.add(getDrawable(R.drawable.nest));
        drawables.add(getDrawable(R.drawable.octopus));
        drawables.add(getDrawable(R.drawable.pizza));
        drawables.add(getDrawable(R.drawable.queen));
        drawables.add(getDrawable(R.drawable.rat));
        drawables.add(getDrawable(R.drawable.sun));
        drawables.add(getDrawable(R.drawable.tree));
        drawables.add(getDrawable(R.drawable.umbrella));
        drawables.add(getDrawable(R.drawable.violin));
        drawables.add(getDrawable(R.drawable.watermelon));
        drawables.add(getDrawable(R.drawable.xylophone));
        drawables.add(getDrawable(R.drawable.yoyo));
        drawables.add(getDrawable(R.drawable.zebra));

//
        names = new ArrayList<>();
        names.add("apple");
        names.add("banana");
        names.add("cat");
        names.add("dog");
        names.add("earth");
        names.add("frog");
        names.add("goat");
        names.add("horse");
        names.add("ice");
        names.add("juice");
        names.add("kangaroo");
        names.add("lemon");
        names.add("monkey");
        names.add("nest");
        names.add("octopus");
        names.add("pizza");
        names.add("queen");
        names.add("rat");
        names.add("sun");
        names.add("tree");
        names.add("umbrella");
        names.add("violin");
        names.add("watermelon");
        names.add("xylophone");
        names.add("yoyo");
        names.add("zebra");

        if(!session.getSession("name", "0").equals("0")){
            i = Integer.parseInt(session.getSession("question", "0"));
            Log.d("VALUE-q", i + "i");
            c = Integer.parseInt(session.getSession("skip", "0"));
            Log.d("VALUE-q", c + " c");
            error = Integer.parseInt(session.getSession("error", "0"));
            playername.setText(session.getSession("name", "Player:"));
            if(error == 1){
                heart1.setBackground(getDrawable(R.drawable.dead));
            } else if (error ==2){
                heart1.setBackground(getDrawable(R.drawable.dead));
                heart2.setBackground(getDrawable(R.drawable.dead));
            }  else if (error == 3){
                heart1.setBackground(getDrawable(R.drawable.dead));
                heart2.setBackground(getDrawable(R.drawable.dead));
                heart3.setBackground(getDrawable(R.drawable.dead));

            } else if (error == 4){
                heart1.setBackground(getDrawable(R.drawable.dead));
                heart2.setBackground(getDrawable(R.drawable.dead));
                heart3.setBackground(getDrawable(R.drawable.dead));
                heart4.setBackground(getDrawable(R.drawable.dead));

            }
            else if (error == 5){
                heart1.setBackground(getDrawable(R.drawable.dead));
                heart2.setBackground(getDrawable(R.drawable.dead));
                heart3.setBackground(getDrawable(R.drawable.dead));
                heart4.setBackground(getDrawable(R.drawable.dead));
                heart5.setBackground(getDrawable(R.drawable.dead));
                gameover();
            }
            update();
        } else {
            name();
        }

        int[] colors = {
                ContextCompat.getColor(this, android.R.color.black),
                ContextCompat.getColor(this, android.R.color.darker_gray),
                ContextCompat.getColor(this, android.R.color.black),
                ContextCompat.getColor(this, android.R.color.holo_orange_dark),
                ContextCompat.getColor(this, android.R.color.holo_red_dark)
        };
        progress.setColors(colors);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                update();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Speech.getInstance().shutdown();
    }

    private void onButtonClick() {
        try{
            if (Speech.getInstance().isListening()) {
                Speech.getInstance().stopListening();
            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    onRecordAudioPermissionGranted();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST);
                }
            }
        }catch (IllegalStateException ignored){

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay!
                onRecordAudioPermissionGranted();
            } else {
                // permission denied, boo!
                Toast.makeText(MainGame.this, R.string.permission_required, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void onRecordAudioPermissionGranted() {
        button.setVisibility(View.GONE);
        button1.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

        try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().startListening(progress, (SpeechDelegate) this);

        } catch (SpeechRecognitionNotAvailable exc) {
            showSpeechNotSupportedDialog();

        } catch (GoogleVoiceTypingDisabledException exc) {
            showEnableGoogleVoiceTyping();
        }
    }

    private void onSpeakClick() {
        if (textToSpeech.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.input_something, Toast.LENGTH_LONG).show();
            return;
        }
    try{
        Speech.getInstance().say(textToSpeech.getText().toString().trim(), new TextToSpeechCallback() {
            @Override
            public void onStart() {
//                Toast.makeText(MainActivity.this, "TTS onStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleted() {
//                Toast.makeText(MainActivity.this, "TTS onCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(MainGame.this, "Voice Recognition onError", Toast.LENGTH_SHORT).show();
            }
        });
    }catch (IllegalStateException e){

    }

    }

    public void playername(String name){
        playername.setText(name);

    }

    @Override
    public void onStartOfSpeech() {
    }

    @Override
    public void onSpeechRmsChanged(float value) {
        //Log.d(getClass().getSimpleName(), "Speech recognition rms is now " + value +  "dB");
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onSpeechResult(String result) {

        button.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        text.setText(result);

        if(result.equals(textToSpeech.getText().toString().toLowerCase())){
            if(i != 25){
                dialog();
            }
                update();
        } else {
            error++;
            session.setSession("error",String.valueOf(error));
            if(error == 5){
                heart5.setBackground(getDrawable(R.drawable.dead));
                gameover();
            } else dialog1();

        }

        if (result.isEmpty()) {
            Speech.getInstance().say(getString(R.string.repeat));

        } else {
            Speech.getInstance().say(result);
        }
    }


    public void update(){
        if(i < drawables.size()-1){
            String value = names.get(i+1).trim();
            session.setSession("skip",String.valueOf(c));
            session.setSession("question",String.valueOf(i));

            i++;
            if(i == 25){
                skip.setVisibility(View.GONE);
            }
            score.setText("SCORE: ".concat(String.valueOf(i - c)));
            scorevalue = i-c;
            imageView.setBackground(drawables.get(i));
            textToSpeech.setText(value);


        } else {
            score.setText("SCORE: ".concat(String.valueOf(i - c)));
            scorevalue = i + 1 - c;
            congrats();
        }
    }




    @Override
    public void onSpeechPartialResults(List<String> results) {
        text.setText("");
        for (String partial : results) {
            text.append(partial + " ");
        }
    }

    private void showSpeechNotSupportedDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        SpeechUtil.redirectUserToGoogleAppOnPlayStore(MainGame.this);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.speech_not_available)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener)
                .show();
    }

    private void showEnableGoogleVoiceTyping() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.enable_google_voice_typing)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                })
                .show();
    }



    public void dialog() {
        LayoutInflater mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.thropy_dialog, null);

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);
        dialog.show();

        MediaPlayer song = MediaPlayer.create(this, R.raw.success);
        song.start();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                       dialog.dismiss();
                    }
                }, 1000);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void dialog1() {
        LayoutInflater mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.wrror_dialog, null);

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));

        } else {
            v.vibrate(500);
        }
        dialog.show();
        if(error == 1){
            heart1.setBackground(getDrawable(R.drawable.dead));
        } else if (error ==2){
            heart2.setBackground(getDrawable(R.drawable.dead));
        } else if (error ==3){
            heart3.setBackground(getDrawable(R.drawable.dead));
        } else if (error ==4){
            heart4.setBackground(getDrawable(R.drawable.dead));
        }

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 1000);
    }


    private void congrats() {
        LayoutInflater mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = mInflater.inflate(R.layout.congrats_dialog, null);
        TextView textView = view1.findViewById(R.id.end);
        TextView score = view1.findViewById(R.id.texticon);
        score.setText("SCORE : ".concat(String.valueOf(scorevalue)));
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(view1);
        dialog.show();
        MediaPlayer song = MediaPlayer.create(this, R.raw.victory);
        song.start();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leaderboard student = new Leaderboard(-1, playername.getText().toString(), scorevalue);

                long id = databaseQueryClass.insertData(student);

                if(id>0){
                    session.clearSession();
                    student.setId(id);
                    gameCreateListener.onUserCreated(student);
                    Intent intent = new Intent(MainGame.this, GameListActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

    }

    public void gameover() {
        LayoutInflater mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = mInflater.inflate(R.layout.gameover_dialog, null);
        TextView textView = view1.findViewById(R.id.end);
         Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(view1);
        dialog.show();
        MediaPlayer song = MediaPlayer.create(this, R.raw.gameover);
        song.start();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leaderboard student = new Leaderboard(-1, playername.getText().toString(), scorevalue);

                long id = databaseQueryClass.insertData(student);

                if(id>0){
                    session.clearSession();
                    student.setId(id);
                    gameCreateListener.onUserCreated(student);
                    finish();
                }

            }
        });
    }

    public void name() {
        LayoutInflater mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.activity_playername, null);

         Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();

        AppCompatButton button =   view.findViewById(R.id.buttonz);
        ImageView exit =   view.findViewById(R.id.exit);
        EditText text = view.findViewById(R.id.name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.getText().toString().isEmpty()){
                    Toast.makeText(MainGame.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    playername("Player: ".concat(text.getText().toString().trim()) );
                    session.setSession("name","Player: ".concat(text.getText().toString().trim()));
                    dialog.dismiss();
                }

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });




    }
}