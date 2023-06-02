package com.example.retrofit_example;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textViewAlbumId;
    private TextView textViewId;
    private TextView textViewTitle;
    private TextView textViewUrl;
    private TextView textViewThumbnailUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAlbumId = findViewById(R.id.textView_albumId);
        textViewId = findViewById(R.id.textView_id);
        textViewTitle = findViewById(R.id.textView_title);
        textViewUrl = findViewById(R.id.textView_url);
        textViewThumbnailUrl = findViewById(R.id.textView_thumbnailUrl);

        PhotoService service = RetrofitClient.getInterface();
        Call<List<PhotoResponse>> call = service.getPhotos();

        call.enqueue(new Callback<List<PhotoResponse>>() {
            @Override
            public void onResponse(Call<List<PhotoResponse>> call, Response<List<PhotoResponse>> response) {
                if (response.isSuccessful()) {
                    List<PhotoResponse> photos = response.body();

                    PhotoResponse photo = photos.get(10);
                    textViewAlbumId.setText(String.valueOf(photo.getAlbumId()));
                    textViewId.setText(String.valueOf(photo.getId()));
                    textViewTitle.setText(photo.getTitle());
                    textViewUrl.setText(photo.getUrl());
                    textViewThumbnailUrl.setText(photo.getThumbnailUrl());
                }
            }

            @Override
            public void onFailure(Call<List<PhotoResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "연결에 실패했습니다", Toast.LENGTH_LONG).show();
            }
        });
    }
}
