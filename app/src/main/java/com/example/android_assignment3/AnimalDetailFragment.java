package com.example.android_assignment3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AnimalDetailFragment extends Fragment implements View.OnClickListener {

    private Animal mAnimal;
    private TextView tv_animalName, tv_paragraph, tv_phoneNumber;
    private ImageView iv_animalImage, iv_favorite, iv_phone;
    private View dialogView;
    private TextView dialogSave, dialogDelete;
    private EditText dialogEditText;
    private ImageView dialogImage;
    private AlertDialog dialog;
    private SharedPreferences sharedPhone;

    private boolean favorite_status;

    public AnimalDetailFragment(Animal animal, SharedPreferences sharedPhone) {
        this.mAnimal = animal;
        this.sharedPhone = sharedPhone;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.screen03_item_animal, container, false);

         initViews(view);

         checkFavoriteStatus();
         checkPhoneNumber();

         return view;
    }

    private void initViews(View view) {
        tv_animalName = view.findViewById(R.id.screen03_tv_nameAnimal);
        tv_paragraph = view.findViewById(R.id.screen03_tv_paragraph);
        iv_animalImage = view.findViewById(R.id.screen03_iv_top);
        iv_favorite = view.findViewById(R.id.screen03_ic_favorite);
        iv_phone = view.findViewById(R.id.screen003_ic_phone);
        tv_phoneNumber = view.findViewById(R.id.screen03_phoneNumber);

        tv_animalName.setText(mAnimal.getName());
        tv_paragraph.setText(mAnimal.getParagraph());
        iv_animalImage.setImageBitmap(mAnimal.getScreen03_image());

        iv_favorite.setOnClickListener(this);
        iv_phone.setOnClickListener(this);

        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog, null, false);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen03_ic_favorite:
                setClickFavorite();
                break;

            case R.id.screen003_ic_phone:
                showDialog();
                break;

            case R.id.dialog_save:
                if(!dialogEditText.getText().toString().isEmpty()) {
                    dialog.dismiss();
                    tv_phoneNumber.setText(dialogEditText.getText().toString());
                    tv_phoneNumber.setVisibility(View.VISIBLE);

                    sharedPhone.edit().putString(mAnimal.getName(), tv_phoneNumber.getText().toString()).apply();
                    sharedPhone.edit().putString(tv_phoneNumber.getText().toString(), "m002_image/" + mAnimal.getGroup() + "/ic_" + mAnimal.getName().toLowerCase()).apply();
                    Log.i("tv_phoneNumber.getText().toString()", "m002_image/" + mAnimal.getGroup() + "/" + mAnimal.getName().toLowerCase());
                } else {
                    Toast.makeText(getContext(), "Please enter the phone number", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.dialog_delete:
                dialog.dismiss();
                sharedPhone.edit().remove(mAnimal.getName()).apply();
                sharedPhone.edit().remove(tv_phoneNumber.getText().toString()).apply();

                tv_phoneNumber.setText("");
                tv_phoneNumber.setVisibility(View.GONE);

                break;

            default:

                break;
        }
    }

    // Kiểm tra tình trạng favorite khi từ màn hình menu chuyển sang màn hình hiển thị thông tin chi tiết
    private void checkFavoriteStatus() {
        boolean favorite = ((MainActivity)getContext()).sharedFavorite.getBoolean(tv_animalName.getText().toString(), false);

        if(!favorite) {
            iv_favorite.setImageResource(R.drawable.ic_favorite_no);
            favorite_status = false;
        } else {
            iv_favorite.setImageResource(R.drawable.ic_favorite_yes);
            favorite_status = true;
        }
    }

    private void setClickFavorite() {
        if(!favorite_status) {
            iv_favorite.setImageResource(R.drawable.ic_favorite_yes);
            favorite_status = true;
            ((MainActivity)getContext()).sharedFavorite.edit().putBoolean(tv_animalName.getText().toString(), true).apply();
            Log.i("textViewName", tv_animalName.getText().toString());
        } else if(favorite_status) {
            iv_favorite.setImageResource(R.drawable.ic_favorite_no);
            favorite_status = false;
            ((MainActivity)getContext()).sharedFavorite.edit().putBoolean(tv_animalName.getText().toString(), false).apply();
        }
    }

    private void showDialog() {
        dialog = new AlertDialog.Builder(getContext()).create();
        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog, null, false);
        dialog.setView(dialogView);

        dialogImage = dialogView.findViewById(R.id.dialog_image);
        dialogSave = dialogView.findViewById(R.id.dialog_save);
        dialogDelete = dialogView.findViewById(R.id.dialog_delete);
        dialogEditText = dialogView.findViewById(R.id.dialog_editText);

        dialogImage.setImageBitmap(mAnimal.getScreen02_image());

        dialogSave.setOnClickListener(this);
        dialogDelete.setOnClickListener(this);

        dialog.show();

    }

    // Kiểm tra animal đã được thêm số điện thoại chưa và hiển thị số điện thoại của animal ở màn hình thông tin chi tiết
    private void checkPhoneNumber() {
        String phoneNumber = sharedPhone.getString(mAnimal.getName(), null);

        if(phoneNumber != null) {
            tv_phoneNumber.setText(phoneNumber);
            tv_phoneNumber.setVisibility(View.VISIBLE);
        }
    }
}
