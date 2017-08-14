package com.example.tomy.entregable5.Controller;

import android.content.Context;

import com.example.tomy.entregable5.Model.DAO.DAOTrackDatabase;
import com.example.tomy.entregable5.Model.DAO.DAOTrackInternet;
import com.example.tomy.entregable5.Model.POJO.Track;
import com.example.tomy.entregable5.Model.POJO.TrackContainer;
import com.example.tomy.entregable5.View.MainActivity;
import com.example.tomy.entregable5.util.HTTPConnectionManager;
import com.example.tomy.entregable5.util.ResultListener;

import java.util.List;

/**
 * Created by tomy on 17/06/17.
 */

public class ControllerTrack {

    public void getListaDeTracks(Context context, final ResultListener<List<Track>> listenerView) {
        if (HTTPConnectionManager.isNetworkonline(context)) {
            DAOTrackInternet daoTrackInternet = new DAOTrackInternet();
            daoTrackInternet.getListaDeTracks(new ResultListener<List<Track>>() {
                @Override
                public void finish(List<Track> resultado) {
                    if(resultado != null) {
                        listenerView.finish(resultado);
                    }
                }
            });
        } else {
            DAOTrackDatabase daoTrackDatabase = new DAOTrackDatabase(context);
            List<Track> trackList = daoTrackDatabase.getTrackListFromDatabase();
            listenerView.finish(trackList);
        }
    }

    public void addTrackList(List<Track> trackList, Context context) {
        DAOTrackDatabase daoTrackDatabase = new DAOTrackDatabase(context);
        daoTrackDatabase.addTrackList(trackList);
    }
}
