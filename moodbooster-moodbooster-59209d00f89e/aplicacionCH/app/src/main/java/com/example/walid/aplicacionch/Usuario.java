package com.example.walid.aplicacionch;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by walid on 12/03/2016.
 */
public class Usuario {

    private String correo;
    private String nombre;
    private String fechaNacimiento;
    private String sexo;
    public Usuario(JSONObject objetoJSON) throws JSONException {

      this.correo=objetoJSON.getString("correo");
        this.nombre=objetoJSON.getString("nombre");
        this.fechaNacimiento=objetoJSON.getString("fechaNacimiento");
        this.sexo=objetoJSON.getString("sexo");


    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
