package br.vince.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.vince.textmarker.TextMarker;
import br.vince.textmarker.TextMarkerGroup;

public class MainActivity extends AppCompatActivity {

    private TextMarker mWelcomeMenuButton;
    private TextMarker mWelcomeSettingsButton;
    private TextMarker mWelcomeRegisterButton;
    private TextMarker mWelcomeContactButton;
    private TextMarker mSetupLikeCheckbox;
    private TextMarker mSetupLikeRadio;
    private TextMarker mSetupInfo;

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

        mWelcomeMenuButton.setOnClickListenerInterception(new TextMarker.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarker view) {
                showMessage("Welcome!");
            }
        });
        mWelcomeSettingsButton.setOnClickListenerInterception(new TextMarker.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarker view) {
                showMessage("Settings!");
            }
        });
        mWelcomeRegisterButton.setOnClickListenerInterception(new TextMarker.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarker view) {
                showMessage("Register!");
            }
        });
        mWelcomeContactButton.setOnClickListenerInterception(new TextMarker.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarker view) {
                showMessage("Contact!");
            }
        });

        mSetupLikeCheckbox.setOnClickListenerInterception(new TextMarker.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarker view) {
                groupOne.setType(TextMarkerGroup.AnimateType.CHECKBOX);
            }
        });
        mSetupLikeRadio.setOnClickListenerInterception(new TextMarker.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarker view) {
                groupOne.setType(TextMarkerGroup.AnimateType.RADIOBUTTON);
            }
        });

    }

    protected void showMessage(final String message) {
        mSetupInfo.setText("Button clicked: " + message);
    }
}
