package com.example.travel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;

import java.util.Locale;

public class MyReceiver extends BroadcastReceiver {
    Context context;
    TextToSpeech tts;

    public MyReceiver() {
    }

    public MyReceiver(final Context context2) {
        this.context = context2;
        this.tts = new TextToSpeech(context2, new OnInitListener() {
            public void onInit(int status) {
                if (status != 0) {
                    Toast.makeText(context2, "TTS is not available", 0).show();
                } else if (MyReceiver.this.tts.setLanguage(Locale.ENGLISH) != 0) {
                    Toast.makeText(context2, "Lang. Not Available", 1).show();
                }
            }
        });
    }

    public void onReceive(Context context2, Intent intent) {
        if (intent.getAction().equals("my.filter.speak")) {
            TextToSpeech textToSpeech = this.tts;
            if (textToSpeech != null) {
                textToSpeech.speak(intent.getStringExtra("data"), 0, null);
                return;
            }
            return;
        }
        TextToSpeech textToSpeech2 = this.tts;
        if (textToSpeech2 != null) {
            textToSpeech2.stop();
        }
    }
}
