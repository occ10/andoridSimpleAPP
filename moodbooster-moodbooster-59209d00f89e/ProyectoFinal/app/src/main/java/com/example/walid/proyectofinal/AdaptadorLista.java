package com.example.walid.proyectofinal;

/**
 * Created by walid on 14/04/2016.
 */
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AdaptadorLista extends ArrayAdapter{
	/*Hacemos que nuestra clase sea un ArrayAdapter extendiendo de la misma*/

    Activity context;
  List<Recomendacion> datos=new ArrayList<>();

	/*Creamos las variables necesarias para capturar el contexto y otra para capturar
	 * los datos que se publicaran en la lista*/

    public AdaptadorLista(Activity context,List<Recomendacion> datos) {
        super(context,R.layout.lista_tema,datos);
        this.context=context;
        this.datos=datos;
        // TODO Auto-generated constructor stub
    }
    /*Constructor de la clase, donde pasamos por parametro los datos a mostrar en la lista y el contexto*/
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View item=inflater.inflate(R.layout.lista_tema, null);
        ImageView imag=(ImageView)  item.findViewById(R.id.ImagenArea1);
        //para cargar imagenes
        Picasso.with(context).load("http://moodbooster.esy.es/moodbooster/public"+datos.get(position).getRutaImagen()).into(imag);



        TextView title=(TextView) item.findViewById(R.id.titulo);
        title.setText(datos.get(position).autor);

        TextView descrip=(TextView) item.findViewById(R.id.tit);
        descrip.setText(datos.get(position).titulo);

        return item;
    }
    /*Este metodo es el mas importante ya que es donde inflamos el fichero lista_tema.xml en cada item
     * de la lista y despues a ambos TextView dentro de ficehro le insertamos los datos que queremos que muestre */

}
