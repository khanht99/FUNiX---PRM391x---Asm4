package com.example.android_assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class Screen02_fragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private List<Animal> animalList;
//    private SharedPreferences sharedFavorite;
    private View fragmentView;
    private DrawerLayout drawer;
    private ImageView iv_menu;
    private TableRow sideBar_sea, sideBar_mammal, sideBar_bird;

    public Screen02_fragment(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.screen02_fragment, container, false);

        initViews();
        setRecyclerView();

        return fragmentView;
    }

    private void initViews() {
        drawer = fragmentView.findViewById(R.id.screen02_drawer);
        iv_menu = fragmentView.findViewById(R.id.ic_menu);
        iv_menu.setOnClickListener(this);

        sideBar_sea = fragmentView.findViewById(R.id.group_seas);
        sideBar_mammal = fragmentView.findViewById(R.id.group_mammals);
        sideBar_bird = fragmentView.findViewById(R.id.group_birds);

        sideBar_sea.setOnClickListener(this);
        sideBar_mammal.setOnClickListener(this);
        sideBar_bird.setOnClickListener(this);
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = fragmentView.findViewById(R.id.screen02_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(new AnimalRecyclerView(animalList, getContext()));
    }


    @Override
    public void onClick(View viewOnClick) {
        ((MainActivity)mContext).showSideBarMenu(viewOnClick, drawer);
    }
}
