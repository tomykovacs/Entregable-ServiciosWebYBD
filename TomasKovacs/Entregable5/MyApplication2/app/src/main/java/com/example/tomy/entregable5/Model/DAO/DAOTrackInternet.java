package com.example.tomy.entregable5.Model.DAO;

import com.example.tomy.entregable5.Model.POJO.Track;
import com.example.tomy.entregable5.Model.POJO.TrackContainer;
import com.example.tomy.entregable5.util.ResultListener;

import java.util.List;

/**
 * Created by tomy on 17/06/17.
 */

public class DAOTrackInternet {

    public void getListaDeTracks(ResultListener<List<Track>> listenerController) {
        TareaTrackInternet tarea = new TareaTrackInternet(listenerController);
        tarea.execute("https://api.myjson.com/bins/zwe9v");
    }

}
