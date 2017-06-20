package com.example.walid.proyectofinal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by walid on 14/04/2016.
 */
public class Recomendacion implements Serializable{

    String id;
    String titulo;
    String autor;
    String descripcion;
    String duracion;
    String rutaImagen;
    String rutaOrigen;
    String estadoAnimo;
    String tipo;

    public Recomendacion() {

    }
    public Recomendacion(JSONObject objetoJSON) throws JSONException {

        this.id=objetoJSON.getString("id");;
        this.titulo=objetoJSON.getString("titulo");;
        this.autor=objetoJSON.getString("autor");;
        this.descripcion=objetoJSON.getString("descripcion");;
        this.duracion=objetoJSON.getString("duracion");;
        this.rutaImagen=objetoJSON.getString("rutaImagen");;
        this.rutaOrigen=objetoJSON.getString("rutaOrigen");;
        this.estadoAnimo=objetoJSON.getString("estadoAnimo");;
        this.tipo=objetoJSON.getString("tipo");


    }
    @Override
    public String toString() {
        return "Recomendacion{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", rutaOrigen='" + rutaOrigen + '\'' +
                ", estadoAnimo='" + estadoAnimo + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaOrigen() {
        return rutaOrigen;
    }

    public void setRutaOrigen(String rutaOrigen) {
        this.rutaOrigen = rutaOrigen;
    }

    public String getEstadoAnimo() {
        return estadoAnimo;
    }

    public void setEstadoAnimo(String estadoAnimo) {
        this.estadoAnimo = estadoAnimo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
