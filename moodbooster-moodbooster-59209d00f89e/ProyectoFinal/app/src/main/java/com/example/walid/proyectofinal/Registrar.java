package com.example.walid.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

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

public class Registrar extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences prefs;
    private EditText EmailView;
    private EditText PasswordView;
    private EditText NameView;
    private TextView dd;
    private TextView mm;
    private TextView aaaa;
    private Spinner snip;
    private  String valToSet;


    private Button boton;

@Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.button3:

                try {
                    attemptLoginX();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.button2:
               // Intent myIntent = new Intent(MainActivity.this, Registrar.class);
                //startActivity(myIntent);
                break;

            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        setTitle("Registrarse");

       // prefs = getSharedPreferences("prefName", MODE_PRIVATE);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


         EmailView=(EditText) findViewById(R.id.emailR);
         PasswordView=(EditText) findViewById(R.id.passwordR);
         NameView=(EditText) findViewById(R.id.nameR);
       dd= (TextView) findViewById(R.id.n1);
        mm=(TextView) findViewById(R.id.n2);
        aaaa=(TextView) findViewById(R.id.n3);
        snip=(Spinner) findViewById(R.id.spinner);
       valToSet = snip.getSelectedItem().toString();
        boton=(Button)findViewById(R.id.button3);
        boton.setOnClickListener((View.OnClickListener) this);
    }


    private void attemptLoginX() throws IOException, JSONException {
        /*if (mAuthTask != null) {
            return;
        }*/

        // Reset errors.
        EmailView.setError(null);
        PasswordView.setError(null);
        NameView.setError(null);
        dd.setError(null);
        mm.setError(null);
        aaaa.setError(null);

        // Store values at the time of the login attempt.
        final String email = EmailView.getText().toString();
        final String password = PasswordView.getText().toString();
        final String name=NameView.getText().toString();
        final String dia=dd.getText().toString();
        final String mes=mm.getText().toString();
        final String anyo=aaaa.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check if password field not empty.
        if (TextUtils.isEmpty(password)) {
            PasswordView.setError("This field is required");
            focusView = PasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(name)) {
            NameView.setError("This field is required");
            focusView = NameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(dia)) {
            dd.setError("This field is required");
            focusView = dd;
            cancel = true;
        }
        if (TextUtils.isEmpty(mes)) {
            mm.setError("This field is required");
            focusView = mm;
            cancel = true;
        }
        if (TextUtils.isEmpty(anyo)) {
            aaaa.setError("This field is required");
            focusView = aaaa;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            PasswordView.setError("This password is to short");
            focusView = PasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            EmailView.setError("This field is required");
            focusView = EmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            EmailView.setError("This email is invalid");
            focusView = EmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {


            conectarX();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public void conectarX(){


        String stringUrl = "http://moodbooster.esy.es/moodbooster/public/api/usuario";
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Log.d("Email", mEmailView.getText().toString());
            new JSONfunctions2().execute(stringUrl,EmailView.getText().toString(),PasswordView.getText().toString(),NameView.getText().toString(),dd.getText().toString(),mm.getText().toString(),aaaa.getText().toString(),snip.getSelectedItem().toString());
        } else {
            //textView.setText("No network connection available.");
            Log.d("MyApp", "no hay conexion");
        }



    }

    private class JSONfunctions2 extends AsyncTask<String,Void, String> {


        public JSONfunctions2() {


        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            String pal="";
            ArrayList<Historial> lista_historial = new ArrayList<>();

try{

    JSONObject rootObject = new JSONObject(result);
    Log.d("MyApp", "Data" + result);

            pal=rootObject.getString("data");
           // Log.d("EL usuario es ",pal);
            if(pal.equalsIgnoreCase("Registro correcto")){
                Log.d("EL usuario es ","ha entrado");
                prefs = getSharedPreferences("prefName", MODE_PRIVATE);
                SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putString("mail", EmailView.getText().toString());
                editor.putString("password", PasswordView.getText().toString());
                editor.commit();
                finish();
                Intent myIntent = new Intent(Registrar.this, Main5Activity.class);
                startActivity(myIntent);
            }else{


              /*Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/
            }

        } catch (JSONException e) {
        }
        }

        @Override
        protected String doInBackground(String... params) {


            try {
                return downloadUrl(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }


        }


        // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
        private String downloadUrl(String myurl,String email,String password,String name,String dia,String mes,String anyo,String snip) throws IOException {
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
                // conn.setReadTimeout(10000 /* milliseconds */);
                //conn.setConnectTimeout(15000 /* milliseconds */);
                //conn.setRequestMethod("GET");
                //conn.setDoInput(true);

                conn.setRequestMethod("POST");
                conn.setDoOutput(true);


                out = new BufferedOutputStream(conn.getOutputStream());
                // Starts the query
                String fechaNacimiento=anyo + "/" + mes + "/" + dia;
                HashMap<String, String> postDataParams=new HashMap<>();

                postDataParams.put("email", email);
                postDataParams.put("password", password);
                postDataParams.put("nombre", name);
                postDataParams.put("fechaNacimiento",fechaNacimiento );
                postDataParams.put("sexo", snip);




                String pal=getPostDataString(postDataParams);
                out.write(pal.getBytes());
                Log.d("logearse", pal);
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

                br.close();


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

