package com.example.travel.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel.R;
import com.example.travel.netutil.Network;
import com.example.travel.netutil.NetworkTask;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity implements Network {
    String codesent;
    TextInputEditText ed1;
    TextInputEditText ed2;
    TextInputEditText ed3;
    TextInputEditText ed4;
    String email;
    String enteredCode;
    ImageView imageView;
    TextInputLayout ly3;
    Uri profile;
    String mobile;
    String name;
    StorageReference mStorageRef;
    String password;
    CircularProgressButton register;
    TextView textView;
    JSONObject json;
    StorageReference profileRef;
    private FirebaseAuth mAuth;

    public static Intent getchooserIntent(Context context) {
        List<Intent> intentList = new ArrayList<>();

        Intent chooserIntent = null;

        Intent pick = new Intent(Intent.ACTION_PICK);
        pick.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        pick.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cam.putExtra("return-data", true);
        cam.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(context)));

        intentList = addIntentstoList(context, intentList, pick);
        intentList = addIntentstoList(context, intentList, cam);
        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }
        return chooserIntent;
    }

    static List<Intent> addIntentstoList(Context context, List<Intent> intentList, Intent intent) {
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            intentList.add(targetedIntent);
        }
        return intentList;
    }

    public static File getTempFile(Context context) {
        File imageFile = new File(context.getExternalCacheDir(), "temp_img");
        imageFile.getParentFile().mkdirs();
        return imageFile;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_register);
        changeStatusBarColor();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        this.mAuth = FirebaseAuth.getInstance();
        this.ed1 = (TextInputEditText) findViewById(R.id.editTextName);
        this.ed2 = (TextInputEditText) findViewById(R.id.editTextEmail);
        this.ed3 = (TextInputEditText) findViewById(R.id.editTextMobile);
        this.ed4 = (TextInputEditText) findViewById(R.id.editTextPassword);
        this.ly3 = (TextInputLayout) findViewById(R.id.textInputPassword);
        this.textView = (TextView) findViewById(R.id.changelog);
        this.imageView = (ImageView) findViewById(R.id.changelog_img);
        this.register = (CircularProgressButton) findViewById(R.id.cirRegisterButton);
        this.ed4.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (!RegisterActivity.this.ly3.isPasswordVisibilityToggleEnabled()) {
                    TextInputLayout textInputLayout = RegisterActivity.this.ly3;
                    boolean z = true;
                    if (s.length() <= 1) {
                        z = false;
                    }
                    textInputLayout.setPasswordVisibilityToggleEnabled(z);
                }
            }
        });
        this.textView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                RegisterActivity.this.changeToLog(v);
            }
        });
        this.imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                RegisterActivity.this.changeToLog(v);
            }
        });
        this.register.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                RegisterActivity.this.register.startAnimation();
                RegisterActivity registerActivity = RegisterActivity.this;
                registerActivity.name = registerActivity.ed1.getText().toString();
                RegisterActivity registerActivity2 = RegisterActivity.this;
                registerActivity2.email = registerActivity2.ed2.getText().toString();
                RegisterActivity registerActivity3 = RegisterActivity.this;
                registerActivity3.mobile = registerActivity3.ed3.getText().toString();
                RegisterActivity registerActivity4 = RegisterActivity.this;
                registerActivity4.password = registerActivity4.ed4.getText().toString();
                String str = "Required...";
                if (TextUtils.isEmpty(RegisterActivity.this.name)) {
                    RegisterActivity.this.ed1.setError(str);
                    RegisterActivity.this.ed1.requestFocus();
                    RegisterActivity.this.register.revertAnimation();
                } else if (TextUtils.isEmpty(RegisterActivity.this.email)) {
                    RegisterActivity.this.ed2.setError(str);
                    RegisterActivity.this.ed2.requestFocus();
                    RegisterActivity.this.register.revertAnimation();
                } else if (TextUtils.isEmpty(RegisterActivity.this.mobile)) {
                    RegisterActivity.this.ed3.setError(str);
                    RegisterActivity.this.ed3.requestFocus();
                    RegisterActivity.this.register.revertAnimation();
                } else if (TextUtils.isEmpty(RegisterActivity.this.password)) {
                    RegisterActivity.this.ed4.setError(str);
                    RegisterActivity.this.ed4.requestFocus();
                    RegisterActivity.this.register.revertAnimation();
                } else {
                    RegisterActivity.this.sendOTP();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean verifyCode() {
        this.mAuth.signInWithCredential(PhoneAuthProvider.getCredential(this.codesent, this.enteredCode)).addOnCompleteListener((AppCompatActivity) this, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final RegisterActivity registerActivity = RegisterActivity.this;
                    Toast.makeText(RegisterActivity.this, "Verifying", Toast.LENGTH_SHORT).show();
                    final NetworkTask networkTask = new NetworkTask(registerActivity, registerActivity, null);
                    final StringBuilder sb = new StringBuilder();
                    sb.append(RegisterActivity.this.getResources().getString(R.string.ip));
                    sb.append("/signup/");
                    json = new JSONObject();
                    try {
                        json.put("name", name);
                        json.put("email", email);
                        json.put("mobile", mobile);
                        json.put("password", password);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (profile != null) {
                        profileRef = mStorageRef.child(email.replace(".", "-") + ".jpg");
                        networkTask.execute(new String[]{sb.toString(), "post", json.toString()});
                        profileRef.putFile(profile)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        //json.put("path", taskSnapshot.getUploadSessionUri().toString());
                                        return;

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, "Upload Failed.!!\nPlease try again.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        Toast.makeText(RegisterActivity.this, "Something went Wrong.!!\nPlease try again.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else {
                        networkTask.execute(new String[]{sb.toString(), "post", json.toString()});
                    }
                }
                //Toast.makeText(RegisterActivity.this, "Mobile Verification Failed", Toast.LENGTH_LONG).show();
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public void sendOTP() {
        PhoneAuthProvider instance = PhoneAuthProvider.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("+91");
        sb.append(this.mobile);
        instance.verifyPhoneNumber(sb.toString(), 2, TimeUnit.SECONDS, this, (PhoneAuthProvider.OnVerificationStateChangedCallbacks) new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            }

            public void onVerificationFailed(FirebaseException e) {
                RegisterActivity registerActivity = RegisterActivity.this;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(e.toString());
                Toast.makeText(registerActivity, sb.toString(), Toast.LENGTH_LONG).show();
            }

            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toast.makeText(RegisterActivity.this, "Sending Code..", Toast.LENGTH_SHORT).show();
                RegisterActivity.this.codesent = s;
                final EditText input = new EditText(RegisterActivity.this);
                Builder alertDialog = new Builder(RegisterActivity.this);
                alertDialog.setTitle("OTP");
                alertDialog.setCancelable(false);
                input.setLayoutParams(new LayoutParams(-1, -1));
                alertDialog.setView(input);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        RegisterActivity.this.enteredCode = input.getText().toString().trim();
                        if (RegisterActivity.this.enteredCode.length() != 0) {
                            boolean access$100 = RegisterActivity.this.verifyCode();
                        }
                    }
                });
                alertDialog.create().show();
            }
        });
    }

    private void changeStatusBarColor() {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void changeToLog(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, 17432579);
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, 17432579);
        finish();
    }

    public void postTask(String data) {
        if (data.equals("1")) {
            RegisterActivity.this.register.stopAnimation();
            Toast.makeText(this, "Registration Successful.", Toast.LENGTH_LONG).show();
            overridePendingTransition(R.anim.slide_in_left, 17432579);
            finish();
            return;
        }
        profileRef.delete();
        Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show();
    }

    public void select_img(View view) {
        startActivityForResult(RegisterActivity.getchooserIntent(this), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            boolean isCamera = (data == null ||
                    data.getData() == null ||
                    data.getData().toString().contains(getTempFile(this).toString()));
            if (isCamera) {
                profile = Uri.fromFile(getTempFile(this));
            } else {
                profile = data.getData();
            }
        } else {
            Toast.makeText(this, "Select an Image.", Toast.LENGTH_SHORT).show();
        }
    }
}
