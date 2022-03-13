package com.example.android_assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalRecyclerView extends RecyclerView.Adapter<AnimalRecyclerView.AnimalItem> {

    private List<Animal> animalList;
    private Context mContext;
    private Animal animalItem;

    public AnimalRecyclerView(List<Animal> animalList, Context mContext) {
        this.animalList = animalList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AnimalItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.screen02_item_animal, parent, false);

        return new AnimalItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalItem holder, int position) {
        animalItem = animalList.get(position);

        holder.imageAnimal.setImageBitmap(animalItem.getScreen02_image());
        holder.nameAnimal.setText(animalItem.getName());
        holder.nameAnimal.setTag(animalItem);
        setFavorite(holder);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    private void setFavorite(AnimalItem holder) {
        boolean favorite = ((MainActivity)mContext).sharedFavorite.getBoolean(animalItem.getName(), false);
        Log.i("ANimalName", animalItem.getName());
        if(!favorite) {
            holder.favorite.setVisibility(View.GONE);
        } else {
            holder.favorite.setVisibility(View.VISIBLE);
        }
    }

    public class AnimalItem extends RecyclerView.ViewHolder {
        TextView nameAnimal;
        ImageView imageAnimal;
        ImageView favorite;

        public AnimalItem(@NonNull View itemView) {
            super(itemView);

            nameAnimal = itemView.findViewById(R.id.screen02_tv_item);
            imageAnimal = itemView.findViewById(R.id.screen02_iv_item);
            favorite = itemView.findViewById(R.id.screen02_favorite);

            imageAnimal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.alpha));
                    ((MainActivity)mContext).gotoScreen03(animalList, (Animal) nameAnimal.getTag());
                }
            });
        }
    }
}
