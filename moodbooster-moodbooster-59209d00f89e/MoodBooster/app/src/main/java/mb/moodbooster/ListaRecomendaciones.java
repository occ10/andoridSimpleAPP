package mb.moodbooster;

import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TraianAlexandru on 02/03/2016.
 */
public class ListaRecomendaciones extends ListFragment {

    String[] canciones ={"Drake - Hotline Bling","Pink Floyd - Another Brick In The Wall","Arctic Monkeys - Do I Wanna Know? ", "Nirvana - Smells Like Teen Spirit"};
    int[] imagenes={R.drawable.drake,R.drawable.floyd,R.drawable.amonkeys,R.drawable.nirvana};
    String[] urls={"https://www.youtube.com/watch?v=uxpDa-c-4Mc," ,
                    "https://www.youtube.com/watch?v=YR5ApYxkU-U",
                    "https://www.youtube.com/watch?v=bpOSxM0rNPM",
                    "https://www.youtube.com/watch?v=hTWKbfoikeg"};


    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    private Button btnAnyadir;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        //MAP
        HashMap<String,String> map= new HashMap<String, String>();
        //RELLENAR
        for(int j=0;j<20;j++){ //BORRAR ESTO,ES SOLO PARA VISUALIZAR MAS ITEMS
        for(int i=0;i<canciones.length;i++){
            map=new HashMap<String,String>();
            map.put("Cancion",canciones[i]);
            map.put("Imagen",Integer.toString(imagenes[i]));
            map.put("urls",urls[i]);

            data.add(map);
        }
        }

        //Llaves en map
        String[] desde={"Cancion","Imagen"};

        //Identificadores de la interfaz
        int[] hacia={R.id.nombreTxt,R.id.imageView1};

        //Adapter
        adapter = new SimpleAdapter(getActivity(),data,R.layout.modelo,desde,hacia);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }




    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), data.get(position).get("Cancion"), Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( data.get(position).get("urls")));
                startActivity(browserIntent);
            }
        });
    }
}
