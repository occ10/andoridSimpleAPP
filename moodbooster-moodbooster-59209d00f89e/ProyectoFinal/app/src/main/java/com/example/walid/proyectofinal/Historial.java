package com.example.walid.proyectofinal;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by walid on 08/04/2016.
 */
public class Historial {


    private String id;
    private String titulo;
    private String autor;
    private String descripcion;
    private String duracion;
    private String rutaImagen;
    private String rutaOrigen;
    private String estadoAnimo;
    private String    tipo;

    public Historial(JSONObject objetoJSON) throws JSONException {

        this.id=objetoJSON.getString("id");
        this.titulo=objetoJSON.getString("titulo");
        this.autor=objetoJSON.getString("autor");
        this.descripcion=objetoJSON.getString("descripcion");
        this.duracion=objetoJSON.getString("duracion");
        this.rutaImagen=objetoJSON.getString("rutaImagen");
        this.rutaOrigen=objetoJSON.getString("rutaOrigen");
        this.estadoAnimo=objetoJSON.getString("estadoAnimo");
        this.tipo=objetoJSON.getString("tipo");






    }


    public Historial(String id, String titulo, String autor, String descripcion, String duracion, String rutaImagen, String rutaOrigen, String estadoAnimo, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.rutaImagen = rutaImagen;
        this.rutaOrigen = rutaOrigen;
        this.estadoAnimo = estadoAnimo;
        this.tipo = tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setRutaOrigen(String rutaOrigen) {
        this.rutaOrigen = rutaOrigen;
    }

    public void setEstadoAnimo(String estadoAnimo) {
        this.estadoAnimo = estadoAnimo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getRutaOrigen() {
        return rutaOrigen;
    }

    public String getEstadoAnimo() {
        return estadoAnimo;
    }

    public String getTipo() {
        return tipo;
    }


    @Override
    public String toString() {
        return "Historial{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", rutaOrigen='" + rutaOrigen + '\'' +
                ", estadoAnimo='" + estadoAnimo + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}

