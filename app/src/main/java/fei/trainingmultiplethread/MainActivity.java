package fei.trainingmultiplethread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Button mLoadImageButton;
    private Button mShowToastButton;
    private static final int mSleepTime = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.activity_main_image_view);
        mLoadImageButton = (Button) findViewById(R.id.activity_main_load_image_button);
        mShowToastButton = (Button) findViewById(R.id.activity_main_show_toast_button);

        mLoadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });

        mShowToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "xxxxxxxxxxxxxxxxx", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(4000);
                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                mImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    private void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
