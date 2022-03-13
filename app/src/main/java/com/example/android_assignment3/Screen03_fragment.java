package com.example.android_assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class Screen03_fragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private List<Animal> animalList;
    private Animal currentAnimal;
    private ImageView iv_favorite;
    private boolean favorite_status = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public Screen03_fragment(List<Animal> animalList, Animal currentAnimal) {
        this.animalList = animalList;
        this.currentAnimal = currentAnimal;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen03_fragment, container, false);

        changeIconMenuToBack(view);

        ((MainActivity)mContext).sharedPhone = mContext.getSharedPreferences(((MainActivity)mContext).SHARED_PHONE, Context.MODE_PRIVATE);

        ViewPager viewPager = view.findViewById(R.id.screen03_viewpage);
        viewPager.setAdapter(new AnimalDetailAdapter(getActivity().getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, animalList, ((MainActivity) mContext).sharedPhone));
        viewPager.setCurrentItem(animalList.indexOf(currentAnimal), true);

        return view;
    }

    private void changeIconMenuToBack(View view) {
        ImageView iv_back = view.findViewById(R.id.ic_menu);
        iv_back.setImageResource(R.drawable.ic_back);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_menu:
                ((MainActivity)mContext).gotoScreen02(animalList);
                break;
//            case R.id.screen03_ic_favorite:
//                if(favorite_status = false) {
//                    iv_favorite.setImageResource(R.drawable.ic_favorite_yes);
//                    favorite_status = true;
//                    ((MainActivity)mContext).sharedFavorite.edit().putBoolean(currentAnimal.getName(), true);
//                } else if(favorite_status) {
//                    iv_favorite.setImageResource(R.drawable.ic_favorite_no);
//                    favorite_status = false;
//                    ((MainActivity)mContext).sharedFavorite.edit().putBoolean(currentAnimal.getName(), false);
//                }
            default:
                break;
        }

    }
}
