package com.arx.sensix;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.Color;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#0a0a0f"));
        layout.setPadding(40, 40, 40, 40);

        Button btnOverlay = new Button(this);
        btnOverlay.setText("1. IZINKAN OVERLAY CROSSHAIR");
        btnOverlay.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()))));
        layout.addView(btnOverlay);

        Button btnStart = new Button(this);
        btnStart.setText("2. MULAI CROSSHAIR (MELAYANG)");
        btnStart.setOnClickListener(v -> startService(new Intent(MainActivity.this, OverlayService.class)));
        layout.addView(btnStart);

        Button btnStop = new Button(this);
        btnStop.setText("3. MATIKAN CROSSHAIR");
        btnStop.setOnClickListener(v -> stopService(new Intent(MainActivity.this, OverlayService.class)));
        layout.addView(btnStop);

        setContentView(layout);
    }
}
