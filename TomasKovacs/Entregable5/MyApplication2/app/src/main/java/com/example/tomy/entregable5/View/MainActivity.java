package com.example.tomy.entregable5.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tomy.entregable5.Controller.ControllerTrack;
import com.example.tomy.entregable5.Model.DAO.DAOTrackDatabase;
import com.example.tomy.entregable5.Model.POJO.Track;
import com.example.tomy.entregable5.Model.POJO.TrackContainer;
import com.example.tomy.entregable5.R;
import com.example.tomy.entregable5.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ControllerTrack controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(MainActivity.this, null);

        controller = new ControllerTrack();
        controller.getListaDeTracks(this, new ResultListener<List<Track>>() {
            @Override
            public void finish(List<Track> resultado) {
                if(resultado != null) {
                    controller.addTrackList(resultado, MainActivity.this);
                    adapter.setTrackList(resultado);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}
