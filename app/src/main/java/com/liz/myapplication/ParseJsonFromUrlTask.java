package com.liz.myapplication;

import android.os.AsyncTask;

import com.liz.myapplication.enity.List;

import java.util.ArrayList;

public class ParseJsonFromUrlTask extends AsyncTask<String,Void,ArrayList<List>>{
    @Override
    protected ArrayList<List> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<List> lists) {
        super.onPostExecute(lists);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

}
