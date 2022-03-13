package com.example.android_assignment3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class PhoneStateEmojiReceiver extends BroadcastReceiver {

    private SharedPreferences sharePhoneBroad;

    private String state, phoneNumber;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

            phoneNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            sharePhoneBroad = context.getSharedPreferences("shared_phone", Context.MODE_PRIVATE);
            String path = sharePhoneBroad.getString(phoneNumber, null);

            if(path != null) {
                try {
                    Bitmap imageBitmap = getImageBitmap(context, path);

                    View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
                    ImageView toastImage = view.findViewById(R.id.toast_image);

                    showToast(context, toastImage, imageBitmap, view);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Log.i("phoneNumber", phoneNumber);
        }
    }

    private Bitmap getImageBitmap(Context context, String path) throws IOException {
        Bitmap imageBitmap = BitmapFactory.decodeStream(context.getAssets().open(path + ".jpg"));

        return imageBitmap;
    }

    private void showToast(Context context, ImageView toastImage, Bitmap image, View view) {
        Toast toast = new Toast(context);
        toastImage.setImageBitmap(image);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
