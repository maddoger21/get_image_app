package ru.samsung.itschool.get_picture;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button enter;
    private EditText textUrl;
    private ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.b_enter);
        textUrl = findViewById(R.id.et_url);
        picture = findViewById(R.id.iv_picture);

        ImageDownloading img = new ImageDownloading();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.execute();
            }
        };
        enter.setOnClickListener(clickListener);
    }

    class ImageDownloading extends AsyncTask <Void, Void, Void>{
        Bitmap bitmap;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(textUrl.getText().toString());
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            picture.setImageBitmap(bitmap);
        }
    }

}