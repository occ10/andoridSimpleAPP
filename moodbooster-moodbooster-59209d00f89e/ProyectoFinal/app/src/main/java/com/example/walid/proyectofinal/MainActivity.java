package com.example.walid.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private SharedPreferences prefs;
    private EditText mEmailView;
    private EditText mPasswordView;
    private TextView textView;
    private Button boton1;
    private Button boton2;
    private AutoCompleteTextView email;
    private EditText password;

    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.button1:

                try {
                    attemptLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.button2:
                Intent myIntent = new Intent(MainActivity.this, Registrar.class);
                startActivity(myIntent);
                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            default:
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("MoodBooster");

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mEmailView = (EditText) findViewById(R.id.email1);
        // populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password1);

        boton1 = (Button) findViewById(R.id.button1);
        boton2=(Button) findViewById(R.id.button2);

        boton1.setOnClickListener((View.OnClickListener) this);

        boton2.setOnClickListener((View.OnClickListener) this);
       // fab.setOnClickListener((View.OnClickListener) this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void attemptLogin() throws IOException, JSONException {
        /*if (mAuthTask != null) {
            return;
        }*/

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check if password field not empty.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("This field is required");
            focusView = mPasswordView;
            cancel = true;
        }


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError("This password is to short");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("This field is required");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("This email is invalid");
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {


            conectar();
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

    public void conectar(){


        String stringUrl = "http://moodbooster.esy.es/moodbooster/public/api/usuario/login";
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("Emai", mEmailView.getText().toString());
            new JSONfunctions().execute(stringUrl,mEmailView.getText().toString(),mPasswordView.getText().toString());

        } else {
            //textView.setText("No network connection available.");
            Log.d("MyApp", "no hay conexion");
        }



    }
private class JSONfunctions extends AsyncTask<String,Void, String> {

    Context context;
    public JSONfunctions() {


    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {

        String pal="";
      //  ArrayList<Historial> lista_historial = new ArrayList<>();

        //Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
       // startActivity(myIntent);
        try {
            JSONObject rootObject = new JSONObject(result);
            //"data":"Login correcto"
                    pal=rootObject.getString("data");
            Log.d("EL usuario es ",pal);
            if(pal.equalsIgnoreCase("Login correcto")){
                 Log.d("EL usuario es ","ha entrado");
                prefs = getSharedPreferences("prefName", MODE_PRIVATE);
                SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putString("mail", mEmailView.getText().toString());
                editor.putString("password", mPasswordView.getText().toString());
                editor.commit();
                finish();


                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(myIntent);
            }else{


             /*   Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/
            }
           /// Log.d("MyApp", "Data" + pal);
         } catch (JSONException e) {
    }

    }

    @Override
    protected String doInBackground(String... params) {


        try {
            return downloadUrl(params[0],params[1],params[2]);
        } catch (IOException e) {
            return e.getMessage();
            // return "Unable to retrieve web page. URL may be invalid.";
        }


    }


    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl,String email,String password) throws IOException {

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

            HashMap<String, String> postDataParams=new HashMap<>();
            //Log.d("Emailxxxxx", email);
            postDataParams.put("email", email);
            postDataParams.put("password", password);



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


/**
 * Created by walid on 07/04/2016.
 */
