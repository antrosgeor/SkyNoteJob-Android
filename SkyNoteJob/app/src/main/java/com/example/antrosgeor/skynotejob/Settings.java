package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Locale;

/**
 * A {@link PreferenceActivity} that presents a set of application settings.
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class Settings extends PreferenceActivity

        implements Preference.OnPreferenceChangeListener {

    String stringValue, id;
    int User_id;
    String languageToload;
    ListPreference listPreference;
    int prefIndex;
     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general);

        // For all preferences, attach an OnPreferenceChangeListener so the UI summary can be
        // updated when the preference changes.
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_units_key)));
// gia to button pou dimiourgisa.
        Preference button = (Preference)findPreference(getString(R.string.Ok_Settings));
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //code for what you want it to do
                if(stringValue.equals("English") || stringValue.equals("Αγγλικά")){
                    //updateconfig("en");
                    languageToload = "eng";
                    Locale locale = new Locale(languageToload);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    setTitle(R.string.app_name);

                    Toast.makeText(getApplicationContext(), R.string.English, Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
                }else if(stringValue.equals("Greece") || stringValue.equals("Ελληνικα")){
                    //  updateconfig("gr");
                    languageToload = "gr";
                    Locale locale = new Locale(languageToload);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    setTitle(R.string.app_name);
                    Toast.makeText(getApplicationContext(), R.string.Greece, Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
                }

                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                User_id = globalVariable.getUser_id();
                if (User_id == -1 ){
                    Intent intent = new Intent(Settings.this, Login.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Settings.this, User_Menu.class);
                    Bundle extras = new Bundle();
                    extras.putInt("First_Login", 2);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    /**
     * Attaches a listener so the summary is always updated with the preference value.
     * Also fires the listener once, to initialize the summary (so it shows up before the value
     * is changed.)
     */
    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        stringValue = value.toString();
        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            listPreference = (ListPreference) preference;
            prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);

                if(stringValue.equals("English") || stringValue.equals("Αγγλικά")){
                    //updateconfig("en");
                    languageToload = "en";
                    Locale locale = new Locale(languageToload);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    setTitle(R.string.app_name);

                    Toast.makeText(getApplicationContext(), R.string.English, Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
                }else if(stringValue.equals("Greece") || stringValue.equals("Ελληνικα")){
                  //  updateconfig("gr");
                    languageToload = "gr";
                    Locale locale = new Locale(languageToload);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    setTitle(R.string.app_name);
                    Toast.makeText(getApplicationContext(), R.string.Greece, Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), stringValue, Toast.LENGTH_SHORT).show();
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
     //Toast.makeText(getApplicationContext(), stringValue, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void updateconfig(String s) {
        String languageToload = s;
        Locale locale = new Locale(languageToload);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setTitle(R.string.app_name);
        this.setContentView(R.layout.user__menu);

        // ((EditTextPreference) findViewById(R.id.location_key)).setText(R.string.pref_location_label);
        // ((ListPreference) findViewById(R.id.Language)).setHint(R.string.pref_units_label);
        //((TextView) findViewById(R.id.AppName)).setText(R.string.app_name);
    }

//    @Override
//    public void onBackPressed() {
//        // Do Here what ever you want do on back press;
//        Intent intent = new Intent(Settings.this, User_Menu.class);
//        startActivity(intent);
//    }
}