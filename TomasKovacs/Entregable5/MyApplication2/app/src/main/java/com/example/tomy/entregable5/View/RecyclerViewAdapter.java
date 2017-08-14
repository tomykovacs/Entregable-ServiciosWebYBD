package com.example.tomy.entregable5.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomy.entregable5.Model.POJO.Track;
import com.example.tomy.entregable5.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dh1 on 19/06/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Track> trackList;

    public RecyclerViewAdapter(Context context, List<Track> trackList) {
        this.context = context;
        this.trackList = trackList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_track, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Track track = trackList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.cargarDatos(track);
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textViewTitle;
        private TextView textViewId;
        private TextView textViewAlbumId;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewTrack);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTrackTitle);
            textViewId = (TextView) itemView.findViewById(R.id.textViewTrackId);
            textViewAlbumId = (TextView) itemView.findViewById(R.id.textViewAlbumId);
        }

        public void cargarDatos(Track track){
            Picasso.with(context).load(track.getThumbnailUrl()).placeholder(R.drawable.background).into(imageView);
            textViewTitle.setText(track.getTitle());
            textViewId.setText("Track ID "+track.getId().toString());
            textViewAlbumId.setText("Album ID "+track.getAlbumId().toString());
        }
    }
}
