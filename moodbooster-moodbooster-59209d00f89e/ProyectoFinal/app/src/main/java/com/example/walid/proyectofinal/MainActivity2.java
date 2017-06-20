package com.example.walid.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

private Button boton1;
    private FloatingActionButton fab;
    SharedPreferences prefe;
    ArrayList<Recomendacion> lista_recomendacion = new ArrayList<>();
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.buttonAnyadir:


                String emayx=prefe.getString("mail","");
                String passx=prefe.getString("password","");
                Log.d("Email",emayx);
                Log.d("password",passx);
                //String stringUrl = "http://moodbooster.esy.es/moodbooster/public/api/recomendacion/porUsuario?email="+emayx+"&password="+passx;
                String estado="Triste";

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    // Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                    // startActivity(myIntent);
                    if(lista_recomendacion.size()>0) {
                        estado=lista_recomendacion.get(0).getEstadoAnimo();
                        new JSONfunctions4().execute("http://moodbooster.esy.es/moodbooster/public/api/historial", emayx, passx, estado);
                    }
                   // new JSONfunctions3().execute(stringUrl);
                } else {
                    //textView.setText("No network connection available.");
                    Log.d("MyApp", "no hay conexion");
                }


//"http://moodbooster.esy.es/moodbooster/public/api/historial",emay,pass,"Triste"


                break;

           /* case R.id.button2:

                break;*/
            case R.id.fab:
                Intent myIntent = new Intent(MainActivity2.this, Main5Activity.class);
                startActivity(myIntent);
                break;
            default:
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Últimas recomendaciones");

         fab = (FloatingActionButton) findViewById(R.id.fab);

       // fab
        boton1 = (Button) findViewById(R.id.buttonAnyadir);
        boton1.setOnClickListener((View.OnClickListener) this);
        fab.setOnClickListener((View.OnClickListener) this);
       // final String[] textos = new String[]{"Tex1","Tex2","Tex3","Tex4","Tex5"};


        prefe =getSharedPreferences("datos",Context.MODE_PRIVATE);
        String emay=prefe.getString("mail","");
        String pass=prefe.getString("password","");
        Log.d("Email",emay);
        Log.d("password",pass);
        String stringUrl = "http://moodbooster.esy.es/moodbooster/public/api/recomendacion/porUsuario?email="+emay+"&password="+pass;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            new JSONfunctions3().execute(stringUrl);


        } else {

            Log.d("MyApp", "no hay conexion");
        }



    }
    private class JSONfunctions3 extends AsyncTask<String,Void, String> {


        public JSONfunctions3() {


        }

        @Override
        protected String doInBackground(String... params) {
            try {


                return downloadUrl(params[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            String pal="";

            try {

                JSONObject rootObject = new JSONObject(result);
                // JSONArray arr = rootObject.getJSONArray("posts");
                JSONArray rows = rootObject.getJSONArray("data"); // Get all JSONArray data
                int count = rows.length();

                for (int i = 0; i < count; i++) {
                    JSONArray jsonArr = rows.getJSONArray(i);

                    lista_recomendacion.add(new Recomendacion(jsonArr.getJSONObject(0)));
                }
if(lista_recomendacion.size()>0) {
   //int i= lista_recomendacion.size();
    switch (lista_recomendacion.get(0).getEstadoAnimo().toString()) {


        case "Triste":
            fab.setImageResource(R.drawable.triste);
            break;
        case "Alegre":
            fab.setImageResource(R.drawable.feliz);
            break;
        case "Enamorado":
            fab.setImageResource(R.drawable.enamorado);
            break;
        case "Desamor":
            fab.setImageResource(R.drawable.desenamorado);
            break;
        case "Genial":
            fab.setImageResource(R.drawable.fiesta);
            break;
        case "Estudio":
            fab.setImageResource(R.drawable.estudiar);
            break;

    }
}
                AdaptadorLista miAdaptador=new AdaptadorLista(MainActivity2.this, lista_recomendacion);
                //Creamos un objeto de el adaptador creado por nosotros anteriormente pasandole el contecto y los datos

                ListView lstOpciones = (ListView) findViewById(R.id.milista);
                lstOpciones.setAdapter(miAdaptador);
               // Log.d("Estadooooooooooooooo",lista_recomendacion.get(0).getEstadoAnimo().toString());

                lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View view, int pos,long id) {
    /* Código a ejecutar */
                       // Log.d("Tipo",lista_recomendacion.get(pos).getTipo());

                       if (lista_recomendacion.get(pos).getTipo().toString().equalsIgnoreCase("1")) {
                            Intent myIntent = new Intent(MainActivity2.this, Main4Activity.class);
                            myIntent.putExtra("MyClassX", lista_recomendacion.get(pos));
                            startActivity(myIntent);

                        } else {

                        Intent myIntent = new Intent(MainActivity2.this, Main3Activity.class);
                        myIntent.putExtra("MyClass", lista_recomendacion.get(pos));
                        startActivity(myIntent);
                    }
                        // lista_recomendacion.get(pos).getRutaOrigen();
                        /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( lista_recomendacion.get(pos).getRutaOrigen()));
                        // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( data.get(position).get("urls")));
                        startActivity(browserIntent);*/
                    }});

            } catch (JSONException e) {
            }

        }

        public   ArrayList<Recomendacion> dev(){

             /*for(int j=0;j<lista_recomendacion.size();j++){

                  System.out.print(lista_recomendacion.get(j).toString());
           // Log.d("r",new Integer(lista_recomendacion.size()).toString());

             }*/
            return lista_recomendacion;
        }


        private String downloadUrl(String myurl) throws IOException {
//email=occc10@hotmail.com&password=123&nombre=ouadi&fechaNacimiento=1981/04/27&sexo = hombre
            final String DEBUG_TAG = "HttpExample";
            InputStream iStream = null;
            OutputStream out=null;
            String data = "";
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                // Reading data from url
                iStream = conn.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while( ( line = br.readLine()) != null){
                    sb.append(line);
                }

                data = sb.toString();

                br.close();


            } finally {
                if (iStream != null ) {
                    iStream.close();
                }
           /* if (out != null ) {
                out.close();
            }*/
            }
            return data;
        }


        // Reads an InputStream and converts it to a String.
        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }

        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for(Map.Entry<String, String> entry : params.entrySet()){
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(entry.getKey());
                result.append("=");
                //Log.d("Emailyyyyy",entry.getValue());
                result.append(entry.getValue());
            }


            return result.toString();
        }



    }






    ////////////////////////////
    private class JSONfunctions4 extends AsyncTask<String,Void, String> {


        public JSONfunctions4() {
        }

        @Override
        protected String doInBackground(String... params) {
            try {


                return downloadUrl(params[0],params[1],params[2],params[3]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {


String pal="";
            String emayx=prefe.getString("mail","");
            String passx=prefe.getString("password","");
            Log.d("Email",emayx);
            Log.d("password",passx);
            String stringUrl = "http://moodbooster.esy.es/moodbooster/public/api/recomendacion/porUsuario?email="+emayx+"&password="+passx;

            try{

                JSONObject rootObject = new JSONObject(result);
                Log.d("MyApp", "Data" + result);

                pal=rootObject.getString("data");
                // Log.d("EL usuario es ",pal);
                //if(pal.equalsIgnoreCase("Estado de ánimo insertado correctamente")){

                    lista_recomendacion.clear();
                    new JSONfunctions3().execute(stringUrl);
               // }else{


              /*Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/
               // }

            } catch (JSONException e) {
            }


        }




        private String downloadUrl(String myurl,String email,String password,String EstadoAnimo) throws IOException {
//email=occc10@hotmail.com&password=123&nombre=ouadi&fechaNacimiento=1981/04/27&sexo = hombre
            final String DEBUG_TAG = "HttpExample";
            InputStream is = null;
            OutputStream out=null;
            String data = "";
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);


                out = new BufferedOutputStream(conn.getOutputStream());


                HashMap<String, String> postDataParams=new HashMap<>();
                //Log.d("Emailxxxxx", email);
                postDataParams.put("email", email);
                postDataParams.put("password", password);
                postDataParams.put("estadoAnimo",EstadoAnimo);



                String pal=getPostDataString(postDataParams);
                out.write(pal.getBytes());

                out.flush();
                out.close();
                //InputStream in = new BufferedInputStream(conn.getInputStream());
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);

                is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();
                Log.d("Mydata", data);
                br.close();
                // conn.disconnect();

            } finally {
                if (is != null ) {
                    is.close();
                }
           /* if (out != null ) {
                out.close();
            }*/

            }

            return data;
        }


        // Reads an InputStream and converts it to a String.
        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }

        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for(Map.Entry<String, String> entry : params.entrySet()){
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(entry.getKey());
                result.append("=");
                //Log.d("Emailyyyyy",entry.getValue());
                result.append(entry.getValue());
            }


            return result.toString();
        }



    }

}

