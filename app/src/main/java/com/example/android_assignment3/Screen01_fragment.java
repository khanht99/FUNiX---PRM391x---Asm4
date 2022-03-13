package com.example.android_assignment3;

import android.content.Context;
import android.media.Image;
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

import java.io.IOException;

public class Screen01_fragment extends Fragment implements View.OnClickListener {

    private View fragmentView;
    private Context mContext;
    private DrawerLayout drawer;
    private ImageView iv_menu;
    TableRow sideBar_Sea, sideBar_Mammal, sideBar_Bird;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.screen01_fragment, container, false);

        initViews();

        return  fragmentView;
    }

    private void initViews() {
        drawer = fragmentView.findViewById(R.id.screen01_drawer);
        iv_menu = fragmentView.findViewById(R.id.ic_menu);
        iv_menu.setOnClickListener(this);

        sideBar_Sea = fragmentView.findViewById(R.id.group_seas);
        sideBar_Mammal = fragmentView.findViewById(R.id.group_mammals);
        sideBar_Bird = fragmentView.findViewById(R.id.group_birds);

        sideBar_Sea.setOnClickListener(this);
        sideBar_Mammal.setOnClickListener(this);
        sideBar_Bird.setOnClickListener(this);
    }

    @Override
    public void onClick(View viewOnClick) {
        ((MainActivity)mContext).showSideBarMenu(viewOnClick, drawer);

    }
}
