package com.example.android_assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String SCREEN02_IMAGE_PATH = "m002_image";
    private final String SCREEN03_IMAGE_PATH = "m003_image";
    private final String SCREEN03_PARAGRAPH_PATH = "Paragraph";
    private final String GROUP_SEA = "Seas";
    private final String GROUP_MAMMALS = "Mammals";
    private final String GROUP_BIRDS = "Birds";
    private final String SHARED_FAVORITE = "favorite";
    public final String SHARED_PHONE = "shared_phone";
    public SharedPreferences sharedFavorite;
    public SharedPreferences sharedPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(new Screen01_fragment());
        checkSelfPermission();
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_screen, fragment, null).commit();
    }

    public void showSideBarMenu(View onClickView, DrawerLayout drawer)  {

        switch (onClickView.getId()) {
            case R.id.ic_menu:
                onClickView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
                drawer.openDrawer(Gravity.LEFT);
                break;

            case R.id.group_seas:
                onClickView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
                gotoScreen02(loadDataAnimal(GROUP_SEA));
                break;

            case R.id.group_mammals:
                onClickView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
                gotoScreen02(loadDataAnimal(GROUP_MAMMALS));
                break;

            case R.id.group_birds:
                onClickView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
                gotoScreen02(loadDataAnimal(GROUP_BIRDS));
                break;

            default:
                drawer.closeDrawers();
                break;
        }
    }

    public void gotoScreen02(List<Animal> animalList) {
        sharedFavorite = getSharedPreferences(SHARED_FAVORITE, MODE_PRIVATE);
        Screen02_fragment screen02 = new Screen02_fragment(animalList);
        showFragment(screen02);
    }

    public List<Animal> loadDataAnimal(String groupAnimal)  {

        List<Animal> animalList = new ArrayList<>();

        try {

            AssetManager assetManager = getAssets();
            String [] listItem  = assetManager.list(SCREEN02_IMAGE_PATH + "/" + groupAnimal);

            for(String fileName : listItem) {
                String name = fileName.substring(3, fileName.indexOf("."));
                String title = name.substring(0, 1).toUpperCase() + name.substring(1);

                Bitmap image02 = getImageScreen02(groupAnimal, fileName);
                Bitmap image03 = getImageScreen03(groupAnimal, name);
                String paragraph = getParagraph(groupAnimal, title);

                animalList.add(new Animal(groupAnimal, title, image02, image03, paragraph));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return animalList;
    }

    private Bitmap getImageScreen02(String groupAnimal, String fileName) throws IOException {
        AssetManager assetManager = getAssets();
        Bitmap image = BitmapFactory.decodeStream(assetManager.open(SCREEN02_IMAGE_PATH + "/" + groupAnimal + "/" + fileName));

        return image;
    }

    private Bitmap getImageScreen03(String groupAnimal, String name) throws IOException {
        AssetManager assetManager = getAssets();
        Bitmap image = BitmapFactory.decodeStream(assetManager.open(SCREEN03_IMAGE_PATH + "/" + groupAnimal + "/" + name + ".jpg"));

        return image;
    }

    private String getParagraph(String groupAnimal, String title) throws IOException {
        AssetManager assetManager = getAssets();
        InputStream inputStream = assetManager.open(SCREEN03_PARAGRAPH_PATH + "/" + groupAnimal + "/" + title + ".txt");

        int size = inputStream.available();
        byte[] buffered = new byte[size];
        inputStream.read(buffered);
        inputStream.close();

        return new String(buffered);
    }

    public void gotoScreen03(List<Animal> animalList, Animal currentItem) {
        Screen03_fragment screen03 = new Screen03_fragment(animalList, currentItem);
        showFragment(screen03);
    }

    private void checkSelfPermission() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) !=
                PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, 101);

            return;
        } else {
            showFragment(new Screen01_fragment());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }
}