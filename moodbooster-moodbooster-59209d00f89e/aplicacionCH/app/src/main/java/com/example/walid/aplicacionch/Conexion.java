package com.example.walid.aplicacionch;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Message;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Pair;



/**
 * Created by walid on 03/03/2016.
 */
public class Conexion {

      public Conexion(){


      }
    public Message readMessage(JsonReader reader) throws IOException {
        String nom = "";
        int mar = 0;



        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("nombre")) {
                nom = reader.nextString();
            } else if (name.equals("marca")) {
                mar = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Message();

    }

    public List readMessagesArray(JsonReader reader) throws IOException {
        List messages = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

   public String getResponse() throws IOException, JSONException {

       String data = "";
       URL url = new URL("http://192.168.2.2:81/proyecto/index.php");
       HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

       String parsedString = "";
       urlConnection.connect();

       // Reading data from url
       InputStream iStream = urlConnection.getInputStream();

       BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

       StringBuffer sb = new StringBuffer();

       String line = "";
       while( ( line = br.readLine()) != null){
           sb.append(line);
       }

       data = sb.toString();

       br.close();
       //if( urlConnection.getDoInput()){



       //}
       String pal="";
       List<Usuario> lista_usuarios = new ArrayList<>();
       JSONObject object = new JSONObject(data);
       JSONArray json_array = object.optJSONArray("usuarios");

       for (int i = 0; i < json_array.length(); i++) {
           lista_usuarios.add(new Usuario(json_array.getJSONObject(i))); //creamos un objeto Fruta y lo insertamos en la lista
       }
       for (int i = 0; i < lista_usuarios.size(); i++) {
           pal+=lista_usuarios.get(i);
       }
       Log.d("MyApp", "Data" + pal);
       return data;
   }
    public void enviarPost(String a1, String a2, String a3, String a4) throws IOException {
        String resultado=null;
        JSONArray json=new JSONArray();
        URL url = new URL("http://192.168.2.2:81/proyecto/index.php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");

        urlConnection.setDoOutput(true);
        urlConnection.setChunkedStreamingMode(0);

        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
        writeStream(out,a1,a2,a3,a4);
        if(urlConnection.getDoOutput()){

            Log.d("MyApp","I am here");

        }

        urlConnection.disconnect();

       // Log.d("MyApp", "I am here");

        //return null;
    }

    private void readStream(InputStream in) throws IOException {
        String input;
        input = "hola mundo";

        switch (in.read()) {
        }
    }

    private void writeStream(OutputStream out,String a1,String a2,String a3,String a4) throws IOException {
        String input="hola mundo";
        HashMap<String, String> postDataParams=new HashMap<>();

        postDataParams.put("Nombre", a1);
        postDataParams.put("Correo", a2);
        postDataParams.put("FechaNacimiento", a3);
        postDataParams.put("Sexo", a4);


       String pal=getPostDataString(postDataParams);
        out.write(pal.getBytes());
        Log.d("MyApp", pal);
        out.flush();
    }
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }


        return result.toString();
    }


    public static String convertinputStreamToString(InputStream ists)
            throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
