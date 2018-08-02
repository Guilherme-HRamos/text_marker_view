package br.vince.sampleapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.animation.OvershootInterpolator;

import br.vince.textmarker.TextMarkerView;
import br.vince.textmarker.TextMarkerGroup;

public class MainActivity extends AppCompatActivity {

    private TextMarkerView mWelcomeMenuButton;
    private TextMarkerView mWelcomeSettingsButton;
    private TextMarkerView mWelcomeRegisterButton;
    private TextMarkerView mWelcomeContactButton;
    private TextMarkerView mSetupLikeCheckbox;
    private TextMarkerView mSetupLikeRadio;
    private TextMarkerView mSetupInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeMenuButton = findViewById(R.id.welcome_home_button);
        mWelcomeSettingsButton = findViewById(R.id.welcome_settings_button);
        mWelcomeRegisterButton = findViewById(R.id.welcome_register_button);
        mWelcomeContactButton = findViewById(R.id.welcome_contact_button);
        mSetupInfo = findViewById(R.id.setup_info);
        mSetupLikeCheckbox = findViewById(R.id.setup_like_checkbox);
        mSetupLikeRadio = findViewById(R.id.setup_like_radio);

        setupListeners();
    }

    private void setupListeners() {
        final TextMarkerGroup groupOne = new TextMarkerGroup(TextMarkerGroup.AnimateType.RADIOBUTTON,
                mWelcomeMenuButton, mWelcomeSettingsButton,
                mWelcomeRegisterButton, mWelcomeContactButton);

        new TextMarkerGroup(TextMarkerGroup.AnimateType.RADIOBUTTON,
                mSetupLikeCheckbox, mSetupLikeRadio);

        mWelcomeMenuButton.setOnClickListenerInterception(new TextMarkerView.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarkerView view) {
                showMessage("Welcome!");
            }
        });
        mWelcomeSettingsButton.setOnClickListenerInterception(new TextMarkerView.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarkerView view) {
                showMessage("Settings!");
            }
        });
        mWelcomeRegisterButton.setOnClickListenerInterception(new TextMarkerView.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarkerView view) {
                showMessage("Register!");
            }
        });
        mWelcomeContactButton.setOnClickListenerInterception(new TextMarkerView.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarkerView view) {
                showMessage("Contact!");
            }
        });

        mSetupLikeCheckbox.setOnClickListenerInterception(new TextMarkerView.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarkerView view) {
                groupOne.setType(TextMarkerGroup.AnimateType.CHECKBOX);
            }
        });
        mSetupLikeRadio.setOnClickListenerInterception(new TextMarkerView.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarkerView view) {
                groupOne.setType(TextMarkerGroup.AnimateType.RADIOBUTTON);
            }
        });
    }



    protected void showMessage(final String message) {
        mSetupInfo.setText("Button clicked: " + message);
    }
}
