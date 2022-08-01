package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.examen.databinding.ValorBinding;

public class ValorActivity extends AppCompatActivity {
    public static final String USUARIO2_KEY = "usuario";
    public static final String BITMAP_KEY="bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ValorBinding binding = ValorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        Usuarios usuario = extras.getParcelable(USUARIO2_KEY);
        binding.setUsuario2(usuario);
        binding.txtGetusuario.setText("Bienvenido: "+usuario.getUsuario());
        Bitmap bitmap = extras.getParcelable(BITMAP_KEY);
        if(bitmap!=null){
            binding.imgInformacion.setImageBitmap(bitmap);
        }
        binding.ratingBar.setRating(usuario.NivelSeguridad(usuario.getClave()));


        if (binding.ratingBar.getRating()==5){

            binding.txtDescrip.setText("La clave de seguridad se considera alta");


        }else if(binding.ratingBar.getRating()==4 ){

            binding.txtDescrip.setText("La clave de seguridad se considera media alta");
        }else if(binding.ratingBar.getRating()==3){

            binding.txtDescrip.setText("La clave de seguridad se considera media");
        }else if(binding.ratingBar.getRating()==2){

            binding.txtDescrip.setText("La clave de seguridad se considera baja");
        }else if(binding.ratingBar.getRating()==1){

            binding.txtDescrip.setText("La clave de seguridad se considera insegura");
        }

    }
}