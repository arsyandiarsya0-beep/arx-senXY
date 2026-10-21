package com.arx.sensix;
import android.app.Service;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class OverlayService extends Service {
    private WindowManager windowManager;
    private View crosshairView;

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        ImageView crosshair = new ImageView(this);
        crosshair.setImageBitmap(createCrosshairBitmap());

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        windowManager.addView(crosshair, params);
        crosshairView = crosshair;
    }

    private android.graphics.Bitmap createCrosshairBitmap() {
        int size = 150;
        android.graphics.Bitmap bmp = android.graphics.Bitmap.createBitmap(size, size, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#00FF00"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawCircle(size/2, size/2, size/2 - 10, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(size/2, size/2, 5, paint);
        canvas.drawLine(size/2, 0, size/2, 30, paint);
        canvas.drawLine(size/2, size-30, size/2, size, paint);
        canvas.drawLine(0, size/2, 30, size/2, paint);
        canvas.drawLine(size-30, size/2, size, size/2, paint);
        return bmp;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (crosshairView != null) windowManager.removeView(crosshairView);
    }
}
