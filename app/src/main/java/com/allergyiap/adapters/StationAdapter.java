package com.allergyiap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.allergyiap.R;
import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.Station;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {
    Context context;
    OnItemClickListener clickListener;
    List<Station> stations;

    public StationAdapter(Context context, List<Station> list) {
        this.context = context;
        this.stations = list;
    }

    public void setStation(List<Station> list) {
        this.stations = list;
        notifyDataSetChanged();
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_station, viewGroup, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StationViewHolder viewHolder, int i) {
        Station station = stations.get(i);

        viewHolder.station = station;
        viewHolder.name.setText(station.getName_station());
    }

    @Override
    public int getItemCount() {
        return stations == null ? 0 : stations.size();
    }


    public class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        Station station;

        public StationViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.chk_name);
            //itemView.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition(), station);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Station station);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
