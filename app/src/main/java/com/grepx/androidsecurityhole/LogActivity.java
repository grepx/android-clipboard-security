package com.grepx.androidsecurityhole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        TextView log = (TextView) findViewById(R.id.log);
        log.setText(ClipboardWatcherService.log.toString());
    }
}
