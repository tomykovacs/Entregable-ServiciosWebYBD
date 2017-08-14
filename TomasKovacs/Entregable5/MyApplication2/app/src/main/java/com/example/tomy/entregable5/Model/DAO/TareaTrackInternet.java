package com.example.tomy.entregable5.Model.DAO;

import android.os.AsyncTask;

import com.example.tomy.entregable5.Model.POJO.Track;
import com.example.tomy.entregable5.Model.POJO.TrackContainer;
import com.example.tomy.entregable5.util.DAOException;
import com.example.tomy.entregable5.util.HTTPConnectionManager;
import com.example.tomy.entregable5.util.ResultListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomy on 17/06/17.
 */

public class TareaTrackInternet extends AsyncTask<String, Void, List<Track>> {

    private ResultListener<List<Track>> listenerController;

    public TareaTrackInternet(ResultListener<List<Track>> listenerController) {
        this.listenerController = listenerController;
    }

    @Override
    protected List<Track> doInBackground(String... params) {
        List<Track> trackList = new ArrayList<>();
        String url = params[0];

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        HTTPConnectionManager httpConnectionManager = null;

        try {

            httpConnectionManager = new HTTPConnectionManager();
            inputStream = httpConnectionManager.getRequestStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            Gson gson = new Gson();
            TrackContainer trackContainer = gson.fromJson(bufferedReader, TrackContainer.class);
            trackList = trackContainer.getTracks();


        } catch (DAOException e) {
            e.printStackTrace();
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                } else if (inputStream != null) {
                    inputStream.close();
                }

            } catch (IOException e2) {
                e2.printStackTrace();
            }
            httpConnectionManager.closeConnection();
        }


        return trackList;
    }

    @Override
    protected void onPostExecute(List<Track> resultado) {
        listenerController.finish(resultado);
    }
}
