package com.example.odintree.audioplayers;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AndroidBuildingMusicPlayerActivity extends Activity implements View.OnClickListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnPreparedListener{

    private ImageButton btnPlay;
    private ImageButton btnPause;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private ImageButton btnPlaylist;
    private SeekBar songProgressBar;
    private TextView songTitleBox;
    private TextView songCountryBox;
    private TextView songCommentBox;


    // Media Player
    private MediaPlayer mediaPlayer;


    private SongsManager songManager;
    private int currentSongIndex = 0;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    public static String PACKAGE_NAME;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        // All player buttons
        btnPlaylist = (ImageButton) findViewById(R.id.btnPlaylist);
        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        songTitleBox = (TextView) findViewById(R.id.songTitle);
        songCountryBox = (TextView) findViewById(R.id.songCOuntry);
        songCommentBox = (TextView) findViewById(R.id.songCommentary);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnPause = (ImageButton) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(this);

        btnPlaylist = (ImageButton) findViewById(R.id.btnPlaylist);
        btnPlaylist.setOnClickListener(this);


        //getContext
        PACKAGE_NAME = getApplicationContext().getPackageName();


        // Mediaplayer
        mediaPlayer = new MediaPlayer();
        songManager = new SongsManager();
        songsList = songManager.getPlayList();

        this.onPrepared(mediaPlayer);


        startPlayer(0);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                onPrepared(mediaPlayer);
                break;
            case R.id.btnPause:
                pausePlayer();
            case R.id.btnNext:
                nextPlayer();
                startPlayer(currentSongIndex);
                playPlayer();
                break;
            case R.id.btnPrevious:
                previousPlayer();
                startPlayer(currentSongIndex);
                playPlayer();
                break;
            case R.id.btnPlaylist:
                Intent i = new Intent(getApplicationContext(), PlayListActivity.class);
                startActivity(i);
        }

    }



    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            currentSongIndex = data.getExtras().getInt("songIndex");
            // play selected song
            startPlayer(currentSongIndex);
        }

    }

    public void startPlayer(int songIndex) {

        //Set up MediaPlayer event listeners
        try {
            mediaPlayer.reset();

            mediaPlayer.setDataSource(songsList.get(songIndex).get("songPath"));
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
            String songTitle = songsList.get(songIndex).get("songTitle");
            songTitleBox.setText(songTitle);

            String songCountry = songsList.get(songIndex).get("songCountry");
            songCountryBox.setText(songCountry);

            String songCommentary = songsList.get(songIndex).get("songCommentary");
            songCommentBox.setText(songCommentary);


            this.currentSongIndex = songIndex;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playPlayer() {
        if (!mediaPlayer.isPlaying()) mediaPlayer.start();
        }



    public void stopPlayer() {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }


    public void pausePlayer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }


    public void nextPlayer() {
        if (currentSongIndex < (songsList.size() - 1)) {
            currentSongIndex++;
        } else {
            // play first song
            currentSongIndex = 0;
        }

        stopPlayer();

        mediaPlayer.reset();
        startPlayer(currentSongIndex);

    }

    public void previousPlayer() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else {
            // play last song
            currentSongIndex = songsList.size() - 1;
        }

        stopPlayer();
        //reset mediaPlayer
        mediaPlayer.reset();
        startPlayer(currentSongIndex);
    }


    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        playPlayer();
    }
}