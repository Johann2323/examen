package com.example.examen;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuarios implements Parcelable {
    private String usuario;
    private String clave;
    private String email;


    public Usuarios(String usuario, String clave, String email) {
        this.usuario = usuario;
        this.clave = clave;
        this.email = email;
    }

    protected Usuarios(Parcel in) {
        usuario = in.readString();
        clave = in.readString();
        email = in.readString();
    }

    public static final Creator<Usuarios> CREATOR = new Creator<Usuarios>() {
        @Override
        public Usuarios createFromParcel(Parcel in) {
            return new Usuarios(in);
        }

        @Override
        public Usuarios[] newArray(int size) {
            return new Usuarios[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(clave);
        dest.writeString(email);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public Usuarios() {
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public float NivelSeguridad(String clave) {
        float resp = 0;
        Pattern p = Pattern.compile("\\W");
        Matcher m = p.matcher(clave);
        while (m.find()) resp++;

        if (clave.length() >= 12 && resp >= 4) return 5;
        else if (clave.length() >= 10 && resp >= 2) return 4;
        else if (clave.length() >= 8 && resp >= 1) return 3;
        else if (clave.length() >= 8) return 2;
        else return 1;

    }

    public boolean ValidarClave(String clave, String repclave){
        if (clave.equals(repclave)){
            if (clave.length()>4){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }

    public boolean RegistroUsuario(String usuarioU, String claveU){
        if (usuarioU.length()>0&&claveU.length()>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}
