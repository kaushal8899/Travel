package com.example.travel.activities;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.travel.R;
import com.example.travel.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;


public class ProfileActivity extends AppCompatActivity {


    StorageReference mStorageRef,prof;
    /* renamed from: t1 */
    TextView f89t1;
    ImageView profile;

    /* renamed from: t2 */
    TextView f90t2;

    /* renamed from: t3 */
    TextView f91t3;

    /* renamed from: t4 */
    TextView f92t4;
    Uri dp;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        mStorageRef = FirebaseStorage.getInstance().getReference();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        User u = (User) getIntent().getExtras().get("user");
        profile = findViewById(R.id.profile);
        prof = mStorageRef.child(u.getEmail().replace(".", "-") + ".jpg");
        updateImage();
        {
            this.f89t1 = (TextView) findViewById(R.id.name);
            this.f90t2 = (TextView) findViewById(R.id.mobile);
            this.f91t3 = (TextView) findViewById(R.id.email);
            this.f92t4 = (TextView) findViewById(R.id.emailtop);
            this.f89t1.setText(u.getName());
            this.f90t2.setText(u.getMobile());
            this.f91t3.setText(u.getEmail());
            this.f92t4.setText(u.getEmail());
        }
        ((TextView) findViewById(R.id.back)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ProfileActivity.this.onBackPressed();
            }
        });
    }

    void updateImage(){
        try {
            final File localFile = File.createTempFile("images", "jpg");
            prof
                    .getFile(localFile)
                        .addOnCompleteListener(this, new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Picasso.get().load(localFile).into(profile);
                                profile
                                        .setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                         startActivityForResult(RegisterActivity.getchooserIntent(getApplicationContext()), 100);
                                        }
                                        });
                        }
                        });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            boolean isCamera = (data == null ||
                    data.getData() == null ||
                    data.getData().toString().contains(RegisterActivity.getTempFile(this).toString()));
            if (isCamera) {
                dp = Uri.fromFile(RegisterActivity.getTempFile(this));
            } else {
                dp = data.getData();
            }
            prof
                    .putFile(dp)
                        .addOnCompleteListener(this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                  updateImage();
                            }
                        })
                        .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(ProfileActivity.this, "Profile Updated.!!", Toast.LENGTH_SHORT).show();
                            }
                        });
        } else {
            Toast.makeText(this, "Select an Image.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, 17432579);
        finish();
    }

    public void changePassword(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this,R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        Button submit,cancel;
        {
            alertDialog.setView(R.layout.dialouge);
            submit = findViewById(R.id.submit);
            submit.setOnClickListener(v -> {
            });
            cancel = findViewById(R.id.cancel);
            cancel.setOnClickListener(v -> {

            });
            alertDialog.setCancelable(false);
        }
        alertDialog.show();
    }
}
