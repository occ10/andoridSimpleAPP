package com.example.walid.aplicacionch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;

public class second extends AppCompatActivity implements View.OnClickListener{

    private Button boton;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {



            case R.id.botonX:

               conectar();
                break;


            default:
                break;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
    }

    public void init(){
        boton = (Button) findViewById(R.id.botonX);
        boton.setOnClickListener((View.OnClickListener) this);

    }

    public void conectar(){


        Thread tr=new Thread() {

            @Override
            public void run() {
                Conexion coño=new Conexion();
                //try {
                try {
                    Log.d("MyApp", "Data" + coño.getResponse());;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //} catch (IOException e) {
                //   e.printStackTrace();
                //}
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                    }
                });


            }
        };
        tr.start();

    }
}
