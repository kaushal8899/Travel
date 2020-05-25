package com.example.travel.util;

import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import java.util.concurrent.atomic.AtomicBoolean;

public class Justify {
    public static void justify(TextView textView) {
        final AtomicBoolean isJustify = new AtomicBoolean(false);
        String textString = textView.getText().toString();
        TextPaint textPaint = textView.getPaint();
        final SpannableStringBuilder builder = new SpannableStringBuilder();
        final TextView textView2 = textView;
        final String str = textString;
        final TextPaint textPaint2 = textPaint;

        textView.post(new Runnable() {
            public void run() {
                int textViewWidth;
                int lineCount;
                if (!isJustify.get()) {
                    int lineCount2 = textView2.getLineCount();
                    int textViewWidth2 = textView2.getWidth();
                    int i = 0;
                    while (true) {
                        if (i >= lineCount2) {
                            int i2 = textViewWidth2;
                            break;
                        }
                        String lineString = str.substring(textView2.getLayout().getLineStart(i), textView2.getLayout().getLineEnd(i));
                        if (i == lineCount2 - 1) {
                            builder.append(new SpannableString(lineString));
                            int i3 = lineCount2;
                            int i4 = textViewWidth2;
                            break;
                        }
                        String trimSpaceText = lineString.trim();
                        String removeSpaceText = lineString.replaceAll(" ", "");
                        float eachSpaceWidth = (((float) textViewWidth2) - textPaint2.measureText(removeSpaceText)) / ((float) (trimSpaceText.length() - removeSpaceText.length()));
                        SpannableString spannableString = new SpannableString(lineString);
                        int j = 0;
                        while (j < trimSpaceText.length()) {
                            if (trimSpaceText.charAt(j) == ' ') {
                                lineCount = lineCount2;
                                ColorDrawable colorDrawable = new ColorDrawable(ViewCompat.MEASURED_SIZE_MASK);
                                textViewWidth = textViewWidth2;
                                colorDrawable.setBounds(0, 0, (int) eachSpaceWidth, 0);
                                ColorDrawable colorDrawable2 = colorDrawable;
                                spannableString.setSpan(new ImageSpan(colorDrawable), j, j + 1, 33);
                            } else {
                                lineCount = lineCount2;
                                textViewWidth = textViewWidth2;
                            }
                            j++;
                            lineCount2 = lineCount;
                            textViewWidth2 = textViewWidth;
                        }
                        int lineCount3 = lineCount2;
                        int i5 = textViewWidth2;
                        builder.append(spannableString);
                        i++;
                        lineCount2 = lineCount3;
                    }
                    textView2.setText(builder);
                    isJustify.set(true);
                }
            }
        });
    }
}
