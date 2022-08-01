package com.example.examen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.examen.databinding.ActivityMainBinding;
import com.example.examen.databinding.RegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    public static final String USUARIO_KEY = "usuario";
    RegistroBinding binding;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> Luncher;
    boolean k = false;
    public static boolean p = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        Usuarios usuario = extras.getParcelable(USUARIO_KEY);
        binding.setUsuario(usuario);

        EditText txt_nombre = binding.txtUser;
        EditText txt_contrasenia = binding.txtClave;
        EditText txt_repetirclave = binding.txtRepclave;
        EditText txt_correo = binding.txtCorreo;
        Button btn_ingresar = binding.button;
        ImageView img_sitio = binding.imgInfo;











        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txt_nombre.getText().toString();
                String clave = txt_contrasenia.getText().toString();
                String repclave = txt_repetirclave.getText().toString();
                String correo = txt_correo.getText().toString();

                if (k == true) {

                    if (usuario.validarEmail(correo) == true) {

                        if (usuario.ValidarClave(clave, repclave) == true) {
                            Context context = RegistroActivity.this;
                            CharSequence text = "Usuario Registrado correctamente";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            AlertDialog.Builder alert = new AlertDialog.Builder(RegistroActivity.this);
                            alert.setMessage("Registrado Correctamente");
                            alert.setTitle("Registro");
                            alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText((RegistroActivity.this), "Bienvenido", Toast.LENGTH_LONG).show();
                                    abrirActivityMain(binding.txtUser.getText().toString(), binding.txtClave.getText().toString(), binding.txtCorreo.getText().toString());
                                    p = true;
                                }
                            });
                            alert.show();


                        } else {
                            Context context = RegistroActivity.this;
                            CharSequence text = "La contraseña debe ser minimo 5 caracteres y las claves deben coincidir";
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }


                    } else {
                        Context context = RegistroActivity.this;
                        CharSequence text = "Ingrese un email correcto";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }else{
                    Context context = RegistroActivity.this;
                    CharSequence text = "Debe ingresar una fotografia";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        MCAMARA();
        binding.imgInfo.setOnClickListener(v->{
            abrirCamara();
            k = true;
        });
    }

    private void abrirCamara() {
        Intent camaraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Luncher.launch(camaraintent);
    }

    public void MCAMARA(){
        Luncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData()!=null){
                        bitmap = result.getData().getExtras().getParcelable("data");
                        binding.imgInfo.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }

    private void abrirActivityMain(String nom, String contra, String email) {
        Intent intent = new Intent(this, MainActivity.class);
        Usuarios usuario = new Usuarios(nom, contra, email);
        intent.putExtra(MainActivity.USUARIO1_KEY, usuario);
        intent.putExtra(MainActivity.BITMAP2_KEY,bitmap);
        startActivity(intent);
    }


}