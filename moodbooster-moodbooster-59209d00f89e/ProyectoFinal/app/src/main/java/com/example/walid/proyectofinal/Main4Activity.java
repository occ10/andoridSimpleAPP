package com.example.walid.proyectofinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    Recomendacion recomendacion = new Recomendacion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);










        recomendacion = (Recomendacion) getIntent().getSerializableExtra("MyClassX");

        if(recomendacion.getTitulo() != null){
            setTitle(recomendacion.getTitulo()+ " - " + recomendacion.getAutor());
            ;}

      //  String des = recomendacion.getDescripcion();

        TextView text1 = (TextView) findViewById(R.id.titlex);
        text1.setText("Titulo: "+recomendacion.getTitulo());

        TextView text2 = (TextView) findViewById(R.id.autorx);
        text2.setText("Autor: "+recomendacion.getAutor());
        TextView text3 = (TextView) findViewById(R.id.descripcionx);
        text3.setText(recomendacion.getDescripcion());


        TextView text6 = (TextView) findViewById(R.id.estadoAnimox);
        text6.setText("Estado de Animo: "+recomendacion.getEstadoAnimo());

       // ImageView text8=(ImageView) findViewById(R.id.rutaImagenx);
        //text8.setImageResource(R.drawable.nirvana);

       // TextView text9 = (TextView) findViewById(R.id.imagenx);
        //text9.setText("Imagen:");
    }

}
