package com.example.wikidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wikidesign.help.ParentBottomSheetDialog;

import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.switchFaceID)
    SwitchCompat switchFaceID;
    @BindView(R.id.switchTouchID)
    SwitchCompat switchTouchID;
    @BindView(R.id.tvAuth)
    TextView tvAuth;
    @BindView(R.id.ivIconHelp)
    ImageView ivIconHelp;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);



    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @OnClick(R.id.ivLoginArrowBack)
    void OnClickBack(){
        SignInAndRegisterActivity.startMe(this);
    }

    @OnClick(R.id.btnSignIn)
    void OnClickIntentWelcome(){
        if (switchFaceID.isChecked() || switchTouchID.isChecked()){
//            checkAuthenticateLogin();
            buttonAuthenticate();
        }

    }

    public void checkAuthenticateLogin(){
        BiometricManager biometricManager = BiometricManager.from(this);
        if (biometricManager.canAuthenticate() != BiometricManager.BIOMETRIC_SUCCESS){

            tvAuth.setText("Biometric Not Supported");
            return;
        }else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED){
            tvAuth.setText("You have not set up any authentication");

        } else {
            executor = ContextCompat.getMainExecutor(this);
            biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    tvAuth.setText("Login by user account");
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    tvAuth.setText("Login success");
                    WelcomeActivity.startMe(LoginActivity.this);
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    tvAuth.setText("Login failed, please check information");
                }
            });

            promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Biometric login for my app")
                    .setSubtitle("Log in using your biometric credential")
                    .setNegativeButtonText("Use account password")
                    .setConfirmationRequired(false)
                    .build();

            biometricPrompt.authenticate(promptInfo);
        }
    }

    private void buttonAuthenticate(){
        BiometricManager biometricManager = BiometricManager.from(this);
        if (biometricManager.canAuthenticate() != BiometricManager.BIOMETRIC_SUCCESS){
            if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE){
                tvAuth.setText("The device does not have a biometric sensor.");
            }else if(biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE){
                tvAuth.setText("Biometric features are currently unavailable.");
            }else if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
                tvAuth.setText("You have not set up any authentication");
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, RESULT_OK);
            }

        }else {
            executor = ContextCompat.getMainExecutor(this);
            biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    tvAuth.setText("Login by user account");
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    tvAuth.setText("Login success");
                    WelcomeActivity.startMe(LoginActivity.this);
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    tvAuth.setText("Login failed, please check information");
                }
            });

            promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Authentication for \"25Hours\" ")
                    .setNegativeButtonText("Cancel")
                    .setConfirmationRequired(true)
                    .build();
            biometricPrompt.authenticate(promptInfo);
        }
    }

    //Open Sheet Bottom Dialog

    @OnClick(R.id.ivIconHelp)
    void openSheeBottomDialog(){
        openParenSheetDialog();
    }

    private void openParenSheetDialog(){
        ParentBottomSheetDialog parentBottomSheetDialog = ParentBottomSheetDialog.newInstance();
        parentBottomSheetDialog.show(getSupportFragmentManager(), parentBottomSheetDialog.getTag());

    }





}