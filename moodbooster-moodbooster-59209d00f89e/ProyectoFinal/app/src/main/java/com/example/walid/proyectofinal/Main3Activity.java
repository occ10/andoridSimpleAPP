package com.example.walid.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class Main3Activity extends AppCompatActivity {
    Recomendacion recomendacion = new Recomendacion();
    Activity context;
    ImageView imag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/






        recomendacion = (Recomendacion) getIntent().getSerializableExtra("MyClass");


        if(recomendacion.getTitulo() != null){
            setTitle(recomendacion.getTitulo()+ " - " + recomendacion.getAutor());
            ;}

            String des = recomendacion.getDescripcion();

        TextView text1 = (TextView) findViewById(R.id.titulo);
        text1.setText(recomendacion.getTitulo());

            TextView text2 = (TextView) findViewById(R.id.autor);
            text2.setText(recomendacion.getAutor());
        TextView text3 = (TextView) findViewById(R.id.descripcion);
        text3.setText(recomendacion.getDescripcion());
        TextView text4 = (TextView) findViewById(R.id.duracion);
        text4.setText(recomendacion.getDuracion());
        TextView text5 = (TextView) findViewById(R.id.rutaOrigen);
        text5.setText(Html.fromHtml("<a href="+recomendacion.getRutaOrigen()+">Enlace</a>"));
        TextView text6 = (TextView) findViewById(R.id.estadoAnimo);
        text6.setText(recomendacion.getEstadoAnimo());

        imag=(ImageView) findViewById(R.id.rutaImagen);
        Picasso.with(context).load("http://moodbooster.esy.es/moodbooster/public"+recomendacion.getRutaImagen()).into(imag);
        // show The Image in a ImageView
      //  new DownloadImageTask((ImageView) findViewById(R.id.rutaImagen))
               // .execute("http://moodbooster.esy.es/moodbooster/public"+recomendacion.getRutaImagen());

    //    ImageView text8=(ImageView) findViewById(R.id.rutaImagen);
    //    text8.setImageResource(R.drawable.nirvana);

      //  TextView text9 = (TextView) findViewById(R.id.imagen);
      //  text9.setText("Imagen:");

        text5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( recomendacion.getRutaOrigen()));
                        // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( data.get(position).get("urls")));
                        startActivity(browserIntent);


            }
        });

    }




    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
