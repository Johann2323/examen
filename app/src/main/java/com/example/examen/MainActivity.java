package com.example.examen;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String USUARIO1_KEY = "usuario1";
    ActivityMainBinding binding;
    Bitmap bitmap;
    public static final String BITMAP2_KEY="bitmap";
    boolean k;

    ActivityResultLauncher<Intent> Luncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Usuarios usuario = new Usuarios();
        EditText txt_nombre = binding.txtUsuarioU;
        EditText txt_contrasenia = binding.txtClaveU;

        Button btn_ingresar = binding.btnIngresar;

        binding.txtRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivityRegistro("", "", "");

            }
        });
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txt_nombre.getText().toString();
                String clave = txt_contrasenia.getText().toString();
                if (usuario.RegistroUsuario(nombre,clave)==true){
                    k = RegistroActivity.p;
                    if (k == true) {
                        if (binding.txtUsuarioU.getText().toString().equals("") || binding.txtClaveU.getText().toString().equals("")) {
                            Context context = MainActivity.this;
                            CharSequence text = "Llene todos los campos";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            Bundle extras = getIntent().getExtras();
                            Usuarios usuario = extras.getParcelable(USUARIO1_KEY);
                            binding.setUsuario1(usuario);
                            if (binding.txtUsuarioU.getText().toString().equals(usuario.getUsuario()) && binding.txtClaveU.getText().toString().equals(usuario.getClave())) {
                                System.out.println(usuario.getUsuario() + " contra: " + usuario.getClave() + " email: " + usuario.getEmail());
                                bitmap = extras.getParcelable(BITMAP2_KEY);
                                abrirActivityValor(usuario.getUsuario(), usuario.getClave(), usuario.getEmail());
                            } else {
                                Context context = MainActivity.this;
                                CharSequence text = "Usuario o Contraseña no coinciden";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }
                    } else {
                        Context context = MainActivity.this;
                        CharSequence text = "Registrese Primero";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }



                }else{
                    Context context = MainActivity.this;
                    CharSequence text = "Debe llenar todos los campos";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }




            }
        });








    }
    private void abrirActivityRegistro(String us, String cla, String emai) {
        Intent intent = new Intent(this, RegistroActivity.class);
        Usuarios usuarios = new Usuarios(us,cla,emai);
        intent.putExtra(RegistroActivity.USUARIO_KEY,usuarios);
        startActivity(intent);
    }

    private void abrirActivityValor(String nom, String contra, String email) {
        Intent intent = new Intent(this, ValorActivity.class);
        Usuarios usuario = new Usuarios(nom, contra, email);
        intent.putExtra(ValorActivity.USUARIO2_KEY, usuario);
        intent.putExtra(ValorActivity.BITMAP_KEY,bitmap);
        startActivity(intent);
    }

}