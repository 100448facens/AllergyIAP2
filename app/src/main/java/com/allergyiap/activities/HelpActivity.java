package com.allergyiap.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.allergyiap.R;

public class HelpActivity extends BaseActivity {

    private static final String TAG = "HelpActivity";

    TextView txtAbout;
    TextView txtLegend;
    TextView txtInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        txtAbout = (TextView) findViewById(R.id.text_about);
        txtLegend = (TextView) findViewById(R.id.text_legend);
        txtInformation = (TextView) findViewById(R.id.text_information);
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();

        txtAbout.setText(Html.fromHtml(getResources().getString(R.string.text_about)));
        txtLegend.setText(Html.fromHtml(getResources().getString(R.string.text_legend)));
        txtInformation.setText(Html.fromHtml(getResources().getString(R.string.text_information)));
    }
}
