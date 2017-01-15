package com.allergyiap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.allergyiap.R;
import com.allergyiap.beans.Allergy;
import com.allergyiap.entities.AllergyLevelEntity;
import com.allergyiap.service.UserAllergyService;

import java.util.List;

public class AllergyAdapter extends RecyclerView.Adapter<AllergyAdapter.AllergyViewHolder> {
    Context context;
    OnItemClickListener clickListener;
    List<Allergy> allergies;

    public AllergyAdapter(Context context, List<Allergy> list) {
        this.context = context;
        this.allergies = list;
    }

    public void setAllergy(List<Allergy> list) {
        this.allergies = list;
        notifyDataSetChanged();
    }

    @Override
    public AllergyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_allergy, viewGroup, false);
        return new AllergyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllergyViewHolder viewHolder, int i) {
        Allergy allergy = allergies.get(i);

        viewHolder.allergyEntity = allergy;
        viewHolder.name.setText(allergy.getAllergy_name());
        viewHolder.name.setChecked(UserAllergyService.getTheCurrentUserHasThisAllergy(allergy.getIdallergy()));
    }

    @Override
    public int getItemCount() {
        return allergies == null ? 0 : allergies.size();
    }


    public class AllergyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CheckedTextView name;
        Allergy allergyEntity;

        public AllergyViewHolder(View view) {
            super(view);
            name = (CheckedTextView) view.findViewById(R.id.chk_name);
            //itemView.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition(), allergyEntity);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Allergy alertEntity);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
