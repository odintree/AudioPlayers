package com.example.odintree.audioplayers;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;


public class SongsManager {

    protected final String TAG = AndroidBuildingMusicPlayerActivity.class.getSimpleName();
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private HashMap<String, Song> songList = new HashMap<>();


     int[] rawPath = {
            R.raw.bensoundbrazilsamba,
            R.raw.bensoundcountryboy,
            R.raw.bensoundindia,
            R.raw.bensoundlittleplanet,
            R.raw.bensoundpsychedelic,
            R.raw.bensoundrelaxing,
            R.raw.bensoundtheelevatorbossanova
    };

     String[] title = {
            "bensoundbrazilsamba",
            "bensoundcountryboy",
            "bensoundindia",
            "bensoundlittleplanet",
            "bensoundpsychedelic",
            "bensoundrelaxing",
            "bensoundtheelevatorbossanova"


    };


     String[] countryList = {
            "Brazil",
            "USA",
            "India",
            "Iceland",
            "South Korea",
            "Indonesia",
            "Brazil"
    };
     String[] comments = {
            "Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade religious particularly of Angola and African traditions.",
            "Country music is a genre of American popular originated Southern States in the 1920s music that in the United",
            "The music of India includes multiple varieties of folk music, pop, and Indian classical music. India's classical music tradition, including Hindustani music and Carnatic, has a history spanning millennia and developed over several eras",
            "The music of Iceland includes vibrant folk and pop traditions. Well-known artists from Iceland include medieval music group Voces Thules, alternative rock band The Sugarcubes, singers Björk and Emiliana Torrini, post- rock band Sigur Rós and indie folk/indie pop band Of Monsters and Men",
            "The Music of South Korea has evolved over the course of the decades since the end of the Korean War, and has its roots in the music of the Korean people, who have inhabited the Korean peninsula for over a millennium. Contemporary South Korean music can be divided into three different categories: Traditional Korean folk music, popular music, or K- pop, and Western- influenced non-popular music",
            "The music of Indonesia demonstrates its cultural diversity, the local musical creativity, as well as subsequent foreign musical influences that shaped contemporary music scenes of Indonesia. Nearly thousands Indonesian having its own cultural and artistic history and character Nearly of islands",
            "Samba is a Brazilian musical genre and dance style, with its roots in Africa via the West African slave trade religious particularly of Angola"
    };

     String[] duration = {"04:00", "03:27", "04:13", "06:36", "03:56", "04:48", "04:14"};



    public ArrayList<HashMap<String, String>> getPlayList() {

        for (int i = 0; i < rawPath.length; i++) {

            HashMap<String, String> song = new HashMap<String, String>();

            song.put("songTitle", title[i]);
            song.put("songCountry", countryList[i]);
            song.put("songCommentary", comments[i]);
            song.put("songPath", "android.resource://" + AndroidBuildingMusicPlayerActivity.PACKAGE_NAME + "/" + rawPath[i]);

            songsList.add(song);
        }
            return songsList;
    }


        /**
         * Class to filter files which are having .mp3 extension, I'm just working with mp3 files, change the format if you nee
         * other formats.
         */
        class FileExtensionFilter implements FilenameFilter {
            public boolean accept(File dir, String name) {
                return (name.endsWith(".mp3") || name.endsWith(".MP3"));
            }
        }
    }



