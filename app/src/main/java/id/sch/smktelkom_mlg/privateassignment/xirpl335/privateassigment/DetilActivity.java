package id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetilActivity extends AppCompatActivity {

    public String url = "https://image.tmdb.org/t/p/w500";
    String Id, img, desc, gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Id = intent.getStringExtra("movie_title");
        img = intent.getStringExtra("poster_path");
        desc = intent.getStringExtra("description");
        setTitle(Id);
        gambar = url + img;
        ImageView detail = (ImageView) findViewById(R.id.imageDetail);
        TextView deskripsi = (TextView) findViewById(R.id.descDetail);
        Glide.with(this).load(gambar)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(detail);
        deskripsi.setText(desc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
