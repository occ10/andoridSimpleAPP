package com.example.walid.aplicacionch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button boton;
    private Button boton2;
    private EditText textNombre;
    private EditText textCorreo;
    private EditText textFecha;
    private EditText textSexo;

   @Override
    public void onClick(View v) {

        switch (v.getId()) {



            case R.id.id_button:
                // boton = (Button) findViewById(R.id.button);
                //textv=(TextView) findViewById(R.id.textView);
                textNombre=(EditText) findViewById(R.id.nombre);
                textCorreo=(EditText) findViewById(R.id.correo);
                textFecha=(EditText) findViewById(R.id.fecha);
                textSexo=(EditText) findViewById(R.id.sexo);

                conectar(textNombre.getText().toString(),textCorreo.getText().toString(),textFecha.getText().toString(),textSexo.getText().toString());

                break;

            case R.id.secend_button:
               Intent toy= new Intent(MainActivity.this,second.class);
                startActivity(toy);
                break;

            default:
                break;
        }

    }

    public void init(){
        boton = (Button) findViewById(R.id.id_button);
        boton2=(Button) findViewById(R.id.secend_button);

        boton.setOnClickListener((View.OnClickListener) this);

        boton2.setOnClickListener((View.OnClickListener) this);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();



    }

    public void conectar(final String a1,final String a2,final String a3, final String a4){

        Thread tr=new Thread() {

            @Override
            public void run() {
                Conexion coño=new Conexion();
                try {
                    coño.enviarPost(a1,a2,a3,a4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
