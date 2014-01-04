package com.edinarobotics.scoutingmachine.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private final String COMPETITION_ACTIVE_PREF = "isCompetitionActive";
    private boolean isCompetitionActive;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        isCompetitionActive = prefs.getBoolean(COMPETITION_ACTIVE_PREF, false);
        toast(COMPETITION_ACTIVE_PREF+": "+Boolean.toString(isCompetitionActive));

        Button resetButton = (Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCompetitionActive = false;
                prefs.edit().putBoolean(COMPETITION_ACTIVE_PREF, isCompetitionActive).commit();
                supportInvalidateOptionsMenu();
                toast(COMPETITION_ACTIVE_PREF+": "+Boolean.toString(isCompetitionActive));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(isCompetitionActive) {
            menu.findItem(R.id.action_new_competition).setVisible(false).setEnabled(false);
        } else {
            menu.findItem(R.id.action_new_competition).setVisible(true).setEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_new_competition:
                isCompetitionActive = true;
                prefs.edit().putBoolean(COMPETITION_ACTIVE_PREF, isCompetitionActive).commit();
                supportInvalidateOptionsMenu();
                toast(COMPETITION_ACTIVE_PREF+": "+Boolean.toString(isCompetitionActive));
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
     }

}
