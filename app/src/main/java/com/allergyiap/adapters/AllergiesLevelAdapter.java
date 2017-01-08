package com.allergyiap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allergyiap.R;
import com.allergyiap.beans.Allergy;
import com.allergyiap.beans.AllergyLevel;
import com.allergyiap.entities.AllergyLevelEntity;
import com.allergyiap.service.AllergyService;

import java.util.List;

public class AllergiesLevelAdapter extends RecyclerView.Adapter<AllergiesLevelAdapter.AllergyViewHolder> {
    Context context;
    OnItemClickListener clickListener;
    List<AllergyLevel> allergies;

    public AllergiesLevelAdapter(Context context, List<AllergyLevel> list) {
        this.context = context;
        this.allergies = list;
    }

    public void setAllergies(List<AllergyLevel> list) {
        Log.d("LevelAllergyFragment", "setAllergies:" + list.size());
        this.allergies = list;
        notifyDataSetChanged();
    }

    @Override
    public AllergyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_allergy_level, viewGroup, false);
        return new AllergyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllergyViewHolder viewHolder, int i) {
        AllergyLevel aLevel = allergies.get(i);

        Allergy allergy = AllergyService.get(aLevel.getAllergy_idallergy());

        viewHolder.allergyLevel = aLevel;
        viewHolder.allergy = allergy;
        viewHolder.name.setText(allergy.getAllergy_name());
        //viewHolder.status.setText(aLevel.getForecast_level());
        int resource = 0, r_status = 0;
        switch((int)aLevel.getCurrent_level()){
            case 0 : resource = R.drawable.legend_level_allergy_null; break;
            case 1 : resource = R.drawable.legend_level_allergy_low; break;
            case 2 : resource = R.drawable.legend_level_allergy_medium; break;
            case 3 : resource = R.drawable.legend_level_allergy_high; break;
            case 4 : resource = R.drawable.legend_level_allergy_veryhigh; break;
        }
        viewHolder.image.setImageResource(resource);

        //if(aLevel.getForecast_level().equals("=")){}
        if(aLevel.getForecast_level().equals("A") || aLevel.getForecast_level().equals(">")){
            r_status = R.drawable.ic_increase;
        }
        if(aLevel.getForecast_level().equals("D") || aLevel.getForecast_level().equals("<")){
            r_status = R.drawable.ic_decrease;
        }
        //if(aLevel.getForecast_level().equals("!")){}

        if(r_status != 0) {
            viewHolder.image_status.setImageResource(r_status);
            viewHolder.image_status.setVisibility(View.VISIBLE);
        } else {
            viewHolder.image_status.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return allergies == null ? 0 : allergies.size();
    }


    public class AllergyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView status;
        ImageView image;
        ImageView image_status;
        AllergyLevel allergyLevel;
        Allergy allergy;
        View finalView;

        public AllergyViewHolder(View view) {
            super(view);
            this.finalView = view;
            name = (TextView) view.findViewById(R.id.name);
            status = (TextView) view.findViewById(R.id.status);
            image = (ImageView) view.findViewById(R.id.image);
            image_status = (ImageView) view.findViewById(R.id.icon_status);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition(), allergyLevel, allergy);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, AllergyLevel allergyLevel, Allergy allergy);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
