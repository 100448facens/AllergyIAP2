package com.allergyiap.activities;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.allergyiap.R;
import com.allergyiap.beans.User;
import com.allergyiap.service.UserService;
import com.allergyiap.utils.A;
import com.allergyiap.utils.Prefs;
import com.allergyiap.utils.TimePreference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else if (preference instanceof TimePreference) {
                TimePreference castPreference = (TimePreference) preference;

                long millis = castPreference.getMilli();
                Date d = new Date(millis);
                SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");
                String hms = sdfDate.format(d) + ":00";
                User u = UserService.getCurrentUser();
                u.setAlarm_time(hms);
                UserService.update(u);
            } else if (preference instanceof MultiSelectListPreference) {
                MultiSelectListPreference castPreference = (MultiSelectListPreference) preference;
                //Set<String> sts = castPreference.getValues();

                String weekdays = stringValue.replaceAll("[^\\w\\s]","");
                weekdays = weekdays.replaceAll("\\s", "");
                /*for (String s : sts) {
                    weekdays += s;
                }*/
                User u = UserService.getCurrentUser();
                u.setAlarm_weekdays(weekdays);
                UserService.update(u);
            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                //preference.setSummary(stringValue);
                if (stringValue.equals("prefs.notification.enabled")) {
                    boolean switched = ((SwitchPreference) preference).isChecked();
                    if (switched) {
                        User u = UserService.getCurrentUser();
                        u.setAlarm_weekdays("1234567");
                        UserService.update(u);
                    } else {
                        User u = UserService.getCurrentUser();
                        u.setAlarm_weekdays("");
                        UserService.update(u);
                    }
                }
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
        Log.d("", "preference.getKey() " + preference.getKey());
        // Trigger the listener immediately with the preference's
        // current value.

        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        String name = preference.getKey();
        Object aux;
        if (preference instanceof SwitchPreference) {
            aux = shared.getBoolean(name, Boolean.FALSE);
        } else if (preference instanceof TimePreference) {
            aux = shared.getLong(name, 0);
        } else if (preference instanceof MultiSelectListPreference) {
            aux = shared.getStringSet(name, new HashSet<String>());
        } else {
            aux = shared.getString(name, "");
        }

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, aux);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        createFragment();
    }

    private void createFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new GeneralPreferenceFragment()).commit();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}

     @Override
     @TargetApi(Build.VERSION_CODES.HONEYCOMB) public void onBuildHeaders(List<Header> target) {
     loadHeadersFromResource(R.xml.pref_general, target);
     }*/

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * Share app click event
     */
    private void shareAppListener() {

    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.

            bindPreferenceSummaryToValue(findPreference("prefs.notification.enabled"));
            bindPreferenceSummaryToValue(findPreference("prefs.notification.days_week"));

            bindPreferenceSummaryToValue(findPreference("prefs.notification.sound"));
            //bindPreferenceSummaryToValue(findPreference("days_week"));
            bindPreferenceSummaryToValue(findPreference("time_alarm"));

            findPreference("prefs.app.share").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    String subject = String.format(getContext().getString(R.string.mail_share_app_subject), getContext().getString(R.string.app_name));
                    String message = String.format(getContext().getString(R.string.mail_share_app_body), getContext().getString(R.string.app_name), getContext().getString(R.string.apps_link));
                    A.startShareText(getContext(), subject, message);
                    return true;
                }
            });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                //startActivity(new Intent(getActivity(), SettingsActivity.class));
                getActivity().onBackPressed();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
