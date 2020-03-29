package com.example.eadrecycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditarAlbumActivity extends AppCompatActivity {

    ImageView capaImageView;
    EditText tituloEditText;
    EditText generoEditText;
    EditText anoEditText;
    Album album;
    int position;
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_album);

        capaImageView = (ImageView) findViewById(R.id.capaImageView);
        tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        generoEditText = (EditText) findViewById(R.id.generoEditText);
        anoEditText = (EditText) findViewById(R.id.anoEditText);

        Bundle bundle = getIntent().getExtras();
        requestCode = bundle.getInt("request_code");

        if (requestCode==MainActivity.REQUEST_EDITAR_ALBUM){
            album = (Album) bundle.getSerializable("album");
            position = bundle.getInt("position");

            capaImageView.setImageResource(album.getImgCapa());
            tituloEditText.setText(album.getTitulo());
            generoEditText.setText(album.getGenero());
            anoEditText.setText(String.valueOf(album.getAno()));
        }else{
            album = new Album();
            album.setImgCapa(R.drawable.sem_capa);
            capaImageView.setImageResource(album.getImgCapa());
        }
    }

    public void concluir(View view){
        Bundle bundle = new Bundle();
        if (requestCode==1)
            bundle.putInt("position",position);

        album.setTitulo(tituloEditText.getText().toString());
        album.setGenero(generoEditText.getText().toString());
        album.setAno(Integer.valueOf(anoEditText.getText().toString()));

        bundle.putSerializable("album", album);

        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }
}
