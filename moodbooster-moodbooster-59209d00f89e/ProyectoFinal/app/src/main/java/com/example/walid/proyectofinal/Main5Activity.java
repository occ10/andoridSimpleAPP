package com.example.walid.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.Map;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView image3;
    private ImageView image2;
    private ImageView image7;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;


    public void onClick(View v) {
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String emay=prefe.getString("mail","");
        String pass=prefe.getString("password","");
        switch (v.getId()) {


            case R.id.imageView3:
                    new JSONfunctions5().execute("http://moodbooster.esy.es/moodbooster/public/api/historial",emay,pass,"Alegre");
                break;
            case R.id.imageView2:
                new JSONfunctions5().execute("http://moodbooster.esy.es/moodbooster/public/api/historial",emay,pass,"Triste");
                break;
            case R.id.imageView4:
                new JSONfunctions5().execute("http://moodbooster.esy.es/moodbooster/public/api/historial",emay,pass,"Enamorado");
                break;
            case R.id.imageView5:
                new JSONfunctions5().execute("http://moodbooster.esy.es/moodbooster/public/api/historial",emay,pass,"Desamor");
                break;
            case R.id.imageView6:
                new JSONfunctions5().execute("http://moodbooster.esy.es/moodbooster/public/api/historial",emay,pass,"Genial");
                break;
            case R.id.imageView7:
                new JSONfunctions5().execute("http://moodbooster.esy.es/moodbooster/public/api/historial",emay,pass,"Estudio");
                break;
            default:
                break;
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Añade tu estado actual");
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

      //  fab.setOnClickListener((View.OnClickListener) this);
        image3 = (ImageView) findViewById(R.id.imageView3); //feliz
        image2=(ImageView) findViewById(R.id.imageView2);   //triste
        image4=(ImageView) findViewById(R.id.imageView4); //enamorado
        image5=(ImageView) findViewById(R.id.imageView5); //desamor
        image6=(ImageView) findViewById(R.id.imageView6); //fiesta
        image7=(ImageView) findViewById(R.id.imageView7);

        image3.setOnClickListener((View.OnClickListener) this);
        image2.setOnClickListener((View.OnClickListener) this);
        image4.setOnClickListener((View.OnClickListener) this);
        image5.setOnClickListener((View.OnClickListener) this);
        image6.setOnClickListener((View.OnClickListener) this);
        image7.setOnClickListener((View.OnClickListener) this);

    }


    ////////////////////////////
    private class JSONfunctions5 extends AsyncTask<String,Void, String> {

        public JSONfunctions5() {
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

            try{

                JSONObject rootObject = new JSONObject(result);
                Log.d("MyApp", "Data" + result);

                pal=rootObject.getString("data");
                // Log.d("EL usuario es ",pal);
                if(pal.equalsIgnoreCase("Estado de ánimo insertado correctamente")){


                    Intent myIntent = new Intent(Main5Activity.this, MainActivity2.class);
                    startActivity(myIntent);
                }else{

                }

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
