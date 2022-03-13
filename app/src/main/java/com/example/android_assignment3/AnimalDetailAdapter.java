package com.example.android_assignment3;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class AnimalDetailAdapter extends FragmentStatePagerAdapter {

    private List<Animal> mAnimalList;
    private SharedPreferences sharedPhone;

    public AnimalDetailAdapter(@NonNull FragmentManager fm, int behavior, List<Animal> animalList, SharedPreferences sharedPhone) {
        super(fm, behavior);
        this.mAnimalList = animalList;
        this.sharedPhone = sharedPhone;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(mAnimalList == null || mAnimalList.isEmpty()) {
            return null;
        }

        Animal animal = mAnimalList.get(position);
        AnimalDetailFragment animalDetailFragment = new AnimalDetailFragment(animal, sharedPhone);

        return animalDetailFragment;
    }

    @Override
    public int getCount() {
        if(mAnimalList != null) {
            return mAnimalList.size();
        }
        return 0;
    }
}
