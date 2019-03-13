package com.example.krist.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyReceiver extends android.content.BroadcastReceiver {

    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ha saltado la alarma", Toast.LENGTH_LONG).show();
        Vibrator vibrador = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrador.vibrate(2000);
        Toast.makeText(context, "ya ha tenido que vibrar", Toast.LENGTH_LONG).show();

        Intent intent6 = new Intent(context, fondo.class);
        System.out.println("pasapor aqui");
        intent6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent6);


    }

}